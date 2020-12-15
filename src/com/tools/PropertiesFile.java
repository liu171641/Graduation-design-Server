package com.tools;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
	/*
	 * java中的properties文件是一种配置文件，主要用于表达配置信息，文件类型为*.properties，格式为文本文件，文件的内容是格式是"键=值"
	 * 的格式，在properties 文件中，可以用"#"来作注释，properties文件在Java编程中用到的地方很多，操作很方便 •void
	 * load(InputStream inStream)：从输入流中加载属性列表； •void store(OutputStream out,
	 * String comments)：根据输出流将属性列表保存到文件中； •String getProperty(String
	 * key)：获取指定键的值； •void setProperty(String key, String
	 * value)：设置指定键的值，若指定键已经在原属性值列表中存在，则覆盖；若指定键在原属性值列表中不存在，则新增；
	 * 
	 * //Properties properties = new Properties();
	 * //properties.load(DBHelper.class
	 * .getClassLoader().getResourceAsStream("db.properties")); //
	 * PlayGround.class.getClassLoader().getResourceAsStream("tag.properties")
	 * //
	 * 非静态方法中用这个properties.load(this.getClass().getClassLoader().getResourceAsStream
	 * ("db.properties"));
	 */
	/**
	 * @param filename	 *            如db.properties
	 * @param keyid
	 * @return
	 */
	public static String GetValue(String filename, String keyid) {
		String value = "";
		Properties properties = new Properties();
		try {
			properties.load(PropertiesFile.class.getClassLoader()
					.getResourceAsStream(filename));
			value = properties.getProperty(keyid);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return value;
	}

	public static void SetValue(String filename, String keyid, String value) {
		Properties properties = new Properties();
		try {
			properties.load(PropertiesFile.class.getClassLoader()
					.getResourceAsStream(filename));
			properties.setProperty(keyid, value);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
