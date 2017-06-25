package com.demicle.ormmodule.table.test;

import org.junit.Test;

import com.gemicle.ormmodule.chainresponsibility.ColumnAnnotation;
import com.gemicle.ormmodule.chainresponsibility.EmbeddedAnnotation;
import com.gemicle.ormmodule.chainresponsibility.EnumeratedAnnotation;
import com.gemicle.ormmodule.chainresponsibility.FieldsWithoutAnnotation;
import com.gemicle.ormmodule.chainresponsibility.IdAnnotation;
import com.gemicle.ormmodule.chainresponsibility.LobAnnotation;
import com.gemicle.ormmodule.chainresponsibility.TableAnnotation;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.pojo.TableInformation;
import com.gemicle.ormmodule.pojo.tets.TableGPS;

import junit.framework.Assert;

public class TableChainTest {
	
	TableInformation tableInformation = new TableInformation();
	IChainAnnotation chainAnnotation;
	
	@Test
	public void tableAnnotationTest(){
		chainAnnotation = new TableAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
	}
	
	@Test
	public void idAnnotationTest(){
		chainAnnotation = new IdAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals("id", tableInformation.getColumns().get("id").getName());
	}
	
	@Test
	public void fieldsWithoutAnnotationTest(){
		chainAnnotation = new FieldsWithoutAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals(1, tableInformation.getColumns().size());
	}
	
	@Test
	public void enumeratedAnnotationTest(){
		chainAnnotation = new EnumeratedAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals(1, tableInformation.getColumns().size());
	}
	
	@Test
	public void embeddedAnnotationTest(){
		chainAnnotation = new EmbeddedAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals(3, tableInformation.getColumns().size());
	}
	
	@Test
	public void lobAnnotationTest(){
		chainAnnotation = new LobAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals(1, tableInformation.getColumns().size());
	}
	
	@Test
	public void columnAnnotationTest(){
		chainAnnotation = new ColumnAnnotation();
		chainAnnotation.generateInformation(TableGPS.class, tableInformation, null);
		Assert.assertEquals("colInt", tableInformation.getColumns().get("columnInt").getName());
		Assert.assertEquals("colStr", tableInformation.getColumns().get("columnString").getName());
		Assert.assertEquals("colBool", tableInformation.getColumns().get("columnBool").getName());
		Assert.assertEquals(350, tableInformation.getColumns().get("columnString").getLength());
	}
}
