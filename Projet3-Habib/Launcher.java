package fr.projet3.oc;

import java.io.*;

import javax.swing.*;

import org.apache.log4j.Logger;

public class Launcher {

	/**
	 * methode run qui lance une partie
	 * 
	 */
	public void run() {

		Logger logger = Logger.getLogger(Launcher.class);
		
		logger.info("Lancement de l'application");

		String mode = JOptionPane
				.showInputDialog("Tapez 1 pour le jouer à Recherche +/-; \n Tapez 2 pour jouer à MasterMind \n ");
		logger.info("Choix du jeu");

		switch (mode) {
		case "1":
			String message = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			logger.info("Vous avez choisi le Recherche +/-");
			logger.info("Choix du mode de jeu");
			if (message.equals("1")) {
				logger.info("Vous avez choisi le mode challenger");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : challenger");
				FenetreDeJeu modedj = new FenetreDeJeu(recherche.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : challenger");
				modedj.actifMode = "R";

			} else if (message.equals("2")) {
				logger.info("Vous avez choisi le mode défenseur");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : défenseur");
				FenetreDeJeu modedj = new FenetreDeJeu("Recherche +/- : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : défenseur");
				modedj.actifMode = "R";
				modedj.toFind();
			} else {
				logger.info("Vous avez choisi le mode duel");
				RecherchePM recherche = new RecherchePM();
				recherche.setMode("Recherche +/- : duel");
				FenetreDeJeu modedj = new FenetreDeJeu("Recherche +/- : duel", new OutputStreamWriter(System.out));
				modedj.setMode("Recherche +/- : duel");
				modedj.actifMode = "R";
				modedj.toFind();
			}
			break;

		case "2":
			String mes = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			logger.info("Vous avez choisi le MasterMind");
			logger.info("Choix du mode de jeu");
			if (mes.equals("1")) {
				logger.info("Vous avez choisi le mode challenger");
				MasterMind mastermind = new MasterMind();
				mastermind.setMode("MasterMind : challenger");
				FenetreDeJeu modedj = new FenetreDeJeu("MasterMind : challenger", new OutputStreamWriter(System.out));
				modedj.setMode("MasterMind  : challenger");
				modedj.actifMode = "M";
			} else if (mes.equals("2")) {
				logger.info("Vous avez choisi le mode défenseur");
				MasterMind mastermind = new MasterMind();
				mastermind.setMode("MasterMind : défenseur");
				FenetreDeJeu modedj = new FenetreDeJeu("MasterMind : défenseur", new OutputStreamWriter(System.out));
				modedj.setMode("MasterMind : défenseur");
				modedj.actifMode = "M";
				modedj.toFind();
			} else {
				logger.info("Vous avez choisi le mode duel");
				MasterMind mastermind = new MasterMind();
				mastermind.setMode("MasterMind : duel");
				FenetreDeJeu modedj = new FenetreDeJeu(mastermind.getMode(), new OutputStreamWriter(System.out));
				modedj.setMode("MasterMind : duel");
				modedj.actifMode = "M";
				modedj.toFind();
			}
		default:
			break;
		}
	}

}
