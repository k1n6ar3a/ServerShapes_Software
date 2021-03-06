package model;

import ca.etsmtl.log.util.IDLogger;

/*******************************************************************
 * @Titre: FORMES ABSTRAITES
 * 
 * @R�sumer:
 * La classe abstraite est con�ue de fa�on � �tre minimaliste. 
 * L'id�e est de permettre l'ajout d'options dans le cas o� l'�volution 
 * du Travail_Pratique_01 prend une tournure diff�rente.
 * 
 * Pour l'instant, on retrouve seulement une variable qui garde en 
 * m�moire le num�ro de s�quence associ� � une forme. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/
public abstract class FormesAbstraites implements InterfaceFormes{

	//Num�ro de s�quence d'une forme
	protected int nseq;

	//M�thode statique qui permet l'ajout d'un num�ro de s�quence au Journal
	IDLogger logger = IDLogger.getInstance();

	/********************
	 * Constructeurs
	 ********************/
	public FormesAbstraites(int nseq){

		this.nseq = nseq;
		
		//Utilis� pour la validation 
		logger.logID(nseq);
	}
}