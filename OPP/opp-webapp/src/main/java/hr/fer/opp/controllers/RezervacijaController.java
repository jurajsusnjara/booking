package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;

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

@WebServlet("/rezervacija")
public class RezervacijaController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik.getUloga() != 1) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		Integer apartmanID = null;
		if (req.getParameter("apartmanID") != null) {
			apartmanID = Integer.parseInt(req.getParameter("apartmanID"));
		}
		
/*		int apartmanID = 1;
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setApartman(DAOProvider.getDAO().getApartmanFor(apartmanID));
		rezervacija.setGost(DAOProvider.getDAO().getGostFor(1));
		rezervacija.setKorisnik(DAOProvider.getDAO().getKorisnikFor("3"));
		rezervacija.setDatumRezervacije(new GregorianCalendar(2016, Calendar.MAY, 1).getTime());
		rezervacija.setRezerviranoOd(new GregorianCalendar(2016, Calendar.JUNE, 1).getTime());
		rezervacija.setRezerviranoDo(new GregorianCalendar(2016, Calendar.JULY, 15).getTime());
		rezervacija.setParking(true);
		rezervacija.setInternet(true);
		rezervacija.setSatelitskaTV(true);
		
		DAOProvider.getDAO().putRezervacija(rezervacija);
		
		*/
		List<Rezervacija> rezervacije = 
				DAOProvider.getDAO().getReservationsFor(DAOProvider.getDAO().getApartmanFor(apartmanID));
		List<Date> slobodniDani = getList(rezervacije);
		
		req.setAttribute("slobodniDani", slobodniDani);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/rezervacija.jsp").forward(req, resp);
	}
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		rezerviraj(req, resp);
		
	}

	//dobije rezervacije koje vrijede
	private List<Date> getList(List<Rezervacija> rezervacije) {
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
			prviDan = new GregorianCalendar(
					godina, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).getTime();
			
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
				LocalDate endRezervirano = rezerviranDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
				
				for (LocalDate date = startRezervirano; date.isBefore(endRezervirano); date = date.plusDays(1)) {
					   slobodni.remove(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
			}
			
		}

		return slobodni;
	}
	
	private void rezerviraj(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
}
