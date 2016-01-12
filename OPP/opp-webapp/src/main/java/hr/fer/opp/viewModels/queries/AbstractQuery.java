package hr.fer.opp.viewModels.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToStringTransformer;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.viewModels.queries.AbstractQuery.Pair;

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
		for (Pair p : list)
			sb.append(p.key).append(" = :").append(parameterChar).append(":c"+p.key).append(", ");
		sb.setLength(sb.length() - 2);
	}
	
	public void addCondition(String columnName, Object columnValue) {
		conditions.add(new Pair(columnName, columnValue));
	}
	public abstract void execute();

	public abstract String toString();
	
}
