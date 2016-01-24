package hr.fer.opp.controllers.pics;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;

public class Zauzetost {

	private List<Objekt> objekti;

	public Zauzetost() {

		objekti = DAOProvider.getDAO().getAllObjekt();
	}

	public class Datum {

		private int dan;
		private boolean zauzet;

		public Datum(int dan, boolean zauzet) {
			super();
			this.dan = dan;
			this.zauzet = zauzet;
		}

		public int getDan() {
			return dan;
		}

		public boolean isZauzet() {
			return zauzet;
		}

		public void setZauzet(boolean zauzet) {
			this.zauzet = zauzet;
		}
	}

	public List<Objekt> getObjekti() {
		return objekti;
	}

	public List<Datum> getDatesFor(Apartman apartman, int month) {

		List<Rezervacija> reservations = DAOProvider.getDAO().getReservationsFor(apartman);
		List<Datum> dates = new ArrayList<>();

		int days = getDays(month);

		for (int i = 1; i <= days; i++) {

			dates.add(new Datum(i, true));
		}

		List<Date> freeDays = getList(reservations);

		for (Date day : freeDays) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(day);
			if (cal.get(Calendar.MONTH) == getCalendarMonth(month)) {
				dates.get(cal.get(Calendar.DAY_OF_MONTH) - 1).setZauzet(false);
			}
		}

		return dates;
	}

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

	private int getCalendarMonth(int month) {

		if (month == 5) {
			return Calendar.MAY;
		} else if (month == 6) {
			return Calendar.JUNE;
		} else if (month == 7) {
			return Calendar.JULY;
		} else if (month == 8) {
			return Calendar.AUGUST;
		} else {
			return Calendar.SEPTEMBER;
		}
	}

	private int getDays(int month) {

		if (month == 5 || month == 7 || month == 8) {
			return 31;
		} else {
			return 30;
		}
	}

	// TODO u jspu iterirat po svim objektima
	// za svakog objekta iterirat po svim jedinicama
	// za svaku jedinicu napravit buttone za 5, 6, 7, 8 i mjesec
	// za svaki mjesec dohvatit listu datuma preko metode getDatesFor
	// i onda iterirat po svim danima crtajuæi tablicu
}
