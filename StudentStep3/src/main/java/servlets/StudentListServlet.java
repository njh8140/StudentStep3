package servlets;

import java.io.IOException;
//import java.sql.Connection;
import java.util.ArrayList;

import DAO.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Student;


@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Connection con = null;
		//Statement st = null;
		//ResultSet rs = null;
		//String query = null;
		
		try{
			ServletContext sc = this.getServletContext();
			//con = (Connection)sc.getAttribute("con");
			
			//StudentDao studentDao = new StudentDao();
			//studentDao.setConnection(con);
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			ArrayList<Student> students = studentDao.selectAll();
			/*
			 * query = "select * from student"; st = con.createStatement(); rs =
			 * st.executeQuery(query);
			 * 
			 * ArrayList<Student> students = new ArrayList<Student>();
			 * 
			 * while(rs.next()) { Student tmp = new Student(); tmp.setNo(rs.getInt("no"));
			 * tmp.setName(rs.getString("name")); tmp.setEmail(rs.getString("email"));
			 * tmp.setPwd(rs.getString("pwd")); tmp.setCre_date(rs.getDate("cre_date"));
			 * tmp.setMod_date(rs.getDate("mod_date"));
			 * 
			 * students.add(tmp); }
			 * 
			 * 
			 */
			request.setAttribute("students", students);
			RequestDispatcher rd = request.getRequestDispatcher("/student/StudentList.jsp");
			rd.include(request, response);
			
			
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}