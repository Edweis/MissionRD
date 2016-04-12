package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import utilisateur.Admin;
import utilisateur.Bdd_utilisateur;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class I_connexionn {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_connexionn window = new I_connexionn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public I_connexionn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(61, 75, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 106, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(40, 109, 70, 14);
		frame.getContentPane().add(lblMotDePasse);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String prenom=textField_2.getText();
			String nom= textField.getText();
			Bdd_utilisateur a = new Bdd_utilisateur();
			try {
				a.connecter("root", "");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String requete="SELECT * from utilisateur where nom='"+nom+"'AND prenom='"+prenom+"'";
			String vrai_mdp=null;
			try {
				ResultSet resultat= a.lecture(requete);
				
				while(resultat.next()){
					 vrai_mdp =resultat.getString("mdp");
				}
				String mdp_ecris= textField_1.getText();
			if(textField_2.getText().equals("admin") && textField.getText().equals("admin") && textField_1.getText().equals("admin")) {
				Admin admin= new Admin();
				I_administrateur b=new I_administrateur(a,admin);
				b.run();
			}else if(vrai_mdp != null && vrai_mdp.equals(mdp_ecris)){
					System.out.println("vous pouvez vous connecter");
				
				}else{
					System.out.println("enfoiré");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("erreur");
			}
			
			}
		});
		btnSeConnecter.setBounds(102, 138, 103, 23);
		frame.getContentPane().add(btnSeConnecter);
		
		lblNom = new JLabel("Nom");
		lblNom.setBounds(30, 78, 46, 14);
		frame.getContentPane().add(lblNom);
		
		lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(160, 78, 46, 14);
		frame.getContentPane().add(lblPrnom);
		
		textField_2 = new JTextField();
		textField_2.setBounds(216, 75, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
	}
}
