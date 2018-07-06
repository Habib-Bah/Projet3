package fr.projet3.oc;

import java.io.*;
import java.nio.file.*;

public class Configurations {

	private int valeurMin;
	private int valeurMax;
	private int nombreDeChiffre;
	private int nombreEssaieAutorisé;

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
	 * methode de mise à jour des valeurs présentes dans le fichier de configuraton
	 * à savoir le nombre de chiffres autorisés, la valeur minimale et maximale qui
	 * pourra etre générée
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
		setValeurMin(Integer.parseInt(tab2[1]));
		setValeurMax(Integer.parseInt(tab3[1]));
		setNombreEssaieAutorisé(Integer.parseInt(tab4[1]));
	}
}
