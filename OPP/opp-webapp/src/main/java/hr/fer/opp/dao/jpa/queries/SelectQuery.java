package hr.fer.opp.dao.jpa.queries;

import java.util.List;
import java.util.jar.JarException;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.cfg.NotYetImplementedException;

import com.mysql.jdbc.NotImplemented;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import net.sf.ehcache.hibernate.HibernateUtil;

public class SelectQuery extends AbstractQuery {

	@SuppressWarnings("rawtypes")
	private List resultList;

	public SelectQuery(String entityName) {
		super(entityName);
	}

	public SelectQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Pair(idColumnName, idValue));
	}

	public void execute() {
		String queryString = createQueryString();
		Query q = JPAEMProvider.getEntityManager().createQuery(queryString);
		setParameters(q, "where", conditions);
		q.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	public Object getResult() {
		return resultList.get(0);
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("select from ").append(entityName).append(" x");
		appendClause(sb, "where", conditions);
		return sb.toString();
	}
}
