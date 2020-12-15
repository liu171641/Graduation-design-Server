package com.tools;

import java.sql.Date;
import java.sql.Blob;
import java.util.List;

public class DataRow {
	List<DataColumn> col;

	public DataRow(List<DataColumn> _col) {
		col = _col;
	}

	public DataRow() {
		super();
	}

	public int getColsCount() {
		if (col != null) {
			return col.size();
		}
		return 0;
	}

	public List<DataColumn> GetAllColumns() {
		return col;
	}

	public void SetAllColumns(List<DataColumn> _col) {
		col = _col;
	}

	/**
	 * 通过列名得到一个单元格
	 * 
	 * @param colName
	 * @return
	 */
	public DataColumn GetColumnByName(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c;
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

		return null;
	}

	/**
	 * @param colindex
	 * @return 通过序号取得一个单无格
	 */
	public DataColumn GetColumnByIndex(int colindex) {
		if (colindex >= getColsCount()) {
			return null;
		}
		return col.get(colindex);
	}

	public String GetColName(int colindex) {
		if (colindex >= getColsCount()) {
			return "";
		}
		return col.get(colindex).GetKey().trim();
	}

	public Object GetColValueByIndex(int colindex) {
		if (colindex >= getColsCount()) {
			return null;
		}
		return col.get(colindex).GetValue();
	}
	public String GetColValueStringByIndex(int colindex) {
		Object o=GetColValueByIndex(colindex);
		if (o!=null) {
			return o.toString().trim();
		}
		return "";
	}
	public Object GetColValueByName(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
					return c.GetValue();
			}
		}
		return null;
	}
	public String GetColValueStringByName(String colName) {
		Object o=GetColValueByName(colName);
		if (o!=null) {
			return o.toString().trim();
		}
		return "";
	}
	/**
	 * 通过列名得到一个单元格的值 ，并将值转换为整形
	 * 
	 * @param colName
	 * @return
	 */
	public int GetColumnValueInt(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Integer.parseInt(c.GetValue().toString());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		return 0;
	}

	public String GetColumnValueString(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c.GetValue().toString();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

		return "";
	}

	public Date GetColumnValueDate(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Date.valueOf(c.GetValue().toString());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

		return null;
	}

	public Blob GetColumnValueBlob(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return (Blob) c.GetValue();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

		return null;
	}

	public float GetColumnValueFloat(String colName) {
		for (DataColumn c : col) {
			if (c.GetKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Float.parseFloat(c.GetValue().toString());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		return 0;
	}
}
