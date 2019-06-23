package club.tourdejeu.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.tourdejeu.dao.EmpruntRepository;
import club.tourdejeu.dao.RoleRepository;
import club.tourdejeu.dao.RoleUtilisateurRepository;
import club.tourdejeu.dao.UtilisateurRepository;
import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Role;
import club.tourdejeu.entities.RoleUtilisateur;
import club.tourdejeu.entities.Utilisateur;

@Service
@Transactional
public class UtilisateurMetierImpl implements IUtilisateurMetier {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleUtilisateurRepository roleUtilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public void enregistrerUtilisateur(Utilisateur u) {

	Utilisateur check = utilisateurRepository.checkAvailability(u.getPrenom(), u.getNom(), u.getEmail());
	Utilisateur pseudo = utilisateurRepository.findOne(u.getUsername());

	Role guest = roleRepository.findByRole("GUEST");
	RoleUtilisateur ru = new RoleUtilisateur(u, guest);

	if (check != null) {
	    throw new RuntimeException("Cet utilisateur existe déjà !");
	}

	if (pseudo != null) {
	    throw new RuntimeException("Ce pseudo n'est pas disponible !");
	}

	else {
	    u.setEnabled(true);
	    u.setPassword(passwordEncoder.encode(u.getPassword()));
	    utilisateurRepository.save(u);
	    roleUtilisateurRepository.save(ru);
	}

    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {

	u.setEnabled(true);
	utilisateurRepository.save(u);

    }

    @Override
    public void modifierUtilisateurForced(Utilisateur u) {

	utilisateurRepository.save(u);

    }

    @Override
    public Utilisateur findOneCheckDispo(String username) {

	Utilisateur u = utilisateurRepository.findOne(username);

	if (u == null) {
	    throw new RuntimeException("Aucun utilisateur avec ce pseudo trouvé !");
	}

	else

	    return u;
    }

    @Override
    public Utilisateur findLogged(Authentication authentication) {

	// récupération du bean de l'utilisateur via SecurityContextHolder de Spring
	// Security
	authentication = SecurityContextHolder.getContext().getAuthentication();
	String currentUserName = authentication.getName();

	Utilisateur u = utilisateurRepository.findOne(currentUserName);

	if (u == null) {
	    throw new RuntimeException("Vous devez être connecté(e) pour effectuer cette action !");
	}

	else

	    return u;
    }

    @Override
    public Page<Utilisateur> trouverTous(int page, int size) {

	return utilisateurRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Page<Utilisateur> chercherPrenom(String prenom, int page, int size) {

	Page<Utilisateur> parPseudo = utilisateurRepository.chercherPseudo("%" + prenom + "%",
		new PageRequest(page, size));

	if (!parPseudo.hasContent()) {
	    throw new RuntimeException("Aucun utilisateur avec ce prénom trouvé !");
	}

	return parPseudo;
    }

    @Override
    public void supprimerUtilisateur(String pseudo, int page, int size) {

	Page<Emprunt> empruntsUtilisateur = empruntRepository.listeEmpruntsPersos(pseudo, new PageRequest(page, size));

	if (empruntsUtilisateur.hasContent()) {
	    throw new RuntimeException(
		    "Impossible de supprimer un utilisateur qui a des prêts en cours ! Retournez ses emprunts avant de procéder.");
	}

	utilisateurRepository.delete(pseudo);

    }

}
