package fr.projet3.oc;

public abstract class Jeu {

	public abstract String joue(String s1, String s2);

	public abstract String donnerIndice(String s1, String s2);

	public abstract boolean finDePartie(String s);
	
	private boolean actif = false;
	
	private String Mode;

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
	
}
