package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.queries.*;
import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.dao.jpa.JPADAOImpl;
import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CommonViewModel {

	@SuppressWarnings("unchecked")
	public static List<Korisnik> getAdministrators() {
		return new SelectQuery("Korisnik").addEqualityCondition("uloga", 2).getResultList();
	}
}
