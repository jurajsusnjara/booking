package hr.fer.opp.dao.jpa.queries;

import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.dao.jpa.queries.AbstractQuery.Pair;

import javax.persistence.Query;

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

	protected static void addClause(StringBuilder sb, String clauseName, List<Pair> list) {
		sb.append("\n    ").append(clauseName);
		sb.append(' ');
		for (Pair p : list)
			sb.append(p.key).append(" = :").append(clauseName + p.key).append(", ");
		sb.setLength(sb.length() - 2);
	}

	protected static void setParameters(Query query, String clauseName, List<Pair> list) {
		for (Pair p : list)
			query.setParameter(clauseName + p.key, p.value);
	}

	public void addEqualityCondition(String columnName, Object columnValue) {
		conditions.add(new Pair(columnName, columnValue));
	}

	public abstract void execute() throws Exception;

	protected abstract String createQueryString();

	public String toString() {
		return createQueryString();
	}

}
