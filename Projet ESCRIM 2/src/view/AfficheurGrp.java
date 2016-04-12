package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import metier.Colis;
import partieMission.GrpColis;

public class AfficheurGrp extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<CBColis> mesCB;
	private GrpColis dernierColis;
	private JLabel text;
	private JLabel zoneDetail;
	private int width = 800;
	private int height = 200;

	public AfficheurGrp() {
		init();
	}

	public AfficheurGrp(String texteInitial) {
		init();
		setText(texteInitial);
	}

	/**
	 * Initilise les variables
	 */
	private void init() {
		mesCB = new ArrayList<CBColis>();
		text = new JLabel();
		text.setHorizontalAlignment(SwingConstants.CENTER);

		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Met à jour les groupements et les affiches
	 * 
	 * @param colis
	 */
	public void MajGrpColis(GrpColis colis) {
		dernierColis = colis;
		// Ajout du des ComboBoxs
		for (Colis c : colis) {
			mesCB.add(new CBColis(c, width));
		}

		// Affichage des CheckBox
		if (!mesCB.isEmpty()) {

			int nbColonnes = mesCB.get(0).getColis().getInfos().length;
			int nbLigne = mesCB.size();
			this.setLayout(new GridLayout(nbLigne + 1, nbColonnes));

			// ajoute le texte en haut
			this.add(text);

			// puis les check box !
			for (CBColis cb : mesCB) {
				this.add(cb);
			}
		}

		// Associe à la zone de lecture
		if (zoneDetail != null) {
			associerZoneDetail();
		}
	}

	/**
	 * renvoie les colis cochés
	 * 
	 * @return les colis cochés
	 */
	public GrpColis Exporter() {
		GrpColis res = new GrpColis();
		for (CBColis cb : mesCB) {
			if (cb.isChecked()) {
				res.add(cb.getColis());
			}
		}
		return res;
	}

	/**
	 * Change le texte en haut
	 * 
	 * @param nouveauText
	 */
	public void setText(String nouveauText) {
		text.setText(nouveauText);
	}

	/**
	 * Ajoute la zone de lecture qui donne des détails sur la selection
	 * 
	 * @param zoneDetail
	 */
	public void ajouterZoneDetail(JLabel zoneDetail) {
		this.zoneDetail = zoneDetail;
		associerZoneDetail();
	}

	/**
	 * Ajoute le lien entre les lignes et la zone de détail
	 */
	private void associerZoneDetail() {
		for (CBColis cb : mesCB) {
			cb.setZoneDetail(zoneDetail);
		}
	}

	/**
	 * modifie les paramètre de formatage des chaque ligne
	 * Si l'on met 0, la taille de la colonne est ajust automatiquement
	 * 
	 * @param param
	 *            est un tableau d'ntier représentant le nombre de caractère à
	 *            afficher par colonne de chaque ligne
	 */
	public void setAllParamFormat(int[] param) {
		
		//on calcul la largeur par defaut (quand il y a des 0 dans le parametre)
		int defaut = 0;
		for (int i = 0; i < param.length; i++) {
			defaut += param[i];
		}
		defaut = (int) width / param.length;

		for (int i = 0; i < param.length; i++) {
			if (param[i] == 0) {
				param[i] = defaut;
			}
		}
		
		
		//on le dit aux lignes
		for (CBColis c : mesCB) {
			c.setParamFormat(param);
		}
		
		//on update !
		MajGrpColis(dernierColis);
	}

	public void setWidth(int width) {
		this.width = width;
		this.setPreferredSize(new Dimension(width, height));
	}

	public void setHeight(int height) {
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
	}
}
