package com.gemicle.ormmodule.chainresponsibility;

import java.lang.reflect.Field;
import java.util.Map;

import com.gemicle.ormmodule.annotations.Column;
import com.gemicle.ormmodule.generators.type.TypeGenerator;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.ColumnTable;
import com.gemicle.ormmodule.pojo.TableInformation;

public class ColumnAnnotation implements IChainAnnotation {

	private TypeGenerator typeGenerator = new TypeGenerator();
	
	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {

		Map<String, ColumnTable> columns = tableInformation.getColumns();

		for (Field field : obj.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				Column columnAnnotation = field.getAnnotation(Column.class);
				ColumnTable columnTable = columns.get(field.getName());

				if(columnTable == null){
					columnTable = generate(field);
				}
				
				columnTable.setName(columnAnnotation.name());
				columnTable.setNullable(columnAnnotation.nullable());
				columnTable.setLength(columnAnnotation.length());
				
				columns.put(field.getName(), columnTable != null ? columnTable : generate(field));
			}

		}

		tableInformation.setColumns(columns);
		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, null);
		}

	}
	public ColumnTable generate(Field field) {
		return ColumnTable.newBuilder().setField(field).setName(field.getName())
				.setTypeSql(typeGenerator.generateType(field.getType())).build();
	}
}
