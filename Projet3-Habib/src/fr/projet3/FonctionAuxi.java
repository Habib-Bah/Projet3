package fr.projet3;

import java.util.HashMap;
import java.util.Map;

public class FonctionAuxi {

	Map<String, Integer> map1 = new HashMap<>();

	// Retourne une nouvelle combinaison en fonction de la reponse du joueur
	public String getCombinaison(String tab1[], String tab3[], String str3) {

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
				if (tab3[i].equals("-")) {
					map1.put("9", 8);
				} else if (tab3[i].equals("+")) {
					map1.put("9", 9);
				} else {
					map1.put("9", 9);
				}
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
		return str3;
	}

	// Transforme un int en un tableau de String
	public String transform(int y) {

		String sx = "" + y;
		int[] tab = new int[sx.length()];
		String tab1[] = new String[tab.length];
		String st = "";

		for (int i = 0; i < sx.length(); i++) {
			tab[i] = Integer.parseInt("" + sx.charAt(i));
		}

		for (int i = 0; i < tab.length; i++) {
			tab1[i] = tab[i] + " ";
		}
		for (int i = 0; i < tab1.length; i++) {
			st = st + tab1[i];
		}
		return st;
	}

	// Tranforme un tableau de String en int

	public int generateNewInt(String[] string) {
		int nbr = 0;
		for (int i = 0; i < string.length; i++) {
			if (string[i].equals("1")) {
				nbr = Integer.valueOf("" + nbr + 1);
			} else if (string[i].equals("2")) {
				nbr = Integer.valueOf("" + nbr + 2);
			} else if (string[i].equals("3")) {
				nbr = Integer.valueOf("" + nbr + 3);
			} else if (string[i].equals("4")) {
				nbr = Integer.valueOf("" + nbr + 4);
			} else if (string[i].equals("5")) {
				nbr = Integer.valueOf("" + nbr + 5);
			} else if (string[i].equals("6")) {
				nbr = Integer.valueOf("" + nbr + 6);
			} else if (string[i].equals("7")) {
				nbr = Integer.valueOf("" + nbr + 7);
			} else if (string[i].equals("8")) {
				nbr = Integer.valueOf("" + nbr + 8);
			} else {
				nbr = Integer.valueOf("" + nbr + 9);
			}
		}
		return nbr;
	}
	
	public String getComputerAns(String str77, String str77bis) {
		Map<String, Integer> map77 = new HashMap<>();
		Map<String, Integer> map77bis = new HashMap<>();
		String res = "";
		
		String[] tab77 = str77.split(" ");
		String[] tab77bis = str77bis.split(" ");
		
		for (int i = 0; i < tab77.length; i++) {
			if (tab77[i].equals("1")) {
				map77.put("1", 1);
			} else if (tab77[i].equals("2")) {
				map77.put("2", 2);
			} else if (tab77[i].equals("3")) {
				map77.put("3", 3);
			} else if (tab77[i].equals("4")) {
				map77.put("4", 4);
			} else if (tab77[i].equals("5")) {
				map77.put("5", 5);
			} else if (tab77[i].equals("6")) {
				map77.put("6", 6);
			} else if (tab77[i].equals("7")) {
				map77.put("7", 7);
			} else if (tab77[i].equals("8")) {
				map77.put("8", 8);
			} else {
				map77.put("9", 9);
			}
		}

		for (int i = 0; i < tab77bis.length; i++) {
			if (tab77bis[i].equals("1")) {
				map77bis.put("1", 1);
			} else if (tab77bis[i].equals("2")) {
				map77bis.put("2", 2);
			} else if (tab77bis[i].equals("3")) {
				map77bis.put("3", 3);
			} else if (tab77bis[i].equals("4")) {
				map77bis.put("4", 4);
			} else if (tab77bis[i].equals("5")) {
				map77bis.put("5", 5);
			} else if (tab77bis[i].equals("6")) {
				map77bis.put("6", 6);
			} else if (tab77bis[i].equals("7")) {
				map77bis.put("7", 7);
			} else if (tab77bis[i].equals("8")) {
				map77bis.put("8", 8);
			} else {
				map77bis.put("9", 9);
			}
		}

		for (int i = 0; i < tab77.length; i++) {
			if (tab77[i].equals("1")) {
				if (map77bis.containsKey("1")) {
					if (map77.get("1") > map77bis.get("1")) {
						res = res + "- ";
					} else if (map77.get("1") < map77bis.get("1")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}
			} else if (tab77[i].equals("2")) {
				if (map77bis.containsKey("2")) {
					if (map77.get("2") > map77bis.get("2")) {
						res = res + "- ";
					} else if (map77.get("2") < map77bis.get("2")) {
						res = res + "+ ";
					} else {
						res = res + "+ ";
					}
				} else {
					res = res + "+ ";
				}

			} else if (tab77[i].equals("3")) {
				if (map77bis.containsKey("3")) {
					if (map77.get("3") > map77bis.get("3")) {
						res = res + "- ";
					} else if (map77.get("3") < map77bis.get("3")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}

			} else if (tab77[i].equals("4")) {
				if (map77bis.containsKey("4")) {
					if (map77.get("4") > map77bis.get("4")) {
						res = res + "- ";
					} else if (map77.get("4") < map77bis.get("4")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}

			} else if (tab77[i].equals("5")) {
				if (map77bis.containsKey("5")) {
					if (map77.get("5") > map77bis.get("5")) {
						res = res + "- ";
					} else if (map77.get("5") < map77bis.get("5")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}
			} else if (tab77[i].equals("6")) {
				if (map77bis.containsKey("6")) {
					if (map77.get("6") > map77bis.get("6")) {
						res = res + "- ";
					} else if (map77.get("6") < map77bis.get("6")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}
			} else if (tab77[i].equals("7")) {
				if (map77bis.containsKey("7")) {
					if (map77.get("7") > map77bis.get("7")) {
						res = res + "- ";
					} else if (map77.get("7") < map77bis.get("7")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}
			} else if (tab77[i].equals("8")) {
				if (map77bis.containsKey("8")) {
					if (map77.get("8") > map77bis.get("8")) {
						res = res + "- ";
					} else if (map77.get("8") < map77bis.get("8")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "+ ";
				}

			} else {
				if (map77bis.containsKey("9")) {
					if (map77.get("9") > map77bis.get("9")) {
						res = res + "- ";
					} else if (map77.get("9") < map77bis.get("9")) {
						res = res + "+ ";
					} else {
						res = res + "= ";
					}
				} else {
					res = res + "";
				}
			}

		}
			return res;
	}

}
