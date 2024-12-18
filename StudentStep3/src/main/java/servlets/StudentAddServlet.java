package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.StudentDao;

@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("StudentInsert.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		//Connection con = null;
		//PreparedStatement pst = null;
		//String query = null;
		//PrintWriter out = response.getWriter();
		
		
		try{
			ServletContext sc = this.getServletContext();
			//con = (Connection)sc.getAttribute("con");
			
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			//StudentDao studentDao = new StudentDao();
			//studentDao.setConnection(con); //DB 연결
			
			Student student = new Student();
			student.setName(name);
			student.setEmail(email);
			student.setPwd(pwd);
			
			studentDao.insert(student);
			response.sendRedirect("list");
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
		}	
}