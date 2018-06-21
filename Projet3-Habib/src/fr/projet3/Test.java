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
				RechechePM recherche = new RechechePM("Mode challenger : recherche +/-", new OutputStreamWriter(System.out));
				recherche.setMode("challenger");
				//recherche.toFind();
				
			} else if (message.equals("2")) {
				RechechePM recherche = new RechechePM("Mode défenseur : recherche +/-", new OutputStreamWriter(System.out));
				recherche.setMode("défenseur");
				recherche.toFind();
			} else {
				RechechePM recherche = new RechechePM("Mode duel : recherche +/-", new OutputStreamWriter(System.out));
				recherche.setMode("duel");
				recherche.toFind();
			}
			break;
		
		case "2" :
			String mes = JOptionPane.showInputDialog(
					"Tapez 1 pour le mode challenger; \n Tapez 2 pour le mode défenseur \n Tapez 3 pour le mode duel ");
			if (mes.equals("1")) {
				MisterMind mastermind = new MisterMind("Mode challenger :  MisterMind", new OutputStreamWriter(System.out));
				mastermind.setMode("challenger");
				mastermind.toFind();
			} else if (mes.equals("2")) {
				MisterMind mastermind = new MisterMind("Mode défenseur : MisterMind ", new OutputStreamWriter(System.out));
				mastermind.setMode("défenseur");
				mastermind.toFind();
			} else {
				MisterMind mastermind = new MisterMind("Mode duel : MisterMind ", new OutputStreamWriter(System.out));
				mastermind.setMode("duel");
				mastermind.toFind();
			}
		}

	}

}
