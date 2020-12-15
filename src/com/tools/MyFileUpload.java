package com.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFileUpload {

	public static String getNowTimeString() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 精确到毫秒
		String nowTime = sd.format(date);
		return nowTime.trim();
	}

	public static String getext(String saveFile) {
		String ext = saveFile.substring(saveFile.indexOf(".") + 1)
				.toLowerCase().trim();
		return ext;
	}

	/**
	 * @param saveFile
	 *            为a.txt,或c://duqiang/b.txt,
	 * @return 返回以当前时间为名字的文件名
	 * 
	 */
	public static String getNewFileName(String saveFile) {
		// 取文件扩展名
		String ext = getext(saveFile);
		// 以当前时间重命名文件
		String filenameString = getNowTimeString() + "." + ext;
		return filenameString;
	}

	/**
	 * 判断扩展名是否合归
	 * 
	 * @param saveFile
	 *            saveFile为a.txt,或c://duqiang/b.txt,
	 * @return 判断是否为图片,只能为png,gif,jpg
	 */
	public static boolean isImageFile(String saveFile) {
		String ext = getext(saveFile);
		if (!(ext.equals("png") || ext.equals("gif") || ext.equals("jpg"))) {
			return false;
		}
		return true;
	}

	/**
	 * @param saveFile
	 *            只能是zip和rar文件
	 * @return
	 */
	public static boolean isZipFile(String saveFile) {
		String ext = getext(saveFile);
		if (!(ext.equals("zip") || ext.equals("rar"))) {
			return false;
		}
		return true;
	}

	public static boolean isDoc(String saveFile) {
		String ext = getext(saveFile);
		if (!(ext.equals("doc") || ext.equals("docx"))) {
			return false;
		}
		return true;
	}

	public static boolean isppt(String saveFile) {
		String ext = getext(saveFile);
		if (!(ext.equals("ppt") || ext.equals("pptx"))) {
			return false;
		}
		return true;
	}

	public static boolean isXls(String saveFile) {
		String ext = getext(saveFile);
		if (!(ext.equals("xls") || ext.equals("xlsx"))) {
			return false;
		}
		return true;
	}

	/**
	 * @param request
	 * @param response
	 * @return 得到服务器的上传路径
	 */
	public static String getServerUploadFilePath(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得客户端的网络地址
		// String remoteAddr = request.getRemoteAddr();
		// 获得服务器的名字
		String serverName = request.getServerName();
		// 取得互联网程序的绝对地址
		String realPath = request.getRealPath(serverName);
		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		// 创建文件的保存目录
		String rootPath = realPath + "\\upload\\";
		return rootPath;
	}

	/**
	 * @return 得到用户自定义的上传服务器路径，主要是解决tomcate下上传目录可能消失的问题。如
	 *         需要在db.Properties文件中定义userfilepath属性如 d:\\upload\\
	 */
	public static String getUserUploadFilePath() {
		return PropertiesFile.GetValue("db.properties", "userfilepath");
	}

	public static boolean upLoadFileToUserPath(String filename,
			String newfilename) {
		String userpathString = getUserUploadFilePath();
		String pathandfile = "";
		if (userpathString != null) {
			pathandfile = userpathString.trim() + newfilename.trim();
			if (upLoadFile(filename, pathandfile)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean upLoadFileToServerPath(String filename,
			String newfilename, HttpServletRequest request,
			HttpServletResponse response) {
		String userpathString = getServerUploadFilePath(request, response);
		String pathandfile = "";

		pathandfile = userpathString.trim() + newfilename.trim();
		if (upLoadFile(filename, pathandfile)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param filename
	 *            客户端的文件路径 如:c:/1.jpg 一般放file控件的路径即可
	 *            request.getParameter("image");
	 * @param newfilename
	 *            以新的文件名进行保存
	 * @return 将文件保存到用户自定义路径中.判断文件类型，文件大小最好是交给js
	 */
	private static boolean upLoadFile(String clientfilename, String newfilepathandname) {
		File fileurl = new File(clientfilename); // 获取表单传过来的图片的url
		FileOutputStream fileOut = null;
		try {
			// 打开文件
			FileInputStream fin = new FileInputStream(fileurl);
			// 建一个缓冲保存数据
			ByteBuffer nbf = ByteBuffer.allocate((int) fileurl.length());
			byte[] array = new byte[1024];
			int length = 0;
			// 读存数据
			while ((length = fin.read(array)) > 0) {
				if (length != 1024)
					nbf.put(array, 0, length);
				else
					nbf.put(array);
			}
			fin.close();
			// 新建一个数组保存要写的内容,dataBytes数组中
			byte[] dataBytes = nbf.array();
			// ////////以上理论上已取得了数据/////////////
			// 创建文件的写出类
			fileOut = new FileOutputStream(newfilepathandname);
			// 保存文件的数据
			fileOut.write(dataBytes);
			fileOut.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
