package hr.fer.opp.viewModels;

import hr.fer.opp.dao.queries.SelectQuery;
import hr.fer.opp.dao.queries.AbstractQuery.Comparison;
import hr.fer.opp.model.Rezervacija;

import java.util.List;


public class AdminViewModel {

	@SuppressWarnings("unchecked")
	public static List<Rezervacija> getAllRezervacija() {
		return new SelectQuery("Rezervacija").getResultList();
	}

	public static Rezervacija getRezervacijaFor(Integer gostID, Integer apartmanID) {
		SelectQuery q = new SelectQuery("Rezervacija");
		q.addEqualityCondition("gostID", gostID);
		q.addEqualityCondition("apartmanID", apartmanID);
		return (Rezervacija) q.getResult();
	}

	public static Rezervacija getRezervacijaFor(String korisnikID, Integer apartmanID) {
		SelectQuery q = new SelectQuery("Rezervacija");
		q.addCondition(Comparison.EQ, "korisnikID", korisnikID);
		q.addCondition(Comparison.EQ, "apartmanID", apartmanID);
		return (Rezervacija) q.getResult();
	}

}
