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

@WebServlet("/objekt")
public class PretrazivanjeObjektaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Objekt trazeniObjekt;

	public PretrazivanjeObjektaController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imeObjekta = request.getParameter("ime");

		List<Objekt> listaObjekata = DAOProvider.getDAO().getAllObjekt();
		for (Objekt tmpObjekt : listaObjekata) {
			if (tmpObjekt.getNazivObjekt().equals(imeObjekta)) {
				trazeniObjekt = tmpObjekt;
				break;
			}
		}

		request.setAttribute("Objekt", trazeniObjekt);
		request.setAttribute("Apartmani", trazeniObjekt.getApartmani());
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/objekt.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		// if (method.equals("filtriraj")) {
		filtriraj(request, response);
		// }
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/objekt.jsp").forward(request, response);
	}

	@SuppressWarnings("deprecation")
	private void filtriraj(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pogled = request.getParameter("pogled");
		Date datumOd = new Date(request.getParameter("datumOd"));
		Date datumDo = new Date(request.getParameter("datumDo"));

		List<Apartman> apartmaniZaIzbacit = new ArrayList<>();
		for (Apartman tmpApartman : trazeniObjekt.getApartmani()) {
			if (!pogled.equals("") && !tmpApartman.getOpisApartmana().getPogled().equals(pogled)) {
				apartmaniZaIzbacit.add(tmpApartman);
				// System.out.println("izbacujem
				// "+tmpApartman.getNazivApartman());
			} else {
				if (!datumOd.equals("") && !datumDo.equals("")) {
					List<Rezervacija> listaRezervacija = DAOProvider.getDAO().getReservationsFor(tmpApartman);
					for (Rezervacija tmpRezervacija : listaRezervacija) {
						if ((tmpRezervacija.getRezerviranoDo().after(datumOd)
								&& tmpRezervacija.getRezerviranoOd().before(datumOd))
								|| (tmpRezervacija.getRezerviranoOd().before(datumDo)
										&& tmpRezervacija.getRezerviranoDo().after(datumDo))) {
							apartmaniZaIzbacit.add(tmpApartman);
						}
					}
				}
			}
		}

		List<Apartman> listaAp = new ArrayList<>();
		listaAp.addAll(trazeniObjekt.getApartmani());
		// System.out.println(listaAp.size());
		listaAp.removeAll(apartmaniZaIzbacit);
		// System.out.println(apartmaniZaIzbacit);
		// System.out.println(listaAp.size());
		request.setAttribute("Objekt", trazeniObjekt);
		request.setAttribute("Apartmani", listaAp);

		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/objekt.jsp").forward(request, response);

	}
}
