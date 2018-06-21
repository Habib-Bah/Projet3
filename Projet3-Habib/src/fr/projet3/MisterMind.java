package fr.projet3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class MisterMind {

	private JFrame frame;
	private JLabel label1;
	private JLabel label2;
	String nombre;
	JTextField newMessageTextField;
	JTextArea chatTextArea;
	JScrollPane chatscrollPane;
	JMenuBar bar;
	JButton instruction = new JButton("Instruction");
	JButton rejouer = new JButton("Rejouer");
	Random random = new Random();
	static int x;
	int nbr = 10;
	int nbressai = 0;
	public static int randomValue;
	Map<String, Integer> map1 = new HashMap<>();
	public static String tab[];
	public static String finaltab[];
	public static String finalString;
	public static String messageToShow;
	private String mode;
	FonctionAuxi fa = new FonctionAuxi();

	public MisterMind(String titre, Writer outPut) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				x = 1000 + random.nextInt(9999 - 1000);

				frame = new JFrame(titre);
				frame.setLayout(new BorderLayout());

				label1 = new JLabel(" nombre à trouvé");
				label2 = new JLabel("  :" + nombre);

				bar = new JMenuBar();
				bar.setPreferredSize(new Dimension(800, 20));
				bar.add(instruction);
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

						if (nbressai > nbr) {
							JOptionPane.showMessageDialog(null,
									"Fin de partie, vous avez atteint le nombre d'essaie autorisé");
						} else {
							newMessageTextField.setText("");
							chatTextArea.append("MOI:  " + message + "\n");
							tab = message.split(" ");
							String string = fa.transform(x);
							finaltab = string.split(" ");
							messageToShow = fa.getCombinaison(finaltab, tab, finalString);

							appendMessage(messageToShow);
							nbressai++;

						}
					}
				});

				instruction.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"- Chacune de vos indications doit imperativement etre séparé par des espaces : exemple + - = = \n - Pour lancer la partie, vous devez entrer une la combinaison = = = = \n - La partie termine lorsque la combinaison sécrète a été trouvée ou lorsque le nombre d'essaie autorisé a été atteint.");

					}
				});

				rejouer.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						chatTextArea.setText(" ");
						nbressai = 0;
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

	//Getter et Setter pour le mode de jeu
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
