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
public class CommodityUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;
	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "images";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private String filePath, title, amount, price, myfile, fileName;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 上传数据及保存文件
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 编码//
		Tools.Encodeing(request, response);
		id = Integer.parseInt(request.getParameter("id").trim());
		// out.print("id=="+id);
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("Error: 表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return;
		}
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// // 中文处理
		// upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = getServletContext().getRealPath("/")
				+ File.separator + UPLOAD_DIRECTORY;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						fileName = new File(item.getName()).getName();
						filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath); // 原始路径

						/* 对存储的路径 调用字符串工具 修改 */
						tool tl = new tool();
						filePath = tl.removeGang(filePath);

						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "文件上传成功!");
						request.setAttribute("logopath", filePath);
					} else {
						// 获取表单中属性名称
						String fieldName = item.getFieldName();
						if (fieldName.equals("title")) {
							// 获取表单属性的值
							title = item.getString("UTF-8");
						} else if (fieldName.equals("amount")) {
							amount = item.getString("UTF-8");
						} else if (fieldName.equals("price")) {
							price = item.getString("UTF-8");
						}
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "错误信息: " + ex.getMessage());
		}
		// out.print(id + name + phonenumber + password + age + address +
		// fileName);

		// //////////进行插入操作////////////////
		String sqlString = "update commodity set title=?,amount=?,price=?,url=? where id =?";
		String ip = "182.254.230.137";
		String newfileNameUrl = "http://" + ip + ":8080/admintes/images/"
				+ fileName;
		Object[] paObjects = new Object[] { title, amount, price,
				newfileNameUrl, id };
		if (DBHelper.Update(sqlString, paObjects)) {
			request.getRequestDispatcher("admin/Success.jsp").forward(request, response);
			out.print("1");
		} else {
			out.print("2");
		}

	}
}