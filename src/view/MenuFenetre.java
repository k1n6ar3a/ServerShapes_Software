
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.CommBase;
import controller.CommunicationServeur;
import controller.LangueConfig;

/*******************************************************************
 * @Titre_Classe: MENU FEN�TRE
 * 
 * @R�sumer:
 * Cree le menu de la fenetre de l'application.
 * Presque rien a �t� chang� de la version du "Squelette" fourni.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 28 mai 2017 
 *******************************************************************/
public class MenuFenetre extends JMenuBar{

	/***************************
	 * Classes instanci�es
	 ***************************/

	private JMenuItem 
	arreterMenuItem, 
	demarrerMenuItem;

	// Pour activer ou desactiver la communication avec le serveur
	private CommBase commBase; 

	/********************
	 * Constantes
	 ********************/

	private static final long serialVersionUID = 1536336192561843187L;

	private static final int
	MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK,
	MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK,
	MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK,
	DELAI_QUITTER_MSEC = 200;

	private static final char
	MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D,
	MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A,
	MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;

	private static final String
	MENU_FICHIER_TITRE = "app.frame.menus.file.title",
	MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
	MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
	MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
	MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
	MENU_AIDE_TITRE = "app.frame.menus.help.title",
	MENU_AIDE_PROPOS = "app.frame.menus.help.about",
	MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  

	/********************
	 * Constructeur
	 ********************/
	public MenuFenetre(CommBase commBase) {

		this.commBase = commBase;

		addMenuDessiner();
		addMenuFichier();
		addMenuAide();

		//Changement des items du menu de fa�on dynamique
		changementMenus();
	}

	/*******************************************************************
	 * @Titre: MENU DESSIN
	 * 
	 * @R�sumer:
	 * Cr�ation du menu "Draw".
	 * Rien a �t� chang� de la version du "Squelette" fourni.
	 * 
	 *******************************************************************/
	protected void addMenuDessiner() {

		JMenu menu = creerMenu(MENU_DESSIN_TITRE, new String[]{ MENU_DESSIN_DEMARRER, 
				MENU_DESSIN_ARRETER });

		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				commBase.start();
				changementMenus();
			}
		});

		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				commBase.stop();
				changementMenus();
			}
		});

		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));

		add(menu);
	}

	/*******************************************************************
	 * @Titre: MENU DESSIN
	 * 
	 * @R�sumer:
	 * Cr�ation du menu "File".
	 * Rien a �t� chang� de la version du "Squelette" fourni.
	 * 
	 *******************************************************************/
	protected void addMenuFichier() {

		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QUITTER });

		menu.getItem(0).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				commBase.stop();

				try {

					Thread.sleep(DELAI_QUITTER_MSEC);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}

				System.exit(0);
			}
		});
		menu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
				MENU_FICHIER_QUITTER_TOUCHE_MASK));

		add(menu);
	}

	/*******************************************************************
	 * @Titre: MENU AIDE
	 * 
	 * @R�sumer:
	 * Cr�ation du menu "Help".
	 * Rien a �t� chang� de la version du "Squelette" fourni.
	 * 
	 *******************************************************************/
	protected void addMenuAide() {

		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });

		menu.getItem(0).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,  
						LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}

	/*******************************************************************
	 * @Titre: CHANGEMENT ITEMS MENU
	 * 
	 * @R�sumer:
	 * Activer ou desactiver les items du menu selon la selection. 
	 * Ajustement des items du menu si le serveur tombe en panne.
	 * 
	 *******************************************************************/
	private void changementMenus() {

		CommunicationServeur communication = new CommunicationServeur();

		//Si le serveur tombe en panne et que la communication �choue
		if(communication.getSocketTCP() == null){

			//Activation de l'option D�marrer pour que l'utilisateur puisse retenter un connexion
			demarrerMenuItem.setEnabled(!communication.serveurActif());

			//Puisque le serveur est tomb� en panne, on retire l'option Arr�ter
			arreterMenuItem.setEnabled(communication.serveurActif());
		}

			//Affiche l'option D�marrer pour laisser l'utilisateur entrer les param�tres de connexion
			demarrerMenuItem.setEnabled(!commBase.isActif());

			//Affiche l'option Arr�ter pour laisser l'utilisateur cesser la connexion
			arreterMenuItem.setEnabled(commBase.isActif());
	}

	/*******************************************************************
	 * @Titre: CREATION MENU
	 * 
	 * @R�sumer:
	 * Creer un element de menu a partir d'un champs principal et ses elements
	 * Rien a �t� chang� de la version du "Squelette" fourni.
	 * 
	 * @param titleKey champs principal
	 * @param itemKeys elements
	 * @return le menu
	 *
	 *******************************************************************/
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {

		JMenu menu = new JMenu(LangueConfig.getResource(titleKey));

		for(int i=0; i < itemKeys.length; ++i) {

			menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
		}
		return menu;
	}
}
