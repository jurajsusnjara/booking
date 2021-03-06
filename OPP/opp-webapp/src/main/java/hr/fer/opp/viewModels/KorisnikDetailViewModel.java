package hr.fer.opp.viewModels;

import java.util.List;

import hr.fer.opp.dao.queries.*;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;

public class KorisnikDetailViewModel {

	// TODO: Provjeriti je li ispravno
	// Vra�a prvu rezervaciju vezanu uz korisnika i apartmanID
	public static Rezervacija getRezervacijaFor(Korisnik korisnik, int apartmanId) {
		for (Rezervacija r : korisnik.getRezervacije())
			if (r.getApartman().getApartmanID() == apartmanId)
				return r;
		return null;
	}

	public static List<Korisnik> getAdministrators() {
		return CommonViewModel.getAdministrators();
	}
	
	/* Nepotrebno
	 * 
	// Mijenja ime, prezime, e-mail, telefon i adresu korisnika
	public static void changeKorisnik(Korisnik korisnik) {
		UpdateQuery uq = new UpdateQuery("Korisnik", "korisnikID", korisnik.getKorisnikID());
		uq.addAssignment("ime", korisnik.getIme());
		uq.addAssignment("prezime", korisnik.getPrezime());
		uq.addAssignment("email", korisnik.getEmail());
		uq.addAssignment("telefon", korisnik.getTelefon());
		uq.addAssignment("adresaID", korisnik.getAdresa().getAdresaID());
		uq.execute();
	}

	// Mijenja lozinku korisnika
	public static void changePassword(Korisnik korisnik, String novaSifra1) {
		UpdateQuery uq = new UpdateQuery("Korisnik", "korisnikID", korisnik.getKorisnikID());
		uq.addAssignment("lozinka", korisnik.getLozinka());
		uq.execute();
	}
	 */
}
