package fr.projet3;

import java.util.*;

public class Challenge {

	Ordinateur o;
	Humain p;
	Random random;
	
	public int getCombinaison() {
		int x = random.nextInt(1000);
		return x;
	}
	
	public String getAnswer(Humain p) {
		String str = "";
		for (String s : p.Splip(p.getMessage())) {
			str = str+s;
		}
		return str;
	}
}
