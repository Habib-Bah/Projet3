package fr.projet3.oc;

import java.util.*;

public class MisterMind extends Jeu{

	String title;
	private String Mode;
	boolean actif = false;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public MisterMind() {
		this.actif = true;
	}

	/**
	 * getter recupérant le mode du jeu au quel on joue
	 * 
	 * @return String mode
	 */
	public String getMode() {
		return Mode;
	}

	/**
	 * setter modifiant le mode du jeu
	 * 
	 * @param le mode
	 * 
	 */
	public void setMode(String mode) {
		Mode = mode;
	}

	/**
	 * methode jouer qui permet à un l'ordinateur je jouer un coup
	 * 
	 * 
	 * @param les deux String, la première etant la proposition du joueur et la
	 *            deuxième etant la combianison à trouvé
	 * 
	 * @return une nouvelle proposition pour l'ordinatur en fonction de la
	 *         condinaison du joueur
	 * 
	 *
	 */
	public String joue(String s1, String s2) {
		String tab[] = new String[s1.length()];
		int i = 0;
		int y = 1;
		int x = 0;
		Random random = new Random();
		int nbr = random.nextInt(2);
		String result = "";
		while (y <= s1.length()) {
			tab[i] = s1.substring(x, y);
			x++;
			y++;
			i++;
		}
		if (!s2.equalsIgnoreCase("4 présents, 4 bien placés")) {

			for (int j = 0; j < tab.length; j++) {
				if (tab[j].equals("" + 0)) {
					nbr = nbr + 2;
					tab[j] = "" + nbr;
				} else if (tab[j].equals("" + 9)) {
					nbr = nbr - 2;
					tab[j] = "" + nbr;
				} else if (tab[j].equals("" + 8)) {
					nbr = nbr - 1;
					tab[j] = "" + nbr;
				} else
					tab[j] = "" + nbr;
			}
		}

		for (int l = 0; l < tab.length; l++) {
			result = result + tab[l];
		}
		return result;

	}

	/**
	 * methode donnerIndice qui genère un indice pour l'ordinateur en fonction de la
	 * proposition du joueur
	 * 
	 * 
	 * @param deux String, la première etant la proposition du joueur et la deuxième
	 *             etant la combianison à trouvé
	 * 
	 * @return un nouvel indice en fonction de la proposition du joueur et de la
	 *         combinaison à trouvée
	 */

	public String donnerIndice(String s1, String s2) {
		int nbrexistant = 0;
		int nbrebienplacé = 0;
		String resultat = "";
		int x = 0;
		int y = 1;
		int i = 0;

		String tab1[] = new String[s1.length()];
		String tab2[] = new String[s2.length()];

		while (y <= s1.length()) {
			tab1[i] = s1.substring(x, y);
			tab2[i] = s2.substring(x, y);
			x++;
			y++;
			i++;
		}

		for (int l = 0; l < tab1.length; l++) {
			for (int k = 0; k < tab2.length; k++) {
				if (tab1[l].equalsIgnoreCase(tab2[k])) {
					nbrexistant++;
					if (l == k) {
						nbrebienplacé++;
					}

				}
			}

		}

		switch (nbrexistant) {
		case 0:
			resultat = ("Aucun chiffre n'est présent");
			break;
		case 1:
			if (nbrebienplacé == 0) {
				resultat = ("1 présent");
			} else {
				resultat = ("1 présent, 1 bien placé");
			}
			break;
		case 2:
			if (nbrebienplacé == 0) {
				resultat = ("2 présents");
			} else if (nbrebienplacé == 1) {
				resultat = ("2 présents, 1 bien placé");
			} else {
				resultat = ("2 présents, 2 bien placés");
			}
			break;
		case 3:
			if (nbrebienplacé == 0) {
				resultat = ("3 présents");
			} else if (nbrebienplacé == 1) {
				resultat = ("3 présents, 1 bien placé");
			} else if (nbrebienplacé == 2) {
				resultat = ("3 présents, 2 bien placés");
			} else {
				resultat = ("3 présents, 3 bien placés");
			}
			break;
		case 4:
			if (nbrebienplacé == 0) {
				resultat = ("4 présents");
			} else if (nbrebienplacé == 1) {
				resultat = ("4 présents, 1 bien placé");
			} else if (nbrebienplacé == 2) {
				resultat = ("4 présents, 2 bien placés");
			} else if (nbrebienplacé == 3) {
				resultat = ("4 présents, 3 bien placés");
			} else {
				resultat = ("4 présents, 4 bien placés");
			}
			break;
		default:
			break;
		}
		return resultat;

	}

	/**
	 * methode finDePartie qui nous indique la fin de la partie
	 * 
	 *  @param une chaine representant la reponse donnée
	 * 
	 * @return un booleen qui vaut true si la combianison a été trouvée et false si
	 *         non
	 */
	public boolean finDePartie(String s) {
		if (s.equalsIgnoreCase("4 présents, 4 bien placés")) {
			return true;
		} else
			return false;
	}

}
