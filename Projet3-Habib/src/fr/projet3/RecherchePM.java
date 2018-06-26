package fr.projet3;

public class RecherchePM {

	
	String title;
	private  String Mode;
	boolean actif = false;

	public RecherchePM() {
 
		this.actif = true;
	}

	public  String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	
}
