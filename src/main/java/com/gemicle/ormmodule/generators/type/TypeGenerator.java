package com.gemicle.ormmodule.generators.type;

import com.gemicle.ormmodule.interfaces.ITypeGenerator;
import com.gemicle.ormmodule.pojo.ColumnTable;

public class TypeGenerator {
	public ITypeGenerator generateType(Class<?> type) {
		if (type.isAssignableFrom(String.class)) {
			return new StringTypeGenerator();
		} else if (type.isAssignableFrom(int.class)) {
			return new IntTypeGenerator();
		} else if (type.isAssignableFrom(boolean.class)) {
			return new BooleanTypeGenerator();
		} else if (type.isAssignableFrom(double.class)) {
			return new DoubleTypeGenerator();
		} else {
			throw new RuntimeException("The type " + type.getName() + " is not defined");
		}

	}

	public String getType(ColumnTable column) {
		return column.getTypeSql().generateTypeSql(column);
	}
}
