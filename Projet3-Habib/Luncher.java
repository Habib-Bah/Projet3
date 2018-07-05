package fr.projet3.oc;

import java.io.*;

import javax.swing.*;

public class Luncher {

	/**
	 * methode run qui lance une partie
	 * 
	 */
	public void run() {

		Logging log = new Logging("Lancement de l'application");

		String mode = JOptionPane
				.showInputDialog("Tapez 1 pour le jouer à Recherche +/-; \n Tapez 2 pour jouer à MisterMind \n ");
		log.ChangeInfoLog("Choix du jeu");

		switch (mode) {
		case "1":
			String message = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			log.ChangeInfoLog("Vous avez choisi le Recherche +/-");
			log.ChangeInfoLog("Choix du mode de jeu");
			if (message.equals("1")) {
				log.ChangeInfoLog("Vous avez choisi le mode challenger");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : challenger");
				FenetreDeJeu modedj = new FenetreDeJeu(recherche.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : challenger");
				modedj.actifMode = false;

			} else if (message.equals("2")) {
				log.ChangeInfoLog("Vous avez choisi le mode défenseur");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : défenseur");
				FenetreDeJeu modedj = new FenetreDeJeu("Recherche +/- : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : défenseur");
				modedj.actifMode = false;
				modedj.toFind();
			} else {
				log.ChangeInfoLog("Vous avez choisi le mode duel");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : duel");
				FenetreDeJeu modedj = new FenetreDeJeu("Recherche +/- : duel", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : duel");
				modedj.actifMode = false;
				modedj.toFind();
			}
			break;

		case "2":
			String mes = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			log.ChangeInfoLog("Vous avez choisi le MisterMind");
			log.ChangeInfoLog("Choix du mode de jeu");
			if (mes.equals("1")) {
				log.ChangeInfoLog("Vous avez choisi le mode challenger");
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("MisterMind : challenger");
				FenetreDeJeu modedj = new FenetreDeJeu("MasterMind : challenger", new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind  : challenger");
				modedj.actifMode = true;
			} else if (mes.equals("2")) {
				log.ChangeInfoLog("Vous avez choisi le mode défenseur");
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("MisterMind : défenseur");
				FenetreDeJeu modedj = new FenetreDeJeu("MasterMind : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind : défenseur");
				modedj.actifMode = true;
				modedj.toFind();
			} else {
				log.ChangeInfoLog("Vous avez choisi le mode duel");
				MisterMind mastermind = new MisterMind();
				mastermind.setMode("MisterMind : duel");
				FenetreDeJeu modedj = new FenetreDeJeu(mastermind.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("MisterMind : duel");
				modedj.actifMode = true;
				modedj.toFind();
			}
		default:
			break;
		}
	}
	
	
}
