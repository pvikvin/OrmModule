package com.gemicle.ormmodule.chainresponsibility;

import java.lang.reflect.Field;
import java.util.Map;

import com.gemicle.ormmodule.annotations.Enumerated;
import com.gemicle.ormmodule.generators.type.StringTypeGenerator;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.ColumnTable;
import com.gemicle.ormmodule.pojo.TableInformation;

public class EnumeratedAnnotation implements IChainAnnotation {

	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {
	
		Map<String, ColumnTable> columns = tableInformation.getColumns();
		for (Field field : obj.getDeclaredFields()) {
			if (field.isAnnotationPresent(Enumerated.class)) {
				ColumnTable columnTable = columns.get(field.getName());
				columns.put(field.getName(), columnTable != null ? columnTable : generate(field));

			}
		}
		tableInformation.setColumns(columns);

		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, new EmbeddedAnnotation());
		}

	}

	public ColumnTable generate(Field field) {
		return ColumnTable.newBuilder().setField(field).setName(field.getName()).setTypeSql(new StringTypeGenerator())
				.build();
	}

}
