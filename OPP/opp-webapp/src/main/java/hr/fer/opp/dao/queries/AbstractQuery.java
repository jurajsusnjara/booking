package hr.fer.opp.dao.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import hr.fer.opp.dao.queries.AbstractQuery.Pair;

public abstract class AbstractQuery {
	String entityName;
	ArrayList<Condition> conditions = new ArrayList<Condition>();

	public AbstractQuery(String entityName) {
		this.entityName = entityName;
	}
	
	public enum Comparison {
		EQ("="), NEQ("!="), GT(">"), GTE(">="), LT("<"), LTE("<=");

		private String value;

		Comparison(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	protected class Pair {
		public final String key;
		public final Object value;

		public Pair(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	protected class Condition extends Pair{
		public final Comparison comparison;

		public Condition(String key, Object value, Comparison comparison) {
			super(key, value);
			this.comparison = comparison;
		}
	}

	protected void appendConditionClause(StringBuilder sb) {
		String clauseName = "where";
		sb.append("\n   ").append(clauseName);
		sb.append(' ');
		for (Condition p : conditions) {
			sb.append("x.").append(p.key).append(' ');
			sb.append(p.comparison.value);
			sb.append(" :").append(clauseName).append(p.key).append(", ");
		}
		sb.setLength(sb.length() - 2);
	}
	
	protected void appendClause(StringBuilder sb, String clauseName, List<Pair> list) {
		sb.append("\n   ").append(clauseName);
		sb.append(' ');
		for (Pair p : list)
			sb.append("x.").append(p.key).append(" = :").append(clauseName).append(p.key).append(", ");
		sb.setLength(sb.length() - 2);
	}

	protected static void setParameters(Query query, String clauseName, List<Pair> list) {
		for (Pair p : list)
			query.setParameter(clauseName + p.key, p.value);
	}
	
	protected void setConditionParameters(Query query) {
		String clauseName = "where";
		for (Pair c : conditions)
			query.setParameter(clauseName + c.key, c.value);
	}

	public AbstractQuery addCondition(Comparison comparison, String columnName, Object columnValue) {
		conditions.add(new Condition(columnName, columnValue, comparison));
		return this;
	}
	
	public AbstractQuery addEqualityCondition(String columnName, Object columnValue) {
		conditions.add(new Condition(columnName, columnValue, Comparison.EQ));
		return this;
	}

	protected abstract String createQueryString();

	public String toString() {
		return createQueryString();
	}

}
