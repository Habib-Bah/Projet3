package fr.projet3;

import java.util.*;

public class FonctionAuxi {

	Map<String, Integer> map1 = new HashMap<>();

	// Retourne un nouvel indice en fonction de la reponse du joueur

	public String getIndice(String str1, String str2) {
		int i = 0;
		int j = 1;
		String result = "";
		while (j <= str1.length()) {
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


	public boolean fin(String s) {
		int i = 0;
		int j = 1;
		int x = 0;
		while (j < s.length()) {
			if (s.substring(i, j).equalsIgnoreCase("=")) {
				x++;
				i ++;
				j ++;
			}
			else {
				i ++;
				j ++;
			}
		}
		if (x == s.length() - 1) {
			return true;
		} else {
			return  false;
		}
	}
}
