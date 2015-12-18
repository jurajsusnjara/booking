package hr.fer.opp.init;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import hr.fer.opp.dao.jpa.JPAEMFProvider;

@WebListener
public class Initialization implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		JPAEMFProvider.setEmf(null);
		EntityManagerFactory emf = (EntityManagerFactory) sce
				.getServletContext().getAttribute("my.application.emf");
		if (emf != null) {
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//TODO IME BAZE
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mihajlodb");
		sce.getServletContext().setAttribute("my.application.emf", emf);
		JPAEMFProvider.setEmf(emf);
	}

}
