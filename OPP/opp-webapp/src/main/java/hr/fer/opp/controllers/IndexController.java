package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Gost;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.model.TestTable;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		UPUTA *********************************************************************************
		
//		svi objekti u paketu hr.fer.opp.model imaju defaultni konstruktor bez argumenata,
//		nakon inicijalizacije objekta, preko settera se postave SVE potrebne vrijednosti 
//		OSIM id-a kojeg æe baza sama automatski generirat, jedino se kod objekta Korisnik
//		treba postavit i id odmah sa setterom koji je zapravo korisnièko ime.
//		Pazite na strane kljuèeve i takve pizdarije.
		
//		PRIMJERI ZA KOMUNIKACIJU S BAZOM ******************************************************
		
		/*Objekt objektA = new Objekt();
		objektA.setNazivObjekt("objekt A");
		DAOProvider.getDAO().putObjekt(objektA);
		
		Objekt objektB = new Objekt();
		objektB.setNazivObjekt("objekt B");
		DAOProvider.getDAO().putObjekt(objektB);
		
		Apartman apartman1A = new Apartman();
		apartman1A.setNazivApartman("apartman420");
		apartman1A.setObjekt(objektA);
		DAOProvider.getDAO().putApartman(apartman1A);
		
		Apartman apartman1B = new Apartman();
		apartman1B.setNazivApartman("apartman6969");
		apartman1B.setObjekt(objektB);
		DAOProvider.getDAO().putApartman(apartman1B);
		
		
		List<Objekt> objekti = DAOProvider.getDAO().getAllObjekt();
		List<Apartman> apartmani = DAOProvider.getDAO().getAllApartman();
		
		for(Objekt objekt : objekti) {
			System.out.println(objekt.getNazivObjekt());
		}
		for(Apartman apartman : apartmani) {
			System.out.println(apartman.getNazivApartman());
		}*/
		
//		Gost gost = new Gost();
//		gost.setBrojGodina((short)10);
//		DAOProvider.getDAO().putGost(gost);
//		
//		Objekt objektX = new Objekt();
//		objektX.setNazivObjekt("objekt X");
//		DAOProvider.getDAO().putObjekt(objektX);
//		
//		Apartman apartman1X = new Apartman();
//		apartman1X.setNazivApartman("apartman1X");
//		apartman1X.setObjekt(objektX);
//		DAOProvider.getDAO().putApartman(apartman1X);
//		
//		Adresa adresa = new Adresa();
//		adresa.setAdresa("Lumbinov Most 3");
//		adresa.setDrzava("Hrvatska");
//		adresa.setGrad("Sinj");
//		adresa.setPostanskiBroj(21230);
//		DAOProvider.getDAO().putAdresa(adresa);
//		
//		Korisnik korisnik = new Korisnik();
//		korisnik.setKorisnikID("mrzlek");
//		korisnik.setAdresa(adresa);
//		korisnik.setDatumReg(new Date());
//		korisnik.setEmail("js@js.com");
//		korisnik.setIme("Juraj");
//		korisnik.setLozinka("juraj");
//		korisnik.setPrezime("Susnjara");
//		korisnik.setTelefon("0996968018");
//		korisnik.setUloga(1);
//		DAOProvider.getDAO().putKorisnik(korisnik);
//		
//		Rezervacija rezervacija = new Rezervacija();
//		rezervacija.setApartman(apartman1X);
//		rezervacija.setDatumRezervacije(new Date());
//		rezervacija.setGost(gost);
//		rezervacija.setInternet(false);
//		rezervacija.setKorisnik(korisnik);
//		rezervacija.setParking(false);
//		rezervacija.setRezerviranoDo(new Date());
//		rezervacija.setRezerviranoOd(new Date());
//		rezervacija.setSatelitskaTV(false);
//		DAOProvider.getDAO().putRezervacija(rezervacija);
		
//		List<Korisnik> korisnici = DAOProvider.getDAO().getAllKorisnik();
//		List<Rezervacija> rez = DAOProvider.getDAO().getReservationsFor(korisnici.get(0));
//		System.out.println(rez.get(0).getKorisnik().getIme());
		
//		Gost gost = DAOProvider.getDAO().getGostFor(69);
//		System.out.println(gost.getBrojGodina());

		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
