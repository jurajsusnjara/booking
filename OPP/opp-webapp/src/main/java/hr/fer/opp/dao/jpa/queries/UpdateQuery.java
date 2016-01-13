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
		String queryString = createQueryString();
		Query q = JPAEMProvider.getEntityManager().createQuery(queryString);
		setParameters(q, "set", assignments);
		setParameters(q, "where", conditions);
		q.executeUpdate();
	}
	
	protected String createQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(entityName);
		AbstractQuery.addClause(sb, "set", assignments);
		AbstractQuery.addClause(sb, "where", conditions);
		sb.append("\n;");
		return sb.toString();
	}
}
