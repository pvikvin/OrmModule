package com.gemicle.ormmodule.validators;

import java.lang.reflect.Field;

import com.gemicle.ormmodule.annotations.Id;

public class Validator {
	
	public void checkIdAnnotation(Field[] fields){
		int count = 0;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				count++;
			}
		}
		if (count != 1) {
			throw new RuntimeException("Annotetion 'id' must be one");
		}
	}
}
