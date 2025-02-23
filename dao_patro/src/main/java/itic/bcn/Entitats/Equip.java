package itic.bcn.Entitats;

import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Equip")
public class Equip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equip")
    private Long id;

    @Column(name = "nom_equip", nullable = false, unique = true)
    private String nom;

    @Column(name = "ciutat", nullable = false)
    private String ciutat;

    @ManyToOne
    @JoinColumn(name = "id_lliga", nullable = false)
    private Lliga lliga;

    @OneToMany(mappedBy = "equip")
    private Set<Jugador> jugadors;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Lliga getLliga() {
        return lliga;
    }

    public void setLliga(Lliga lliga) {
        this.lliga = lliga;
    }

    public Set<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
    @Override
    public String toString() {
        return "Equip{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", ciutat='" + ciutat + '\'' +
                ", lliga=" + (lliga != null ? lliga.getId() : "null") +
                '}';
    }
}