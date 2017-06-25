package com.gemicle.ormmodule.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.gemicle.ormmodule.pojo.TableInformation;

public class TableDAO {

	private ConnectorDatabase connector = new ConnectorDatabase();
	private LineGeneratorTable lineGeneratorTable = new LineGeneratorTable();

	public boolean createTable(TableInformation tableInformation) {
		boolean result = false;
		String sql = generateText(tableInformation);
		Statement stmt = connector.getStatement();
		try {
			stmt.executeUpdate(sql);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String generateText(TableInformation tableInformation) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS `" + tableInformation.getNameDb() + "`.`"
				+ tableInformation.getNameTable() + "` " + "(");
		
		lineGeneratorTable.generateTextLine(tableInformation.getColumns(), sb);

		sb.append("`CreateTime` timestamp NOT NULL default CURRENT_TIMESTAMP,");
		sb.append("PRIMARY KEY (`" + tableInformation.getNameIdField() + "`)");
		sb.append(");");

		return sb.toString();
	}

}
