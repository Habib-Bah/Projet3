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
	JMenu select;
	JMenuItem[] it;
	JMenuItem[] ins;
	JMenu instructions;
	JMenuItem b1;
	JMenuItem b2;
	JMenuItem b3;
	JMenuItem i1;
	JMenuItem i2;
	JMenuItem i3;

	String nombre;
	JTextField newMessageTextField;
	JTextArea chatTextArea;
	JScrollPane chatscrollPane;
	JMenuBar bar;
	JButton rejouer = new JButton("Rejouer");
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

	RecherchePM recherche = new RecherchePM();
	MisterMind mastermind = new MisterMind();

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
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
				str = String.valueOf(intervalle);
				str2 = String.valueOf(intervalle);

				frame = new JFrame(titre);
				frame.setLayout(new BorderLayout());

				b1 = new JMenuItem("Challengeur");
				b2 = new JMenuItem("Défenseur");
				b3 = new JMenuItem("duel");

				i1 = new JMenuItem("Inst-Challengeur");
				i2 = new JMenuItem("Inst-Défenseur");
				i3 = new JMenuItem("Inst-duel");

				select = new JMenu("Mode");
				instructions = new JMenu("Instructions");

				b1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setMode("Challengeur");
						nbressai = 0;
						chatTextArea.setText("");
						intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
						str = String.valueOf(intervalle);
						str2 = String.valueOf(intervalle);
						frame.setTitle("Challengeur");
					}
				});

				b2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setMode("Défenseur");
						nbressai = 0;
						chatTextArea.setText(" ");
						intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
						str = String.valueOf(intervalle);
						str2 = String.valueOf(intervalle);
						frame.setTitle("Défenseur");
					}
				});

				b3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setMode("Duel");
						nbressai = 0;
						chatTextArea.setText("");
						intervalle = getValeurMin() + random.nextInt(getValeurMax() - getValeurMin());
						str = String.valueOf(intervalle);
						str2 = String.valueOf(intervalle);
						frame.setTitle("Duel");

					}
				});

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

				it = new JMenuItem[3];
				it[0] = b1;
				it[1] = b2;
				it[2] = b3;

				ins = new JMenuItem[3];
				ins[0] = i1;
				ins[1] = i2;
				ins[2] = i3;

				for (JMenuItem i : it)
					select.add(i);

				for (JMenuItem i : ins) {
					instructions.add(i);
				}

				label1 = new JLabel(" Combinaison:");
				label2 = new JLabel("  :" + nombre);

				bar = new JMenuBar();
				bar.setPreferredSize(new Dimension(800, 20));
				bar.add(instructions);
				bar.add(select);
				bar.add(rejouer);
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

						if (actifMode == false) {
							switch (getMode()) {
							case "Recherche +/- : challenger":
								if (nbressai >= nbr) {
									JOptionPane.showMessageDialog(null,
											"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
									log.ChangeInfoLog("Fin de partie");
								}

								else {
									String m = recherche.donnerIndice(str, message);
									if (recherche.finDePartie(m) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez trouvé la combianaison sécrète");
										log.ChangeInfoLog("Fin de partie");
									} else {

										newMessageTextField.setText("");
										chatTextArea.append("MOI:  " + message + "\n");
										String str = String.valueOf(intervalle);
										System.out.println(" " + str);
										chatTextArea.append("Ordinateur: " + m + "\n");
										nbressai++;
										log.ChangeInfoLog("Valeur entré : " + message + "   Valeur à trouver : " + str);
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
									if (recherche.finDePartie(message) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, votre combianaison sécrète a été trouvée");
										log.ChangeInfoLog("Fin de partie");
									} else {
										newMessageTextField.setText("");
										chatTextArea.append("MOI:  " + message + "\n");
										str = recherche.joue(message, str);
										chatTextArea.append("Ordinateur: " + str + "\n");
										nbressai++;
										log.ChangeInfoLog(
												"Valeur donnée : " + str + "   Valeur à trouver : " + label2.getText());
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

									String indiceO = recherche.donnerIndice(str, message);
									String fIindiceP = recherche.donnerIndice(label2.getText(), str2);

									String EndindiceP = recherche.donnerIndice(label2.getText(), str2);
									System.out.println(" " + str2);

									newMessageTextField.setText("");

									if (recherche.finDePartie(indiceO) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez trouvé la combianaison sécrète");
										log.ChangeInfoLog("Fin de partie");
									} else if (recherche.finDePartie(EndindiceP) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, votre combianaison sécrète a été trouvée");
										log.ChangeInfoLog("Fin de partie");
									} else {
										chatTextArea.append("Ordinateur: " + indiceO + "\n");
										log.ChangeInfoLog(
												"Valeur entrée : " + message + "   Valeur à trouver : " + str);
										chatTextArea.append("Ordinateur: " + str2 + "   ------------->  ");
										chatTextArea.append("Moi: " + EndindiceP + "\n");
										log.ChangeInfoLog("Valeur donnée : " + str2 + "   Valeur à trouver : "
												+ label2.getText());
										str2 = recherche.joue(fIindiceP, str2);
										nbressai++;
										log.ChangeInfoLog("essais " + nbressai);
									}

								}
								break;
							default:
								break;
							}
						}

						else if (actifMode == true) {
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
									String m = mastermind.donnerIndice(str, message);
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
										str = mastermind.joue(str, message);
										System.out.println(" " + str);
										chatTextArea.append("Ordinateur: " + str + "\n");
										nbressai++;
										log.ChangeInfoLog(
												"Valeur donnée : " + str + "   Valeur à trouver : " + label2.getText());
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

									String indiceO = mastermind.donnerIndice(message, str);
									String fIindiceP = mastermind.donnerIndice(label2.getText(), str2);

									String EndindiceP = mastermind.joue(label2.getText(), str2);

									System.out.println(" " + str);

									newMessageTextField.setText("");

									if (mastermind.finDePartie(indiceO) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, vous avez trouvé la combianaison sécrète");
										log.ChangeInfoLog("Fin de partie");
									} else if (mastermind.finDePartie(EndindiceP) == true) {
										JOptionPane.showMessageDialog(null,
												"Fin de partie, votre combianaison sécrète a été trouvée");
										log.ChangeInfoLog("Fin de partie");
									} else {
										chatTextArea.append("Ordinateur: " + indiceO + "\n");
										log.ChangeInfoLog("Valeur entré : " + message + "   Valeur à trouver : " + str);
										chatTextArea.append("Ordinateur: " + str2 + "   ------->  ");
										chatTextArea.append("Moi: " + EndindiceP + "\n");
										str2 = mastermind.joue(str2, fIindiceP);
										log.ChangeInfoLog("Valeur donnée : " + str2 + "   Valeur à trouver : "
												+ label2.getText());
										nbressai++;
										log.ChangeInfoLog("essais " + nbressai);
									}

								}
								break;
							default:
								break;
							}

						} else {
							JOptionPane.showMessageDialog(null, "Mauvaise selection");
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
