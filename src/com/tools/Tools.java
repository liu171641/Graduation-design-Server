
package com.tools;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @����:��� 
 * @����:1716413010@qq.com 
 * @�汾: v1.0
 * @����:2020��5��17�� ����4:07:41
 * @����:ת��
 */
public class Tools {

	public static void Encodeing(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
