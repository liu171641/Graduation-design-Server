package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���post���������
		//request.setCharacterEncoding("utf-8");
		
	/*	//��ȡһ��������
		ServletInputStream in = request.getInputStream();
		
		//��ȡ��
		String str = IOUtils.toString(in);
		
		System.out.println(str);*/
		
		
		//��ȡ������ʵ��
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//������������ʵ��
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//fileUploadͨ���ö����������ļ��Ĵ�С
		//���õ��ļ��Ĵ�СΪ50KB
		//fileUpload.setFileSizeMax(1024*50);
		
		//���ö���ļ����ܴ�СΪ300mb
		fileUpload.setSizeMax(1024*1024*300);
		
		try {
			//����request
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			
			//����fileItems����ȡ������Ϣ
			for (FileItem fileItem : fileItems) {
				
				//�жϵ�ǰ�����Ƿ���һ����ͨ����
				if(fileItem.isFormField()){
					//��ȡ������
					String fieldName = fileItem.getFieldName();
					
					//��ȡ����ֵ
					String value = fileItem.getString("utf-8");
					
					System.out.println(fieldName+" = "+value);
				}else{
					//������ļ�����
					//��ȡ�ļ��Ĵ�С
					long size = fileItem.getSize();
					
					//�ж�size�Ƿ�Ϊ0
					if(size==0){
						continue;
					}
					
					//��ȡ�ļ�������
					String contentType = fileItem.getContentType();
					
					//��ȡ�ļ�������
					String name = fileItem.getName();
					//�ж�name���Ƿ������·����Ϣ
					if(name.contains("\\")){
						//����������ȡ�ַ���
						name = name.substring(name.lastIndexOf("\\")+1);
					}
					

					
					
					//��ȡ�����������
					String fieldName = fileItem.getFieldName();
					
					System.out.println("�ļ��Ĵ�С: "+size);
					System.out.println("�ļ�������: "+contentType);
					System.out.println("�ļ�������: "+name);
					System.out.println("����name������: "+fieldName);
					
					//��ȡServletContext����
					ServletContext context = this.getServletContext();
					//��ȡ��Ŀ����ʵ·��
					String path = context.getRealPath("images");
					
					//�ж�·���Ƿ����
					File file = new File(path);
					if(!file.exists()){
						//��������ڸ�·�����򴴽�һ��·��
						file.mkdirs();
					}
					
					//���ļ�д�뵽������
					fileItem.write(new File(path+"/"+name));
					
				}
			}
			
			//�ض���һ��ҳ��
			response.sendRedirect(request.getContextPath()+"/uploadimg.jsp");
			
		}catch(FileSizeLimitExceededException e){
			//һ�����񵽸��쳣����˵�������ļ���С�������ơ�
			//����һ��������Ϣ
			request.setAttribute("msg", "�����ļ���С�벻Ҫ����50KB");
			//ת����index.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}catch(SizeLimitExceededException e){
			//һ�����񵽸��쳣����˵�������ļ���С�������ơ�
			//����һ��������Ϣ
			request.setAttribute("msg", "�����ļ���С�벻Ҫ����300mb");
			//ת����index.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
