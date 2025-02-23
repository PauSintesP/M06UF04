package itic.bcn.Entitats;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Lliga")
public class Lliga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lliga")
    private int id;

    @Column(name = "nom_lliga", nullable = false, unique = true)
    private String nom;

    @Column(name = "temporada", nullable = false)
    private String temporada;

    @OneToMany(mappedBy = "lliga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Equip> equips;
    public Lliga() {
    }

    public Lliga(String nom2, String temporadaCheck) {
        this.nom = nom2;
        this.temporada = temporadaCheck;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        if (comprovaFormatTemporada(temporada) != null) {
            this.temporada = temporada;
        }
        else {
            System.out.println("Format de temporada incorrecte");
        }
    }

    public Set<Equip> getEquips() {
        return equips;
    }

    public void setEquips(Set<Equip> equips) {
        this.equips = equips;
    }

    @Override
    public String toString() {
        return "Lliga{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", temporada='" + temporada + '\'' +
                '}';
    }
    private static String comprovaFormatTemporada(String temporada) {
        if (temporada.matches("\\d{4}/\\d{2}")) {
            return temporada;
        } else {
            System.out.println("Format de temporada incorrecte");
            return null;
        }
    }
}