package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Gost;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.util.DateUtils;
import hr.fer.opp.util.Mail;
import hr.fer.opp.viewModels.KorisnikDetailViewModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@WebServlet("/rezervacija/*")
public class RezervacijaController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static class Datum {
		private int dan;
		private boolean zauzet;
		
		public Datum(int dan, boolean zauzet) {
			this.dan = dan;
			this.zauzet = zauzet;
		}
		
		public void setZauzet(boolean zauzet) {
			this.zauzet = zauzet;
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnikPom == null || korisnikPom.getUloga() != 1) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		Integer apartmanID = null;
		if (req.getParameter("id") != null) {
			apartmanID = Integer.parseInt(req.getParameter("id"));
		} else {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		String info = req.getPathInfo();
		//List<Rezervacija> rezervacije = korisnik.getRezervacije();
		if (info != null) {
			String elements[] = info.substring(1).split("/");
			if (elements[0].equals("nova")) {
				List<Rezervacija> rez = (List<Rezervacija>) req.getSession().getAttribute("rezervacije");
				if (rez != null && apartmanID < rez.size()) {
					Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikPom.getKorisnikID());
					spremiRezervaciju(req, resp, korisnik, rez.get(apartmanID));
					return;
				}
			} else {
				throw new RuntimeException("Invalid url!");
			}
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
		Korisnik k = (Korisnik) req.getSession().getAttribute("korisnik");
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(k.getKorisnikID());
		korisnik.getRezervacije().add(rezervacija);
		
		DAOProvider.getDAO().putRezervacija(rezervacija);
		
		*/
		List<Rezervacija> rezervacije = 
				DAOProvider.getDAO().getReservationsFor(DAOProvider.getDAO().getApartmanFor(apartmanID));
		List<Date> slobodniDani = getList(rezervacije);
		
		List<Datum> svibanj = new ArrayList<>();
		List<Datum> lipanj = new ArrayList<>();
		List<Datum> srpanj = new ArrayList<>();
		List<Datum> kolovoz = new ArrayList<>();
		List<Datum> rujan = new ArrayList<>();
		
		for (int i=1; i<=30; i++) {
			lipanj.add(new Datum(i, true));
			rujan.add(new Datum(i, true));
		}
		
		for (int i=1; i<=31; i++) {
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
		
		
		req.setAttribute("svibanj", svibanj);
		req.setAttribute("lipanj", lipanj);
		req.setAttribute("srpanj", srpanj);
		req.setAttribute("kolovoz", kolovoz);
		req.setAttribute("rujan", rujan);
		
		req.setAttribute("apartman", DAOProvider.getDAO().getApartmanFor(apartmanID));
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/rezervacija.jsp").forward(req, resp);
	}
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			rezerviraj(req, resp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	//dobije listu dan koji se mogu rezervirat
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
	
	private void rezerviraj(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ParseException {
		Integer apartmanID = null;
		if (req.getParameter("id") != null) {
			apartmanID = Integer.parseInt(req.getParameter("id"));
		}
		List<Date> slobodniDani = 
				getList(DAOProvider.getDAO().getReservationsFor(DAOProvider.getDAO().getApartmanFor(apartmanID)));
		
		String rezerviranoOd = req.getParameter("rezerviranoOd"); //2016-01-06
		String rezerviranoDo = req.getParameter("rezerviranoDo");
		boolean parking = getBoolean(req.getParameter("parking"));
		boolean internet = getBoolean(req.getParameter("internet"));
		boolean satelitskaTV = getBoolean(req.getParameter("satelitskaTV"));
		String odrasi = req.getParameter("odrasli");
		String godina8_14 = req.getParameter("godina8_14");
		String godina2_7 = req.getParameter("godina2_7");
		String godina0_1= req.getParameter("godina0_1");
		int broj;
		if (rezerviranoOd.equals("") || rezerviranoDo.equals("")) {
			errorRezervacija(req, resp, "Niste unijeli datum rezervacije!");
			return;
		}
		try {
			broj = Integer.parseInt(odrasi) + Integer.parseInt(godina0_1) + 
					Integer.parseInt(godina2_7) + Integer.parseInt(godina8_14);
		} catch (Exception e) {
			errorRezervacija(req, resp, "Krivi unos!");
			return;
		}
		OpisApartmana opis = DAOProvider.getDAO().getApartmanFor(apartmanID).getOpisApartmana();
		
		String greska = "Broj osoba treba biti između [" + opis.getMinBroj() + "]."; 
		if (opis.getMaxBroj() < broj) {
			errorRezervacija(req, resp, "Unijeli ste previše osoba! " + greska);
			return;
		} else if (opis.getMinBroj() > broj) {
			errorRezervacija(req, resp, "Unijeli ste premalo osoba! " + greska);
			return;
		}
		System.out.println(rezerviranoOd);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = formatter.parse("01/29/02");
		Date dateOd = formatter.parse(rezerviranoOd);
		Date dateDo = formatter.parse(rezerviranoDo);
		
		if (dateOd.after(dateDo)) {
			errorRezervacija(req, resp, "Datum prijave treba biti prije datuma odjave!");
			return;
		}
		LocalDate startRezervirano = dateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endRezervirano = dateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
		
		for (LocalDate date = startRezervirano; date.isBefore(endRezervirano); date = date.plusDays(1)) {
			   if (!slobodniDani.contains(DateUtils.asDate(date))) {
				   errorRezervacija(req, resp, "Izabrani datumi su već rezervirani!");
					return;
			   }
		}
		
		//datumi slobodni
		
		int index = slobodniDani.indexOf(dateOd) - 3;
		
		Gost gost = new Gost();
		gost.setOdrasli(Short.parseShort(odrasi));
		gost.setGodina8_14(Short.parseShort(godina8_14));
		gost.setGodina2_7(Short.parseShort(godina2_7));
		gost.setGodina0_1(Short.parseShort(godina0_1));
		
		//provjerava zadnja 3 dana
		if (index >= 0 && slobodniDani.get(index).equals(DateUtils.asDate(startRezervirano.minusDays(3)))) {
			index = slobodniDani.indexOf(dateOd) - 21;
			//provjerava zadnja 3 tjedna
			if (!(index > 0 && slobodniDani.get(index).equals(DateUtils.asDate(startRezervirano.minusDays(21))))) {
				List<Rezervacija> rezervacije = getRezervacijeFor(dateOd, dateDo, slobodniDani, opis, 
						apartmanID, parking, internet, satelitskaTV, gost);;
				req.setAttribute("rezervacije", rezervacije);
				req.getSession().setAttribute("rezervacije", rezervacije);
				req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/popisPredlozenihRezervacija.jsp").forward(req, resp);
			//	errorRezervacija(req, resp, "Optimizacija ne valja!");
				return;
				
			}
			
		}
		
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(
				((Korisnik)req.getSession().getAttribute("korisnik")).getKorisnikID());
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setApartman(DAOProvider.getDAO().getApartmanFor(apartmanID));
		rezervacija.setGost(gost);
		rezervacija.setInternet(internet);
		rezervacija.setParking(parking);
		rezervacija.setSatelitskaTV(satelitskaTV);
		rezervacija.setRezerviranoOd(dateOd);
		rezervacija.setRezerviranoDo(dateDo);
		
		spremiRezervaciju(req, resp, korisnik, rezervacija);
		
		
	}
	
	private void spremiRezervaciju(HttpServletRequest req, HttpServletResponse resp, Korisnik korisnik, Rezervacija rezervacija) throws IOException {
		rezervacija.setKorisnik(korisnik);
		rezervacija.setDatumRezervacije(new Date());
		if (korisnik.getRezervacije() == null || korisnik.getRezervacije().isEmpty()) {
			rezervacija.setPotvrda(false);
		} else {
			rezervacija.setPotvrda(true);
		}
		
		DAOProvider.getDAO().putGost(rezervacija.getGost());
		DAOProvider.getDAO().putRezervacija(rezervacija);
		
		req.getSession().removeAttribute("rezervacije");
		resp.sendRedirect("/opp-webapp/");
		String poruka = "Poštovani,\n\nprimili smo Vašu razervaciju, "
				+ "u roku od tri dana kontaktirati ce Vas predstavnik turistickog naselja.\n\nLp,\nMihajlo";
		Mail.sendEmail(korisnik.getEmail(), poruka, "Rezervacija");
		
	}



	private List<Rezervacija> getRezervacijeFor(Date dateOd, Date dateDo,
			List<Date> slobodniDani, OpisApartmana opis, Integer apartmanID, 
			boolean parking, boolean internet, boolean satelitskaTV, Gost gost) {
		
		List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
		
		int index = slobodniDani.indexOf(dateOd);
		long razlika = Math.abs(dateOd.getTime() - dateDo.getTime()) / (24 * 60 * 60 * 1000);
		
		LocalDate startRezervirano = dateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endRezervirano = dateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
		
		for (LocalDate date = startRezervirano; 
				slobodniDani.get(index).equals(DateUtils.asDate(date)); date = date.minusDays(1)) {
			   index--;
			   if (index < 0) break;
		}
		Rezervacija r = new Rezervacija();
		r.setRezerviranoOd(slobodniDani.get(++index));
		r.setRezerviranoDo(slobodniDani.get((int) (index + razlika)));
		r.setApartman(DAOProvider.getDAO().getApartmanFor(apartmanID));
		r.setParking(parking);
		r.setInternet(internet);
		r.setSatelitskaTV(satelitskaTV);
		r.setGost(gost);
		rezervacije.add(r);
		
		for (Apartman apartman : DAOProvider.getDAO().getAllApartman()) {
			if (apartman.getApartmanID() == apartmanID) continue;
			List<Date> slobodni = 
					getList(DAOProvider.getDAO().getReservationsFor(apartman));
			
			boolean rezerviraj = true;
			for (LocalDate date = startRezervirano; date.isBefore(endRezervirano); date = date.plusDays(1)) {
				if (!slobodni.contains(DateUtils.asDate(date))) {
					rezerviraj = false;
					continue;
				   }
			}
			
			if (!rezerviraj)  continue;
			//datumi slobodni
			
			int i = slobodni.indexOf(dateOd) - 3;
			
			//provjerava zadnja 3 dana
			if (i >= 0 && slobodni.get(i).equals(DateUtils.asDate(startRezervirano.minusDays(3)))) {
				i = slobodni.indexOf(dateOd) - 21;
				//provjerava zadnja 3 tjedna
				if (!(i > 0 && slobodni.get(i).equals(DateUtils.asDate(startRezervirano.minusDays(21))))) {
					rezerviraj = false;
					continue;
					
				}
				
			}
			
			Rezervacija nova = new Rezervacija();
			nova.setRezerviranoOd(dateOd);
			nova.setRezerviranoDo(dateDo);
			nova.setApartman(apartman);
			nova.setParking(parking);
			nova.setInternet(internet);
			nova.setSatelitskaTV(satelitskaTV);
			nova.setGost(gost);
			rezervacije.add(nova);
		}
		return rezervacije;
	}



	private boolean getBoolean(String parameter) {
		if (parameter == null) return false;
		if (parameter.equals("on")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void errorRezervacija(HttpServletRequest req,
			HttpServletResponse resp, String message) throws IOException, ServletException {
		req.setAttribute("error", message);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/rezervacija.jsp").forward(req, resp);
		
	}
	
}
