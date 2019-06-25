package club.tourdejeu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication
public class TourDeJeuApplication implements CommandLineRunner {

    /*
     * @Autowired private UtilisateurRepository utilisateurRepository;
     * 
     * @Autowired private RoleRepository roleRepository;
     * 
     * @Autowired private RoleUtilisateurRepository roleUtilisateurRepository;
     * 
     * @Autowired private JeuRepository jeuRepository;
     * 
     * @Autowired private EmpruntRepository empruntRepository;
     * 
     * @Autowired private EvenementRepository evenementRepository;
     * 
     * @Autowired private CommentaireRepository commentaireRepository;
     * 
     * @Autowired private BenevolatRepository benevolatRepository;
     * 
     * @Autowired private PasswordEncoder passwordEncoder;
     */

    public static void main(String[] args) {
	SpringApplication.run(TourDeJeuApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	/*
	 * String passwordU0 = "bigboss"; String passwordU1 = "kirk"; String passwordU2
	 * = "picard"; String passwordU3 = "spock"; // String mdp =
	 * 
	 * Utilisateur u0 = utilisateurRepository.save(new Utilisateur("Moi",
	 * "moi@tourdejeu.club", passwordEncoder.encode(passwordU0), "Solid", "Snake",
	 * "13118", "0666666666", true)); Utilisateur u1 =
	 * utilisateurRepository.save(new Utilisateur("picard", "jlpicard@fdp.org",
	 * passwordEncoder.encode(passwordU1), "Jean-Luc", "Picard", "13118",
	 * "0606060606", true)); Utilisateur u2 = utilisateurRepository.save(new
	 * Utilisateur("kirk", "jtkirk@fdp.org", passwordEncoder.encode(passwordU2),
	 * "Jim", "Kirk", "13400", "0606060606", true)); Utilisateur u3 =
	 * utilisateurRepository.save(new Utilisateur("spock", "mrspock@fdp.org",
	 * passwordEncoder.encode(passwordU3), "Mr", "Spock", "13800", "0606060606",
	 * true));
	 * 
	 * Role superAdmin = roleRepository.save(new Role("SUPER")); Role admin =
	 * roleRepository.save(new Role("ADMIN")); Role membre = roleRepository.save(new
	 * Role("USER")); Role guest = roleRepository.save(new Role("GUEST"));
	 * 
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u0, superAdmin));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u0, admin));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u0, membre));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u0, guest));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u1, admin));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u1, membre));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u2, membre));
	 * roleUtilisateurRepository.save(new RoleUtilisateur(u3, guest));
	 * 
	 * Jeu j1 = jeuRepository.save(new Jeu("Nomades", "Gary Kim", "40mn", 2, 5,
	 * "Familial")); Jeu j2 = jeuRepository.save(new Jeu("Galèrapagos",
	 * "Laurence et Philippe Gamelin", "20mn", 3, 12, "Ambiance")); Jeu j3 =
	 * jeuRepository .save(new Jeu("Sekigahara The Unification of Japan",
	 * "Matthew Calkins", "180mn", 2, 2, "Expert")); Jeu j4 = jeuRepository
	 * .save(new Jeu("Mr. Jack Pocket", "Bruno Cathala et Ludovic Maublanc", "15mn",
	 * 2, 2, "Familial")); Jeu j5 = jeuRepository.save(new Jeu("Bang!",
	 * "Emiliano Sciarra", "20-40mn", 4, 7, "Ambiance")); Jeu j6 =
	 * jeuRepository.save(new Jeu("La Gloire de Rome", "Carl Chudyk", "60mn+", 2, 5,
	 * "Expert")); Jeu j7 = jeuRepository.save(new Jeu("Tides of Time",
	 * "Kristian Curla", "20mn", 2, 2, "Expert")); Jeu j8 = jeuRepository.save(new
	 * Jeu("Mille sabords !", "Haim Shafir", "30mn", 2, 5, "Ambiance")); Jeu j9 =
	 * jeuRepository.save(new Jeu("Dixit ODYSSEY", "Jean-Louis Roubira", "30mn", 3,
	 * 12, "Familial")); Jeu j10 = jeuRepository.save(new Jeu("Room 25",
	 * "François Rouzé", "30mn", 1, 6, "Familial"));
	 * 
	 * j4.setDispoPret(true); j1.setDispoPret(true); j2.setDispoPret(true);
	 * j7.setDispoPret(true); j9.setDispoPret(true);
	 * 
	 * jeuRepository.save(j4); jeuRepository.save(j1); jeuRepository.save(j2);
	 * jeuRepository.save(j7); jeuRepository.save(j9);
	 * 
	 * Emprunt e1 = empruntRepository.save(new Emprunt(u2, j2, new Date()));
	 * empruntRepository.save(new Emprunt(u1, j3, new Date())); e1.setDateRetour(new
	 * Date()); empruntRepository.save(e1); empruntRepository.save(new Emprunt(u1,
	 * j5, new Date())); empruntRepository.save(new Emprunt(u1, j6, new Date()));
	 * empruntRepository.save(new Emprunt(u1, j10, new Date()));
	 * empruntRepository.save(new Emprunt(u1, j8, new Date()));
	 * 
	 * Evenement ev1 = evenementRepository.saveAndFlush(new
	 * Evenement("Meurtre à la Plage",
	 * "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
	 * + "\n" +
	 * "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.\n"
	 * + "\n" +
	 * "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.\n"
	 * + "\n" +
	 * "Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.\n"
	 * + "\n" +
	 * "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis.\n"
	 * + "\n" +
	 * "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  At accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat. \n"
	 * + "\n" +
	 * "Consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
	 * + "", "2019-06-22", "https://www.istres-tourisme.com/plages.html"));
	 * 
	 * evenementRepository.saveAndFlush(new Evenement("Apéro à la Plage",
	 * "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
	 * + "\n" +
	 * "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.\n"
	 * + "\n" +
	 * "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.\n"
	 * + "\n" +
	 * "Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.\n"
	 * + "\n" +
	 * "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis.\n"
	 * + "\n" +
	 * "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  At accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat. \n"
	 * + "\n" +
	 * "Consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,  sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
	 * + "", "2019-06-28", "https://www.istres-tourisme.com/plages.html"));
	 * 
	 * commentaireRepository.save(new
	 * Commentaire("C'est trop de la balle, j'ai vraiment hâte d'y être !", u2,
	 * ev1)); commentaireRepository.save(new
	 * Commentaire("C'est vraiment débile, ne comptez pas sur moi !", u1, ev1));
	 * 
	 * benevolatRepository.save(new Benevolat(ev1, u3));
	 * benevolatRepository.save(new Benevolat(ev1, u3));
	 */

    }

}
