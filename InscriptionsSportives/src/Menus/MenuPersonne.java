package Menus;

import java.util.ArrayList;
import java.util.SortedSet;

import Inscriptions.Equipe;
import Inscriptions.Inscriptions;
import Inscriptions.Personne;
import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListAction;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

public class MenuPersonne {

	void PersonneListOptions(Inscriptions inscriptions)
	{
		List<Personne> list = getPersonneList(inscriptions);
		list.start();
	}
	
    // Returns the list to print
	public static List<Personne> getPersonneList(Inscriptions inscriptions)
	{
		List<Personne> liste = new List<>("Select personne", "s",
				getListPersonne(inscriptions),
				getOptionListPersonne());
		liste.setAutoBack(false);
		liste.addQuit("q");
		liste.addBack("r");
		return liste;
	}
	
	private static ListData<Personne> getListPersonne(Inscriptions inscriptions)
	{
		return new ListData<Personne>()
		{
			@Override
			public java.util.List<Personne> getList()
			{
				return new ArrayList<>(inscriptions.getPersonnes());
			}
		};
	}
	
	private static ListOption<Personne> getOptionListPersonne()
	{
		return new ListOption<Personne>()
		{
			// Each person will become an option
			// The following method returns the option 
			// associated with each one. 
			public Option getOption(Personne personne)
			{
				return getPersonneMenu(personne);
			}
		};
	}
	
	// Creates an sub-menu for someone. 
	private static Option getPersonneMenu(final Personne personne)
	{
		Menu personneMenu = new Menu("Edit " + personne.getNom(), personne.getNom(), null);
		personneMenu.add(affichePersonne(personne));
		personneMenu.add(afficheEquipePersonne(personne));
		personneMenu.add(setNomPersonne(personne));
		personneMenu.add(setPrenomPersonne(personne));
		personneMenu.add(setMailPersonne(personne));
		personneMenu.add(editEquipe(personne));
		personneMenu.add(editCompetition(personne));
		personneMenu.add(supprimePersonne(personne));
		personneMenu.setAutoBack(true);
		personneMenu.addBack("r");
		return personneMenu;
	}
	
	private static Menu editEquipe(Personne personne)
	{
		Menu competitionMenu = new Menu("edit �quipe of personne Sub-Menu", "Equipe", "eq");
		competitionMenu.add(getEquipeOfPerson(personne));
		competitionMenu.add(addPersonToEquipe(personne));
		competitionMenu.add(removePersonToEquipe(personne));
		competitionMenu.addBack("r");

		return competitionMenu;
	}
	
	private static Menu editCompetition(Personne personne)
	{
		Menu competitionMenu = new Menu("edit �quipe of personne Sub-Menu", "Equipe", "eq");
		competitionMenu.add(getCompetitionOfPerson(personne));
//		competitionMenu.add(addPersonToCompetition(personne));
//		competitionMenu.add(removePersonToCompetition(personne));
		competitionMenu.addBack("r");

		return competitionMenu;
	}
	
	private static Option getCompetitionOfPerson(Personne personne)
	{
		return new Option("show Competition", "1", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Liste des membres :");
				System.out.println(personne.getCompetitions());

			}
		});
	}
	
//	private static Option addPersonToCompetition(Personne personne) {
//        return new List<>(
//                "Ajouter cette personne � une competition", "2",
//                new ListData<Competition>()
//                {
//                    @Override
//                    public java.util.List<Competition> getList() {
//                        return new ArrayList<>(Inscriptions.getInscriptions().getCompetitions());
//                    }
//                },
//                new ListAction<Competition>()
//                {
//                    @Override
//                    public void itemSelected(int i, Competition competition) {
//                        competition.add(personne);
//                    }
//
//                }
//        );
//    }
//	
//	private static Option removePersonToCompetition(Personne personne) {
//        return new List<>(
//                "Ajouter un membre � l'�quipe", "3",
//                new ListData<Competition>()
//                {
//                    @Override
//                    public java.util.List<Competition> getList() {
//                        return new ArrayList<>(Inscriptions.getInscriptions().getCompetitions());
//                    }
//                },
//                new ListAction<Competition>()
//                {
//                    @Override
//                    public void itemSelected(int i, Competition competition) {
//                        competition.remove(personne);
//                    }
//
//                }
//        );
//    }
	
	private static Option getEquipeOfPerson(Personne personne)
	{
		return new Option("show Equipe", "1", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Liste des membres :");
				System.out.println(personne.getEquipes());

			}
		});
	}
	
	private static Option addPersonToEquipe(Personne personne) {
        return new List<>(
                "Ajouter un membre � l'�quipe", "2",
                new ListData<Equipe>()
                {
                    @Override
                    public java.util.List<Equipe> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getEquipes());
                    }
                },
                new ListAction<Equipe>()
                {
                    @Override
                    public void itemSelected(int i, Equipe equipe) {
                        equipe.add(personne);
                    }

                }
        );
    }
	
	private static Option removePersonToEquipe(Personne personne) {
        return new List<>(
                "Ajouter un membre � l'�quipe", "3",
                new ListData<Equipe>()
                {
                    @Override
                    public java.util.List<Equipe> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getEquipes());
                    }
                },
                new ListAction<Equipe>()
                {
                    @Override
                    public void itemSelected(int i, Equipe equipe) {
                        equipe.remove(personne);
                    }

                }
        );
    }
	
	private static Option setNomPersonne(Personne personne) {
		return new Option("set Nom", "stn", new Action()
				
				{
					@Override
					public void optionSelected()
					{
						System.out.println("veillez saisir le nouveau nom");
						String nom = InOut.getString("Nom : ");
						personne.setNom(nom);
						System.out.println("le nom a bien �t� chang�");
					}
				});
	}
	
	private static Option setPrenomPersonne(Personne personne) {
		return new Option("set Prenom", "stp", new Action()
				
				{
					@Override
					public void optionSelected()
					{
						System.out.println("veillez saisir le nouveau nom");
						String prenom = InOut.getString("Nom : ");
						personne.setPrenom(prenom);
						System.out.println("le pr�nom a bien �t� chang�");
					}
				});
	}
	
	private static Option setMailPersonne(Personne personne) {
		return new Option("set Mail", "stm", new Action()
				
				{
					@Override
					public void optionSelected()
					{
						System.out.println("veillez saisir le nouveau nom");
						String mail = InOut.getString("Nom : ");
						personne.setMail(mail);
						System.out.println("le mail a bien �t� chang�");
					}
				});
	}
	
	private static Option afficheEquipePersonne(Personne personne) {
		return new Option("show groupe", "shg", new Action()
				
				{
					@Override
					public void optionSelected()
					{
						System.out.println("Equipe : " + personne.getEquipes() + ".");
						System.out.println("competition : " + personne.getCompetitions() + ".");

					}
				});
	}

	// Returns the option to display someone
	private static Option affichePersonne(Personne personne)
	{
		return new Option("show", "sh", new Action()
		
		{
			@Override
			public void optionSelected()
			{
				System.out.println("You must give the man a name : " + personne.getNom() + ".");
				System.out.println("You must give the man a prenom : " + personne.getPrenom() + ".");
				System.out.println("You must give the man a mail : " + personne.getMail() + ".");
			}
		});
	}
	
	private static Option supprimePersonne(Personne personne)
	{
		return new Option("delete", "d", new Action()
		{
			@Override
			public void optionSelected()
			{
				personne.delete();
				System.out.println(personne + " has been deleted.");
			}
		});
	}
	
	public static  Option creerPersonneOption(Inscriptions inscriptions) {
		return new Option("cr�er une personne", "c", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Enter le nom, le prenom et l'adresse email de la personne que vous voulez ajouter ");
				String nom = InOut.getString("Nom : ");
				String prenom = InOut.getString("Pr�nom : ");
                String mail = InOut.getString("Email : ");
                Personne personne = inscriptions.createPersonne(nom, prenom, mail);
			}
		});
	}
}
