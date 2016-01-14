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

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Ime = request.getParameter("Ime");
		String Prezime = request.getParameter("Prezime");
		String Telefon = request.getParameter("Telefon");
		String Email = request.getParameter("Email");
		String Lozinka = request.getParameter("Lozinka");
		String LozinkaPotvrda = request.getParameter("LozinkaPotvrda");
		String Adresa = request.getParameter("Adresa");
		String Grad = request.getParameter("Grad");
		String Drzava = request.getParameter("Drzava");
		Integer PostanskiBroj = Integer.parseInt(request.getParameter("PostanskiBroj"));
		 
		 
		Adresa adresaObj = new Adresa();
		adresaObj.setAdresa(Adresa);
		adresaObj.setDrzava(Drzava);
		adresaObj.setGrad(Grad);
		adresaObj.setPostanskiBroj(PostanskiBroj);
		DAOProvider.getDAO().putAdresa(adresaObj);

		Korisnik novi = new Korisnik();
		novi.setAdresa(adresaObj);
		//datum pravi
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
		String lozinka =  new String(messageDigest.digest());
		novi.setLozinka(lozinka);
		novi.setTelefon(Telefon);
		novi.setKorisnikID(Email);
		novi.setUloga(1);
		
		DAOProvider.getDAO().putKorisnik(novi);
		request.getSession().setAttribute("korisnik", novi);
		RequestDispatcher rd = request.getRequestDispatcher("/index");
		rd.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String sifra = request.getParameter("sifra");

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(sifra.getBytes());
		sifra = new String(messageDigest.digest());
		request.setAttribute("greska", false);
		if (!provjeri(korisnickoIme, sifra, request, response)) {
			request.setAttribute("greska", true);
		} else {

		}
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
					request.setAttribute("greska", true);
					return false;
				}
			}
		}
		request.setAttribute("greska", true);
		return false;
	}

}
