package com.gemicle.ormmodule.chainresponsibility;

import java.lang.reflect.Field;
import java.util.Map;

import com.gemicle.ormmodule.annotations.Lob;
import com.gemicle.ormmodule.generators.type.BinaryTypeGenerator;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.ColumnTable;
import com.gemicle.ormmodule.pojo.TableInformation;

public class LobAnnotation implements IChainAnnotation {

	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {

		Map<String, ColumnTable> columns = tableInformation.getColumns();
		for (Field field : obj.getDeclaredFields()) {
			if (field.isAnnotationPresent(Lob.class)) {
				ColumnTable columnTable = columns.get(field.getName());
				columns.put(field.getName(), columnTable != null ? columnTable : generate(field));
			}
		}
		tableInformation.setColumns(columns);

		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, new ColumnAnnotation());
		}

	}

	public ColumnTable generate(Field field) {
		return ColumnTable.newBuilder().setField(field).setName(field.getName()).setTypeSql(new BinaryTypeGenerator())
				.build();
	}
}
