package fr.projet3.oc;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import org.apache.log4j.Logger;

public class FenetreDeJeu {

	private JFrame frame;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	JMenu select;
	JMenuItem[] ins;
	JMenu instructions;
	JMenuItem rejouer;
	JMenuItem i1;
	JMenuItem i2;
	JMenuItem i3;

	String nombre;
	JTextField newMessageTextField;
	JTextArea chatTextArea;
	JScrollPane chatscrollPane;
	JMenuBar bar;
	Random random = new Random();
	static int intervalle;
	int nbr;
	int nbressai = 0;
	int NombreDeChiffes;
	int valeurMin;
	int valeurMax;
	public static int randomValue;
	private String mode;
	String str;
	String str2;
	boolean actifMode;
	String modeDeveloppeur;
	int borne;
	Configurations conf;
	Jeu jeu;

	/**
	 * Constructeur de la classe
	 * 
	 */

	public FenetreDeJeu(String titre, Writer outPut) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					conf = new Configurations();
					setValeurMin(conf.getValeurMin());
					setValeurMax(conf.getValeurMax());
					NombreDeChiffes = conf.getNombreDeChiffre();
					nbr = conf.getNombreEssaieAutorisé();
					modeDeveloppeur = conf.getModeDeveloppeur();
					intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
					conf.setValeur(intervalle);
					borne = conf.getValeurUtilisable(intervalle);
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				Logger logger = Logger.getLogger(Launcher.class);
				if (modeDeveloppeur.equalsIgnoreCase("oui")) {
					if (getMode().equalsIgnoreCase("Recherche +/- : challenger")
							|| getMode().equalsIgnoreCase("Recherche +/- : duel")) {
						label3 = new JLabel(" OrdiCombianison:" + intervalle + " ");
					} else if (getMode().equalsIgnoreCase("MasterMind  : challenger")
							|| getMode().equalsIgnoreCase("MasterMind : duel")) {
						label3 = new JLabel(" OrdiCombianison:" + borne + " ");
					}

					else {
						label3 = new JLabel("OrdiCombianison : ????  ");
					}

				} else {
					label3 = new JLabel("OrdiCombianison : ????  ");
				}

				str = String.valueOf(intervalle);
				str2 = String.valueOf(intervalle);

				frame = new JFrame(titre);
				frame.setLayout(new BorderLayout());

				rejouer = new JMenuItem("rejouer");
				i1 = new JMenuItem("Inst-Challengeur");
				i2 = new JMenuItem("Inst-Défenseur");
				i3 = new JMenuItem("Inst-duel");

				instructions = new JMenu("Instructions");
				select = new JMenu("Selection");

				i1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								" - Vous avez la main, donnez une proposition pour trouver la combinaison secrète."
										+ "\n- La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				i2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- Attendez la proposition de l'ordinateur puis donnez des indications après"
										+ " \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				i3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- A tour de role, donnez votre proposition puis attendre les indications."
										+ " \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				ins = new JMenuItem[3];
				ins[0] = i1;
				ins[1] = i2;
				ins[2] = i3;

				for (JMenuItem i : ins) {
					instructions.add(i);
				}

				select.add(instructions);
				select.add(rejouer);

				label1 = new JLabel(" PlayerCombinaison:");
				label2 = new JLabel("  :" + nombre);

				bar = new JMenuBar();
				bar.setPreferredSize(new Dimension(800, 20));
				bar.add(select);
				bar.add(label3);
				bar.add(label1);
				bar.add(label2);
				frame.add(bar);
				frame.setJMenuBar(bar);

				newMessageTextField = new JTextField();

				newMessageTextField.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String message = newMessageTextField.getText();

						try {
							outPut.write(message);
							outPut.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						try {

							if (actifMode == false) {
								jeu = new RecherchePM();
								intervalle = borne;
								switch (getMode()) {
								case "Recherche +/- : challenger":
									if (message.length() < NombreDeChiffes) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ NombreDeChiffes + "\n");
										newMessageTextField.setText("");
									} else {
										if (nbressai >= nbr) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
											JOptionPane.showMessageDialog(null, "La solution est: " + str);

											logger.info("Fin de partie");
										}

										else {
											String m = jeu.donnerIndice(str, message);
											if (jeu.finDePartie(m) == true) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");

												logger.info("Fin de partie");
											} else {

												newMessageTextField.setText("");
												chatTextArea.append("MOI:  " + message + "\n");
												String str = String.valueOf(borne);
												chatTextArea.append("Ordinateur: " + m + "\n");
												nbressai++;

												logger.info(
														"Valeur entrée : " + message + "   Valeur à trouver : " + str);
												logger.info("essais " + nbressai);
											}

										}
									}

									break;
								case "Recherche +/- : défenseur":

									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

										JOptionPane.showMessageDialog(null, "La solution est: " + label2.getText());

										logger.info("Fin de partie");
									} else {
										if (jeu.finDePartie(message) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combianaison sécrète a été trouvée");

											logger.info("Fin de partie");
										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											str = jeu.joue(message, str);
											chatTextArea.append("Ordinateur: " + str + "\n");
											nbressai++;

											logger.info("Valeur donnée : " + str + "   Valeur à trouver : "
													+ label2.getText());
											logger.info("essais " + nbressai);
										}

									}

									break;
								case "Recherche +/- : duel":

									if (message.length() < NombreDeChiffes) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ NombreDeChiffes + "\n");
										newMessageTextField.setText("");
									} else {
										if (nbressai >= nbr) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null,
													"La solution etait : \n  - OridiCombianaison : " + str
															+ "\n - PlayerCombinaison :" + label2.getText());

											logger.info("Fin de partie");
										}

										else {

											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "   ------------->  ");

											String indiceO = jeu.donnerIndice(str, message);
											String fIindiceP = jeu.donnerIndice(label2.getText(), str2);

											String EndindiceP = jeu.donnerIndice(label2.getText(), str2);

											newMessageTextField.setText("");

											if (jeu.finDePartie(indiceO) == true) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");

												logger.info("Fin de partie");
											}
											if (jeu.finDePartie(EndindiceP) == true) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, votre combianaison sécrète a été trouvée");

												logger.info("Fin de partie");
											}
											chatTextArea.append("Ordinateur: " + indiceO + "\n");

											logger.info("Valeur entrée : " + message + "   Valeur à trouver : " + str);
											chatTextArea.append("Ordinateur: " + str2 + "   ------------->  ");
											chatTextArea.append("Moi: " + EndindiceP + "\n");

											logger.info("Valeur donnée : " + str2 + "   Valeur à trouver : "
													+ label2.getText());
											str2 = jeu.joue(fIindiceP, str2);
											nbressai++;

											logger.info("essais " + nbressai);
										}
									}

									break;
								default:
									break;
								}
							}

							if (actifMode == true) {

								jeu = new MasterMind();

								switch (getMode()) {

								case "MasterMind  : challenger":

									intervalle = borne;
									if (message.length() < NombreDeChiffes || message.length() > NombreDeChiffes) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ NombreDeChiffes + "\n");
										newMessageTextField.setText("");
									} else {
										if (nbressai >= nbr) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null, "La solution est: " + borne);

											logger.info("Fin de partie");
										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											String str = String.valueOf(intervalle);
											String m = jeu.donnerIndice(str, message);
											if (m.equalsIgnoreCase("4 présents, 4 bien placés")) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianison sécrète");

												logger.info("Fin de partie");
											} else {
												System.out.println(" " + str);
												chatTextArea.append("Ordinateur: " + m + "\n");
												nbressai++;

												logger.info(
														"Valeur entrée : " + message + "   Valeur à trouver : " + str);
												logger.info("essais " + nbressai);
											}

										}
									}

									break;
								case "MasterMind : défenseur":

									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

										JOptionPane.showMessageDialog(null, "La solution est: " + label2.getText());

										logger.info("Fin de partie");
									} else {
										if (message.equalsIgnoreCase("4 présents, 4 bien placés")) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combinaison a été trouvée.");

											logger.info("Fin de partie");

										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI: " + message + "\n");
											str = jeu.joue(str, message);
											chatTextArea.append("Ordinateur: " + str + "\n");
											nbressai++;

											logger.info("Valeur donnée : " + str + "   Valeur à trouver : "
													+ label2.getText());
											logger.info("essais " + nbressai);
										}

									}

									break;

								case "MasterMind : duel":

									if (message.length() < NombreDeChiffes || message.length() > NombreDeChiffes) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ NombreDeChiffes + "\n");
										newMessageTextField.setText("");
									} else {
										if (nbressai >= nbr) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null,
													"La solution etait : \n  - OridiCombianaison : " + borne
															+ "\n - PlayerCombinaison :" + label2.getText());
											logger.info("Fin de partie");
										} else {

											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "   ------->  ");

											String indiceO = jeu.donnerIndice(message, str);
											String fIindiceP = jeu.donnerIndice(label2.getText(), str2);

											String EndindiceP = jeu.joue(label2.getText(), str2);

											newMessageTextField.setText("");

											if (jeu.finDePartie(indiceO) == true) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");
												logger.info("Fin de partie");
											}
											if (jeu.finDePartie(EndindiceP) == true) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, votre combianaison sécrète a été trouvée");
												logger.info("Fin de partie");
											}
											chatTextArea.append("Ordinateur: " + indiceO + "\n");
											logger.info("Valeur entrée : " + message + "   Valeur à trouver : " + str);
											chatTextArea.append("Ordinateur: " + str2 + "   ------->  ");
											chatTextArea.append("Moi: " + fIindiceP + "\n");
											str2 = jeu.joue(str2, EndindiceP);
											nbressai++;
											logger.info("Valeur donnée : " + str2 + "   Valeur à trouver : "
													+ label2.getText());
											logger.info("essais " + nbressai);

										}

									}

									break;
								default:
									break;

								}
							}
						} catch (Exception e2) {
							if (actifMode == false) {
								if (message.length() > NombreDeChiffes || message.length() < NombreDeChiffes) {
									System.out.println("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ NombreDeChiffes + "\n");
									chatTextArea.append("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ NombreDeChiffes + "\n");
									newMessageTextField.setText("");
								}

							} else {
								if (str.length() > NombreDeChiffes || str.length() < NombreDeChiffes) {
									System.out.println("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ NombreDeChiffes + "\n");
									chatTextArea.append("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ NombreDeChiffes + "\n");
									newMessageTextField.setText("");
								}
							}
						}

					}
				});

				rejouer.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						chatTextArea.setText(" ");
						nbressai = 0;
						intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
						borne = conf.getValeurUtilisable(intervalle);
						intervalle = borne;
						if (getMode().equalsIgnoreCase("Recherche +/- : défenseur")
								|| getMode().equalsIgnoreCase("MasterMind : défenseur")) {
							chatTextArea.setText("Ordinateur: " + str + "\n");
							nbressai++;
							logger.info("Valeur donnée : " + str + "   Valeur à trouver : " + label2.getText());
							logger.info("essais " + nbressai);
						}

					}
				});

				frame.add(newMessageTextField, BorderLayout.SOUTH);

				chatTextArea = new JTextArea();
				chatTextArea.setEditable(false);
				chatscrollPane = new JScrollPane(chatTextArea);
				frame.add(chatscrollPane, BorderLayout.CENTER);

				if (getMode().equalsIgnoreCase("Recherche +/- : défenseur")
						|| getMode().equalsIgnoreCase("MasterMind : défenseur")) {
					chatTextArea.setText("Ordinateur: " + str + "\n");
					nbressai++;
					logger.info("Valeur donnée : " + str + "   Valeur à trouver : " + label2.getText());
					logger.info("essais " + nbressai);
				}

				frame.setResizable(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setMaximumSize(new Dimension(400, 400));
				frame.setMinimumSize(new Dimension(400, 400));
				frame.setPreferredSize(new Dimension(400, 400));
				frame.pack();
				frame.setVisible(true);

			}

		});

	}

	/**
	 * methode qui demande à l'utilisateur de rentrer sa combianaison sécrète
	 * 
	 */

	public void toFind() {

		String message = JOptionPane.showInputDialog(null, "Entrez la combinason secrète");
		while (message.length() > NombreDeChiffes || message.length() < NombreDeChiffes) {
			message = JOptionPane.showInputDialog(null, "Un chiffre d'une longueur de " + message.length()
					+ " a été saisi. Veuillez en saisir un d'une longueur de " + NombreDeChiffes);
		}
		label2.setText("" + message);

	}

	public void modeDeveloppeur() {
		label3.setText("CombinaisonO : " + intervalle);
	}

	/**
	 * getter recupérant le mode du jeu au quel on joue
	 * 
	 * @return String mode
	 */

	public String getMode() {
		return this.mode;
	}

	/**
	 * setter modifiant le mode du jeu
	 * 
	 * @param le mode
	 * 
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * getter qui renvoie le nombre minimun de cases de la combinaison secrète
	 * 
	 * @return un int
	 */
	public int getValeurMin() {
		return valeurMin;
	}

	/**
	 * setter qui change le nombre minimun de cases de la combinaison secrète
	 * 
	 * @param la valeur minimale
	 */

	public void setValeurMin(int valeurMin) {
		this.valeurMin = valeurMin;
	}

	/**
	 * getter qui renvoie le nombre maximun de cases de la combinaison secrète
	 * 
	 * @return un int
	 */
	public int getValeurMax() {
		return valeurMax;
	}

	/**
	 * setter qui change le nombre maximale de cases de la combinaison secrète
	 * 
	 * @param la valeur maximale
	 */

	public void setValeurMax(int valeurMax) {
		this.valeurMax = valeurMax;
	}

}
