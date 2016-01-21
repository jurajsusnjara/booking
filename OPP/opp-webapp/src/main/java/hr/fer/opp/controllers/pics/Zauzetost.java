package hr.fer.opp.controllers.pics;

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
	}
	
	public List<Objekt> getObjekti() {
		return objekti;
	}
	
	public List<Datum> getDatesFor(Apartman apartman, int mjesec) {
	
//		TODO vradi List<Datum> za dani mjesec i apartman
		
		return null;
	}
	
//	TODO u jspu iterirat po svim objektima
//	za svakog objekta iterirat po svim jedinicama
//	za svaku jedinicu napravit buttone za 5, 6, 7, 8 i mjesec
//	za svaki mjesec dohvatit listu datuma preko metode getDatesFor
//	i onda iterirat po svim danima crtajuæi tablicu
}
