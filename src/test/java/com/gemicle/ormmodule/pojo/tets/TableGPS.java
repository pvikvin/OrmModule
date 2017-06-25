package com.gemicle.ormmodule.pojo.tets;

import com.gemicle.ormmodule.annotations.Column;
import com.gemicle.ormmodule.annotations.Embedded;
import com.gemicle.ormmodule.annotations.Enumerated;
import com.gemicle.ormmodule.annotations.Id;
import com.gemicle.ormmodule.annotations.Lob;
import com.gemicle.ormmodule.annotations.Table;
import com.gemicle.ormmodule.annotations.Transient;
import com.gemicle.ormmodule.enums.TrackType;

import lombok.Data;

@Data
@Table(name = "tableGPS")
public class TableGPS {

	@Id
	private int id;

	@Column(name = "colInt", nullable = true, length = 20)
	private int columnInt;
	
	@Column(name = "colStr", nullable = true, length = 350)
	private String columnString;
	
	@Column(name = "colBool")
	private boolean columnBool;
	
	private double columnDouble;
	
	@Transient
	private int columnTransiant;
	
	@Enumerated
	private TrackType columnEnum;

	@Embedded
	private Point point;
	
	@Lob
	private int[] columns;
}
