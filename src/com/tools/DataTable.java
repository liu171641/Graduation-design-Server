package com.tools;

import java.util.List;

//import java.util.ArrayList;

public class DataTable {
	List<DataRow> row = null;

	public DataTable() {
	}

	public DataTable(List<DataRow> _row) {
		row = _row;
	}

	public List<DataRow> GetAllRow() {
		return row;
	}

	public void SetAllRow(List<DataRow> _row) {
		row = _row;
	}

	public int getRowsCount() {
		if (row != null) {
			return GetAllRow().size();
		}
		return 0;
	}

	public int getColsCount() {
		if (getRowsCount() > 0) {
			return GetAllRow().get(0).getColsCount();
		}
		return 0;
	}
	public DataRow getRow(int rowindex) {
		if(rowindex>=getRowsCount()){
			return null;
		}
		return GetAllRow().get(rowindex);
	}
	public Object getColumnValue(int i,int j) {
		if(i>=getRowsCount() || j>=getColsCount()){
			return null;
		}
		return GetAllRow().get(i).GetColValueByIndex(j);		
	}
	public String getColumnStringValue(int i,int j) {
		if(i>=getRowsCount() || j>=getColsCount()){
			return "";
		}else {
			Object object=GetAllRow().get(i).GetColValueByIndex(j);
			if (object==null) {
				return "";
			}else {
				return object.toString().trim();
			}
		}	
	}
	/*
	 * public static void PrintTable(DataTable dt) { for (DataRow r :
	 * dt.GetRow()) { for (DataColumn c : r.GetColumn()) {
	 * System.out.print(c.GetKey() + ":" + c.GetValue() + "  "); }
	 * System.out.println(""); } }
	 */
	// 静态方法是可以通过类型来调用的，不过根据类对象来调用也是可以的

	// RowCount,ColumnCount是直接在DBHelper中赋值组datatable的同时给定的
	// 如果不是直接由DBHelper赋值的datatable这两个属性值为0.那最好用getrowscount()来取得
	public int RowCount = 0;
	public int ColumnCount = 0;
}
