
package com.tools;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @作者:刘宇航 
 * @邮箱:1716413010@qq.com 
 * @版本: v1.0
 * @日期:2020年5月17日 下午4:07:41
 * @作用:转码
 */
public class Tools {

	public static void Encodeing(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
