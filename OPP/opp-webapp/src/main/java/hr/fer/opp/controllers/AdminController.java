package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.AdminViewModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//http://localhost:8080/opp-webapp/admin?apartmanID=12&gostID=5

@WebServlet("/admin")
public class AdminController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik.getUloga() != 2) {
			resp.sendRedirect("");
			return;
		}
		
		
		Integer apartmanID = null;
		Integer korisnikID = null;
		Integer gostID = null;
		
		if (req.getParameter("apartmanID") != null) {
			apartmanID = Integer.parseInt(req.getParameter("apartmanID"));
		}
		if (req.getParameter("korisnikID") != null) {
			 korisnikID = Integer.parseInt(req.getParameter("korisnikID"));
		}
		if (req.getParameter("gostID") != null) {
			gostID = Integer.parseInt(req.getParameter("gostID"));
		}
		
		
		if (apartmanID == null && korisnikID == null && gostID == null) {
			List<Rezervacija> rezervacije = AdminViewModel.getAllRezervacija();
			req.setAttribute("rezervacije", rezervacije);
			req.getRequestDispatcher("/WEB-INF/JSP/admin.jsp");
			return;
			
		} else if (apartmanID != null){
			if (korisnikID != null) {
				Rezervacija rezervacija = AdminViewModel.getRezervacijaFor(korisnikID.toString(), apartmanID);
				req.setAttribute("rezervacija", rezervacija);
			} else if (gostID != null) {
				Rezervacija rezervacija = AdminViewModel.getRezervacijaFor(gostID, apartmanID);
				req.setAttribute("rezervacija", rezervacija);
			} else {
				throw new RuntimeException("Invalid url!");
			}
		} else {
			throw new RuntimeException("Invalid url!");
		}
		
		
		List<Apartman> apartmani = DAOProvider.getDAO().getAllApartman();
		req.setAttribute("apartmani", apartmani);
		req.getRequestDispatcher("/WEB-INF/JSP/promijenaRezervacije.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if (method.equals("promijeniRezervaciju")) {
			promijeniRezervaciju(req, resp);
		}
		resp.sendRedirect("admin");
	}


	private void promijeniRezervaciju(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Integer apartmanID = null;
		Integer korisnikID = null;
		Integer gostID = null;
		boolean korisnik = true;
		
		if (req.getParameter("apartmanID") != null) {
			apartmanID = Integer.parseInt(req.getParameter("apartmanID"));
		}
		if (req.getParameter("korisnikID") != null) {
			 korisnikID = Integer.parseInt(req.getParameter("korisnikID"));
		}
		if (req.getParameter("gostID") != null) {
			gostID = Integer.parseInt(req.getParameter("gostID"));
			korisnik = false;
		}
		//System.out.println(apartmanID + "  " + korisnikID + " " + gostID);
		if ((gostID == null && korisnikID == null) || apartmanID == null) {
			throw new RuntimeException("Invalid url!");
		}
		
		Rezervacija rezervacija;
		if (korisnik) {
			rezervacija = AdminViewModel.getRezervacijaFor(korisnikID, apartmanID);
		} else {
			rezervacija = AdminViewModel.getRezervacijaFor(gostID, apartmanID);
		}
		
		try {
			DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date rezerviranoOd = format.parse(req.getParameter("rezerviranoOd"));
			Date rezerviranoDo = format.parse(req.getParameter("rezerviranoDo"));
			boolean parking = getBoolean(req.getParameter("parking"));
			boolean internet = getBoolean(req.getParameter("internet"));
			boolean satelitskaTV = getBoolean(req.getParameter("satelitskaTV"));
			int id = Integer.parseInt(req.getParameter("izabranApartmanID"));
			
			rezervacija.setApartman(DAOProvider.getDAO().getApartmanFor(id));
			rezervacija.setInternet(internet);
			rezervacija.setParking(parking);
			rezervacija.setRezerviranoDo(rezerviranoDo);
			rezervacija.setRezerviranoOd(rezerviranoOd);
			rezervacija.setSatelitskaTV(satelitskaTV);
			
		} catch (Exception ex) {
			req.setAttribute("error", "Neispravni parametri!");
			try {
				req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/vlasnik.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
	
	}


	private boolean getBoolean(String parameter) {
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
