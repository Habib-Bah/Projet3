package fr.projet3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class RechechePM {

	private JFrame frame;
	private JLabel label1;
	private JLabel label2;
	JMenu select;
	JMenuItem[] it;
	JMenuItem [] ins;
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
	Map<String, Integer> map1 = new HashMap<>();
	public static String tab[];
	public static String finaltab[];
	public String finalString = "";
	public static String messageToShow;
	private String mode;
	String msg;
	FonctionAuxi fa = new FonctionAuxi();

	public RechechePM(String titre, Writer outPut) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				x = 1000 + random.nextInt(9999 - 1000);

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
								"- Chacune de vos combinaisons doit imperativement etre séparé par des espaces : exemple 1 2 3 4 \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");
						
						
					}
				});
				
				i2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- Chacune de vos indications doit imperativement etre séparé par des espaces : exemple + - = = \n - Pour lancer la partie, vous devez entrer une la combinaison = = = = \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

						
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
				
				for(JMenuItem i : it)
					select.add(i);
				
				for(JMenuItem i : ins) {
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

						switch (getMode()) {
						case "challenger":
							if (nbressai > nbr) {
								JOptionPane.showMessageDialog(null,
										"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
							} else {
								newMessageTextField.setText("");
								chatTextArea.append("MOI:  " + message + "\n");
								tab = message.split(" ");
								msg = fa.transform(x);
								// finaltab = msg.split(" ");
								// messageToShow = fa.getCombinaison(finaltab, tab, finalString);
								messageToShow = fa.getComputerAns(message, msg);

								appendMessage(messageToShow);
								nbressai++;
							}
							break;
						case "défenseur":
							if (nbressai > nbr) {
								JOptionPane.showMessageDialog(null,
										"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
							} else {
								newMessageTextField.setText("");
								chatTextArea.append("MOI:  " + message + "\n");
								tab = message.split(" ");
								msg = fa.transform(x);
								finaltab = msg.split(" ");
								messageToShow = fa.getCombinaison(finaltab, tab, finalString);

								appendMessage(messageToShow);
								nbressai++;
								String[] renew = messageToShow.split(" ");
								int l = fa.generateNewInt(renew);
								 x = fa.generateNewInt(renew);
								System.out.println(l);
							}
						case "duel":
							break;
						default:
							break;
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

	// Affiche la reponse de l'utisateur
	public void appendMessage(String message) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				chatTextArea.append("Ordinateur:  " + message + "\n");
			}
		});

	}

	// Demande puis met à jour la combinaison secrete
	public void toFind() {
		String message = JOptionPane.showInputDialog("Entrer le nombre à trouver");
		label2.setText(" " + message);
	}

	/*
	 * D'un tableau de string obtenir un String Cette fonction me permet une fois le
	 * split effectuer De recuperer le string correspondant public String
	 * getString(String [] s) { String st = ""; for(int i = 0; i < s.length; i++) {
	 * st = st+s[i]; } return st; }
	 */

	// Getter et Setter pour le mode de jeu
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
