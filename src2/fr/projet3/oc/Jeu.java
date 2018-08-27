package fr.projet3.oc;

public abstract class Jeu {

	public abstract String joue(String s1, String s2);

	public abstract String donnerIndice(String proposition, String combianison);

	public abstract boolean finDePartie(String s);

	static int nombreEssaiEffectuer = 0;
	
}
