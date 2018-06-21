package fr.projet3;

public class Humain  {

	public String[] Splip(String message) {
		int L = message.length();
		String tab [] = new String[L];
		tab = message.split(".");
		return tab;
	}

	public String getMessage() {
		return "Moi: ";
	}

}
