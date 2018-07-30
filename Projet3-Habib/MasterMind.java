package fr.projet3.oc;

import java.io.IOException;
import java.util.*;

public class MasterMind extends Jeu {

	String title;
	private String Mode;
	boolean actif = false;
	Configurations conf;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public MasterMind() {
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
		try {
			conf = new Configurations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String valeur ="";
		if (!s2.equalsIgnoreCase("4 présents, 4 bien placés")) {
			Random r = new Random();
			int valeurMax = conf.getNombreDeChiffre();
			Set<Integer> monHashSet = new HashSet<>();
			while (monHashSet.size() < 10000)
			    monHashSet.add(r.nextInt(500000));
			for (int elt : monHashSet) {
				String slt = Integer.toString(elt);
				if(slt.length() == valeurMax) {		
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
