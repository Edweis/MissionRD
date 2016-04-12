package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import metier.Colis;

public class CBColis extends JPanel {
	private static final long serialVersionUID = 1L;
	private JCheckBox cb;
	private JButton askInfos;
	private JLabel label;
	private Colis colis;
	private int[] paramFormat;
	private JLabel zoneDetail;

	public CBColis(Colis c, int width) {
		cb = new JCheckBox();
		askInfos = new JButton("?");
		this.colis = c;
		label = new JLabel();
		label.setPreferredSize(new Dimension(width, 15));

		// equirepartition des colonnes
		int nbCol = colis.getInfos().length;
		paramFormat = new int[nbCol];
		for (int i = 0; i < nbCol; i++) {
			paramFormat[i] = 1+(int) width / nbCol;
		}

		genererLigne();
	}

	/**
	 * Ajoute les JLabel nécessaires pour faire une ligne. Ils contiennent les
	 * éléments de colis.infos()
	 */
	private void genererLigne() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(cb);
		this.add(askInfos);

		String infos = "";
		JLabel t;
		for (int i = 0; i < colis.getInfos().length; i++) {
			t = new JLabel(colis.getInfos()[i]);
			t.setHorizontalAlignment(SwingConstants.CENTER);
			t.setPreferredSize(new Dimension(20, paramFormat[i]));
			
			
			this.add(t);
		}

		label.setText(infos);

		this.add(label);

	}

	/**
	 * Permet de formater le texte écrit sur une ligne afin qu'il ait le bon
	 * nombre de caractère et tout. Il faut bien que ce soit joli et aligné non
	 * ?
	 * 
	 * @param nbChar
	 * @param chaine
	 * @return une jolie chaine
	 */
	private String formateur(int nbChar, String chaine) {
		if (chaine.length() <= nbChar) {
			for (int i = 0; i <= nbChar - chaine.length(); i++) {
				chaine = chaine.concat("x");
			}
		} else {
			chaine = chaine.substring(0, nbChar);
		}
		return chaine;
	}

	/**
	 * modifie les paramètre de formatage de la ligne
	 * 
	 * @param param
	 *            est un tableau d'ntier représentant le nombre de caractère à
	 *            afficher par colonne de chaque ligne
	 */
	public void setParamFormat(int[] param) {
		if (param.length == colis.getInfos().length) {
			paramFormat = param;
		} else {
			System.out.println("Nombre de parametres incorrects");
		}
	}

	public Colis getColis() {
		return colis;
	}

	public boolean isChecked() {
		return cb.isEnabled();
	}

	/**
	 * associe la zone de détail avec la ligne
	 * 
	 * @param zoneLecture
	 */
	public void setZoneDetail(JLabel zoneLecture) {

		zoneDetail = zoneLecture;
		askInfos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				zoneDetail.setText(colis.details());
			}

		});
	}
}
