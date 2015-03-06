package so.xunta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test1
 */
@WebServlet("/test1")
public class test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public test1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("User-Agent   :   "+request.getParameter("User-Agent"));
		System.out.println("Host   :   "+request.getParameter("Host"));
		System.out.println("Cookie   :   "+request.getParameter("Cookie"));
		System.out.println("Connection   :   "+request.getParameter("Connection"));
		System.out.println("Accept-Language   :   "+request.getParameter("Accept-Language"));
		System.out.println("Accept-Encoding   :   "+request.getParameter("Accept-Encoding"));
		System.out.println("Accept   :   "+request.getParameter("Accept"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
