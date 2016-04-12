package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import utilisateur.Admin;
import utilisateur.Bdd_utilisateur;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class I_administrateur {

	private JFrame frame;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtMdp;
	private JTextField txtGrade;
	private JTextField txtNom_1;
	private JTextField txtPrenom_1;
	private JTextField txtMdp_1;
	private JTextField txtGrade_1;
	private Bdd_utilisateur bdd;
	private Admin admin;
	private JTextField txtNom_2;
	private JTextField txtPrenom_2;
	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					I_administrateur window = new I_administrateur(bdd,admin);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 */
	public I_administrateur(Bdd_utilisateur bdd,Admin admin) {
		this.bdd=bdd;
		this.admin=admin;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNom = new JTextField();
		txtNom.setText("nom");
		txtNom.setBounds(10, 160, 86, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setText("prenom");
		txtPrenom.setBounds(106, 160, 86, 20);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtMdp = new JTextField();
		txtMdp.setText("mdp");
		txtMdp.setBounds(201, 160, 86, 20);
		frame.getContentPane().add(txtMdp);
		txtMdp.setColumns(10);
		
		txtGrade = new JTextField();
		txtGrade.setText("grade");
		txtGrade.setBounds(297, 160, 86, 20);
		frame.getContentPane().add(txtGrade);
		txtGrade.setColumns(10);
		
		final JCheckBox chckbxDroitDeLire = new JCheckBox("Droit de lire");
		chckbxDroitDeLire.setBounds(389, 159, 97, 23);
		frame.getContentPane().add(chckbxDroitDeLire);
		
		final JCheckBox chckbxDroitDeModifier = new JCheckBox("Droit de modifier");
		chckbxDroitDeModifier.setBounds(488, 159, 114, 23);
		frame.getContentPane().add(chckbxDroitDeModifier);
		
		txtNom_1 = new JTextField();
		txtNom_1.setText("nom2");
		txtNom_1.setBounds(10, 191, 86, 20);
		frame.getContentPane().add(txtNom_1);
		txtNom_1.setColumns(10);
		
		txtPrenom_1 = new JTextField();
		txtPrenom_1.setText("prenom");
		txtPrenom_1.setBounds(106, 191, 86, 20);
		frame.getContentPane().add(txtPrenom_1);
		txtPrenom_1.setColumns(10);
		
		txtMdp_1 = new JTextField();
		txtMdp_1.setText("mdp2");
		txtMdp_1.setBounds(201, 191, 86, 20);
		frame.getContentPane().add(txtMdp_1);
		txtMdp_1.setColumns(10);
		
		txtGrade_1 = new JTextField();
		txtGrade_1.setText("grade");
		txtGrade_1.setBounds(297, 191, 86, 20);
		frame.getContentPane().add(txtGrade_1);
		txtGrade_1.setColumns(10);
		
		final JCheckBox chckbxDroitLecture = new JCheckBox("droit lecture");
		chckbxDroitLecture.setBounds(389, 190, 97, 23);
		frame.getContentPane().add(chckbxDroitLecture);
		
		final JCheckBox chckbxDroitDeModifier_1 = new JCheckBox("droit de modifier");
		chckbxDroitDeModifier_1.setBounds(488, 190, 97, 23);
		frame.getContentPane().add(chckbxDroitDeModifier_1);
		
		JButton btnExporterListe = new JButton("Exporter liste");
		btnExporterListe.setBounds(593, 223, 164, 23);
		frame.getContentPane().add(btnExporterListe);
		JButton btnAjouterUtilisateur = new JButton("Ajouter utilisateur");
		btnAjouterUtilisateur.setBounds(593, 159, 164, 23);
		frame.getContentPane().add(btnAjouterUtilisateur);
		
		btnAjouterUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					admin.creer_utilisateur(10, txtNom.getText(), txtPrenom.getText(), txtMdp.getText(), chckbxDroitDeLire.isSelected(), chckbxDroitDeModifier.isSelected(), txtGrade.getText(), bdd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		JButton btnMettreJour = new JButton("Mettre \u00E0 jour");
		btnMettreJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					admin.mise_a_jour(txtNom_1.getText(), txtPrenom_1.getText(), txtMdp_1.getText(), chckbxDroitDeModifier_1.isSelected(), chckbxDroitLecture.isSelected(), txtGrade.getText(), admin, bdd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMettreJour.setBounds(591, 190, 166, 23);
		frame.getContentPane().add(btnMettreJour);
		
		txtNom_2 = new JTextField();
		txtNom_2.setText("nom");
		txtNom_2.setBounds(10, 129, 86, 20);
		frame.getContentPane().add(txtNom_2);
		txtNom_2.setColumns(10);
		
		txtPrenom_2 = new JTextField();
		txtPrenom_2.setText("prenom");
		txtPrenom_2.setBounds(106, 129, 86, 20);
		frame.getContentPane().add(txtPrenom_2);
		txtPrenom_2.setColumns(10);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.supprimer(txtNom_2.getText(),)
			}
		});
		btnSupprimer.setBounds(198, 128, 89, 23);
		frame.getContentPane().add(btnSupprimer);
		
		
	}
}
