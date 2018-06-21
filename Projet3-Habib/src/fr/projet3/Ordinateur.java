package fr.projet3;

import java.util.*;

public class Ordinateur  {

	Map<String, Integer> map1 = new HashMap<>();
	String message;
	static int x;
	Random random;

	public int getCombinaison() {
		x = 1000 + random.nextInt(1999 - 1000);
		return x;
	}

	public void getCombinaison(String tab1[], String tab3[], String str3) {

		for (int i = 0; i < tab1.length; i++) {

			if (tab1[i].equals("1")) {
				if (tab3[i].equals("-")) {
					map1.put("1", 1);
				} else if (tab3[i].equals("+")) {
					map1.put("1", 2);
				} else {
					map1.put("1", 1);
				}
			} else if (tab1[i].equals("2")) {
				if (tab3[i].equals("-")) {
					map1.put("2", 1);
				} else if (tab3[i].equals("+")) {
					map1.put("2", 3);
				} else {
					map1.put("2", 2);
				}
			} else if (tab1[i].equals("3")) {
				if (tab3[i].equals("-")) {
					map1.put("3", 2);
				} else if (tab3[i].equals("+")) {
					map1.put("3", 4);
				} else {
					map1.put("3", 3);
				}
			} else if (tab1[i].equals("4")) {
				if (tab3[i].equals("-")) {
					map1.put("4", 3);
				} else if (tab3[i].equals("+")) {
					map1.put("4", 5);
				} else {
					map1.put("4", 4);
				}
			} else if (tab1[i].equals("5")) {
				if (tab3[i].equals("-")) {
					map1.put("5", 4);
				} else if (tab3[i].equals("+")) {
					map1.put("5", 6);
				} else {
					map1.put("5", 5);
				}
			} else if (tab1[i].equals("6")) {
				if (tab3[i].equals("-")) {
					map1.put("6", 5);
				} else if (tab3[i].equals("+")) {
					map1.put("6", 7);
				} else {
					map1.put("6", 6);
				}
			} else if (tab1[i].equals("7")) {
				if (tab3[i].equals("-")) {
					map1.put("7", 6);
				} else if (tab3[i].equals("+")) {
					map1.put("7", 8);
				} else {
					map1.put("7", 7);
				}
			} else if (tab1[i].equals("8")) {
				if (tab3[i].equals("-")) {
					map1.put("8", 7);
				} else if (tab3[i].equals("+")) {
					map1.put("8", 9);
				} else {
					map1.put("8", 8);
				}
			} else {
				map1.put("9", 9);
			}
		}

		for (int i = 0; i < tab1.length; i++) {

			if (tab1[i].equals("1")) {
				str3 = str3 + " " + (map1.get("1"));
			} else if (tab1[i].equals("2")) {
				str3 = str3 + " " + (map1.get("2"));
			} else if (tab1[i].equals("3")) {
				str3 = str3 + " " + (map1.get("3"));
			} else if (tab1[i].equals("4")) {
				str3 = str3 + " " + (map1.get("4"));
			} else if (tab1[i].equals("5")) {
				str3 = str3 + " " + (map1.get("5"));
			} else if (tab1[i].equals("6")) {
				str3 = str3 + " " + (map1.get("6"));
			} else if (tab1[i].equals("7")) {
				str3 = str3 + " " + (map1.get("7"));
			} else if (tab1[i].equals("8")) {
				str3 = str3 + " " + (map1.get("8"));
			} else {
				str3 = str3 + " " + (map1.get("9"));
			}
		}
	}
}
