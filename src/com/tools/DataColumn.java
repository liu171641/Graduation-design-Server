package com.tools;

/**
 * @author Administrator
 *DataColumn可以理解为单元格，这个单元有列名，和值
 */
public class DataColumn {
	String key;
	Object value;//用Object可以容纳空值  null，即数据表中的空值不影晌。打印出来是null
	
	public DataColumn(String _key,Object _value)
	{
		key = _key;
		value = _value;
	}
	
	public String GetKey()
	{
		return key;
	}
	
	public Object GetValue()
	{
		return value;
	}
	
	public void SetKey(String _key)
	{
		key = _key;
	}
	
	public void SetValue(Object _value)
	{
		value = _value;
	}
}
