package fr.projet3.oc;

public class RecherchePM extends Jeu {

	String title;
	private String Mode;
	boolean actif = false;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public RecherchePM() {

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
	 * @param le mode de jeu
	 */
	public void setMode(String mode) {
		Mode = mode;
	}

	/**
	 * methode joue qui permet à un l'ordinateur je jouer un coup et qui genère une
	 * reponse pour l'ordinateur
	 * 
	 * @param les deux String, la première etant la proposition du joueur et la
	 *            deuxième etant la combianison à trouvé
	 * 
	 * @return une nouvelle proposition pour l'ordinatur en fonction de la
	 *         condinaison du joueur
	 * 
	 *
	 */
	public String joue(String myS1, String myS2) {
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

	public String donnerIndice(String str1, String str2) {
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

	/**
	 * methode finDePartie qui nous indique la fin de la partie
	 * 
	 * @param une chaine representant la reponse donnée
	 * 
	 * @return un booleen qui vaut true si la combianison a été trouvée et false si
	 *         non
	 */
	public boolean finDePartie(String s) {
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

}
