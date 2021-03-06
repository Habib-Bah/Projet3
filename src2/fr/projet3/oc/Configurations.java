package fr.projet3.oc;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.apache.log4j.Logger;

public class Configurations {

	private String combinaison;
	private int nombreDeChiffre;
	private int nombreEssaieAutorise;
	private int nombreDeChiffreUtilisables;
	private String modeDeveloppeur;
	private int borne;
	private int valeur;
	Logger logger = Logger.getLogger(Launcher.class);

	public Configurations() throws IOException {
		MiseAJourDesValeurs();
		logger.info("Mise à jour des valeurs du fichiers de configuration");
	}

	/**
	 * getter recupérant la combinaison de l'ordinateur
	 * 
	 * @return int combinaison
	 */
	public String getCombinaison() {
		return combinaison;
	}

	/**
	 * setter modifiant la combinaison de l'ordinateur
	 * 
	 * @param la combinaison
	 * 
	 */
	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}

	/**
	 * getter recupérant le nombre de chiffres utilisabes donné par l'utilisateur
	 * dans le fichier de configuration
	 * 
	 * @return int nombreDeChiffre
	 */

	public int getNombreDeChiffre() {
		return nombreDeChiffre;
	}

	/**
	 * setter modifiant le nombre de chiffres utilisables donné par l'utilisateur
	 * dans le fichier de configuration
	 * 
	 * @param le mode
	 * 
	 */
	public void setnombreDeChiffre(int nombreDeChiffre) {
		this.nombreDeChiffre = nombreDeChiffre;
	}

	/**
	 * getter recupérant le nombre d'essaie donné par l'utilisateur dans le fichier
	 * de configuration
	 * 
	 * @return int valeurMax
	 */

	public int getNombreEssaieAutorise() {
		return nombreEssaieAutorise;
	}

	/**
	 * setter modifiant le nombre d'essaie autorisé donné par l'utilisateur dans le
	 * fichier de configuration
	 * 
	 * @param le mode
	 * 
	 */

	public void setNombreEssaieAutorise(int nombreEssaieAutorise) {
		this.nombreEssaieAutorise = nombreEssaieAutorise;
	}

	/**
	 * getter recupérant le nombre de chiffre utilisable par l'utilisateur dans le
	 * fichier de configuration
	 * 
	 * @return int nombreDeChiffreUtilisables
	 */

	public int getNombreDeChiffreUtilisables() {
		return nombreDeChiffreUtilisables;
	}

	/**
	 * setter modifiant le nombre de chiffre utilisable donné par l'utilisateur dans
	 * le fichier de configuration
	 * 
	 * @param le nombreDeChiffreUtilisables
	 * 
	 */
	public void setNombreDeChiffreUtilisables(int nombreDeChiffreUtilisables) {
		this.nombreDeChiffreUtilisables = nombreDeChiffreUtilisables;
	}

	/**
	 * getter recupérant le l'etat du mode developpeur dans le fichier de
	 * configuration
	 * 
	 * @return String modeDeveloppeur
	 */

	public String getModeDeveloppeur() {
		return modeDeveloppeur;
	}

	/**
	 * setter modifiant le mode developpeur donné par l'utilisateur dans le fichier
	 * de configuration
	 * 
	 * @param le modeDeveloppeur
	 * 
	 */

	public void setModeDeveloppeur(String modeDeveloppeur) {
		this.modeDeveloppeur = modeDeveloppeur;
	}

	/**
	 * getter recupérant le nombre de utilisatble
	 * 
	 * @return int borne
	 */
	public int getBorne() {
		return borne;
	}

	/**
	 * setter modifiant le mode developpeur donné par l'utilisateur dans le fichier
	 * de configuration
	 * 
	 * @param la borne
	 */
	public void setBorne(int borne) {
		this.borne = borne;
	}

	/**
	 * getter recupérant la valeur utilisatble
	 * 
	 * @return int valeur
	 */

	public int getValeur() {
		return valeur;
	}

	/**
	 * setter modifiant la valeur utilisable
	 * 
	 * @param la valeur
	 */

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * methode qui formate la combinaison à trouvée de manière à ce qu'aucun chiffre
	 * ne depasse le nombre de chiffres utilisable
	 * 
	 * @return int valeur
	 */

	public int getValeurUtilisable(int val) {

		int nbUse = getNombreDeChiffreUtilisables();
		int i = 0;
		int j = 1;
		int comp = 0;
		String valS = "" + val;
		String tab[] = new String[valS.length()];
		String resS = "";
		int resi;
		int tabi[] = new int[valS.length()];

		while (j <= valS.length()) {
			tab[comp] = valS.substring(i, j);
			i++;
			j++;
			comp++;
		}

		for (int yy = 0; yy < tab.length; yy++) {
			tabi[yy] = Integer.parseInt(tab[yy]);
		}

		for (int valT : tabi) {
			if (valT > nbUse) {
				resS = resS + nbUse;
			} else {
				resS = resS + valT;
			}
		}

		resi = Integer.parseInt(resS);
		return resi;

	}

	/**
	 * methode de mise à jour des valeurs présentes dans le fichier de configuraton
	 * 
	 */

	public void MiseAJourDesValeurs() throws IOException {

		Path path = Paths.get("Ressources/config.properties");
		String tab[] = new String[5];

		int i = 0;

		for (String lignes : Files.readAllLines(path)) {
			tab[i] = lignes;
			i++;
		}

		String[] tab1 = tab[0].split(": ");
		String[] tab2 = tab[1].split(": ");
		String[] tab3 = tab[2].split(": ");
		String[] tab4 = tab[3].split(": ");

		String tableauDetableau[][] = new String[4][2];
		tableauDetableau[0] = tab1;
		tableauDetableau[1] = tab2;
		tableauDetableau[2] = tab3;
		tableauDetableau[3] = tab4;

		for (int ln = 0; ln < 4; ln++) {
			if ((tableauDetableau[ln][0]).equalsIgnoreCase("jeu.nombreDeCaseDeCombinaisonSecrete ")) {
				setnombreDeChiffre(Integer.parseInt(tableauDetableau[ln][1]));
			}

			if ((tableauDetableau[ln][0]).equalsIgnoreCase("jeu.nombreDessaisAutorise ")) {
				setNombreEssaieAutorise(Integer.parseInt(tableauDetableau[ln][1]));
			}

			if ((tableauDetableau[ln][0]).equalsIgnoreCase("jeu.nombreDeChiffreUtilisables ")) {
				setNombreDeChiffreUtilisables(Integer.parseInt(tableauDetableau[ln][1]));
			}

			if ((tableauDetableau[ln][0]).equalsIgnoreCase("jeu.modeDeveloppeur(oui/non) ")) {
				setModeDeveloppeur(tableauDetableau[ln][1]);
			}
		}

		setCombinaison(genererCombinaison());

	}

	public String genererCombinaison() {
		Random random = new Random();
		int number = random.nextInt((int) (Math.pow(10, getNombreDeChiffre()) - 1));
		String numberInString = Integer.toString(number);

		int diff = (getNombreDeChiffre() - numberInString.length());
		if (diff != 0) {
			for (int w = 0; w < diff; w++) {
				numberInString = "0" + String.valueOf(numberInString);
			}
		}
		
		setCombinaison(numberInString);

		return getCombinaison();
	}

}
