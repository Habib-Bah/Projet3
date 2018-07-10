package fr.projet3.oc;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

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
	Logging log = new Logging("Début de la partie");
	String modeDeveloppeur;
	int borne;

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
					Configurations conf = new Configurations();
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

				if (modeDeveloppeur.equalsIgnoreCase("oui")) {
					if (getMode().equalsIgnoreCase("Recherche +/- : challenger")
							|| getMode().equalsIgnoreCase("MisterMind  : challenger")
							|| getMode().equalsIgnoreCase("Recherche +/- : duel")
							|| getMode().equalsIgnoreCase("MisterMind : duel")) {
						label3 = new JLabel(" CombianisonOrdi:" + intervalle + " ");
					} else {
						label3 = new JLabel("CombianisonOrdi : ????  ");
					}

				} else {
					label3 = new JLabel("CombianisonOrdi : ????  ");
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
								" - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				i2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- Pour lancer la partie, vous devez entrer une la combinaison =+-+ \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				i3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- Pour lancer la partie, vous devez entrer votre proposition =+-+ \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

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

				label1 = new JLabel(" CombinaisonPlayer:");
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
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
									}

									else {
										String m = jeu.donnerIndice(str, message);
										if (jeu.finDePartie(m) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez trouvé la combianaison sécrète");
											log.ChangeInfoLog("Fin de partie");
										} else {

											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											String str = String.valueOf(borne);
											System.out.println(" " + str);
											chatTextArea.append("Ordinateur: " + m + "\n");
											nbressai++;
											log.ChangeInfoLog(
													"Valeur entrée : " + message + "   Valeur à trouver : " + str);
											log.ChangeInfoLog("essais " + nbressai);
										}

									}
									break;
								case "Recherche +/- : défenseur":
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
									} else {
										if (jeu.finDePartie(message) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combianaison sécrète a été trouvée");
											log.ChangeInfoLog("Fin de partie");
										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											str = jeu.joue(message, str);
											chatTextArea.append("Ordinateur: " + str + "\n");
											nbressai++;
											log.ChangeInfoLog("Valeur donnée : " + str + "   Valeur à trouver : "
													+ label2.getText());
											log.ChangeInfoLog("essais " + nbressai);
										}

									}
									break;
								case "Recherche +/- : duel":
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
									}

									else {

										newMessageTextField.setText("");
										chatTextArea.append("MOI:  " + message + "   ------------->  ");

										String indiceO = jeu.donnerIndice(str, message);
										String fIindiceP = jeu.donnerIndice(label2.getText(), str2);

										String EndindiceP = jeu.donnerIndice(label2.getText(), str2);
										System.out.println(" " + str2);

										newMessageTextField.setText("");

										if (jeu.finDePartie(indiceO) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez trouvé la combianaison sécrète");
											log.ChangeInfoLog("Fin de partie");
										}
										if (jeu.finDePartie(EndindiceP) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combianaison sécrète a été trouvée");
											log.ChangeInfoLog("Fin de partie");
										}
										chatTextArea.append("Ordinateur: " + indiceO + "\n");
										log.ChangeInfoLog(
												"Valeur entrée : " + message + "   Valeur à trouver : " + str);
										chatTextArea.append("Ordinateur: " + str2 + "   ------------->  ");
										chatTextArea.append("Moi: " + EndindiceP + "\n");
										log.ChangeInfoLog("Valeur donnée : " + str2 + "   Valeur à trouver : "
												+ label2.getText());
										str2 = jeu.joue(fIindiceP, str2);
										nbressai++;
										log.ChangeInfoLog("essais " + nbressai);
									}

									break;
								default:
									break;
								}
							}

							else if (actifMode == true) {
								jeu = new MisterMind();
								intervalle = borne;
								switch (getMode()) {
								case "MisterMind  : challenger":
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
									} else {
										newMessageTextField.setText("");
										chatTextArea.append("MOI:  " + message + "\n");
										String str = String.valueOf(intervalle);
										System.out.println(" " + str);
										String m = jeu.donnerIndice(str, message);
										if (m.equalsIgnoreCase("4 présents, 4 bien placés")) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez trouvé la combianison sécrète");
											log.ChangeInfoLog("Fin de partie");
										} else {
											chatTextArea.append("Ordinateur: " + m + "\n");
											nbressai++;
											log.ChangeInfoLog(
													"Valeur entrée : " + message + "   Valeur à trouver : " + str);
											log.ChangeInfoLog("essais " + nbressai);
										}

									}
									break;
								case "MisterMind : défenseur":
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
									} else {
										if (message.equalsIgnoreCase("4 présents, 4 bien placés")) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combinaison a été trouvée.");
											log.ChangeInfoLog("Fin de partie");

										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI: " + message + "\n");
											str = jeu.joue(str, message);
											chatTextArea.append("Ordinateur: " + str + "\n");
											nbressai++;
											log.ChangeInfoLog("Valeur donnée : " + str + "   Valeur à trouver : "
													+ label2.getText());
											log.ChangeInfoLog("essais " + nbressai);
										}

									}
									break;

								case "MisterMind : duel":
									if (nbressai >= nbr) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
										log.ChangeInfoLog("Fin de partie");
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
											log.ChangeInfoLog("Fin de partie");
										}
										if (jeu.finDePartie(EndindiceP) == true) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combianaison sécrète a été trouvée");
											log.ChangeInfoLog("Fin de partie");
										}
										chatTextArea.append("Ordinateur: " + indiceO + "\n");
										log.ChangeInfoLog(
												"Valeur entrée : " + message + "   Valeur à trouver : " + str);
										chatTextArea.append("Ordinateur: " + str2 + "   ------->  ");
										chatTextArea.append("Moi: " + fIindiceP + "\n");
										str2 = jeu.joue(str2, EndindiceP);
										log.ChangeInfoLog("Valeur donnée : " + str2 + "   Valeur à trouver : "
												+ label2.getText());
										nbressai++;
										log.ChangeInfoLog("essais " + nbressai);
									}

									break;
								default:
									break;
								}

							}

						} catch (Exception e2) {
							System.out.println(
									" \n Erreur de valeur. \n Veuillez consulter le fichier de configuration pour connaitre \n le nombre de chiffres autorisé");

						}
					}
				});

				rejouer.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						chatTextArea.setText(" ");
						nbressai = 0;
						intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
					}
				});

				frame.add(newMessageTextField, BorderLayout.SOUTH);

				chatTextArea = new JTextArea();
				chatTextArea.setEditable(false);
				chatscrollPane = new JScrollPane(chatTextArea);
				frame.add(chatscrollPane, BorderLayout.CENTER);

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
		String message = JOptionPane.showInputDialog("Entrer la combinaison secrète");
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
