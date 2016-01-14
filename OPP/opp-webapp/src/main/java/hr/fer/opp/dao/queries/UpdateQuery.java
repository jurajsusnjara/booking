package hr.fer.opp.dao.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.queries.AbstractQuery.Comparison;

public class UpdateQuery extends AbstractQuery {
	ArrayList<Pair> assignments = new ArrayList<Pair>();

	public UpdateQuery(String entityName) {
		super(entityName);
	}

	public UpdateQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Condition(idColumnName, idValue, Comparison.EQ));
	}

	public UpdateQuery addAssignment(String columnName, Object columnValue) {
		assignments.add(new Pair(columnName, columnValue));
		return this;
	}
	
	public UpdateQuery addCondition(Comparison comparison,String columnName, Object columnValue) {
		return (UpdateQuery) super.addCondition(comparison, columnName, columnValue);
	}

	public UpdateQuery addEqualityCondition(String columnName, Object columnValue) {
		return (UpdateQuery) super.addEqualityCondition(columnName, columnValue);
	}

	public UpdateQuery execute() {
		if (assignments.size() == 0)
			throw new IllegalStateException("No assignments specified.");
		String queryString = createQueryString();
		Query q = JPAEMProvider.getEntityManager().createQuery(queryString);
		setParameters(q, "set", assignments);
		setConditionParameters(q);
		q.executeUpdate();
		return this;
	}

	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(entityName).append(" x");
		appendClause(sb, "set", assignments);
		if (conditions.size() > 0)
			appendConditionClause(sb);
		return sb.toString();
	}
}
