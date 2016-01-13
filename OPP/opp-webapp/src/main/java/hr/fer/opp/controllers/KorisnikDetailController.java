package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.KorisnikDetailViewModel;

import java.io.IOException;
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik == null) {
			resp.sendRedirect("/");
		}
		String info = req.getPathInfo();
		List<Rezervacija> rezervacije = korisnik.getRezervacije();
		if (info != null) {
			String elements[] = info.substring(1).split("/");
			if (!elements[0].equals("rezervacija")) {
				throw new RuntimeException("Invalid url!");
			}
			try {
				int apartmanId = Integer.parseInt(elements[1]);
				req.setAttribute("rezervacija", KorisnikDetailViewModel.getRezervacijaFor(
						korisnik, apartmanId));
			} catch (NumberFormatException e) {
				throw new RuntimeException("Invalid url!");
			}
		} else {
			req.setAttribute("rezervacije", rezervacije);
		}
		
		req.setAttribute("korisnik", korisnik);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if (method.equals("promijeniPodatke")) {
			promijeniPodatke(req, resp);
		} else if (method.equals("promijeniSifru")) {
			promijeniSifru(req, resp);
		} else if (method.equals("posaljiMolbuZaPromijenu")) {
			posaljiMolbuZaPromijenu(req, resp);
		}
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
	}
	
	private void promijeniPodatke(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Korisnik korisnik = (Korisnik) req.getAttribute("korisnik");
		if (checkNull(korisnik.getIme()) || checkNull(korisnik.getEmail()) 
				|| checkNull(korisnik.getPrezime()) || checkNull(korisnik.getTelefon()) 
				|| !isValidEmailAddress(korisnik.getEmail()) || checkAdresa(korisnik.getAdresa())) {
			error(req, resp);
			return;
		}
		 if (!DAOProvider.getDAO().getAllAdresa().contains(korisnik.getAdresa())) {
			 DAOProvider.getDAO().putAdresa(korisnik.getAdresa());
		}
		 
		// TODO
		//KorisnikDetailViewModel.changeKorisnik(korisnik);
	}
	
	private void promijeniSifru(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik korisnik = (Korisnik) req.getAttribute("korisnik");
		String staraSifra = req.getParameter("staraSifra");
		String novaSifra1 = req.getParameter("novaSifra1");
		String novaSifra2 = req.getParameter("novaSifra2");
		
		if (!checkPassword(korisnik, staraSifra)) {
			req.setAttribute("error", "Neispravna stara sifra!");
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
			return;
		}
		if (!novaSifra1.equals(novaSifra2) && novaSifra1.length() < 30 && novaSifra1.length() > 5) {
			error(req, resp);
			return;
		}
		
		// TODO
		//KorisnikDetailViewModel.changePassword(korisnik, novaSifra1);
		
	}
	
	private void posaljiMolbuZaPromijenu(HttpServletRequest req, HttpServletResponse resp) {
		Rezervacija rezervacija = (Rezervacija) req.getAttribute("rezervacija");
		
		for (Korisnik admin : KorisnikDetailViewModel.getAdministrators()) {
			sendEmail(admin.getEmail(), rezervacija);
		}
	}

	private void sendEmail(String emailTo, Rezervacija rezervacija) {

	 /*     String from = rezervacija.getKorisnik().getEmail();

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

	         // Set Subject: header field
	         message.setSubject("Promijena rezervacije");

	         // Now set the actual message
	         message.setText("KorisnikID: " + rezervacija.getKorisnik().getKorisnikID() + " htio bi promijenit"
	        		 + "rezervaciju za apartman: " + rezervacija.getApartman().getApartmanID() + " , od datuma: "
	        		 + rezervacija.getRezerviranoOd() + ", do datuma: "  + rezervacija.getRezerviranoDo());

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		*/
	}

	private boolean checkPassword(Korisnik korisnik, String staraSifra) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	private boolean checkAdresa(Adresa adresa) {
		Integer postBroj = adresa.getPostanskiBroj();
		if (checkNull(adresa.getAdresa()) || checkNull(adresa.getGrad()) 
				|| checkNull(adresa.getDrzava()) || postBroj != null) {
			return true;
		}
		return false;
	}

	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

	private boolean checkNull(String name) {
		if (name == null || name.equals("")) {
			return true;
		}
		return false;
	}
	
	private void error(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setAttribute("error", "Neispravni parametri!");
		try {
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/korisnik.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}
}
