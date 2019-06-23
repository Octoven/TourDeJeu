package club.tourdejeu.metier;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import club.tourdejeu.entities.Utilisateur;

public interface IUtilisateurMetier {

    public void enregistrerUtilisateur(Utilisateur u);

    public void modifierUtilisateur(Utilisateur u);

    public void modifierUtilisateurForced(Utilisateur u);

    public void supprimerUtilisateur(String pseudo, int page, int size);

    public Utilisateur findOneCheckDispo(String username);

    public Utilisateur findLogged(Authentication authentication);

    public Page<Utilisateur> trouverTous(int page, int size);

    public Page<Utilisateur> chercherPrenom(String prenom, int page, int size);

}
