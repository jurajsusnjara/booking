package hr.fer.opp.model;

public class Datum {

	private int dan;
	private boolean zauzet;

	public Datum(int dan, boolean zauzet) {
		this.dan = dan;
		this.zauzet = zauzet;
	}

	public void setZauzet(boolean zauzet) {
		this.zauzet = zauzet;
	}

	public int getDan() {
		return dan;
	}

	public boolean getZauzet() {
		return zauzet;
	}

}
