package com.gemicle.ormmodule.generators.type;

import com.gemicle.ormmodule.interfaces.ITypeGenerator;
import com.gemicle.ormmodule.pojo.ColumnTable;

public class DoubleTypeGenerator implements ITypeGenerator{

	@Override
	public String generateTypeSql(ColumnTable column) {
		return "double";
	}

}
