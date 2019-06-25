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

	// checking if a user with the same first name, last name and email address is
	// already recorded in the database
	// also checking if the username isn't already reserved
	Utilisateur check = utilisateurRepository.checkAvailability(u.getPrenom(), u.getNom(), u.getEmail());
	Utilisateur pseudo = utilisateurRepository.findOne(u.getUsername());

	Role guest = roleRepository.findOne("GUEST");
	RoleUtilisateur ru = new RoleUtilisateur(u, guest);

	if (check != null) {
	    throw new RuntimeException("Cet utilisateur existe déjà !");
	}

	if (pseudo != null) {
	    throw new RuntimeException("Ce pseudo n'est pas disponible !");
	}

	// setting the newly created user/account to enabled and encrypting password
	// prior to saving the user to the relevant table
	else {
	    u.setEnabled(true);
	    u.setPassword(passwordEncoder.encode(u.getPassword()));
	    utilisateurRepository.save(u);
	    roleUtilisateurRepository.save(ru);
	}

    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {

	// making sure to keep the account/user enabled prior to recording personal
	// information updates
	u.setEnabled(true);
	utilisateurRepository.save(u);

    }

    @Override
    public void modifierUtilisateurForced(Utilisateur u) {

	// updating the user informations without setting the enabled boolean prior to
	// recording the change - used primarily to disable the user account
	utilisateurRepository.save(u);

    }

    @Override
    public Utilisateur findOneCheckDispo(String username) {

	// checking if a user with this username is currently recorded in the database
	Utilisateur u = utilisateurRepository.findOne(username);

	if (u == null) {
	    throw new RuntimeException("Aucun utilisateur avec ce pseudo trouvé !");
	}

	// returning the user whose username is the one sent by the request
	else

	    return u;
    }

    @Override
    public Utilisateur findLogged(Authentication authentication) {

	// fetching user bean through Spring Security SecurityContextHolder
	authentication = SecurityContextHolder.getContext().getAuthentication();
	String currentUserName = authentication.getName();

	// checking if the user is still logged/if the user session is still up
	Utilisateur u = utilisateurRepository.findOne(currentUserName);

	if (u == null) {
	    throw new RuntimeException("Vous devez être connecté(e) pour effectuer cette action !");
	}

	// returning the user bean
	else

	    return u;
    }

    @Override
    public Page<Utilisateur> trouverTous(int page, int size) {

	// returning all users from the database table
	return utilisateurRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Page<Utilisateur> chercherPrenom(String prenom, int page, int size) {

	// checking if the keywords provided by the user return a result where the
	// first name contains to the string of characters received
	Page<Utilisateur> parPseudo = utilisateurRepository.chercherPseudo("%" + prenom + "%",
		new PageRequest(page, size));

	if (!parPseudo.hasContent()) {
	    throw new RuntimeException("Aucun utilisateur avec ce prénom trouvé !");
	}

	// returning a page of users whose first name contains the string of character
	// received from the request form
	return parPseudo;
    }

    @Override
    public void supprimerUtilisateur(String pseudo, int page, int size) {

	// checking if the user the super admin is about to be deleted has ongoing loans
	// prior to the deletion - the super admin is advised to return the loans prior
	// to deleting the user
	Page<Emprunt> empruntsUtilisateur = empruntRepository.listeEmpruntsPersos(pseudo, new PageRequest(page, size));

	if (empruntsUtilisateur.hasContent()) {
	    throw new RuntimeException(
		    "Impossible de supprimer un utilisateur qui a des prêts en cours ! Retournez ses emprunts avant de procéder.");
	}

	// deleting the user
	utilisateurRepository.delete(pseudo);

    }

}
