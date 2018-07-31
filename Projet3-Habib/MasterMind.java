package fr.projet3.oc;

import java.io.IOException;
import java.util.*;

public class MasterMind extends Jeu {

	Configurations conf;

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
		try {
			conf = new Configurations();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String valeur = "";
		if (!s2.equalsIgnoreCase("4 présents, 4 bien placés")) {
			Random r = new Random();
			int valeurMax = conf.getNombreDeChiffre();
			Set<Integer> monHashSet = new HashSet<>();
			while (monHashSet.size() < 10000)
				monHashSet.add(r.nextInt(500000));
			for (int elt : monHashSet) {
				String slt = Integer.toString(elt);
				if (slt.length() == valeurMax) {
					valeur = slt;
					break;
				}
			}
		}
		return valeur;
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
		int nbrebienplacé = 0;
		String resultat = "";
		int nombrePresent = 0;

		int tab[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int tab2[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		String tableau1[] = new String[s1.length()];
		String tableau2[] = new String[s2.length()];
		int longueur = 0;
		int largeur = 1;
		int compteur = 0;

		while (largeur <= s1.length()) {
			tableau1[compteur] = s1.substring(longueur, largeur);
			tableau2[compteur] = s2.substring(longueur, largeur);
			longueur++;
			largeur++;
			compteur++;
		}

		for (int x = 0; x < tableau1.length; x++) {
			for (int y = 0; y < tableau2.length; y++) {
				if (tableau1[x].equalsIgnoreCase(tableau2[y])) {
					if (x == y) {
						nbrebienplacé++;
					}

				}
			}
		}

		int i = 0;
		int j = 1;
		int k = 0;
		int l = 1;

		for (int cmp = 0; cmp < s1.length(); cmp++) {

			switch (s1.substring(i, j)) {

			case "0":
				tab[0] = tab[0] + 1;
				break;
			case "1":
				tab[1] = tab[1] + 1;
				break;
			case "2":
				tab[2] = tab[2] + 1;
				break;
			case "3":
				tab[3] = tab[3] + 1;
				break;
			case "4":
				tab[4] = tab[4] + 1;
				break;
			case "5":
				tab[5] = tab[5] + 1;
				break;
			case "6":
				tab[6] = tab[6] + 1;
				break;
			case "7":
				tab[7] = tab[7] + 1;
				break;
			case "8":
				tab[8] = tab[8] + 1;
				break;
			case "9":
				tab[9] = tab[9] + 1;
				break;
			default:
				break;
			}
			i++;
			j++;
		}

		for (int cm = 0; cm < s2.length(); cm++) {

			switch (s2.substring(k, l)) {

			case "0":
				tab2[0] = tab2[0] + 1;
				break;
			case "1":
				tab2[1] = tab2[1] + 1;
				break;
			case "2":
				tab2[2] = tab2[2] + 1;
				break;
			case "3":
				tab2[3] = tab2[3] + 1;
				break;
			case "4":
				tab2[4] = tab2[4] + 1;
				break;
			case "5":
				tab2[5] = tab2[5] + 1;
				break;
			case "6":
				tab2[6] = tab2[6] + 1;
				break;
			case "7":
				tab2[7] = tab2[7] + 1;
				break;
			case "8":
				tab2[8] = tab2[8] + 1;
				break;
			case "9":
				tab2[9] = tab2[9] + 1;
				break;
			default:
				break;
			}
			k++;
			l++;
		}

		for (int cmt = 0; cmt < tab.length; cmt++) {
			if (tab[cmt] != 0 && tab2[cmt] != 0) {
				if (tab[cmt] > tab2[cmt]) {
					nombrePresent = nombrePresent + tab2[cmt];
				}
				if (tab[cmt] <= tab2[cmt]) {
					nombrePresent = nombrePresent + tab[cmt];
				}
			}
		}

		switch (nombrePresent) {
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
	 * @param une chaine representant la reponse donnée
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
