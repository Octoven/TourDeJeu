package club.tourdejeu.metier;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import club.tourdejeu.entities.Utilisateur;

public interface IUtilisateurMetier {

    // saving a new user in the user table
    public void enregistrerUtilisateur(Utilisateur u);

    // updating an user in the user table
    public void modifierUtilisateur(Utilisateur u);

    // force updating an user in the user table - only used to disable account
    public void modifierUtilisateurForced(Utilisateur u);

    // deleting an user in the user table
    public void supprimerUtilisateur(String pseudo, int page, int size);

    // returning a specific user by its username
    public Utilisateur findOneCheckDispo(String username);

    // returning an instance/bean of the currently logged user
    public Utilisateur findLogged(Authentication authentication);

    // returning a page containing all users in the database
    public Page<Utilisateur> trouverTous(int page, int size);

    // returning a page of users whose first name contains a specific chain of
    // characters
    public Page<Utilisateur> chercherPrenom(String prenom, int page, int size);

}
