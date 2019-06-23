package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Jeu;

public interface IJeuMetier {

    public Page<Jeu> rechercheTitre(String titre, int page, int size);

    public Page<Jeu> rechercheJoueurs(int joueurs, int page, int size);

    public Page<Jeu> trouverTousAlpha(int page, int size);

    public Jeu trouverUnJeu(Long idJeu);

    public void enregistrerJeu(Jeu j);

    public void modifierJeu(Jeu j);

    public void supprimerJeu(Long idJeu);

}
