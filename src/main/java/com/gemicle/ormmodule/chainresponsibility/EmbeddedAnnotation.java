package com.gemicle.ormmodule.chainresponsibility;

import java.lang.reflect.Field;
import java.util.Map;

import com.gemicle.ormmodule.annotations.Embedded;
import com.gemicle.ormmodule.generators.type.TypeGenerator;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.ColumnTable;
import com.gemicle.ormmodule.pojo.TableInformation;

public class EmbeddedAnnotation implements IChainAnnotation {

	private TypeGenerator typeGenerator = new TypeGenerator();

	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {

		Map<String, ColumnTable> columns = tableInformation.getColumns();
		for (Field field : obj.getDeclaredFields()) {
			if (field.isAnnotationPresent(Embedded.class)) {
				try {
					Class<?> objClass = Class.forName(field.getType().getTypeName());
					Field[] fieldsObj = objClass.getDeclaredFields();
					for (Field fieldObj : fieldsObj) {
						ColumnTable columnTable = columns.get(field.getName());
						columns.put(fieldObj.getName(), columnTable != null ? columnTable : generate(fieldObj));

					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		tableInformation.setColumns(columns);

		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, new LobAnnotation());
		}

	}

	public ColumnTable generate(Field field) {
		return ColumnTable.newBuilder().setField(field).setName(field.getName())
				.setTypeSql(typeGenerator.generateType(field.getType())).build();
	}

}
