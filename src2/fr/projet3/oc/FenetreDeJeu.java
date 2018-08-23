package fr.projet3.oc;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

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

	JTextField newMessageTextField;
	JTextArea chatTextArea;
	JScrollPane chatscrollPane;
	JMenuBar bar;
	// Variable qui indique le mode au quel on joue sur la fenetre (Challengeur,
	// defenseur ou duel)
	private String mode;
	// Les deux variables servent à stocker les valeurs qui serons traités dans le
	// mode duel pour le joueur et l'ordinateur
	String variableIntermediaire;
	String variableIntermediare2;
	// Variable qui nous indique le mode actif sur la fenetre(R pour Recherche +/-
	// et M pour MasterMind)
	String actifMode;
	Configurations conf;
	Jeu jeu;

	/**
	 * Constructeur de la classe
	 * 
	 */

	public FenetreDeJeu(String titre, Writer outPut) {

		SwingUtilities.invokeLater(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {

				try {
					conf = new Configurations();
				} catch (IOException e3) {
					e3.printStackTrace();
				}

				conf.genererCombinaison();
				variableIntermediaire = conf.getCombinaison();
				variableIntermediare2 = conf.getCombinaison();
				Logger logger = Logger.getLogger(Launcher.class);
				if (conf.getModeDeveloppeur().equalsIgnoreCase("oui")) {
					if (getMode().equalsIgnoreCase("Recherche +/- : challenger")
							|| getMode().equalsIgnoreCase("Recherche +/- : duel")) {
						label3 = new JLabel(" CombinaisonOrdi:" + variableIntermediaire + " ");
					} else if (getMode().equalsIgnoreCase("MasterMind  : challenger")
							|| getMode().equalsIgnoreCase("MasterMind : duel")) {
						label3 = new JLabel(" CombinaisonOrdi:"
								+ conf.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())) + " ");
					}

					else {
						label3 = new JLabel("CombinaisonOrdi : ????  ");
					}

				} else {
					label3 = new JLabel("CombinaisonOrdi : ????  ");
				}

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

				label1 = new JLabel(" CombinaisonJoueur:");
				label2 = new JLabel("  :" + "????");

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

							if (actifMode == "R") {
								jeu = new RecherchePM();
								switch (getMode()) {
								case "Recherche +/- : challenger":
									if (message.length() < conf.getNombreDeChiffre()) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ conf.getNombreDeChiffre() + "\n");
										newMessageTextField.setText("");
									} else {
										if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
											JOptionPane.showMessageDialog(null,
													"La solution est: " + variableIntermediaire);

											logger.info("Fin de partie");
										}

										else {
											String m = jeu.donnerIndice(variableIntermediaire, message);
											if (message.equalsIgnoreCase(variableIntermediaire)) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");

												logger.info("Fin de partie");
											} else {

												newMessageTextField.setText("");
												chatTextArea.append("MOI:  " + message + "\n");
												String str = String.valueOf(conf.genererCombinaison());
												chatTextArea.append("Ordinateur: " + m + "\n");
												jeu.nombreEssaiEffectuer++;

												logger.info(
														"Valeur entrée : " + message + "   Valeur à trouver : " + str);
												logger.info("essais " + jeu.nombreEssaiEffectuer);
											}

										}
									}

									break;
								case "Recherche +/- : défenseur":

									if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

										JOptionPane.showMessageDialog(null, "La solution est: " + label2.getText());

										logger.info("Fin de partie");
									} else {
										if (variableIntermediaire.equalsIgnoreCase(label2.getText())) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combianaison sécrète a été trouvée");

											logger.info("Fin de partie");
										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											variableIntermediaire = jeu.joue(message, variableIntermediaire);
											chatTextArea.append("Ordinateur: " + variableIntermediaire + "\n");
											jeu.nombreEssaiEffectuer++;

											logger.info("Valeur donnée : " + variableIntermediaire
													+ "   Valeur à trouver : " + label2.getText());
											logger.info("essais " + jeu.nombreEssaiEffectuer);
										}

									}

									break;
								case "Recherche +/- : duel":

									if (message.length() < conf.getNombreDeChiffre()) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ conf.getNombreDeChiffre() + "\n");
										newMessageTextField.setText("");
									} else {
										if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null,
													"La solution etait : \n  - CombinaisonDeLOrdi : "
															+ variableIntermediaire + "\n - CombinaisonDuJoueur :"
															+ label2.getText());

											logger.info("Fin de partie");
										}

										else {

											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "   ------------->  ");

											String indiceO = jeu.donnerIndice(variableIntermediaire, message);
											String fIindiceP = jeu.donnerIndice(label2.getText(),
													variableIntermediare2);

											String EndindiceP = jeu.donnerIndice(label2.getText(),
													variableIntermediare2);

											newMessageTextField.setText("");

											if (message.equalsIgnoreCase(variableIntermediaire)) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");

												logger.info("Fin de partie");
											}
											if (variableIntermediare2.equalsIgnoreCase(label2.getText())) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, votre combianaison sécrète a été trouvée");

												logger.info("Fin de partie");
											}
											chatTextArea.append("Ordinateur: " + indiceO + "\n");

											logger.info("Valeur entrée : " + message + "   Valeur à trouver : "
													+ variableIntermediaire);
											chatTextArea.append(
													"Ordinateur: " + variableIntermediare2 + "   ------------->  ");
											chatTextArea.append("Moi: " + EndindiceP + "\n");

											logger.info("Valeur donnée : " + variableIntermediare2
													+ "   Valeur à trouver : " + label2.getText());
											variableIntermediare2 = jeu.joue(fIindiceP, variableIntermediare2);
											jeu.nombreEssaiEffectuer++;

											logger.info("essais " + jeu.nombreEssaiEffectuer);
										}
									}

									break;
								default:
									break;
								}
							}

							if (actifMode == "M") {

								jeu = new MasterMind();

								switch (getMode()) {

								case "MasterMind  : challenger":

									if (message.length() < conf.getNombreDeChiffre()
											|| message.length() > conf.getNombreDeChiffre()) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ conf.getNombreDeChiffre() + "\n");
										newMessageTextField.setText("");
									} else {
										if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null, "La solution est: " + conf
													.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())));

											logger.info("Fin de partie");
										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "\n");
											String str = String.valueOf(
													conf.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())));
											String m = jeu.donnerIndice(str, message);
											if (message.equalsIgnoreCase(str)) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianison sécrète");

												logger.info("Fin de partie");
											} else {
												System.out.println(" " + str);
												chatTextArea.append("Ordinateur: " + m + "\n");
												jeu.nombreEssaiEffectuer++;

												logger.info(
														"Valeur entrée : " + message + "   Valeur à trouver : " + str);
												logger.info("essais " + jeu.nombreEssaiEffectuer);
											}

										}
									}

									break;
								case "MasterMind : défenseur":
									conf.setCombinaison(Integer.toString(
											conf.getValeurUtilisable(Integer.parseInt(conf.genererCombinaison()))));
									variableIntermediaire = conf.getCombinaison();

									if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

										JOptionPane.showMessageDialog(null, "La solution est: " + label2.getText());

										logger.info("Fin de partie");
									} else {
										if (variableIntermediaire.equalsIgnoreCase(label2.getText())) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, votre combinaison a été trouvée.");

											logger.info("Fin de partie");

										} else {
											newMessageTextField.setText("");
											chatTextArea.append("MOI: " + message + "\n");
											chatTextArea.append("Ordinateur: " + variableIntermediaire + "\n");
											jeu.nombreEssaiEffectuer++;

											logger.info("Valeur donnée : " + variableIntermediaire
													+ "   Valeur à trouver : " + label2.getText());
											logger.info("essais " + jeu.nombreEssaiEffectuer);
										}

									}

									break;

								case "MasterMind : duel":

									if (message.length() < conf.getNombreDeChiffre()) {
										chatTextArea.append("Un chiffre d'une longueur de " + message.length()
												+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
												+ conf.getNombreDeChiffre() + "\n");
										newMessageTextField.setText("");
									} else {
										if (jeu.nombreEssaiEffectuer >= conf.getNombreEssaieAutorise()) {
											JOptionPane.showMessageDialog(null,
													"Fin de partie, vous avez atteint le nombre d'essaie autorisé");

											JOptionPane.showMessageDialog(null,
													"La solution etait : \n  - CombinaisonDeLOrdi : "
															+ variableIntermediaire + "\n - CombinaisonDuJoueur :"
															+ label2.getText());

											logger.info("Fin de partie");
										}

										else {

											newMessageTextField.setText("");
											chatTextArea.append("MOI:  " + message + "   ------------->  ");

											String indiceO = jeu.donnerIndice(variableIntermediaire, message);
											@SuppressWarnings("unused")
											String fIindiceP = jeu.donnerIndice(label2.getText(),
													variableIntermediare2);

											String EndindiceP = jeu.donnerIndice(label2.getText(),
													variableIntermediare2);

											newMessageTextField.setText("");

											if (message.equalsIgnoreCase(variableIntermediaire)) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, vous avez trouvé la combianaison sécrète");

												logger.info("Fin de partie");
											}
											if (variableIntermediare2.equalsIgnoreCase(label2.getText())) {
												JOptionPane.showMessageDialog(null,
														"Fin de partie, votre combianaison sécrète a été trouvée");

												logger.info("Fin de partie");
											}
											chatTextArea.append("Ordinateur: " + indiceO + "\n");

											logger.info("Valeur entrée : " + message + "   Valeur à trouver : "
													+ variableIntermediaire);
											chatTextArea.append(
													"Ordinateur: " + variableIntermediare2 + "   ------------->  ");
											chatTextArea.append("Moi: " + EndindiceP + "\n");

											logger.info("Valeur donnée : " + variableIntermediare2
													+ "   Valeur à trouver : " + label2.getText());
											variableIntermediare2 = Integer.toString(
													conf.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())));
											conf.setCombinaison(Integer.toString(conf
													.getValeurUtilisable(Integer.parseInt(conf.genererCombinaison()))));
											variableIntermediaire = conf.getCombinaison();
											jeu.nombreEssaiEffectuer++;

											logger.info("essais " + jeu.nombreEssaiEffectuer);
										}
									}

									break;
								default:
									break;

								}
							}
						} catch (Exception e2) {
							if (actifMode == "R") {
								if (message.length() > conf.getNombreDeChiffre()
										|| message.length() < conf.getNombreDeChiffre()) {
									System.out.println("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ conf.getNombreDeChiffre() + "\n");
									chatTextArea.append("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ conf.getNombreDeChiffre() + "\n");
									newMessageTextField.setText("");
								}

							} else {
								if (message.length() > conf.getNombreDeChiffre()
										|| message.length() < conf.getNombreDeChiffre()) {
									System.out.println("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ conf.getNombreDeChiffre() + "\n");
									chatTextArea.append("Un chiffre d'une longueur de " + message.length()
											+ " a été saisi. \nVeuillez en saisir un d'une longueur de "
											+ conf.getNombreDeChiffre() + "\n");
									newMessageTextField.setText("");
								}
							}
						}
					}

				});

				rejouer.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						chatTextArea.setText(" ");
						jeu.nombreEssaiEffectuer = 0;
						conf.genererCombinaison();
						variableIntermediaire = conf.getCombinaison();

						if (getMode().equalsIgnoreCase("Recherche +/- : challenger")) {
							label3.setText("CombinaisonOrdi :" + conf.getCombinaison() + " ");
						}
						if (getMode().equalsIgnoreCase("MasterMind  : challenger")) {
							label3.setText("CombinaisonOrdi :"
									+ conf.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())) + " ");
						}

						if (getMode().equalsIgnoreCase("Recherche +/- : défenseur")
								|| getMode().equalsIgnoreCase("MasterMind : défenseur")) {
							conf.setCombinaison(conf.genererCombinaison());
							chatTextArea.setText("Ordinateur: " + conf.getCombinaison() + "\n");
							jeu.nombreEssaiEffectuer++;
							logger.info("Valeur donnée : " + variableIntermediaire + "   Valeur à trouver : "
									+ label2.getText());
							logger.info("essais " + jeu.nombreEssaiEffectuer);
						}

						if (getMode().equalsIgnoreCase("Recherche +/- : duel")) {
							conf.setCombinaison(conf.genererCombinaison());
							label3.setText("CombinaisonOrdi :" + conf.getCombinaison() + " ");
						}
						if (getMode().equalsIgnoreCase("MasterMind : duel")) {
							label3.setText("CombinaisonOrdi :"
									+ conf.getValeurUtilisable(Integer.parseInt(conf.getCombinaison())) + " ");
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
					chatTextArea.setText("Ordinateur: " + variableIntermediaire + "\n");
					jeu.nombreEssaiEffectuer++;
					logger.info(
							"Valeur donnée : " + variableIntermediaire + "   Valeur à trouver : " + label2.getText());
					logger.info("essais " + jeu.nombreEssaiEffectuer);
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
		while (message.length() > conf.getNombreDeChiffre() || message.length() < conf.getNombreDeChiffre()) {
			message = JOptionPane.showInputDialog(null, "Un chiffre d'une longueur de " + message.length()
					+ " a été saisi. Veuillez en saisir un d'une longueur de " + conf.getNombreDeChiffre());
		}
		label2.setText("" + message);

	}

	public void modeDeveloppeur() {
		label3.setText("CombinaisonOrdi : " + conf.getCombinaison());
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
}
