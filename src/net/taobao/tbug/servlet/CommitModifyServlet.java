package net.taobao.tbug.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommitModifyServlet
 */
public class CommitModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String formName = request.getParameter("formName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println("===>" + title + "=" + content);
		
	}
	
	
	/**
	 * 读取本地文件
	 */
	public static String readFile(String filename){
		String content = ""; //路径 ，比如d: 标示D盘
		File f = new File(filename);
		try{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			try{
				String line = br.readLine();
				while(line != null){
					content += line;
					content += br.readLine();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return content;
	} 
	
	/**
	 * 写文件
	 */
	public void writeFile(String content, String filename){
		File f = new File(filename);
		FileWriter fw;
		try{
			fw = new FileWriter(f);
			fw.write(filename);
			fw.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
