package fr.projet3;

import java.io.*;

import javax.swing.JOptionPane;

public class Test {

	public static void main(String[] args) throws IOException {

		String mode = JOptionPane
				.showInputDialog("Tapez 1 pour le jouer à Recherche +/-; \n Tapez 2 pour jouer à MisterMind \n ");
		
		switch (mode) {
		case "1":
			String message = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			if (message.equals("1")) {
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : challenger");
				ModeDeJeu modedj = new ModeDeJeu(recherche.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : challenger");
				
			} else if (message.equals("2")) {
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : défenseur");
				ModeDeJeu modedj = new ModeDeJeu("Recherche +/- : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : défenseur");
				modedj.toFind();
			} else {
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : duel");
				ModeDeJeu modedj = new ModeDeJeu("Recherche +/- : duel", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : duel");
				//recherche.toFind();
			}
			break;
		
		case "2" :
			String mes = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			if (mes.equals("1")) {
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("MisterMind : challenger");
				ModeDeJeu modedj = new ModeDeJeu("MasterMind : challenger", new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind  : challenger");
				//mastermind.toFind();
			} else if (mes.equals("2")) {
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("MisterMind : défenseur");
				ModeDeJeu modedj = new ModeDeJeu("MasterMind : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind : défenseur");
				//mastermind.toFind();
			} else {
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("duel");
				ModeDeJeu modedj = new ModeDeJeu(mastermind.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind : duel");
				modedj.setMode("duel");
				//mastermind.toFind();
			}
			default :
				break;
		}
	}

	

}
