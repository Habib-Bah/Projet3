package fr.projet3.oc;

import org.apache.log4j.Logger;

public class RecherchePM extends Jeu {
	
	Logger logger = Logger.getLogger(Launcher.class);


	/**
	 * methode joue qui permet à un l'ordinateur je jouer un coup et qui genère une
	 * reponse pour l'ordinateur
	 * 
	 * @param les deux String, la première etant la proposition du joueur et la
	 *            deuxième etant la combianison à trouvé
	 * 
	 * @return une nouvelle proposition pour l'ordinatur en fonction de la
	 *         conbinaison du joueur
	 * 
	 *
	 */
	public String joue(String myS1, String myS2) {
		
		String tableau1[] = new String[myS1.length()];
		String tableau2[] = new String[myS1.length()];
		String resultat = "";
		String Indication = myS1;
		String proposition = myS2;
		int x = 0;
		int y = 1;
		int z = 0;
		
		while(y <= tableau1.length) {
			tableau1[z] = (proposition.substring(x, y));
			tableau2[z] = (Indication.substring(x, y));
			y++;
			x++;
			z++;
		}
		
		for(int i = 0; i < tableau1.length; i++) {
			if(tableau2[i].equalsIgnoreCase("+")) {
				int val = Integer.parseInt(tableau1[i]);
				if(val != 9) {
					val = val + 1;
					resultat = resultat + val;
				}
				else {
					resultat = resultat + val;
				}		 
			}
			if(tableau2[i].equalsIgnoreCase("-")) {
				int val = Integer.parseInt(tableau1[i]);
				if(val != 0) {
					val = val - 1;
					resultat = resultat + val;
				}
				else {
					resultat = resultat + val;
				}		 
			}
			if(tableau2[i].equalsIgnoreCase("=")) {
				resultat = resultat + tableau1[i];
			}
			
		}
		
		logger.info("génération d'une nouvelle proposition");
		return resultat;

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
		logger.info("génération d'un nouvel indice");
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
