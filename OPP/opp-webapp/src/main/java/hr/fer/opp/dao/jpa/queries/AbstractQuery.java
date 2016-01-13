package hr.fer.opp.dao.jpa.queries;

import java.util.ArrayList;
import java.util.List;

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
	
	protected static void addList(StringBuilder sb, List<Pair> list, char parameterChar){
		sb.append(' ');
		for (Pair p : list)
			sb.append(p.key).append(" = :").append(parameterChar).append(":c"+p.key).append(", ");
		sb.setLength(sb.length() - 2);
	}
	
	public void addEqualityCondition(String columnName, Object columnValue) {
		conditions.add(new Pair(columnName, columnValue));
	}
	public abstract void execute() throws Exception;
	
	protected abstract String createQueryString();

	public String toString(){
		return createQueryString();
	}

	
}
