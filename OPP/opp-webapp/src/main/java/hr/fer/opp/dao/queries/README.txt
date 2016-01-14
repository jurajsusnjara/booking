Primjeri korištenja:


//SelectQuery

public static List<Korisnik> getAdministrators() {
	return new SelectQuery("Korisnik").addEqualityCondition("uloga", 2).getResultList();
}

public static Korisnik getKorisnikById(Object id) {
	return new SelectQuery("Korisnik", "korisnikID", id).getResult();  
	// isto kao SelectQuery("Korisnik").addEqualityCondition("korisnikID", id).getResult()
}

//DeleteQuery

public static void deleteApartman(int id) {
	new DeleteQuery("Apartman", "apartmanID", id).execute();
}

// UpdateQuery

public static void omoguæiParkiranje() {
	UpdateQuery uq = new UpdateQuery("Rezervacija");
	uq.addEqualityCondition("parking", false);
	uq.addAssignment("parking", true);
	uq.execute();
}
