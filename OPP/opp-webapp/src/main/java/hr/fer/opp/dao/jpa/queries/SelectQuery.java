package hr.fer.opp.dao.jpa.queries;

import java.util.List;
import javax.persistence.Query;
import hr.fer.opp.dao.jpa.JPAEMProvider;

public class SelectQuery extends AbstractQuery {

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
		for (Pair p : conditions)
			q.setParameter('c' + p.key, p.value);
		q.executeUpdate();
		resultList = q.getResultList();
	}

	public List getResultList() {
		return resultList;
	}

	public Object getResult() {
		return resultList.get(0);
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(entityName);
		sb.append(" where");
		addList(sb, conditions, 'c');
		sb.append(';');
		return sb.toString();
	}
}
