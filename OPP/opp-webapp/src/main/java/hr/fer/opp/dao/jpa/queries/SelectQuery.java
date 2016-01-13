package hr.fer.opp.dao.jpa.queries;

import java.util.List;
import javax.persistence.Query;
import hr.fer.opp.dao.jpa.JPAEMProvider;

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
		Query q = JPAEMProvider.getEntityManager().createQuery(createQueryString());
		setParameters(q, "where", conditions);
		q.executeUpdate();
		resultList = q.getResultList();
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
		sb.append("select * from ").append(entityName);
		AbstractQuery.addClause(sb, "where", conditions);
		sb.append("\n;");
		return sb.toString();
	}
}
