package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.JPAEMProvider;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hr.fer.opp.viewModels.queries.*;

public class VlasnikViewModel {

	public static void deleteApartman(int id) {
		new DeleteQuery("Apartman", "apartmanID", id).execute();
	}

	public static void deleteObjekt(int id) {
		new DeleteQuery("Objekt", "objektID", id).execute();
	}

	public static void deleteOpisApartmana(int id) {
		new DeleteQuery("OpisApartmana", "opisID", id).execute();
	}

	public static void changeObjekt(Objekt objekt) {
		UpdateQuery uq = new UpdateQuery("Objekt", "objektID", objekt.getObjektID());
		uq.addAssignment("nazivObjekt",  objekt.getNazivObjekt());
		if (objekt.getFotografija() != null) 
			uq.addAssignment("fotografija",  objekt.getFotografija());
		uq.execute();
	}

	public static void changeApartman(Apartman apartman) {
		UpdateQuery uq = new UpdateQuery("OpisApartmana", "apartmanID", apartman.getApartmanID());
		uq.addAssignment("nazivApartman", apartman.getNazivApartman());
		uq.addAssignment("objektID", apartman.getObjekt().getObjektID());
		uq.addAssignment("opisID", apartman.getOpisApartmana().getOpisID());
		uq.execute();
		// TODO?: apartman.rezervacije
	}

	public static void changeOpisApartmana(OpisApartmana opis) {
		UpdateQuery uq = new UpdateQuery("OpisApartmana", "opisID", opis.getOpisID());
		uq.addAssignment("naslov", opis.getNaslov());
		uq.addAssignment("kat", opis.getKat());
		uq.addAssignment("pogled", opis.getPogled());
		uq.addAssignment("minBroj", opis.getMinBroj());
		uq.addAssignment("maxBroj", opis.getMaxBroj());
		uq.addAssignment("opis", opis.getOpis());
		uq.addAssignment("opisID", opis.getOpisID());
		uq.execute();
		// TODO?: OpisApartmana.apartmani
		// TODO?: OpisApartmana.fotografije
	}

	public static List<Korisnik> getAdministrators() {
		return CommonViewModel.getAdministrators();
	}
}
