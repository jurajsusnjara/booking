package hr.fer.opp.controllers.pics;

import java.util.List;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Objekt;

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
	
	public List<Apartman> getApartmaniFor(Objekt objekt) {
		
		return objekt.getApartmani();
	}
	
	
}
