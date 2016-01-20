package hr.fer.opp.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Datum;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;

@WebServlet("/apartman")
public class SmjestajnaJedinicaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

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
		Integer IDApartmana = Integer.parseInt(request.getParameter("id"));

		List<Apartman> listaApartmana = DAOProvider.getDAO().getAllApartman();
		for (Apartman tmpApartman : listaApartmana) {
			if (tmpApartman.getApartmanID().equals(IDApartmana)) {
				trazeniApartman = tmpApartman;
				break;
			}
		}

		List<Rezervacija> rezervacije = DAOProvider.getDAO()
				.getReservationsFor(DAOProvider.getDAO().getApartmanFor(trazeniApartman.getApartmanID()));
		List<Date> slobodniDani = getList(rezervacije);

		List<Datum> svibanj = new ArrayList<>();
		List<Datum> lipanj = new ArrayList<>();
		List<Datum> srpanj = new ArrayList<>();
		List<Datum> kolovoz = new ArrayList<>();
		List<Datum> rujan = new ArrayList<>();

		for (int i = 1; i <= 30; i++) {
			lipanj.add(new Datum(i, true));
			rujan.add(new Datum(i, true));
		}

		for (int i = 1; i <= 31; i++) {
			svibanj.add(new Datum(i, true));
			srpanj.add(new Datum(i, true));
			kolovoz.add(new Datum(i, true));
		}

		for (Date dan : slobodniDani) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dan);
			if (cal.get(Calendar.MONTH) == Calendar.MAY) {
				svibanj.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			} else if (cal.get(Calendar.MONTH) == Calendar.JUNE) {
				lipanj.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			} else if (cal.get(Calendar.MONTH) == Calendar.JULY) {
				srpanj.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			} else if (cal.get(Calendar.MONTH) == Calendar.AUGUST) {
				kolovoz.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			} else if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
				rujan.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			}
		}

		request.setAttribute("svibanj", svibanj);
		request.setAttribute("lipanj", lipanj);
		request.setAttribute("srpanj", srpanj);
		request.setAttribute("kolovoz", kolovoz);
		request.setAttribute("rujan", rujan);
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

	public static List<Date> getList(List<Rezervacija> rezervacije) {
		Date trenutniDatum = new Date();
		int godina = Calendar.getInstance().get(Calendar.YEAR);

		Date prviDan = new GregorianCalendar(godina, Calendar.MAY, 1).getTime();
		Date zadnjiDan = new GregorianCalendar(godina, Calendar.SEPTEMBER, 30).getTime();
		Calendar cal = Calendar.getInstance();

		List<Date> slobodni = new ArrayList<Date>();

		if (trenutniDatum.after(zadnjiDan)) {
			prviDan = new GregorianCalendar(godina + 1, Calendar.MAY, 1).getTime();
			zadnjiDan = new GregorianCalendar(godina + 1, Calendar.SEPTEMBER, 30).getTime();

		} else if (!trenutniDatum.before(prviDan)) {
			cal.setTime(trenutniDatum);
			prviDan = new GregorianCalendar(godina, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).getTime();

		}

		LocalDate start = prviDan.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = zadnjiDan.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			slobodni.add(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		for (Rezervacija rezervacija : rezervacije) {
			Date rezerviranOd = rezervacija.getRezerviranoOd();
			Date rezerviranDo = rezervacija.getRezerviranoDo();

			if (rezerviranDo.after(prviDan)) {

				LocalDate startRezervirano = rezerviranOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate endRezervirano = rezerviranDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.plusDays(1);

				for (LocalDate date = startRezervirano; date.isBefore(endRezervirano); date = date.plusDays(1)) {
					slobodni.remove(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
			}

		}

		return slobodni;
	}

}
