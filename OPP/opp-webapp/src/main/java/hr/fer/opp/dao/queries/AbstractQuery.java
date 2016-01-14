package hr.fer.opp.dao.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import hr.fer.opp.dao.queries.AbstractQuery.Pair;

public abstract class AbstractQuery {
	String entityName;
	ArrayList<Pair> conditions = new ArrayList<Pair>();

	public AbstractQuery(String entityName) {
		this.entityName = entityName;
	}

	protected class Pair {
		public final String key;
		public final Object value;

		public Pair(String x, Object y) {
			this.key = x;
			this.value = y;
		}
	}

	protected void appendClause(StringBuilder sb, String clauseName, List<Pair> list) {
		sb.append("\n   ").append(clauseName);
		sb.append(' ');
		for (Pair p : list)
			sb.append("x.").append(p.key).append(" = :").append(clauseName).append(p.key).append(", ");
		sb.setLength(sb.length() - 2);
	}

	protected static void setParameters(Query query, String clauseName, List<Pair> list) {
		String parameter;
		for (Pair p : list)
			query.setParameter(clauseName + p.key, p.value);
	}

	public AbstractQuery addEqualityCondition(String columnName, Object columnValue) {
		conditions.add(new Pair(columnName, columnValue));
		return this;
	}

	protected abstract String createQueryString();

	public String toString() {
		return createQueryString();
	}

}
