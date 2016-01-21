package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Integer id = Integer.parseInt(request.getParameter("id"));

		List<Objekt> listaObjekata = DAOProvider.getDAO().getAllObjekt();
		for (Objekt tmpObjekt : listaObjekata) {
			if (tmpObjekt.getObjektID().equals(id)) {
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
		//String method = request.getParameter("method");
		// if (method.equals("filtriraj")) {
		filtriraj(request, response);
		// }
		// request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/objekt.jsp").forward(request,
		// response);
	}

	@SuppressWarnings("deprecation")
	private void filtriraj(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pogled = request.getParameter("pogled");
		String datOd = request.getParameter("datumOd");
		String datDo = request.getParameter("datumDo");
		System.out.println("DATUM JE -"+request.getParameter("datumOd"));
		System.out.println(request.getParameter("datumOd").equals(""));
		List<Apartman> listaAp = new ArrayList<>();
		listaAp.addAll(trazeniObjekt.getApartmani());
		Set<Apartman> apartmaniZaIzbacit = new HashSet<>();
		if (!(pogled == null) && !pogled.equals("")) {
			for (Apartman tmpApartman : listaAp) {
				if (!tmpApartman.getOpisApartmana().getPogled().equals(pogled)) {
					apartmaniZaIzbacit.add(tmpApartman);
				}
			}
		}
		listaAp.removeAll(apartmaniZaIzbacit);
		apartmaniZaIzbacit = new HashSet<>();
		if (!(datDo == null || datOd == null || datDo.equals("") || datOd.equals(""))) {
			Date datumOd;
			Date datumDo;
			String[] datOdd = datOd.split("-");
			String[] datDoo = datDo.split("-");
			// try {
			datumOd = new Date(Integer.parseInt(datOdd[0]), Integer.parseInt(datOdd[1]), Integer.parseInt(datOdd[2]));
			datumDo = new Date(Integer.parseInt(datDoo[0]), Integer.parseInt(datDoo[1]), Integer.parseInt(datDoo[2]));
			// } catch (Exception e) {
			// System.out.println("pogresan datum");
			for (Apartman tmpApartman : listaAp) {
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
		listaAp.removeAll(apartmaniZaIzbacit);

		request.setAttribute("Objekt", trazeniObjekt);
		request.setAttribute("Apartmani", listaAp);

		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/objekt.jsp").forward(request, response);

	}
}
