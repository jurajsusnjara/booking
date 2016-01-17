package hr.fer.opp.viewModels;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.dao.queries.SelectQuery;
import hr.fer.opp.model.Rezervacija;

import java.util.List;


public class AdminViewModel {

	@SuppressWarnings("unchecked")
	public static List<Rezervacija> getAllRezervacija() {
		return new SelectQuery("Rezervacija").getResultList();
	}

	public static Rezervacija getRezervacijaFor(String korisnikID, Integer apartmanID) {
		List<Rezervacija> rezervacije = DAOProvider.getDAO().getReservationsFor(DAOProvider.getDAO().getKorisnikFor(korisnikID));
		for (Rezervacija r : rezervacije) {
			if (r.getApartman().getApartmanID() == apartmanID) {
				return r;
			}
		}
		return null;
	}

}
