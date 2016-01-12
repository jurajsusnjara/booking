/*package hr.fer.opp.viewModels.queries;

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



public class SelectQuery extends AbstractQuery {
	
	public SelectQuery(String entityName) {
		super(entityName);
	}

	public SelectQuery(String entityName, String idColumnName, Object idValue) {
		super(entityName);
		conditions.add(new Pair(idColumnName, idValue))
	}

	public void execute() {
		Query q = JPAEMProvider.getEntityManager().createQuery(this.toString());
		for (Pair p : conditions)
			q.setParameter("c"+p.key, p.value);
		q.executeUpdate();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(entityName);
		sb.append("where ");
		addList(sb, conditions, 'c');
		return sb.toString();
	}	
}
*/