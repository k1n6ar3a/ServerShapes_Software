package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME CARR�
 * 
 * @R�sumer:
 * Cr�ation d'un objet graphique de type carr� 2D. 
 * On identifie la forme � l'aide d'un remplissage color�.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/

public class FormeCarre extends FormesAbstraites{
	
	/********************
	 * Variables
	 ********************/
	private int x1, y1, x2, y2;

	/********************
	 * Constructeur
	 ********************/
	public FormeCarre(int nseq, int x1, int x2, int y1, int y2){
		
		//Num�ro de s�quence de la forme
		super(nseq);
		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	/********************
	 * Graphics 
	 ********************/
	@Override
	public void graphiqueForme(Graphics g){
		
		//Remplissage color� de la forme
		g.setColor(Color.RED);
		g.fillRect(x1, y1, (x2 - x1), (y2 - y1));
		
		//Contour noir aide � identifier une forme qui emboite une autre.
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, (x2 - x1), (y2 - y1));
	}
}
