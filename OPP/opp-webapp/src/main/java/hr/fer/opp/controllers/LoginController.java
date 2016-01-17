package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;

@WebServlet("/registracija")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Apartman trazeniApartman;

	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("error", null);
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/registracija.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("register")) {
			register(request, response);
		}

		// request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/registracija.jsp").forward(request,
		// response);

	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("greska", null);
		String Ime = request.getParameter("Ime");
		String Prezime = request.getParameter("Prezime");
		String Telefon = request.getParameter("Telefon");
		String Email = request.getParameter("Email");
		String Lozinka = request.getParameter("Lozinka");
		String LozinkaPotvrda = request.getParameter("LozinkaPotvrda");
		String Adresa = request.getParameter("Adresa");
		String Grad = request.getParameter("Grad");
		String Drzava = request.getParameter("Drzava");
		String PostanskiBr = request.getParameter("PostanskiBroj");

		if (checkNull(Ime, Prezime, Telefon, Email, Lozinka, LozinkaPotvrda, Adresa, Grad, Drzava, PostanskiBr)) {
			greska(request, response, "Molimo popunite sve podatke");
			return;
		}

		if (!isValidEmailAddress(Email)) {
			greska(request, response, "Neispravan e-mail");
			return;
		}
		if (vecPostojiEmail(Email)) {
			greska(request, response, "Korisnik s tim e-mailom vec postoji, molimo odaberite drugi");
			return;
		}

		if (lozinkeNisuJednake(Lozinka, LozinkaPotvrda)) {
			greska(request, response, "Lozinke se ne podudaraju, molimo pazljivo unesite lozinku");
			return;
		}

		Integer PostanskiBroj = null;
		try {
			PostanskiBroj = Integer.parseInt(PostanskiBr);
		} catch (Exception e) {
			greska(request, response, "Neispravan postanski broj");
			return;
		}

		Adresa adresaObj = new Adresa();
		adresaObj.setAdresa(Adresa);
		adresaObj.setDrzava(Drzava);
		adresaObj.setGrad(Grad);
		adresaObj.setPostanskiBroj(PostanskiBroj);
		DAOProvider.getDAO().putAdresa(adresaObj);

		Korisnik novi = new Korisnik();
		novi.setAdresa(adresaObj);
		// datum pravi
		Date sad = new Date();
		sad.setTime(100000000);
		novi.setDatumReg(sad);
		novi.setIme(Ime);
		novi.setPrezime(Prezime);
		novi.setEmail(Email);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(Lozinka.getBytes());
		String lozinka = new String(messageDigest.digest());
		novi.setLozinka(lozinka);
		novi.setTelefon(Telefon);
		novi.setKorisnikID(Email);
		novi.setUloga(1);

		DAOProvider.getDAO().putKorisnik(novi);
		request.getSession().setAttribute("korisnik", novi);
		RequestDispatcher rd = request.getRequestDispatcher("/index");
		rd.forward(request, response);
	}

	private boolean lozinkeNisuJednake(String lozinka, String lozinkaPotvrda) {
		if (lozinka.equals(lozinkaPotvrda)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean vecPostojiEmail(String email) {
		List<Korisnik> listaKorisnika = DAOProvider.getDAO().getAllKorisnik();
		for (Korisnik tmpKorisnik : listaKorisnika) {
			if (tmpKorisnik.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	private void greska(HttpServletRequest request, HttpServletResponse response, String string) throws IOException {
		request.setAttribute("greska", string);
		try {
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/registracija.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void error(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException {
		req.setAttribute("error", message);
		try {
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/registracija.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String sifra = request.getParameter("sifra");
		request.setAttribute("error", null);

		if (checkNull(korisnickoIme, sifra)) {
			error(request, response, "Upisite lozinku i korisnicko ime");
			return;
		}

		if (!isValidEmailAddress(korisnickoIme)) {
			error(request, response, "Neispravan e-mail");
			return;
		}

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(sifra.getBytes());
		sifra = new String(messageDigest.digest());
		request.setAttribute("greska", null);
		if (!provjeri(korisnickoIme, sifra, request, response)) {
			error(request, response, "Pogresna lozinka/korisnicko ime");
		} else {
			return;
		}
	}

	private boolean isValidEmailAddress(String korisnickoIme) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(korisnickoIme);
		return m.matches();
	}

	private boolean provjeri(String korisnickoIme, String sifra, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Korisnik> listaSvihKorisnika = DAOProvider.getDAO().getAllKorisnik();
		for (Korisnik tmpKorisnik : listaSvihKorisnika) {
			if (tmpKorisnik.getEmail().equals(korisnickoIme)) {
				if (tmpKorisnik.getLozinka().equals(sifra)) {
					request.getSession().setAttribute("korisnik", tmpKorisnik);
					RequestDispatcher rd = request.getRequestDispatcher("/index");
					rd.forward(request, response);
					return true;
				} else {
					return false;
				}
			}
		}
		request.setAttribute("greska", true);
		return false;
	}

	private boolean checkNull(String... podaci) {
		for (String tmp : podaci) {
			System.out.println(tmp);
			if (tmp == null || tmp.equals("")) {

				return true;
			}
		}
		return false;
	}

}
