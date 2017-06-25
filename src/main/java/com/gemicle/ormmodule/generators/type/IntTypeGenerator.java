package com.gemicle.ormmodule.generators.type;

import com.gemicle.ormmodule.interfaces.ITypeGenerator;
import com.gemicle.ormmodule.pojo.ColumnTable;

public class IntTypeGenerator implements ITypeGenerator{

	@Override
	public String generateTypeSql(ColumnTable column) {
		return "int("+column.getLength()+") default '0'";
	}

}
