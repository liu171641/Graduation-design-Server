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

public class Upload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Upload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("deprecation")
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");  //���ñ���  
        //��ô����ļ���Ŀ����  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //��ȡ�ļ���Ҫ�ϴ�����·��  
        String path = request.getRealPath("/upload");  
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        factory.setRepository(new File(path));  
        //���� ����Ĵ�С
        factory.setSizeThreshold(1024*1024) ;  
        //�ļ��ϴ�����  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        try {  
            //�����ϴ�����ļ�  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
            for(FileItem item : list){  
                //��ȡ��������  
                String name = item.getFieldName();  
                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
                if(item.isFormField()){                     
                    //��ȡ�û�����������ַ���,��Ϊ���ύ�������� �ַ������͵�  
                    String value = item.getString() ;  
                    request.setAttribute(name, value);  
                }else{  
                    //��ȡ·����  
                    String value = item.getName() ;  
                    //���������һ����б��  
                    int start = value.lastIndexOf("\\");  
                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�  
                    String filename = value.substring(start+1);  
                    request.setAttribute(name, filename);  
                    //д��������  
                    item.write( new File(path,filename) );//�������ṩ��  
                    System.out.println("�ϴ��ɹ���"+filename);
                    response.getWriter().print(filename);//��·�����ظ��ͻ���
                }  
            }  

        } catch (Exception e) {  
            System.out.println("�ϴ�ʧ��");
            response.getWriter().print("�ϴ�ʧ�ܣ�"+e.getMessage());
        }  

    }

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
