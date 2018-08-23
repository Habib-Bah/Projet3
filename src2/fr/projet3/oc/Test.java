package fr.projet3.oc;

import javax.swing.JOptionPane;

public class Test {

	/**
	 * Classe principale
	 * 
	 */
	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "BIENVENUE \n"
				+ "Dans les fenetres qui vont suivre, vous allez choisir à quel jeu vous voulez jouer \n"
				+ "puis le mode au quel vous voulez jouer.\n"
				+ "Amusez vous bien");
		Launcher l = new Launcher();
		l.run();
	}

}