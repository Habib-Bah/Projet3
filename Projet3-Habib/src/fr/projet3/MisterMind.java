package fr.projet3;

public class MisterMind {

	
	String title;
	private  String Mode;
	boolean actif = false;
	
	public MisterMind() {
		 this.actif = true;
	}

	public  String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

}
