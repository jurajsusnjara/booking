package hr.fer.opp.controllers;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.jpa.queries.DeleteQuery;
import hr.fer.opp.dao.jpa.queries.SelectQuery;
import hr.fer.opp.dao.jpa.queries.UpdateQuery;
import hr.fer.opp.model.Objekt;

@WebServlet("/proba")
public class ProbaController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Proba1");
		
		/*Query q = JPAEMProvider.getEntityManager().createQuery("delete from Objekt x where x.id = :id");
		int i = 5;
		Object o=i;
		q.setParameter("id", o);
		q.executeUpdate();*/
		
		SelectQuery dQuery= new SelectQuery("Objekt", "objektID", 3);
		dQuery.execute();
		
		Objekt objekt = (Objekt) dQuery.getResult();
		objekt = objekt;
		
		/*System.out.println("Proba2");
		UpdateQuery uq = new UpdateQuery("Apartman", "apartmanID", 21);
		uq.addAssignment("nazivApartman", "Apartman1");
		uq.addAssignment("objektID", 45);
		uq.addAssignment("opisID", 22);
		uq.addEqualityCondition("nazivApartman", "apartman1");
		
		System.out.println(uq.toString());
				
		uq.execute();*/
		
		System.out.println("/Proba1");
	}
}
