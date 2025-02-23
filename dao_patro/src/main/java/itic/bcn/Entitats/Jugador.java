package itic.bcn.Entitats;

import jakarta.persistence.*;

@Entity
@Table(name = "Jugador")
public class Jugador {
    public Jugador() {
    }

    public Jugador(String nom, String cognom, Equip equip) {
        this.nom = nom;
        this.cognoms = cognom;
        this.equip = equip;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private int id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "cognoms", nullable = false)
    private String cognoms;

    @ManyToOne
    @JoinColumn(name = "id_equip", nullable = true)
    private Equip equip;

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

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom=" + cognoms + '\'' +
                ", equip=" + (equip != null ? equip.getId() : "null") +
                '}';
    }
}