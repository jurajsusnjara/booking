package hr.fer.opp.dao.queries;

import javax.persistence.Query;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.queries.AbstractQuery.Comparison;

public class DeleteQuery extends AbstractQuery {

	public DeleteQuery(String entityName) {
		super(entityName);
	}

	public DeleteQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Condition(idColumnName, idValue, Comparison.EQ));
	}

	public DeleteQuery addCondition(Comparison comparison,String columnName, Object columnValue) {
		return (DeleteQuery) super.addCondition(comparison, columnName, columnValue);
	}
	
	public DeleteQuery addEqualityCondition(String columnName, Object columnValue) {
		return (DeleteQuery) super.addEqualityCondition(columnName, columnValue);
	}

	public void execute() {
		Query q = JPAEMProvider.getEntityManager().createQuery(createQueryString());
		setConditionParameters(q);
		q.executeUpdate();
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(entityName).append(" x");
		if (conditions.size() > 0)
			appendConditionClause(sb);
		return sb.toString();
	}
}
