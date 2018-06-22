package fr.projet3;

import java.io.*;

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

	
	public void luncher() {
		@SuppressWarnings("unused")
		ModeDeJeu mdj = new ModeDeJeu(getMode(), new OutputStreamWriter(System.out));
	}
	
	
}
