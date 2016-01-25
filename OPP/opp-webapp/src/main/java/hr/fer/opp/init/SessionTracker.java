package hr.fer.opp.init;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionTracker implements HttpSessionListener {
	
	private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    public static HttpSession find(String sessionId) {
        return sessions.get(sessionId);
    }
    
//    svaki put kada netko otvori stranicu napravi se session i doda se u mapu
//    iz httpsession-a se mogu izvuc atributi za pojedini session, ako atribut korisnik postoji onda
//    je u tom sessionu netko ulogiran, ako nije onda ništa
//    za pronalazak svih ulogiranih korisnika probat iz nekog kontrolera iterirat po mapi sessions,
//    dohvacat sve sessione i kod svakog provjerit postoji li atribut korisnik, ako postoji onda
//    treba iz njega izvuc podatke

}
