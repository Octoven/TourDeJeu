package club.tourdejeu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Jeu implements Serializable {

    @Id
    @GeneratedValue
    private Long idJeu;
    @NotBlank
    private String titre;
    private String auteurs;
    private String duree;
    private int joueursMin;
    private int joueursMax;
    private String genre;
    private boolean extension;
    private boolean dispoPret;
    @OneToMany(mappedBy = "jeu", fetch = FetchType.LAZY)
    private Collection<Emprunt> emprunts;

    public Jeu() {

    }

    public Jeu(String titre, String auteurs, String duree, int joueursMin, int joueursMax, String genre) {
	this.titre = titre;
	this.auteurs = auteurs;
	this.duree = duree;
	this.joueursMin = joueursMin;
	this.joueursMax = joueursMax;
	this.genre = genre;
    }

    public Long getIdJeu() {
	return idJeu;
    }

    public void setIdJeu(Long idJeu) {
	this.idJeu = idJeu;
    }

    public String getTitre() {
	return titre;
    }

    public void setTitre(String titre) {
	this.titre = titre;
    }

    public String getAuteurs() {
	return auteurs;
    }

    public void setAuteurs(String auteurs) {
	this.auteurs = auteurs;
    }

    public String getDuree() {
	return duree;
    }

    public void setDuree(String duree) {
	this.duree = duree;
    }

    public int getJoueursMin() {
	return joueursMin;
    }

    public void setJoueursMin(int joueursMin) {
	this.joueursMin = joueursMin;
    }

    public int getJoueursMax() {
	return joueursMax;
    }

    public void setJoueursMax(int joueursMax) {
	this.joueursMax = joueursMax;
    }

    public String getGenre() {
	return genre;
    }

    public void setGenre(String genre) {
	this.genre = genre;
    }

    public boolean isExtension() {
	return extension;
    }

    public void setExtension(boolean extension) {
	this.extension = extension;
    }

    public boolean isDispoPret() {
	return dispoPret;
    }

    public void setDispoPret(boolean dispoPret) {
	this.dispoPret = dispoPret;
    }

    public Collection<Emprunt> getEmprunts() {
	return emprunts;
    }

    public void setEmprunts(Collection<Emprunt> emprunts) {
	this.emprunts = emprunts;
    }

}
