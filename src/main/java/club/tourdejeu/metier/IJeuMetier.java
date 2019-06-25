package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Jeu;

public interface IJeuMetier {

    // returning a page of games from the database containing a specific chain of
    // characters in their title
    public Page<Jeu> rechercheTitre(String titre, int page, int size);

    // returning a page of games that can be played for a specific number of players
    // from the database
    public Page<Jeu> rechercheJoueurs(int joueurs, int page, int size);

    // returning a page of all games alphabetically ordered by title
    public Page<Jeu> trouverTousAlpha(int page, int size);

    // return one game through its game id
    public Jeu trouverUnJeu(Long idJeu);

    // add a game to the concerned database table
    public void enregistrerJeu(Jeu j);

    // update a specific game informations in the database
    public void modifierJeu(Jeu j);

    // remove a specific game to the concerned database table
    public void supprimerJeu(Long idJeu);

}
