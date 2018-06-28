package fr.projet3;

import java.util.*;

public class FonctionAuxi {

	Map<String, Integer> map1 = new HashMap<>();

	// Retourne un nouvel indice en fonction de la reponse du joueur

	public String getIndice(String str1, String str2) {
		int i = 0;
		int j = 1;
		String result = "";
		while (j <= str2.length()) {
			if (str1.substring(i, j).compareTo(str2.substring(i, j)) > 0) {
				result = result + "+";
				i++;
				j++;
			} else if (str1.substring(i, j).compareTo(str2.substring(i, j)) < 0) {
				result = result + "-";
				i++;
				j++;
			} else {
				result = result + "=";
				i++;
				j++;
			}
		}
		return result;
	}

	// Retour une nouvelle combinaison pour l'ordinateur

	public String getNewCombinaison(String myS1, String myS2) {
		String res = "";
		int i = 0;
		int j = 1;
		while (j <= myS2.length()) {
			if (myS1.substring(i, j).equalsIgnoreCase("-")) {
				String g = myS2.substring(i, j);
				switch (g) {
				case "0":
					res = res + "0";
				case "1":
					res = res + "0";
					break;
				case "2":
					res = res + "1";
					break;
				case "3":
					res = res + "2";
					break;
				case "4":
					res = res + "3";
					break;
				case "5":
					res = res + "4";
					break;
				case "6":
					res = res + "5";
					break;
				case "7":
					res = res + "6";
					break;
				case "8":
					res = res + "7";
					break;
				case "9":
					res = res + "8";
					break;
				default:
					break;
				}

				i++;
				j++;
			} else if (myS1.substring(i, j).equalsIgnoreCase("+")) {

				String g = myS2.substring(i, j);
				switch (g) {
				case "0":
					res = res + "1";
				case "1":
					res = res + "2";
					break;
				case "2":
					res = res + "3";
					break;
				case "3":
					res = res + "4";
					break;
				case "4":
					res = res + "5";
					break;
				case "5":
					res = res + "6";
					break;
				case "6":
					res = res + "7";
					break;
				case "7":
					res = res + "8";
					break;
				case "8":
					res = res + "9";
					break;
				case "9":
					res = res + "9";
					break;
				default:
					break;
				}

				i++;
				j++;
			} else {
				
				res = res + myS2.substring(i, j);
				i++;
				j++;
			}
		}
		return res;
	}

	// Fin de partie
	public boolean fin(String s) {
		int i = 0;
		int j = 1;
		int x = 0;
		while (j < s.length()) {
			if (s.substring(i, j).equalsIgnoreCase("=")) {
				x++;
				i++;
				j++;
			} else {
				i++;
				j++;
			}
		}
		if (x >= s.length() - 1) {
			return true;
		} else {
			return false;
		}
	}

	// Indice de l'ordinateur pour le MasterMind

	public String getIndiceMister(String s1, String s2) {
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

	// Genère une nouvelle combinaison pour le MiM

	public String getNewTabMister(String s1, String s2) {

		String tab[] = new String[s1.length()];
		int i = 0;
		int y = 1;
		int x = 0;
		Random random = new Random();
		int nbr = random.nextInt(5);
		String result = "";
		while (y <= s1.length()) {
			tab[i] = s1.substring(x, y);
			x++;
			y++;
			i++;
		}
		if (!s2.equalsIgnoreCase("4 présents, 4 bien placés")) {
			tab[0] = "" + nbr;
			tab[1] = "" + (nbr + 1);
			tab[2] = "" + (nbr - 1);
			tab[3] = "" + (nbr - 1);
		}
		for (int l = 0; l < tab.length; l++) {
			result = result + tab[l];
		}
		return result;
	}

	public boolean finPM(String s) {
		if (s.equalsIgnoreCase("4 présents, 4 bien placés")) {
			return true;
		} else
			return false;
	}
}
