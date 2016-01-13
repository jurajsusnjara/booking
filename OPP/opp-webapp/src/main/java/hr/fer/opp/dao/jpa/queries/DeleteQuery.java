package hr.fer.opp.dao.jpa.queries;

import javax.persistence.Query;

import hr.fer.opp.dao.jpa.JPAEMProvider;

public class DeleteQuery extends AbstractQuery {

	public DeleteQuery(String entityName) {
		super(entityName);
	}

	public DeleteQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Pair(idColumnName, idValue));
	}

	public void execute() {
		Query q = JPAEMProvider.getEntityManager().createQuery(createQueryString());
		for (Pair p : conditions)
			q.setParameter('c' + p.key, p.value);
		q.executeUpdate();
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(entityName);
		sb.append(" where");
		AbstractQuery.addList(sb, conditions, 'c');
		sb.append(';');
		return sb.toString();
	}
}
