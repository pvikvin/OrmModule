package com.gemicle.ormmodule.chainresponsibility;

import com.gemicle.ormmodule.annotations.Table;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.TableInformation;

public class TableAnnotation implements IChainAnnotation {

	@Override
	public void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation) {
		

		if (!obj.isAnnotationPresent(Table.class)) {
			throw new RuntimeException("No table annotation");
		}
		Table tableAnnotation = obj.getAnnotation(Table.class);
		tableInformation.setNameTable(tableAnnotation.name());
		
		if (nextAnnotation != null) {
			nextAnnotation.generateInformation(obj, tableInformation, new FieldsWithoutAnnotation());
		}
		
	}

}
