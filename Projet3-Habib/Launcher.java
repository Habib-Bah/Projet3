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

		Jeu jeu;
		FenetreDeJeu fenetre;
		switch (mode) {
		case "1":
			jeu = new RecherchePM();
			String message = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			logger.info("Vous avez choisi le Recherche +/-");
			logger.info("Choix du mode de jeu");
			if (message.equals("1")) {
				logger.info("Vous avez choisi le mode challenger");
				jeu.setMode("Recherche +/- : challenger");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode(jeu.getMode());
				fenetre.actifMode = "R";

			} else if (message.equals("2")) {
				logger.info("Vous avez choisi le mode défenseur");
				jeu.setMode("Recherche +/- : défenseur");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode(jeu.getMode());
				fenetre.actifMode = "R";
				fenetre.toFind();
			} else {
				logger.info("Vous avez choisi le mode duel");
				jeu.setMode("Recherche +/- : duel");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode("Recherche +/- : duel");
				fenetre.actifMode = "R";
				fenetre.toFind();
			}
			break;

		case "2":
			jeu = new MasterMind();
			String mes = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			logger.info("Vous avez choisi le MasterMind");
			logger.info("Choix du mode de jeu");
			if (mes.equals("1")) {
				logger.info("Vous avez choisi le mode challenger");
				jeu.setMode("MasterMind : challenger");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode("MasterMind  : challenger");
				fenetre.actifMode = "M";
			} else if (mes.equals("2")) {
				logger.info("Vous avez choisi le mode défenseur");
				jeu.setMode("MasterMind : défenseur");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode("MasterMind : défenseur");
				fenetre.actifMode = "M";
				fenetre.toFind();
			} else {
				logger.info("Vous avez choisi le mode duel");
				jeu.setMode("MasterMind : duel");
				fenetre = new FenetreDeJeu(jeu.getMode(), new OutputStreamWriter(System.out));
				fenetre.setMode("MasterMind : duel");
				fenetre.actifMode = "M";
				fenetre.toFind();
			}
		default:
			break;
		}
	}

}
