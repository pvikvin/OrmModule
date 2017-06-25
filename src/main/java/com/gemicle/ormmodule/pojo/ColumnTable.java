package com.gemicle.ormmodule.pojo;

import java.lang.reflect.Field;

import com.gemicle.ormmodule.interfaces.ITypeGenerator;

import lombok.Data;

@Data
public class ColumnTable {

	private String name;
	private ITypeGenerator typeSql;
	private boolean nullable;
	private int length;
	private Field field;
	
	public static Builder newBuilder(){
		return new ColumnTable().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Builder setName(String name) {
			ColumnTable.this.name = name;
			return this;
		}

		public Builder setTypeSql(ITypeGenerator typeSql) {
			ColumnTable.this.typeSql = typeSql;
			return this;
		}

		public Builder setNullable(boolean nullable) {
			ColumnTable.this.nullable = nullable;
			return this;
		}

		public Builder setLength(int length) {
			ColumnTable.this.length = length;
			return this;
		}

		public Builder setField(Field field) {
			ColumnTable.this.field = field;
			return this;
		}

		public ColumnTable build() {
			return ColumnTable.this;
		}
	}
}
