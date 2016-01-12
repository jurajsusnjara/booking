package hr.fer.opp.viewModels.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToStringTransformer;
import org.jboss.jandex.Main;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.viewModels.queries.AbstractQuery.Pair;

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
		Query q = JPAEMProvider.getEntityManager().createQuery(this.toString());
		for (Pair p : assignments)
			q.setParameter("a" + p.key, p.value);
		for (Pair p : conditions)
			q.setParameter("c" + p.key, p.value);
		q.executeUpdate();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(entityName);
		sb.append(" set ");
		addList(sb, assignments, 'a');
		sb.append(" where ");
		addList(sb, conditions, 'c');
		return sb.toString();
	}
}
