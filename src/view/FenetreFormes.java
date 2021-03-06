package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: FEN�TRE FORMES
 * 
 * @R�sumer: 
 * Cette fen�tre g�re l'affichage des forme � l'�cran.
 * La fen�tre poss�de une dimension fixe de 500 x 500 et 
 * supporte un maximum de 10 formes.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 26 mai 2017 
 * 
 *******************************************************************/ 
public class FenetreFormes extends JComponent{

	/***************************
	 * Classes instanci�es
	 ***************************/

	private final Dimension dimension;
	private FormesAbstraites[] listeAbstraites;
	
	/********************
	 * Constantes
	 ********************/
	
	private static final long serialVersionUID = -2262235643903749505L;
	
	//On donne les dimensions de la fen�tre et la quantit� maximale de formes 
	private static final int 
	WIDTH = 500,
	HEIGHT = 500,
	MAX_FORMES = 10;
	
	/********************
	 * Variable
	 ********************/
	
	//Utilis� pour compter le nombre de formes � l'affiche
	private int nombreFormes, quantiteFormes = 0;
	
	/********************
	 * Constructeur
	 ********************/
	public FenetreFormes(){
		
		//On donnes les dimensions de la fen�tre
		dimension = new Dimension(WIDTH, HEIGHT);

		//On cr�� une liste qui peut garder en m�moire au maximum 10 formes
		listeAbstraites = new FormesAbstraites[MAX_FORMES];
	}

	/*******************************************************************
	 * @Titre: AJOUTER FORME
	 * 
	 * @R�sumer:
	 * G�re la capacit� maximale de formes � afficher � l'�cran avec une 
	 * limite de 10. Permet aussi d'afficher continuellement 10 formes
	 * avec un changement dynamique.
	 * 
	 *******************************************************************/
	public void ajoutforme(FormesAbstraites formesAbstraites){
		
		//Liste qui permet l'affichage au maximum de 10 formes
		listeAbstraites[quantiteFormes] = formesAbstraites;

		if(quantiteFormes <= MAX_FORMES){
			
			quantiteFormes++;
			
			//Si on atteint la capacit� maximale de 10 formes
			if(quantiteFormes == MAX_FORMES){
				
					//on s'assure de recommencer le compte � 0
					quantiteFormes = 0;
			}
			
			//Si on reste sous la barre des 10 formes
			if(nombreFormes < MAX_FORMES){
				
				//Incr�mente pour identifier le nombre de formes � imprimer
				nombreFormes++;
			}
		}
		else{
			
			System.out.println("Un probl�me est survenu avec la capacit� maximal de formes � afficher.");
			
			//On s'assure d'arr�ter le processus
			return;
		}
	}

	/*******************************************************************
	 * @Titre: AFFICHE FORMES
	 * 
	 * @R�sumer:
	 * Affiche � l'�cran les formes gard�es en m�moire de la liste.
	 * 
	 *******************************************************************/
	@Override 
	public void paintComponent(Graphics g){
		
		//Boucle en fonction du nombre de formes
		for (int i = 0; i < nombreFormes; i++) {

			//Affiche � l'�cran les formes de la liste
			listeAbstraites[i].graphiqueForme(g);
		}
	}

	/*******************************************************************
	 * @Titre: DIMENSION
	 * 
	 * @R�sumer:
	 * Le Layout qui utilise (contient) FenetreFormes doit reserver 
	 * l'espace n�cessaire a son affichage.
	 * 
	 *******************************************************************/
	@Override 
	public Dimension getPreferredSize(){

		return dimension;
	}
}