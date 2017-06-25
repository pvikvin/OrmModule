package com.gemicle.ormmodule.dao;

import java.util.HashMap;
import java.util.Map;

import com.gemicle.ormmodule.generators.type.TypeGenerator;
import com.gemicle.ormmodule.interfaces.ITypeGenerator;
import com.gemicle.ormmodule.pojo.ColumnTable;

import lombok.Data;

@Data
public class LineGeneratorTable {
	private Map<String, ITypeGenerator> columns = new HashMap<String, ITypeGenerator>();
	private String nameIdColumn;
	private TypeGenerator typeGenerator = new TypeGenerator();

//	private String generateLineId(){
//		return "PRIMARY KEY (`"+getNameIdColumn()+"`)";
//	}
	
	public void generateTextLine(Map<String, ColumnTable> columns, StringBuilder sb){
		
		for(Map.Entry<String, ColumnTable> entry:columns.entrySet()){
			sb.append("`" + entry.getValue().getName() + "` " + typeGenerator.getType(entry.getValue()) + " , ");
		}
	}
	
}
