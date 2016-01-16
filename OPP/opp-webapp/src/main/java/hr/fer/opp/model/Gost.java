package hr.fer.opp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gost")
public class Gost {

	private Integer gostID;
	private short godina0_1;
	private short godina2_7;
	private short godina8_14;
	private short odrasli;
	private List<Rezervacija> rezervacije;
	
	@Id
	@GeneratedValue
	public Integer getGostID() {
		return gostID;
	}
	
	public void setGostID(Integer gostiID) {
		this.gostID = gostiID;
	}
	
	@Column
	public short getGodina0_1() {
		return godina0_1;
	}
	
	public void setGodina0_1(short godina0_1) {
		this.godina0_1 = godina0_1;
	}
	
	@Column
	public short getGodina2_7() {
		return godina2_7;
	}
	
	public void setGodina2_7(short godina2_7) {
		this.godina2_7 = godina2_7;
	}
	
	@Column
	public short getGodina8_14() {
		return godina8_14;
	}
	
	public void setGodina8_14(short godina8_14) {
		this.godina8_14 = godina8_14;
	}
	
	@Column
	public short getOdrasli() {
		return odrasli;
	}
	
	public void setOdrasli(short odrasli) {
		this.odrasli = odrasli;
	}
	
	@OneToMany(mappedBy = "gost", cascade = CascadeType.ALL)
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + godina0_1;
		result = prime * result + godina2_7;
		result = prime * result + godina8_14;
		result = prime * result + ((gostID == null) ? 0 : gostID.hashCode());
		result = prime * result + odrasli;
		result = prime * result + ((rezervacije == null) ? 0 : rezervacije.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gost other = (Gost) obj;
		if (godina0_1 != other.godina0_1)
			return false;
		if (godina2_7 != other.godina2_7)
			return false;
		if (godina8_14 != other.godina8_14)
			return false;
		if (gostID == null) {
			if (other.gostID != null)
				return false;
		} else if (!gostID.equals(other.gostID))
			return false;
		if (odrasli != other.odrasli)
			return false;
		if (rezervacije == null) {
			if (other.rezervacije != null)
				return false;
		} else if (!rezervacije.equals(other.rezervacije))
			return false;
		return true;
	}
}
