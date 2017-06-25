package com.gemicle.ormmodule.interfaces;

import java.lang.reflect.Field;

import com.gemicle.ormmodule.pojo.ColumnTable;

public interface IColumnTableGenerator {
	ColumnTable generate(IChainAnnotation chainAnnotation);
}
