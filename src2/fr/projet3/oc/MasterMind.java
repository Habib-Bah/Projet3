package fr.projet3.oc;

import java.util.*;

import org.apache.log4j.Logger;

public class MasterMind extends Jeu {
	

	Logger logger = Logger.getLogger(Launcher.class);
	
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
	
		String valeur = "";
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
			logger.info("génération d'une nouvelle proposition");
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
		int nbrebienplace = 0;
		int nombrePresent = 0;
		String combinaisonAtrouver = s1;
		String Proposition = s2;
		
		String tabl1[] = new String[combinaisonAtrouver.length()];
		String tabl2[] = new String[Proposition.length()];
		int tableauPresenceCombinaisonATrouver[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int tableauPresenceProposition[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int tableauF[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int x = 0;
		int y = 1;
		int z = 0;
		
		while(y <= tabl1.length) {
			tabl1[z] = (combinaisonAtrouver.substring(x, y));
			tabl2[z] = (Proposition.substring(x, y));
			y++;
			x++;
			z++;
		}
		

		for (int cm = 0; cm < tabl1.length; cm++) {
			for (int cmp = 0; cmp < tabl2.length; cmp++) {
				if (tabl1[cm].equalsIgnoreCase(tabl2[cmp])) {
					if (cm == cmp) {
						nbrebienplace++;
						logger.info("compatage du nombre de valeurs bien placées");
					}

				}
			}
		}
		
		
		for(int i = 0; i < tabl1.length; i++) {
			int val1 =  Integer.parseInt(tabl1[i]); 
			int val2 =  Integer.parseInt(tabl2[i]);
			tableauPresenceCombinaisonATrouver[val1] = tableauPresenceCombinaisonATrouver[val1] + 1;
			tableauPresenceProposition[val2] = tableauPresenceProposition[val2] + 1;
		}
		
		
		for(int i = 0; i < tableauF.length; i++) {
			if(tableauPresenceCombinaisonATrouver[i] !=0 && tableauPresenceProposition[i] !=0) {
				if(tableauPresenceCombinaisonATrouver[i] > tableauPresenceProposition[i]) {
					tableauF[i] =  tableauF[i] + tableauPresenceProposition[i];
					nombrePresent = nombrePresent + tableauF[i];
				}
				if((tableauPresenceCombinaisonATrouver[i] < tableauPresenceProposition[i]) || (tableauPresenceCombinaisonATrouver[i]== tableauPresenceProposition[i])) {
					tableauF[i] =  tableauF[i] + tableauPresenceCombinaisonATrouver[i];
					nombrePresent = nombrePresent + tableauF[i];
				}
			}
		}
		
		logger.info("Comptage du nombre de valeurs existants");
		logger.info("génération d'un nouvel indice");
		
		return nombrePresent + " présent," +nbrebienplace +" bien placé";
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
		if (s.equalsIgnoreCase(conf.getNombreDeChiffre() + " présent, " +  conf.getNombreDeChiffre() + " bien placé")) {
			return true;
		} else
			return false;
	}

}
