package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Korisnik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ulogirani")
public class UlogiraniKorisnici extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnik == null || !(korisnik.getUloga() == 2 || korisnik.getUloga() == 3)) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		List<Korisnik> logirani = new ArrayList<Korisnik>();
		List<Korisnik> svi = DAOProvider.getDAO().getAllKorisnik();
		for (Korisnik k : svi) {
			if (k.isLogiran() && k.getUloga() != 3) {
				logirani.add(k);
			}
		};
		
		req.setAttribute("logirani", logirani);
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/logiraniKorisnici.jsp").forward(req, resp);
	}
	
	
}
