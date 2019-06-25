package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.RoleUtilisateur;

public interface IRoleUtilisateurMetier {

    // returning a page of user/role association for a specific user - through the
    // username
    public Page<RoleUtilisateur> quelRole(String pseudo, int p, int s);

    // deleting an existing user/role association
    public void supprimerRole(Long idRoUt);

    // creating a new user/role association
    public void ajouterRole(RoleUtilisateur ru);

}
