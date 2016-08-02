package controller;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.EmpregadoTO;
import model.EspecialistaEmpregado;

/**
 * Servlet implementation class BuscarFoto
 */
@WebServlet("/BuscarFoto")
public class BuscarFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarFoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EspecialistaEmpregado especilista = new EspecialistaEmpregado();
		EmpregadoTO empregado = especilista.pesquisar(request.getParameter("codigo"));

		byte[] imagemStream = empregado.getFoto();
	    response.setContentType( "image/jpeg" );
	    OutputStream out = response.getOutputStream();  
	    out.write( imagemStream );  
	    out.flush();
		
		
	}
		
	
}
