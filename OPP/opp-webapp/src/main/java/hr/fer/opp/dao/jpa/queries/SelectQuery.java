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
	
	public SelectQuery addEqualityCondition(String columnName, Object columnValue) {
		return (SelectQuery) super.addEqualityCondition(columnName, columnValue);
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		String queryString = createQueryString();
		Query q = JPAEMProvider.getEntityManager().createQuery(queryString);
		setParameters(q, "where", conditions);		
		return q.getResultList();
	}

	public Object getResult() {
		return getResultList().get(0);
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("select x from ").append(entityName).append(" x");
		appendClause(sb, "where", conditions);
		return sb.toString();
	}
}
