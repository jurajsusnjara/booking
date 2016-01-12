package hr.fer.opp.viewModels;

import java.util.List;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToStringTransformer;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.Rezervacija;
import hr.fer.opp.viewModels.queries.*;

public class test {
	
	public static void main() {
		DeleteQuery dQuery=new DeleteQuery("OpisApartmana", "opisID", 2);
		
		UpdateQuery uq = new UpdateQuery("OpisApartmana", "apartmanID", 125);
		uq.addAssignment("nazivApartman", "naziv");
		uq.addAssignment("objektID", 15);
		uq.addAssignment("opisID", 2 );
		
	}
}
