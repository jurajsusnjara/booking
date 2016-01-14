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

	public DeleteQuery addEqualityCondition(String columnName, Object columnValue) {
		return (DeleteQuery) super.addEqualityCondition(columnName, columnValue);
	}
	
	public void execute() {
		Query q = JPAEMProvider.getEntityManager().createQuery(createQueryString());
		setParameters(q, "where", conditions);
		q.executeUpdate();
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(entityName).append(" x");
		appendClause(sb, "where", conditions);
		return sb.toString();
	}
}
