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
	import hr.fer.opp.model.Apartman;
	import hr.fer.opp.model.Objekt;
	import hr.fer.opp.model.Rezervacija;
	
	@WebServlet("/apartman")
	public class SmjestajnaJedinicaController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		Apartman trazeniApartman;
	
		public SmjestajnaJedinicaController() {
			super();
		}
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String imeApartmana = request.getParameter("ime");
	
			List<Apartman> listaApartmana = DAOProvider.getDAO().getAllApartman();
			for (Apartman tmpApartman : listaApartmana) {
				if (tmpApartman.getNazivApartman().equals(imeApartmana)) {
					trazeniApartman = tmpApartman;
					break;
				}
			}
			request.setAttribute("apartman", trazeniApartman);
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/apartman.jsp").forward(request, response);
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/apartman.jsp").forward(request, response);
		}
	
	}
