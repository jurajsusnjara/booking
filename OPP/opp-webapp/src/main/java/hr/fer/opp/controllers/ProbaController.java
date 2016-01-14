package hr.fer.opp.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.queries.DeleteQuery;
import hr.fer.opp.dao.queries.SelectQuery;
import hr.fer.opp.dao.queries.UpdateQuery;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.AdminViewModel;

@WebServlet("/proba")
public class ProbaController extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AdminViewModel.getRezervacijaFor("asdad", 11);

		System.out.println("/Proba1");
	}
}
