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
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.StudentDao;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		//Connection con = null;
		
		//PrintWriter out = response.getWriter();
		
		try{
			ServletContext sc = this.getServletContext();
			//con = (Connection)sc.getAttribute("con");
			
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			//StudentDao studentDao = new StudentDao();
			//studentDao.setConnection(con);
			
			request.setAttribute("student", studentDao.selectOne(no));
			RequestDispatcher rd = request.getRequestDispatcher("/student/StudentUpdate.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송된 정보 저장
		//DB연결해서 update
		//목록화면 출력
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		//Connection con = null;
		//PreparedStatement pst = null;
		//String query = null;
		//PrintWriter out = response.getWriter();
		
		try{
			ServletContext sc = this.getServletContext();
			//Class.forName(sc.getInitParameter("driver"));
			//con = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			//con = (Connection)sc.getAttribute("con");
			//query = "update student set name = ?, email=?, pwd=?, mod_date=now() where no=?";
			//pst = con.prepareStatement(query);
			
			//pst.setString(1, name);
			//pst.setString(2, email);
			//pst.setString(3, pwd);
			//pst.setInt(4, no);
			
			//pst.executeUpdate();
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEmail(email);
			student.setPwd(pwd);
			
			studentDao.update(student);
			
			response.sendRedirect("list");
			
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
		}	
	}