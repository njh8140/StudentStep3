package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제할 정보 받아서 DB 연결, 삭제
		//목록 화면 출력
		int no = Integer.parseInt(request.getParameter("no"));
		
		//Connection con = null;
		
		try{
			ServletContext sc = this.getServletContext();
			//con = (Connection)sc.getAttribute("con");
			
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			//StudentDao studentDao = new StudentDao();
			//studentDao.setConnection(con);
			studentDao.delete(no);
			
			response.sendRedirect("list");
			
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
		}	
}