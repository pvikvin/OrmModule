package com.gemicle.ormmodule.generators.type;

import com.gemicle.ormmodule.interfaces.ITypeGenerator;
import com.gemicle.ormmodule.pojo.ColumnTable;

public class StringTypeGenerator implements ITypeGenerator{

	@Override
	public String generateTypeSql(ColumnTable column) {	
		return "varchar("+(column.getLength() == 0 ? 250 : column.getLength())+") default ''";
	}

}
