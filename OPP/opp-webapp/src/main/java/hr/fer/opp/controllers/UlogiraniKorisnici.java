package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Korisnik;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UlogiraniKorisnici extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik == null || !(korisnik.getUloga() == 2 || korisnik.getUloga() == 3)) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		for (Korisnik k : DAOProvider.getDAO().getAllKorisnik()) {
			
		};
	}
	
	
}
