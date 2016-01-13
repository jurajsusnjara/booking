package hr.fer.opp.controllers;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.jpa.queries.UpdateQuery;

@WebServlet("/proba")
public class ProbaController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Proba1");
		Query q = JPAEMProvider.getEntityManager().createQuery("select * from Apartman");
		//q.executeUpdate();
		
		/*System.out.println("Proba2");
		UpdateQuery uq = new UpdateQuery("Apartman", "apartmanID", 21);
		uq.addAssignment("nazivApartman", "Apartman1");
		uq.addAssignment("objektID", 45);
		uq.addAssignment("opisID", 22);
		uq.addEqualityCondition("nazivApartman", "apartman1");
		//System.out.println(uq.toString());		
		uq.execute();*/
		
		System.out.println("Proba");
	}
}
