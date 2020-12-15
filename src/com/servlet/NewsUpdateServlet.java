package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.servlet.tool;
import com.tools.DBHelper;
import com.tools.Tools;

/**
 * Servlet implementation class UploadServlet
 */

@WebServlet("/UploadServlet")
public class NewsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;
	// �ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "images";

	// �ϴ�����
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private String filePath, name, phonenumber, password, age, address, myfile,
			fileName;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * �ϴ����ݼ������ļ�
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// ����//
		Tools.Encodeing(request, response);
		id = Integer.parseInt(request.getParameter("id").trim());
		// out.print("id=="+id);
		// ����Ƿ�Ϊ��ý���ϴ�
		if (!ServletFileUpload.isMultipartContent(request)) {
			// ���������ֹͣ
			PrintWriter writer = response.getWriter();
			writer.println("Error: ��������� enctype=multipart/form-data");
			writer.flush();
			return;
		}
		// �����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// // ���Ĵ���
		// upload.setHeaderEncoding("UTF-8");

		// ������ʱ·�����洢�ϴ����ļ�
		// ���·����Ե�ǰӦ�õ�Ŀ¼
		String uploadPath = getServletContext().getRealPath("/")
				+ File.separator + UPLOAD_DIRECTORY;

		// ���Ŀ¼�������򴴽�
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// ���������������ȡ�ļ�����
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// ����������
				for (FileItem item : formItems) {
					// �����ڱ��е��ֶ�
					if (!item.isFormField()) {
						fileName = new File(item.getName()).getName();
						filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// �ڿ���̨����ļ����ϴ�·��
						System.out.println(filePath); // ԭʼ·��

						/* �Դ洢��·�� �����ַ������� �޸� */
						tool tl = new tool();
						filePath = tl.removeGang(filePath);

						// �����ļ���Ӳ��
						item.write(storeFile);
						request.setAttribute("message", "�ļ��ϴ��ɹ�!");
						request.setAttribute("logopath", filePath);
					} else {
						// ��ȡ������������
						String fieldName = item.getFieldName();
						if (fieldName.equals("name")) {
							// ��ȡ�����Ե�ֵ
							name = item.getString("UTF-8");
							// out.print(name);
						} else if (fieldName.equals("phonenumber")) {
							phonenumber = item.getString("UTF-8");
							// out.print(phonenumber);
						} else if (fieldName.equals("password")) {
							password = item.getString("UTF-8");
							// out.print(password);
						} else if (fieldName.equals("age")) {
							age = item.getString("UTF-8");
							// out.print(age);
						} else if (fieldName.equals("address")) {
							address = item.getString("UTF-8");
							// out.print(address);
						}
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "������Ϣ: " + ex.getMessage());
		}
//		out.print(id + name + phonenumber + password + age + address + fileName);

		// //////////���в������////////////////
		String sqlString = "update user set name=?,photo=?,phonenumber=?,password=?,age=?,address=? where id =?";
		String ip = "182.254.230.137";
		String newfileNameUrl = "http://" + ip + ":8080/admintes/images/"
				+ fileName;
		Object[] paObjects = new Object[] { name, newfileNameUrl, phonenumber,
				password, age, address, id };
		if (DBHelper.Update(sqlString, paObjects)) {
			request.getRequestDispatcher("admin/Success.jsp").forward(request, response);
		} else {
			out.print("2");
		}

	}
}