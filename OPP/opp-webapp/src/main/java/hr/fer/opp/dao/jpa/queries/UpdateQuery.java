package hr.fer.opp.dao.jpa.queries;

import java.util.ArrayList;
import javax.persistence.Query;
import hr.fer.opp.dao.jpa.JPAEMProvider;


public class UpdateQuery extends AbstractQuery {
	ArrayList<Pair> assignments = new ArrayList<Pair>();

	public UpdateQuery(String entityName) {
		super(entityName);
	}

	public UpdateQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Pair(idColumnName, idValue));
	}

	public void addAssignment(String columnName, Object columnValue) {
		assignments.add(new Pair(columnName, columnValue));
	}

	public void execute() {
		Query q = JPAEMProvider.getEntityManager().createQuery(createQueryString());
		for (Pair p : assignments)
			q.setParameter('a' + p.key, p.value);
		for (Pair p : conditions)
			q.setParameter('c' + p.key, p.value);
		q.executeUpdate();
	}
	
	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(entityName);
		sb.append(" set ");
		addList(sb, assignments, 'a');
		sb.append(" where");
		addList(sb, conditions, 'c');
		sb.append(';');
		return sb.toString();
	}
}
