package hr.fer.opp.controllers;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Fotografija;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.VlasnikViewModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
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
	private boolean error = false;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	/*	VlasnikModelView.deleteObjekt(2);
		Objekt obj = new Objekt();
		obj.setNazivObjekt("objekt xxx");
		VlasnikModelView.changeObjekt(obj, 3); */
		Korisnik korisnikPom = (Korisnik) req.getSession().getAttribute("korisnik");
		if (korisnikPom == null || korisnikPom.getUloga() != 3) {
			resp.sendRedirect("/opp-webapp/");
			return;
		}
		
		
		String info = req.getPathInfo();
		
		setAttributes(req);
		
		if (info != null) {
			String elements[] = info.substring(1).split("/");
			// ako je url /vlasnik/objekt/:id
			
			if (elements.length == 2 && elements[1].equals("uredi")) {
				if (req.getParameter("id") == null) {
					throw new RuntimeException("Invalid url!");
				}
				int id;
				try {
					id = Integer.parseInt(req.getParameter("id"));
				} catch (Exception e) {
					throw new RuntimeException("Invalid url!");
				}
				
				
				if (elements[0].equals("objekt")) {
					try {
						Objekt objekt = DAOProvider.getDAO().getObjektFor(id);
						req.setAttribute("o", objekt);
						req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/uredi/objekt.jsp").forward(req, resp);
						return;
					} catch (Exception e) {
						throw new RuntimeException("Invalid url!");
					}
					
				// ako je url /vlasnik/opis/:id
				} else if (elements[0].equals("opis")) {
					try {
						OpisApartmana opis = DAOProvider.getDAO().getOpisApartmanaFor(id);
						req.setAttribute("opis", opis);
						req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/uredi/opis.jsp").forward(req, resp);
						return;
					} catch (Exception e) {
						throw new RuntimeException("Invalid url!");
					}
				} else if (elements[0].equals("apartman")) {
					try {
						Apartman apartman = DAOProvider.getDAO().getApartmanFor(id);
						req.setAttribute("apartman", apartman);
						req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/uredi/apartman.jsp").forward(req, resp);
						return;
					} catch (Exception e) {
						throw new RuntimeException("Invalid url!");
					}
				}
			}
			
		}
		req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/vlasnik.jsp").forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
	/*	PrintWriter out = resp.getWriter();  
		resp.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("\"$(\"#dodajObjekt\").show(); $(\"#dodajApartman\").hide();\""); 
		out.println("</script>"); */
		
		if (method.equals("dodajObjekt")) {
			dodajObjekt(req, resp);
		} else if (method.equals("dodajApartman")) {
			dodajSmjestajnuJedinicu(req, resp);
			req.setAttribute("pogled", "apartman");
		} else if (method.equals("dodajOpis")) {
			dodajOpis(req, resp);
		} else if (method.equals("dodajAdmina")) {
			dodajAdministratora(req, resp);
		} else if (method.equals("obrisiObjekt")) {
			obrisiObjekt(req, resp);
		} else if (method.equals("obrisiOpis")) {
			obrisiOpisApartmana(req, resp);
		} else if (method.equals("obrisiApartman")) {
			int apartmanId = Integer.parseInt(req.getParameter("apartmanID"));
			obrisiSmjestajnuJedinicu(req, resp, apartmanId);
		} else if (method.equals("dodajAdmina")) {
			obrisiAdministratora(req, resp);
		} else if (method.equals("obrisiAdmina")) {
			obrisiAdministratora(req, resp);	
		} else if (method.equals("promjeniObjekt")) {
			promjeniObjekt(req, resp);
		} else if (method.equals("promjeniOpis")) {
			promjeniOpisApartmana(req, resp);
		} else if (method.equals("promjeniApartman")) {
			promjeniSmjestajnuJedinicu(req, resp);
		} else if (method.equals("dodajFotografiju")) {
			dodajFotografiju(req, resp);
		} else if (method.equals("obrisiFotografiju")) {
			obrisiFotografiju(req, resp);
		}
		
		if (!error) {
			setAttributes(req);
			
			resp.sendRedirect("/opp-webapp/vlasnik");
			
		}
		
	//	req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/vlasnik.jsp").forward(req, resp);
	}
	
	private void obrisiFotografiju(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("fotoID"));
			VlasnikViewModel.deleteFotografija(id);
		} catch (Exception e) {
			
		}
		
	}



	private void setAttributes(HttpServletRequest req) {
		List<Objekt> objekti = DAOProvider.getDAO().getAllObjekt();
		req.setAttribute("objekti", objekti);
		
		List<Apartman> apartmani = DAOProvider.getDAO().getAllApartman();
		List<OpisApartmana> opisi = DAOProvider.getDAO().getAllOpisApartmana();
		
		
		
		List<Korisnik> administratori = VlasnikViewModel.getAdministrators();
		List<Korisnik> sviKorisnici = DAOProvider.getDAO().getAllKorisnik();
		List<Korisnik> korisnici = new ArrayList<Korisnik>(sviKorisnici);
		for (Korisnik k : sviKorisnici) {
			if (k.getUloga() != 1) {
				korisnici.remove(k);
			}
		}
		req.setAttribute("apartmani", apartmani);
		req.setAttribute("opisi", opisi);
		req.setAttribute("administratori", administratori);
		req.setAttribute("brojac", administratori.size());
		req.setAttribute("korisnici", korisnici);
		req.setAttribute("slike", DAOProvider.getDAO().getAllFotografija());
		
	}



	private void dodajObjekt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Objekt objekt = new Objekt();
		if (!setObjekt(req, resp, objekt)) {
			return;
		}
		DAOProvider.getDAO().putObjekt(objekt);	
	}

	private void dodajSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Apartman apartman = new Apartman();
		if (!setApartman(req, resp, apartman)) return;
		DAOProvider.getDAO().putApartman(apartman);	
			
		
	}

	private void dodajOpis(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		OpisApartmana opisApartmana = new OpisApartmana();
		if (!setOpisApartmana(req, resp, opisApartmana)) return;
		DAOProvider.getDAO().putOpisApartmana(opisApartmana);	
	}

	private void dodajFotografiju(HttpServletRequest req, HttpServletResponse resp) {
		Fotografija f = new Fotografija();
		String fotoDatoteka = req.getParameter("fotoDatoteka");
		Integer id = Integer.parseInt(req.getParameter("opisID"));
		
		f.setFotoDatoteka(fotoDatoteka);
		f.setOpisApartmana(DAOProvider.getDAO().getOpisApartmanaFor(id));
		DAOProvider.getDAO().putFotografija(f);
		
	}
	
	private void obrisiObjekt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int objektId = Integer.parseInt(req.getParameter("objektID"));
			System.out.println(objektId);
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
			for (Apartman apartman : objekt.getApartmani()) {
				VlasnikViewModel.deleteRezervacije(apartman);
				VlasnikViewModel.deleteApartman(apartman.getApartmanID());
			//	obrisiSmjestajnuJedinicu(req, resp, apartman.getApartmanID());
			}
			VlasnikViewModel.deleteObjekt(objektId);
		} catch (NumberFormatException e) {
			error(req, resp);
			return;
		}
	}
	
	private void obrisiSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp,
			int apartmanId) {
		Apartman apartman = DAOProvider.getDAO().getApartmanFor(apartmanId);
	//	apartman.getOpisApartmana().getApartmani().remove(apartman);
	//	apartman.getObjekt().getApartmani().remove(apartman);
		VlasnikViewModel.deleteRezervacije(apartman);
		VlasnikViewModel.deleteApartman(apartmanId);
	}
	
	private void obrisiOpisApartmana(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int opisApartmanaId = Integer.parseInt(req.getParameter("opisID"));
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(opisApartmanaId);
			if (opisApartmana.getApartmani().isEmpty()) {
				VlasnikViewModel.deleteFotografije(opisApartmana);
				VlasnikViewModel.deleteOpisApartmana(opisApartmanaId);
			} else {
				req.setAttribute("error", "Ne mogu'e obrisat opis koji se koristi!");
				resp.sendRedirect("/opp-webapp/vlasnik/");
				error = true;
			}
		} catch (NumberFormatException e) {
			error(req, resp);
			return;
		}
	}
	
	private void promjeniObjekt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int objektId = Integer.parseInt(req.getParameter("id"));
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
			if (!setObjekt(req, resp, objekt)) return;
			// TODO
			//VlasnikViewModel.changeObjekt(objekt);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid url!");
			
		}
	}
	
	private void promjeniSmjestajnuJedinicu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			String g = req.getParameter("id");
			int id = Integer.parseInt(req.getParameter("id"));
			Apartman apartman = DAOProvider.getDAO().getApartmanFor(id);
			if (!setApartman(req, resp, apartman)) return;
			// TODO
			//VlasnikViewModel.changeApartman(apartman);
		} catch (NumberFormatException e) {
			error(req, resp);
			return;
		}
	}
	
	private void promjeniOpisApartmana(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(id);
			if (!setOpisApartmana(req, resp, opisApartmana)) return;
			// TODO
			//VlasnikViewModel.changeOpisApartmana(opisApartmana);
			
		} catch (NumberFormatException e) {
			error(req, resp);
			return;
		}
		
	}
	
	private boolean setOpisApartmana(HttpServletRequest req,
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
				return false;
			}
			
			opisApartmana.setKat(kat);
			opisApartmana.setMaxBroj(maxBroj);
			opisApartmana.setMinBroj(minBroj);
			opisApartmana.setNaslov(naslov);
			opisApartmana.setPogled(pogled);
			opisApartmana.setOpis(opis);
			
		} catch (NumberFormatException e) {
			error(req, resp);
			return false;
		}
		return true;
	}
	
	private boolean setObjekt(HttpServletRequest req, HttpServletResponse resp,
			Objekt objekt) throws IOException {
		
		String nazivObjekta = req.getParameter("nazivObjekt");
		String fotografija = req.getParameter("fotografija");
		
		if (checkNull(nazivObjekta)) {
			error(req, resp);
			return false;
		}
		
		objekt.setNazivObjekt(nazivObjekta);
		if (fotografija != null && !fotografija.equals("")) {
			objekt.setFotografija(fotografija);
		}
		return true;
	}

	private boolean setApartman(HttpServletRequest req, HttpServletResponse resp, Apartman apartman) throws IOException {
		try {
			String nazivApartmana = req.getParameter("nazivApartman");
			Integer objektId = Integer.parseInt(req.getParameter("objektID"));
			Integer opisId = Integer.parseInt(req.getParameter("opisID"));
			
			if (checkNull(nazivApartmana) || objektId == null || opisId == null) {
				error(req, resp);
				return false;
			}
			
			apartman.setNazivApartman(nazivApartmana);
			
			OpisApartmana opisApartmana = DAOProvider.getDAO().getOpisApartmanaFor(opisId);
		//	opisApartmana.getApartmani().add(apartman);
			Objekt objekt = DAOProvider.getDAO().getObjektFor(objektId);
		//	objekt.getApartmani().add(apartman);
			
			apartman.setObjekt(objekt);
			apartman.setOpisApartmana(opisApartmana);
		} catch (NumberFormatException e) {
			error(req, resp);
			return false;
		}
		return true;
		
	}
	
	private void dodajAdministratora(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String korisnikId = req.getParameter("adminID");
			if (korisnikId != null) {
				Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikId);
				korisnik.setUloga(2); // 1 za vlasnika, 2 administrator, 3 prijevljeni korisnik
			}
			
		} catch (NumberFormatException e) {}
	}
	
	private void obrisiAdministratora(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String korisnikId = req.getParameter("adminID");
			Korisnik korisnik = DAOProvider.getDAO().getKorisnikFor(korisnikId);
			korisnik.setUloga(1);
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
		error = true;
		req.setAttribute("error", "Neispravni parametri!");
		try {
			req.getServletContext().getRequestDispatcher("/WEB-INF/JSP/vlasnik.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

}