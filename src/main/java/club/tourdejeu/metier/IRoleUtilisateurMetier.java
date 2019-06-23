package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.RoleUtilisateur;

public interface IRoleUtilisateurMetier {

    public Page<RoleUtilisateur> quelRole(String pseudo, int p, int s);

    public void supprimerRole(Long idRoUt);

    public void ajouterRole(RoleUtilisateur ru);

}
