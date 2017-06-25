package com.gemicle.ormmodule.generators;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.gemicle.ormmodule.chainresponsibility.IdAnnotation;
import com.gemicle.ormmodule.chainresponsibility.TableAnnotation;
import com.gemicle.ormmodule.dao.TableDAO;
import com.gemicle.ormmodule.interfaces.IChainAnnotation;
import com.gemicle.ormmodule.interfaces.ITableGenertor;
import com.gemicle.ormmodule.pojo.TableInformation;

public class TableGenerator implements ITableGenertor {

	private static final String DATA_BASE_NAME = "GPSGenerator";
	private TableDAO tableDao = new TableDAO();
	private TableInformation tableInformation = new TableInformation();

	private IChainAnnotation tableAnnotation = new TableAnnotation();

	public Field[] getFields(Class<?> obj) {
		return obj.getDeclaredFields();
	}

	public Method[] getMethods(Class<?> obj) {
		return obj.getMethods();
	}

	@Override
	public boolean constructorTable(Class<?> obj) {

		tableInformation.setNameDb(DATA_BASE_NAME);
		
		tableAnnotation.generateInformation(obj, tableInformation, new IdAnnotation());
				
		return tableDao.createTable(tableInformation);
		
	}

}
