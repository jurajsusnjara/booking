package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;

@WebServlet("/statistika/*")
public class StatistikaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Korisnik korisnik = (Korisnik)
		// req.getSession().getAttribute("korisnik");
		// if (korisnik == null || korisnik.getUloga() != 3) {
		// resp.sendRedirect("/opp-webapp/");
		// return;
		// }
		
//		TODO ispisat broj korisnika za svaku državu i grad, eventualno napravit piechart za usluge

		List<ZahtjevStat> usluge = uslugePoTrazenju();
		req.setAttribute("usluge", usluge);

		Map<String, Integer> drzave = drzavePoDolaskuGostiju();
		req.getSession().setAttribute("drzave", drzave);

		Map<String, Integer> gradovi = gradoviPoDolaskuGostiju();
		req.getSession().setAttribute("gradovi", gradovi);

		List<ZauzetiDatumi> jedinice = zauzetost();
		req.getSession().setAttribute("jedinice", jedinice);

		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/statistika.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/statistika.jsp").forward(req, resp);
	}
	
	public class Datumi {
		
		private int dan;
		private boolean zauzet;
		
		public Datumi(int dan, boolean zauzet) {
			
			this.dan = dan;
			this.zauzet = zauzet;
		}
		
		public int getDan() {
			return dan;
		}
		
		public boolean isZauzet() {
			return zauzet;
		}
	}

	public class ZauzetiDatumi {

		private Apartman apartman;
		private String naziv;
		private List<Date> dates;

		public ZauzetiDatumi(Apartman apartman) {
			this.apartman = apartman;
			this.naziv = apartman.getNazivApartman();
			this.dates = new ArrayList<>();
			findDates();
		}

		private void findDates() {

			List<Rezervacija> reservations = DAOProvider.getDAO().getReservationsFor(apartman);
			for (Rezervacija r : reservations) {
				
				if (r.isPotvrda() == false) {
					continue;
				}

				Date start = r.getRezerviranoOd();
				Date end = r.getRezerviranoDo();

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(start);

				while (calendar.getTime().before(end)) {

					Date result = calendar.getTime();
					dates.add(result);
					calendar.add(Calendar.DATE, 1);
				}
			}
		}
		
		public String getNaziv() {
			return naziv;
		}

		public Apartman getApartman() {
			return apartman;
		}

		public List<Date> getDates() {
			return dates;
		}
	}

	public class ZahtjevStat {

		private String zahtjev;
		private int n;
		private String topCountry;

		public ZahtjevStat(String zahtjev, int n, String topCountry) {
			this.zahtjev = zahtjev;
			this.n = n;
			this.topCountry = topCountry;
		}

		public String getZahtjev() {
			return zahtjev;
		}

		public int getN() {
			return n;
		}

		public String getTopCountry() {
			return topCountry;
		}
	}

	private Map<String, Integer> gradoviPoDolaskuGostiju() {

		Map<String, Integer> result = new HashMap<>();

		List<Rezervacija> reservations = DAOProvider.getDAO().getAllRezervacija();

		for (Rezervacija r : reservations) {

			if (r.isPotvrda() == false) {
				continue;
			}

			Korisnik user = r.getKorisnik();
			Adresa address = user.getAdresa();
			String city = address.getGrad();
			if (result.get(city) == null) {
				result.put(city, 1);
			} else {
				int n = result.get(city);
				result.put(city, n + 1);
			}
		}

		return result;
	}

	private List<ZauzetiDatumi> zauzetost() {

		List<Objekt> objekti = DAOProvider.getDAO().getAllObjekt();
		List<ZauzetiDatumi> result = new ArrayList<>();

		for (Objekt o : objekti) {

			List<Apartman> apartmani = o.getApartmani();
			for (Apartman a : apartmani) {

				result.add(new ZauzetiDatumi(a));
			}
		}

		return result;
	}

	private Map<String, Integer> drzavePoDolaskuGostiju() {

		Map<String, Integer> result = new HashMap<>();

		List<Rezervacija> reservations = DAOProvider.getDAO().getAllRezervacija();

		for (Rezervacija r : reservations) {

			if (r.isPotvrda() == false) {
				continue;
			}

			Korisnik user = r.getKorisnik();
			Adresa address = user.getAdresa();
			String country = address.getDrzava();
			if (result.get(country) == null) {
				result.put(country, 1);
			} else {
				int n = result.get(country);
				result.put(country, n + 1);
			}
		}

		return result;
	}

	private List<ZahtjevStat> uslugePoTrazenju() {

		List<ZahtjevStat> result = new ArrayList<>();
		List<Rezervacija> reservations = DAOProvider.getDAO().getAllRezervacija();

		int nParking = 0;
		int nSatelitskaTV = 0;
		int nInternet = 0;
		String topCountrySatelitskaTV;
		String topCountryInternet;
		String topCountryParking;
		Map<String, Integer> satCount = new HashMap<>();
		Map<String, Integer> intCount = new HashMap<>();
		Map<String, Integer> parCount = new HashMap<>();

		for (Rezervacija r : reservations) {

			if (r.isPotvrda() == false) {
				continue;
			}

			Korisnik user = r.getKorisnik();
			Adresa address = user.getAdresa();
			String country = address.getDrzava();

			if (r.isParking()) {
				nParking++;
				if (parCount.get(country) == null) {
					parCount.put(country, 1);
				} else {
					int n = parCount.get(country);
					parCount.put(country, n + 1);
				}

			}
			if (r.isInternet()) {
				nInternet++;

				if (intCount.get(country) == null) {
					intCount.put(country, 1);
				} else {
					int n = intCount.get(country);
					intCount.put(country, n + 1);
				}
			}
			if (r.isSatelitskaTV()) {
				nSatelitskaTV++;
				if (satCount.get(country) == null) {
					satCount.put(country, 1);
				} else {
					int n = satCount.get(country);
					satCount.put(country, n + 1);
				}
			}
		}

		topCountrySatelitskaTV = findTopCountry(satCount);
		topCountryInternet = findTopCountry(intCount);
		topCountryParking = findTopCountry(parCount);

		result.add(new ZahtjevStat("Internet", nInternet, topCountryInternet));
		result.add(new ZahtjevStat("satelitskaTV", nSatelitskaTV, topCountrySatelitskaTV));
		result.add(new ZahtjevStat("Parking", nParking, topCountryParking));

		return result;
	}

	private String findTopCountry(Map<String, Integer> map) {

		if (map.isEmpty()) {
			return null;
		}

		Set<Entry<String, Integer>> set = map.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

				if (o1.getValue() > o2.getValue()) {
					return -1;
				} else if (o1.getValue() < o2.getValue()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		Map.Entry<String, Integer> first = list.get(0);

		return first.getKey();
	}
}
