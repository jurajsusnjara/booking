package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAO;
import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.util.Mail;
import hr.fer.opp.viewModels.KorisnikDetailViewModel;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/korisnik/*")
public class KorisnikDetailController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean error = false;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		
		if (korisnikPom == null) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikPom.getKorisnikID());
		String info = req.getPathInfo();
		//List<Rezervacija> rezervacije = korisnik.getRezervacije();
		if (info != null) {
			String elements[] = info.substring(1).split("/");
			if (!elements[0].equals("rezervacija")) {
				throw new RuntimeException("Invalid url!");
			}
			try {
				Integer.parseInt(elements[1]);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Invalid url!");
			}
			int apartmanId = Integer.parseInt(elements[1]);
			Rezervacija rezervacija = KorisnikDetailViewModel.getRezervacijaFor(korisnik, apartmanId);
			if (rezervacija == null) {
				resp.sendRedirect("/opp-webapp/");
				return;
			}
			req.setAttribute("rezervacija", rezervacija);
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnikRezervacija.jsp").forward(req, resp);
			return;
		}
		
		req.setAttribute("rezervacije", korisnik.getRezervacije());
		req.setAttribute("korisnik", korisnik);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		error = false;
		
		if (method.equals("promijeniPodatke")) {
			promijeniPodatke(req, resp);
		} else if (method.equals("promijeniSifru")) {
			promijeniSifru(req, resp);
			if (!error) {
				resp.sendRedirect("/opp-webapp/korisnik");
				return;
			}
		} else if (method.equals("posaljiMolbuZaPromijenu")) {
			posaljiMolbuZaPromijenu(req, resp);
			if (!error) {
				resp.sendRedirect("/opp-webapp/korisnik");
				return;
			}
		}
		if (!error) {
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
		}
		
	}
	
	private void promijeniPodatke(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("mijenja podatke");
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikPom.getKorisnikID());
		
		
		String ime = req.getParameter("ime");
		String prezime = req.getParameter("prezime");
		String email = req.getParameter("email");
		String telefon = req.getParameter("telefon");
		String adresa = req.getParameter("adresa");
		String grad = req.getParameter("grad");
		String drzava = req.getParameter("drzava");
		String postanskiBroj = req.getParameter("postanskiBroj");
		
		
		if (checkNull(ime, prezime, email, telefon, adresa, grad, drzava, postanskiBroj) ) {
			error(req, resp, "Niste unijeli sve podatke");
			return;
		}
		if (!isValidEmailAddress(email)) {
			error(req, resp, "Neispravni email");
			return;
		}
		boolean novo = false;
		
		try {
			Integer.parseInt(postanskiBroj);
			Integer.parseInt(telefon);
		} catch (NumberFormatException e) {
			error(req, resp, "Neispravni telefon ili postanski broj!");
			return;
		}

		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setEmail(email);
		korisnik.setTelefon(telefon);
		
		
		Adresa adresaa = null;
		if ((adresaa = korisnik.getAdresa()) == null) {
			adresaa = new Adresa();
			novo = true;
		}
	//	DAOProvider.getDAO().getKorisnikFor(Integer.parseInt(korisnik.getKorisnikID())).setPrezime(prezime); 
		adresaa.setAdresa(adresa);
		adresaa.setDrzava(drzava);
		adresaa.setGrad(grad);
		adresaa.setPostanskiBroj(Integer.parseInt(postanskiBroj));
		
		if (novo) {
			korisnik.setAdresa(adresaa);
		}
		
		
		 if (novo) {
			 DAOProvider.getDAO().putAdresa(korisnik.getAdresa());
		}
		 
		 req.getSession().setAttribute("korisnik", korisnik);
		// TODO
		//KorisnikDetailViewModel.changeKorisnik(korisnik);
	}
	
	private void promijeniSifru(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikPom.getKorisnikID());
		String staraSifra = req.getParameter("staraLozinka");
		String novaSifra1 = req.getParameter("novaLozinka1");
		String novaSifra2 = req.getParameter("novaLozinka2");
		
		if (!checkPassword(korisnik, staraSifra)) {
			error(req, resp, "Neispravna stara sifra!");
			return;
		}
		if (!novaSifra1.equals(novaSifra2)) {
			error(req, resp, "Niste isprano unijeli novu sifru!");
			return;
		}
		
		if (novaSifra1.length() > 30 || novaSifra1.length() < 5) {
			error(req, resp, "Nova sifra mora biti u rasponu od [5,30]!");
			return;
		}
		
		String lozinka = hashLozinka(novaSifra1);
	
		korisnik.setLozinka(lozinka);
		
		req.getSession().removeAttribute("korisnik");
		// TODO
		//KorisnikDetailViewModel.changePassword(korisnik, novaSifra1);
		
	}
	
	private String hashLozinka(String novaSifra1) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(novaSifra1.getBytes());
		return new String(messageDigest.digest());
	}


	private void posaljiMolbuZaPromijenu(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikPom.getKorisnikID());
		String info = req.getPathInfo();
		String elements[] = info.substring(1).split("/");
		int apartmanID = Integer.parseInt(elements[1]);
			
		String rezerviranoOd = req.getParameter("rezerviranoOd"); //2016-01-06
		String rezerviranoDo = req.getParameter("rezerviranoDo");
		boolean parking = getBoolean(req.getParameter("parking"));
		boolean internet = getBoolean(req.getParameter("internet"));
		boolean satelitskaTV = getBoolean(req.getParameter("satelitskaTV"));
		String odrasi = req.getParameter("odrasli");
		String godina8_14 = req.getParameter("godina8_14");
		String godina2_7 = req.getParameter("godina2_7");
		String godina0_1= req.getParameter("godina0_1");
		int broj;
		Rezervacija rezervacija = KorisnikDetailViewModel.getRezervacijaFor(korisnik, apartmanID);
		if (rezerviranoOd.equals("") || rezerviranoDo.equals("")) {
			errorRezervacija(req, resp, rezervacija, "Niste unijeli sve parametre!");
			return;
		}
		try {
			broj = Integer.parseInt(odrasi) + Integer.parseInt(godina0_1) + 
					Integer.parseInt(godina2_7) + Integer.parseInt(godina8_14);
		} catch (Exception e) {
			errorRezervacija(req, resp, rezervacija, "Krivi unos!");
			return;
		}
		
		OpisApartmana opis = DAOProvider.getDAO().getApartmanFor(apartmanID).getOpisApartmana();
		if (broj > opis.getMaxBroj()) {
			errorRezervacija(req, resp, rezervacija, "Previse osoba!");
			return;
		}
		if (broj < opis.getMinBroj()) {
			errorRezervacija(req, resp, rezervacija, "Premalo osoba!");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Korisnik: " + korisnik.getIme() + " " + korisnik.getPrezime() +
				", id:" + korisnik.getKorisnikID() + " htio bi promijeniti "
       		 + "rezervaciju za apartman id: " + apartmanID + " , od datuma: "
       		 + rezerviranoOd + ", do datuma: "  + rezerviranoDo);
		if (parking) {
			sb.append(", sa ukljucenim parkingom");
		}
		if (internet) {
			sb.append(", sa ukljucenim internetom");
		}
		if (satelitskaTV) {
			sb.append(", sa ukljucenim satelitskimTV-om");
		}
		
		sb.append(", odrasli: " + odrasi + ", djeca(8-14): " + godina8_14 + 
				", djeca(2-7): " + godina2_7 + ", djeca(0-1): " + godina0_1);
		
		for (Korisnik admin : KorisnikDetailViewModel.getAdministrators()) {
			Mail.sendEmail(admin.getEmail(), sb.toString(), "Promjena rezervacije!");
		}
	}

	private boolean checkPassword(Korisnik korisnik, String sifra) {
		sifra = hashLozinka(sifra);
		
		List<Korisnik> listaSvihKorisnika = DAOProvider.getDAO().getAllKorisnik();
		for (Korisnik tmpKorisnik : listaSvihKorisnika) {
			if (tmpKorisnik.getEmail().equals(korisnik.getEmail())) {
				if (tmpKorisnik.getLozinka().equals(sifra)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

	private boolean checkNull(String... name) {
		for (String ime : name) {
			if (ime == null || ime.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	private void error(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setAttribute("error", "Neispravni parametri!");
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
		error = true;
		
		
	}
	
	private void error(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException, ServletException {
		req.setAttribute("error", message);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
		error = true;
		
	}
	
	private void errorRezervacija(HttpServletRequest req,
			HttpServletResponse resp, Rezervacija rezervacija, String message) throws IOException, ServletException {
		req.setAttribute("error", message);
		req.setAttribute("rezervacija", rezervacija);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnikRezervacija.jsp").forward(req, resp);
		error = true;
		
	}
	
	private boolean getBoolean(String parameter) {
		if (parameter == null) return false;
		int bool = Integer.parseInt(parameter);
		if (bool == 0) {
			return false;
		} else if (bool == 1){
			return true;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
}
