package hr.fer.opp.controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Korisnik;

@WebServlet("/potvrda")
public class PotvrdaController extends HttpServlet {

	public PotvrdaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Email = request.getParameter("email");
		String Ime = request.getParameter("ime");
		String Prezime = request.getParameter("prezime");
		String Telefon = request.getParameter("tel");
		request.setAttribute("Email", Email);
		request.setAttribute("Ime", Ime);
		request.setAttribute("Prezime", Prezime);
		request.setAttribute("Telefon", Telefon);
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/potvrda.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Email = request.getParameter("email");
		String Ime = request.getParameter("ime");
		String Prezime = request.getParameter("prezime");
		String Telefon = request.getParameter("tel");
		request.setAttribute("Email", Email);
		request.setAttribute("Ime", Ime);
		request.setAttribute("Prezime", Prezime);
		request.setAttribute("Telefon", Telefon);

		String Lozinka = request.getParameter("Lozinka");
		String LozinkaPotvrda = request.getParameter("LozinkaPotvrda");
		if (lozinkaManjaOdosam(Lozinka)) {
			greska(request, response, "Lozinka je prekratka. Molimo upisite lozinku duzu od 8 znakova.");
			return;
		}
		if (lozinkeNisuJednake(Lozinka, LozinkaPotvrda)) {
			greska(request, response, "Lozinke se ne podudaraju, molimo pazljivo unesite lozinku");
			return;
		}

		Korisnik novi = new Korisnik();
		Integer idAdrese = Integer.parseInt(request.getParameter("adrID"));
		Adresa adresaObj = DAOProvider.getDAO().getAdresaFor(idAdrese);
		novi.setAdresa(adresaObj);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		novi.setDatumReg(date);
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
		sendEmail(Email, request, "Uspjesno ste registrirani na \"Kod nas je najljepse\"\n" + "Email: " + Email + "\n"
				+ "Lozinka: " + Lozinka);
		RequestDispatcher rd = request.getRequestDispatcher("/index");
		rd.forward(request, response);
	}

	private boolean lozinkaManjaOdosam(String lozinka) {
		if (lozinka.length() < 8) {
			return true;
		} else {
			return false;
		}
	}

	private boolean lozinkeNisuJednake(String lozinka, String lozinkaPotvrda) {
		if (lozinka.equals(lozinkaPotvrda)) {
			return false;
		} else {
			return true;
		}
	}

	private void greska(HttpServletRequest request, HttpServletResponse response, String string)
			throws IOException, ServletException {
		request.setAttribute("greska", string);
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/potvrda.jsp").forward(request, response);

	}

	private void sendEmail(String emailTo, HttpServletRequest req, String poruka) {

		final String username = "mihajlo.info@gmail.com";
		final String password = "mihajlo7";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject("Potvrdite registraciju");
			message.setText(poruka);

			Transport.send(message);

			System.out.println("Email sent!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
