package com.gemicle.ormmodule.chainresponsibility;

import java.lang.reflect.Field;
import java.util.Map;

import com.gemicle.ormmodule.annotations.Id;
import com.gemicle.ormmodule.generators.type.IntTypeGenerator;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.ColumnTable;
import com.gemicle.ormmodule.pojo.TableInformation;
import com.gemicle.ormmodule.validators.Validator;

public class IdAnnotation implements IChainAnnotation {

	private Validator validator = new Validator();
	
	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {
		
		Field[] Fields = obj.getDeclaredFields();
		
		validator.checkIdAnnotation(Fields);
		
		String idName = "";
		Map<String, ColumnTable> columns = tableInformation.getColumns();
		for (Field field : Fields){
			if (field.isAnnotationPresent(Id.class)) {
				idName = field.getName();
				ColumnTable columnTable = columns.get(field.getName());
				columns.put(field.getName(), columnTable != null ? columnTable : generate(field));
			}			
		}
		tableInformation.setNameIdField(idName);
		tableInformation.setColumns(columns);
		
		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, new EnumeratedAnnotation());
		}
		
		
	}
	
	public ColumnTable generate(Field field) {
		return ColumnTable.newBuilder().setField(field).setName(field.getName())
				.setTypeSql(new IntTypeGenerator()).setLength(20).build();
	}

}
