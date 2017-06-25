package com.gemicle.ormmodule.pojo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TableInformation {
	private String nameDb;
	private String nameTable;
	private String nameIdField;
	private Map<String, ColumnTable> columns = new HashMap<>();
}
