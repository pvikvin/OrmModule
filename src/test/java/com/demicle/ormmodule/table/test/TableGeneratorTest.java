package com.demicle.ormmodule.table.test;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.gemicle.ormmodule.generators.TableGenerator;
import com.gemicle.ormmodule.pojo.tets.TableGPS;

public class TableGeneratorTest {
	
	TableGenerator tableGenerator = new TableGenerator();
	
	@Test
	public void constructorTableTest(){
		Assert.assertTrue(tableGenerator.constructorTable(TableGPS.class));
	}
	
	@Test
	public void checkIdAnnotationTest(){
		Field[] fields = tableGenerator.getFields(TableGPS.class);
		//tableGenerator.checkIdAnnotation(fields);
		
	}
	
	
}
