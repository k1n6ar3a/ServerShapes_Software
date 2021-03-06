package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************************************
 * @Titre_Classe: TAG PARSER
 * 
 * @R�sumer: 
 * Permet de d�composer les cha�nes de caract�res re�ue par le
 * ServeurForme � l'aide de certaines informations comme le num�ro de 
 * s�quence d'une forme par exemple. 
 * 
 * @SOurce:
 * Expressions rationnelles: https://cours.etsmtl.ca/log121/private/lab/lab1/expressionsRationnelles.shtml
 * Syntaxe: http://www.commentcamarche.net/php/phpreg.php3
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 31 mai 2017 
 *******************************************************************/ 
public final class TagParser {
	
	/********************
	 * Constante
	 ********************/

	//Repr�sente un ensemble de caract�re permettent d'identifer un argument
	private static String CARACTERE_PATTERN = "(.*) <(.*)> (.*) </\\2>";
	
	/*******************************************************************
	 * @Titre: STRUCTURE FORME
	 * 
	 * @R�sumer:
	 * De fa�on g�n�rale, on converti les valeurs pour uniformiser le tout
	 * et l'information d�crivant une forme est structur�e dans un ordre pr�cis.
	 * 
	 * @Source:
	 * array.split(): http://stackoverflow.com/questions/14414582/java-split-string-to-array
	 *
	 *******************************************************************/
	public static final String[] main(String informationForme) {

		Pattern pattern = Pattern.compile(CARACTERE_PATTERN); 
		
		//Retourne l'information compl�te d'une forme 
		return findTag(pattern, informationForme);
	}

	/*******************************************************************
	 * @Titre: STRUCTURE FORME
	 * 
	 * @R�sumer:
	 * De fa�on g�n�rale, on converti les valeurs pour uniformiser le tout
	 * et l'information d�crivant une forme est structur�e dans un ordre pr�cis.
	 * 
	 * @Source:
	 * array.split(): http://stackoverflow.com/questions/14414582/java-split-string-to-array
	 *
	 *******************************************************************/
	private static String[] findTag(Pattern pattern, String informationForme) {
		
		//Strings qui composent l'identit� d'une forme
		String 
		numeroSeq = null,
		identiteForme = null,
		dimensionsForme = null;
		
		//Liste qui garde en m�moire toute l'information concernant une forme
		String[] descriptionCompleteForme = new String[3];
		
		//Identification correspondance avec une forme abstraite
		Matcher matcher = pattern.matcher(informationForme);
		
		//Si un r�sultat est identifi�, la primitive "resultat" tombe � true.
		boolean resultat = matcher.find();

		//On valide le r�sultat (true)
		if (resultat == true) {

			numeroSeq = matcher.group(1);
			identiteForme = matcher.group(2);
			dimensionsForme = matcher.group(3);
			
			System.out.println("\n*****************************\nPARAM�TRES FORME:  " + matcher.group(2) + "\n*****************************");
			System.out.println("Num�ro de s�quence: " + matcher.group(1) + "\nDimensions: " + matcher.group(3) + "\n");
		}
	
		//Insertition des �l�ments qui composent l'identt� d'une forme dans une liste
		descriptionCompleteForme[0] = numeroSeq;
		descriptionCompleteForme[1] = identiteForme; 
		descriptionCompleteForme[2] = dimensionsForme;
		
		//Retourne l'identit� d'une forme et ses param�tres
		return descriptionCompleteForme;
	}	
}