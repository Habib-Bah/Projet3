package fr.projet3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class ModeDeJeu {

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
	static int x;
	int nbr = 10;
	int nbressai = 0;
	public static int randomValue;
	private String mode;
	String str;

	RecherchePM recherche = new RecherchePM();
	MisterMind mastermind = new MisterMind();
	FonctionAuxi fa = new FonctionAuxi();

	public ModeDeJeu(String titre, Writer outPut) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				x = 1000 + random.nextInt(9999 - 1000);
				str = String.valueOf(x);

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

					}
				});

				b2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setMode("Défenseur");

					}
				});

				b3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setMode("Duel");

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
								"- Pour lancer la partie, vous devez entrer une la combinaison ==== \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				i3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

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

						if (recherche.actif == true) {
							switch (getMode()) {
							case "Recherche +/- : challenger":
								if (nbressai > nbr) {
									JOptionPane.showMessageDialog(null,
											"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
								} else {
									newMessageTextField.setText("");
									chatTextArea.append("MOI:  " + message + "\n");
									String str = String.valueOf(x);
									System.out.println(" " + str);
									String m = fa.getIndice(str, message);
									chatTextArea.append("Ordinateur: " + m + "\n");
									nbressai++;
								}
								break;
							case "Recherche +/- : défenseur":
								if (nbressai > nbr) {
									JOptionPane.showMessageDialog(null,
											"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
								} else {
									newMessageTextField.setText("");
									chatTextArea.append("MOI:  " + message + "\n");
									str = fa.getNewCombinaison(message, str);
									chatTextArea.append("Ordinateur: " + str + "\n");
									nbressai++;

								}
							case "Recherche +/- : duel":
								break;
							default:
								break;
							}
						}

						else if (mastermind.actif == true) {
							switch (getMode()) {
							case "MisterMind : challenger":
								if (nbressai > nbr) {
									JOptionPane.showMessageDialog(null,
											"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
								} else {
									newMessageTextField.setText("");
									chatTextArea.append("MOI:  " + message + "\n");
									String str = String.valueOf(x);
									String m = fa.getIndice(str, message);
									chatTextArea.append("Ordinateur: " + m + "\n");
									nbressai++;
								}
								break;
							case "MisterMind : défenseur":
								if (nbressai > nbr) {
									JOptionPane.showMessageDialog(null,
											"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
								} else {
									newMessageTextField.setText("");
									chatTextArea.append("MOI:  " + message + "\n");
									str = fa.getNewCombinaison(message, str);
									chatTextArea.append("Ordinateur: " + str + "\n");
									nbressai++;

								}
							case "MisterMind : duel":
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
						x = 1000 + random.nextInt(9999 - 1000);
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

	// Demande puis met à jour la combinaison secrete
	public void toFind() {
		String message = JOptionPane.showInputDialog("Entrer le nombre à trouver");
		label2.setText(" " + message);
	}

	// Getter et Setter pour le mode de jeu
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
