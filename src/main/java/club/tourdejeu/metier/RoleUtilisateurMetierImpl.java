package club.tourdejeu.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.tourdejeu.dao.RoleUtilisateurRepository;
import club.tourdejeu.entities.RoleUtilisateur;

@Service
@Transactional
public class RoleUtilisateurMetierImpl implements IRoleUtilisateurMetier {

    @Autowired
    private RoleUtilisateurRepository ruRepository;

    @Override
    public Page<RoleUtilisateur> quelRole(String pseudo, int p, int s) {

	Page<RoleUtilisateur> quelRole = ruRepository.rolesUtilisateur(pseudo, new PageRequest(p, s));

	if (!quelRole.hasContent()) {
	    throw new RuntimeException("Aucun rôle n'a été attribué à cet(te) utilisateur(trice) !");
	}

	return quelRole;
    }

    @Override
    public void supprimerRole(Long idRoUt) {

	ruRepository.delete(idRoUt);

    }

    @Override
    public void ajouterRole(RoleUtilisateur ru) {

	RoleUtilisateur ruCheck = ruRepository.verifierRole(ru.getUtilisateur().getUsername(), ru.getRole().getRole());

	if (ruCheck != null) {
	    throw new RuntimeException("Ce rôle est déjà attribué à cet(te) utilisateur(trice) !");
	}

	ruRepository.save(ru);

    }

}
