package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Fotografija;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.viewModels.VlasnikModelView;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vlasnik/*")
public class VlasnikController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	/*	VlasnikModelView.deleteObjekt(2);
		Objekt obj = new Objekt();
		obj.setNazivObjekt("objekt xxx");
		VlasnikModelView.changeObjekt(obj, 3); */
		
		String info = req.getPathInfo();
		List<Objekt> objekti = DAOProvider.getDAO().getAllObjekt();
		req.setAttribute("objekti", objekti);
		
		List<Korisnik> administratori = VlasnikModelView.getAdministrators();
		req.setAttribute("administratori", administratori);
		
		if (info != null) {
			String elements[] = info.substring(1).split("/");
			// ako je url /vlasnik/objekt/:id
			if (elements[0].equals("objekt")) {
				try {
					Objekt objekt = DAOProvider.getDAO().getObjektFor(Integer.parseInt(elements[1]));
					req.setAttribute("objekt", objekt);
				} catch (Exception e) {
					throw new RuntimeException("Invalid url!");
				}
				
			// ako je url /vlasnik/opis/:id
			} else if (elements[0].equals("opis")) {
				try {
					OpisApartmana opis = DAOProvider.getDAO().getOpisApartmanaFor(Integer.parseInt(elements[1]));
					req.setAttribute("opis", opis);
				} catch (Exception e) {
					throw new RuntimeException("Invalid url!");
				}
			} else if (elements[0].equals("apartman")) {
				try {
					Apartman apartman = DAOProvider.getDAO().getApartmanFor(Integer.parseInt(elements[1]));
					req.setAttribute("apartman", apartman);
				} catch (Exception e) {
					throw new RuntimeException("Invalid url!");
				}
			}
		}
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/vlasnik.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if (method.equals("dodajObjekt")) {
			dodajObjekt(req, resp);
		} else if (method.equals("dodajSmjestajnuJedinicu")) {
			dodajSmjestajnuJedinicu(req, resp);
		} else if (method.equals("dodajOpis")) {
			dodajOpis(req, resp);
		} else if (method.equals("dodajAdministratora")) {
			dodajAdministratora(req, resp);
		} else if (method.equals("obrisiObjekt")) {
			obrisiObjekt(req, resp);
		} else if (method.equals("obrisiOpisApartmana")) {
			obrisiOpisApartmana(req, resp);
		} else if (method.equals("")) {
			int apartmanId = Integer.parseInt("apartmanId");
			obrisiSmjestajnuJedinicu(req, resp, apartmanId);
		} else if (method.equals("obrisiAdministratora")) {
			obrisiAdministratora(req, resp);
		} else if (method.equals("promjeniObjekt")) {
			promjeniObjekt(req, resp);
		} else if (method.equals("promjeniOpisApartmana")) {
			promjeniOpisApartmana(req, resp);
		} else if (method.equals("promjeniSmjestajnuJedinicu")) {
			promjeniSmjestajnuJedinicu(req, resp);
		}
	}
	
	private void dodajObjekt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Objekt objekt = new Objekt();
		setObjekt(req, resp, objekt);	
		DAOProvider.getDAO().putObjekt(objekt);	
	}

	private void dodajSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Apartman apartman = new Apartman();
		setApartman(req, resp, apartman);
		DAOProvider.getDAO().putApartman(apartman);	
			
		
	}

	private void dodajOpis(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		OpisApartmana opisApartmana = new OpisApartmana();
		setOpisApartmana(req, resp, opisApartmana);
		DAOProvider.getDAO().putOpisApartmana(opisApartmana);
		
		String[] fotografije = (String[]) req.getAttribute("fotografije");
		for (String foto : fotografije) {
			if (foto != null) {
				Fotografija f = new Fotografija();
				f.setFotoDatoteka(foto);
				f.setOpisApartmana(opisApartmana);
				opisApartmana.getFotografije().add(f);
				DAOProvider.getDAO().putFotografija(f);
			}
		}			
	}

	private void obrisiObjekt(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int objektId = Integer.parseInt(req.getParameter("objektId"));
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
			for (Apartman apartman : objekt.getApartmani()) {
				obrisiSmjestajnuJedinicu(req, resp, apartman.getApartmanID());
			}
			VlasnikModelView.deleteObjekt(objektId);
		} catch (NumberFormatException e) {
			
		}
	}
	
	private void obrisiSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp,
			int apartmanId) {
		Apartman apartman = DAOProvider.getDAO().getApartmanFor(apartmanId);
		apartman.getOpisApartmana().getApartmani().remove(apartman);
		apartman.getObjekt().getApartmani().remove(apartman);
		VlasnikModelView.deleteApartman(apartmanId);
	}
	
	private void obrisiOpisApartmana(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int opisApartmanaId = Integer.parseInt(req.getParameter("opisApartmanaId"));
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(opisApartmanaId);
			if (opisApartmana.getApartmani() == null) {
				VlasnikModelView.deleteOpisApartmana(opisApartmanaId);
			} else {
				req.setAttribute("error", "Ne mogu'e obrisat opis koji se koristi!");
				resp.sendRedirect("vlasnik/" + req.getPathInfo());
			}
		} catch (NumberFormatException e) {
			error(req, resp);
		}
	}
	
	private void promjeniObjekt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int objektId = Integer.parseInt(req.getParameter("objektId"));
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
			setObjekt(req, resp, objekt);
			VlasnikModelView.changeObjekt(objekt, objektId);
		} catch (NumberFormatException e) {
			error(req, resp);
		}
	}
	
	private void promjeniSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("apartmanId"));
			Apartman apartman = DAOProvider.getDAO().getApartmanFor(id);
			setApartman(req, resp, apartman);
			VlasnikModelView.changeApartman(apartman, id);
		} catch (NumberFormatException e) {
			error(req, resp);
		}
	}
	
	private void promjeniOpisApartmana(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("opisId"));
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(id);
			setOpisApartmana(req, resp, opisApartmana);
			
			String[] fotografije = (String[]) req.getAttribute("fotografije");
			for (String foto : fotografije) {
				if (foto != null) {
					Fotografija f = new Fotografija();
					f.setFotoDatoteka(foto);
					f.setOpisApartmana(opisApartmana);
					if (!opisApartmana.getFotografije().contains(f)) {
						opisApartmana.getFotografije().add(f);
						DAOProvider.getDAO().putFotografija(f);
					}
					
				}
			}
			VlasnikModelView.changeOpisApartmana(opisApartmana, id);
			
		} catch (NumberFormatException e) {
			error(req, resp);
		}
		
	}
	
	private void setOpisApartmana(HttpServletRequest req,
			HttpServletResponse resp, OpisApartmana opisApartmana) throws IOException {
		
		try {
			String naslov = req.getParameter("naslov");
			String pogled = req.getParameter("pogled");
			Short kat = Short.parseShort(req.getParameter("kat"));
			Short minBroj = Short.parseShort(req.getParameter("minBroj"));
			Short maxBroj = Short.parseShort(req.getParameter("maxBroj"));
			String opis = req.getParameter("opis");
			
			if (checkNull(naslov) || checkNull(pogled)) {
				error(req, resp);
			}
			
			opisApartmana.setKat(kat);
			opisApartmana.setMaxBroj(maxBroj);
			opisApartmana.setMinBroj(minBroj);
			opisApartmana.setNaslov(naslov);
			opisApartmana.setPogled(pogled);
			opisApartmana.setOpis(opis);
			
		} catch (NumberFormatException e) {
			error(req, resp);
		}
		
	}
	
	private void setObjekt(HttpServletRequest req, HttpServletResponse resp,
			Objekt objekt) throws IOException {
		
		String nazivObjekta = req.getParameter("nazivObjekta");
		String fotografija = req.getParameter("fotografija");
		
		if (checkNull(nazivObjekta)) {
			error(req, resp);
		}
		
		objekt.setNazivObjekt(nazivObjekta);
		if (fotografija != null && !fotografija.equals("")) {
			objekt.setFotografija(fotografija);
		}
	}

	private void setApartman(HttpServletRequest req, HttpServletResponse resp, Apartman apartman) throws IOException {
		try {
			String nazivApartmana = req.getParameter("nazivApartmana");
			Integer objektId = Integer.parseInt(req.getParameter("objektId"));
			Integer opisId = Integer.parseInt(req.getParameter("opisId"));
			
			if (checkNull(nazivApartmana) || objektId != null || opisId != null) {
				error(req, resp);
			}
			
			apartman.setNazivApartman(nazivApartmana);
			
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(opisId);
			opisApartmana.getApartmani().add(apartman);
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
			objekt.getApartmani().add(apartman);
			
			apartman.setObjekt(objekt);
			apartman.setOpisApartmana(opisApartmana);
		} catch (NumberFormatException e) {
			error(req, resp);
		}
		
	}
	
	private void dodajAdministratora(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int korisnikId = Integer.parseInt(req.getParameter("korisnikId"));
			Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikId);
			korisnik.setUloga(2); // 1 za vlasnika, 2 administrator, 3 prijevljeni korisnik
		} catch (NumberFormatException e) {}
	}
	
	private void obrisiAdministratora(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int korisnikId = Integer.parseInt(req.getParameter("korisnikId"));
			Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikId);
			korisnik.setUloga(3);
		} catch (NumberFormatException e) {
			error(req, resp);
		}
	}
	
	private boolean checkNull(String name) {
		if (name == null || name.equals("")) {
			return true;
		}
		return false;
	}
	
	

	private void error(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setAttribute("error", "Neispravni parametri!");
		resp.sendRedirect("vlasnik/" + req.getPathInfo());
		
	}

}
