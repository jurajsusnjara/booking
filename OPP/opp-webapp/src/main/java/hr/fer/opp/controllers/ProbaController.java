package hr.fer.opp.controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.queries.DeleteQuery;
import hr.fer.opp.dao.queries.SelectQuery;
import hr.fer.opp.dao.queries.UpdateQuery;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.AdminViewModel;

@WebServlet("/proba")
public class ProbaController extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnikFor("10");
		
		req.getSession().setAttribute("korisnik", k);
/*
		Korisnik korisnik = new Korisnik();
		korisnik.setAdresa(DAOProvider.getDAO().getAdresaFor(16));
		korisnik.setIme("Damjan");
		korisnik.setPrezime("Miko");
		korisnik.setDatumReg(new Date());
		korisnik.setEmail("damjan7mikogmail.com");
		korisnik.setKorisnikID("10");
		
		String sifra = "mihajlo7";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(sifra.getBytes());
		sifra = new String(messageDigest.digest());
		korisnik.setLozinka(sifra);
		korisnik.setUloga(2);
		korisnik.setTelefon("0201452");
		DAOProvider.getDAO().putKorisnik(korisnik);
*/
	//	Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor("3");
	//	sendEmail("damjan7@miko.hotmail.com", req, k, "bok");
		System.out.println("/Proba1");
	}
	
	
		
	

}
