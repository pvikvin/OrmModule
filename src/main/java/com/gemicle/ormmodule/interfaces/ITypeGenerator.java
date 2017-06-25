package com.gemicle.ormmodule.interfaces;

import com.gemicle.ormmodule.pojo.ColumnTable;

public interface ITypeGenerator {
	
	String generateTypeSql(ColumnTable column);
}
