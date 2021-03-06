package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Fotografija;
import hr.fer.opp.model.Gost;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.model.TestTable;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		List<Objekt> Objekti = DAOProvider.getDAO().getAllObjekt();
		
		request.setAttribute("Objekti", Objekti);
		request.getSession().getAttribute("korisnik");
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
