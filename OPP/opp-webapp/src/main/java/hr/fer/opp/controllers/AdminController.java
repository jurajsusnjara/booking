package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.AdminViewModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//http://localhost:8080/opp-webapp/admin?apartmanID=12&korisnikID=2

@WebServlet("/admin")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik == null || korisnik.getUloga() != 2) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		
		Integer apartmanID = null;
		Integer korisnikID = null;
		
		if (req.getParameter("apartmanID") != null) {
			apartmanID = Integer.parseInt(req.getParameter("apartmanID"));
		}
		if (req.getParameter("korisnikID") != null) {
			 korisnikID = Integer.parseInt(req.getParameter("korisnikID"));
		}
		
		
		if (apartmanID == null && korisnikID == null) {
			List<Rezervacija> rezervacije = AdminViewModel.getAllRezervacija();
			req.setAttribute("rezervacije", rezervacije);
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/admin.jsp").forward(req, resp);
			return;
			
		} else if (apartmanID != null && korisnikID != null){
			Rezervacija rezervacija = AdminViewModel.getRezervacijaFor(korisnikID.toString(), apartmanID);
			if (rezervacija == null) {
				resp.sendRedirect("/opp-webapp/admin");
				return;
			}
			req.setAttribute("rezervacija", rezervacija);
		} else {
			throw new RuntimeException("Invalid url!");
		}
		
		
		List<Apartman> apartmani = DAOProvider.getDAO().getAllApartman();
		req.setAttribute("apartmani", apartmani);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/promjenaRezervacije.jsp").forward(req, resp);
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


	@SuppressWarnings("deprecation")
	private void promijeniRezervaciju(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		Integer apartmanID = null;
		Integer korisnikID = null;
		
		if (req.getParameter("apartmanID") != null) {
			apartmanID = Integer.parseInt(req.getParameter("apartmanID"));
		}
		if (req.getParameter("korisnikID") != null) {
			 korisnikID = Integer.parseInt(req.getParameter("korisnikID"));
		}
		//System.out.println(apartmanID + "  " + korisnikID + " " + gostID);
		if (korisnikID == null || apartmanID == null) {
			throw new RuntimeException("Invalid url!");
		}
		
		Rezervacija rezervacija  = AdminViewModel.getRezervacijaFor(korisnikID.toString(), apartmanID);;
		String rezerviranoOd = req.getParameter("rezerviranoOd");
		String rezerviranoDo = req.getParameter("rezerviranoDo");
		boolean parking = false;
		boolean internet = false;
		boolean satelitskaTV = false;
		int id = 0;
		
	
		if (rezerviranoOd.equals("") || rezerviranoDo.equals("")) {
			req.setAttribute("error", "Nije izabran datum!");
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/promjenaRezervacije.jsp").forward(req, resp);
			return;
		}
		try {
			parking = getBoolean(req.getParameter("parking"));
			internet = getBoolean(req.getParameter("internet"));
			satelitskaTV = getBoolean(req.getParameter("satelitskaTV"));
			id = Integer.parseInt(req.getParameter("izabranApartmanID"));
		
		} catch (Exception ex) {
			req.setAttribute("error", "Neispravni parametri!");
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/promjenaRezervacije.jsp").forward(req, resp);
			return;
		}
		Apartman apartman = DAOProvider.getDAO().getApartmanFor(id);
		
		if (!provjeriDostupnost(apartman, new Date(rezerviranoOd), new Date(rezerviranoDo))) {
			req.setAttribute("error", "Neispravni parametri!");
			try {
				req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/promjenaRezervacije.jsp").forward(req, resp);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
		rezervacija.setApartman(apartman);
		rezervacija.setInternet(internet);
		rezervacija.setParking(parking);
		rezervacija.setRezerviranoDo(new Date(rezerviranoOd));
		rezervacija.setRezerviranoOd(new Date(rezerviranoDo));
		rezervacija.setSatelitskaTV(satelitskaTV);
	}


	private boolean provjeriDostupnost(Apartman apartman, Date rezerviranoDo, Date rezerviranoOd) {
		List<Date> list = RezervacijaController.getList(apartman.getRezervacije());
		
		LocalDate start = rezerviranoOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = rezerviranoDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			   if (!list.contains(date)) {
				   return false;
			   }
		}
		return true;
		
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
