package fr.projet3.oc;

import java.io.*;
import java.nio.file.*;


public class Configurations {

	private int valeurMin;
	private int valeurMax;
	private int nombreDeChiffre;
	private int nombreEssaieAutorisé;
	private int nombreDeChiffreUtilisables;
	private String modeDeveloppeur;
	private int borne;
	private int valeur;

	public Configurations() throws IOException {
		MiseAJourDesValeurs();
	}

	/**
	 * getter recupérant la valeur minimale donnée par l'utilisateur dans le fichier
	 * de configuration
	 * 
	 * @return int valeurMin
	 */
	public int getValeurMin() {
		return valeurMin;
	}

	/**
	 * setter modifiant la valeur minimale donnée dans le fichier de configuration
	 * 
	 * @param le mode
	 * 
	 */
	public void setValeurMin(int valeurMin) {
		this.valeurMin = valeurMin;
	}

	/**
	 * getter recupérant la valeur maximale donnée par l'utilisateur dans le fichier
	 * de configuration
	 * 
	 * @return int valeurMax
	 */
	public int getValeurMax() {
		return valeurMax;
	}

	/**
	 * setter modifiant la valeur maximale donnée dans le fichier de configuration
	 * 
	 * @param le mode
	 * 
	 */
	public void setValeurMax(int valeurMax) {
		this.valeurMax = valeurMax;
	}

	/**
	 * getter recupérant le nombre de chiffres utilisabes donné par l'utilisateur
	 * dans le fichier de configuration
	 * 
	 * @return int valeurMax
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

	public int getNombreEssaieAutorisé() {
		return nombreEssaieAutorisé;
	}

	/**
	 * setter modifiant le nombre d'essaie autorisé donné par l'utilisateur dans le
	 * fichier de configuration
	 * 
	 * @param le mode
	 * 
	 */

	public void setNombreEssaieAutorisé(int nombreEssaieAutorisé) {
		this.nombreEssaieAutorisé = nombreEssaieAutorisé;
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
		
		setnombreDeChiffre(Integer.parseInt(tab1[1]));
		setNombreEssaieAutorisé(Integer.parseInt(tab2[1]));
		setNombreDeChiffreUtilisables(Integer.parseInt(tab3[1]));

		switch (getNombreDeChiffre()) {
		case 1:
			setValeurMin(1);
			setValeurMax(9);
			break;
		case 2:
			setValeurMin(10);
			setValeurMax(99);
			break;
		case 3:
			setValeurMin(100);
			setValeurMax(999);
			break;
		case 4:
			setValeurMin(1000);
			setValeurMax(9999);
			break;
		case 5:
			setValeurMin(10000);
			setValeurMax(99999);
			break;
		case 6:
			setValeurMin(100000);
			setValeurMax(999999);
			break;
		case 7:
			setValeurMin(1000000);
			setValeurMax(9999999);
			break;
		case 8:
			setValeurMin(10000000);
			setValeurMax(99999999);
			break;
		case 9:
			setValeurMin(1100000000);
			setValeurMax(99999999);
			break;
		default:
			break;
		}

		if (tab4[1].equalsIgnoreCase("oui")) {
			setModeDeveloppeur("oui");
		} else {
			setModeDeveloppeur("non");
		}

	}

}
