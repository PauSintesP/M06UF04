package itic.bcn.Entitats;


import jakarta.persistence.*;

@Entity
@Table(name = "Classificacio")
public class Classificacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classificacio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equip", nullable = false)
    private Equip equip;

    @Column(name = "punts", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int punts;

    @Column(name = "partits_jugats", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int partitsJugats;

    @Column(name = "victories", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int victories;
    public Classificacio() {
    }

    public Classificacio(Equip equip2, int partitsJugats, int punts2) {
        this.equip = equip2;
        this.punts = punts2;
        this.partitsJugats = partitsJugats;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPartitsJugats() {
        return partitsJugats;
    }

    public void setPartitsJugats(int partitsJugats) {
        this.partitsJugats = partitsJugats;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
    @Override
    public String toString() {

        Long equipId = (equip != null) ? equip.getId() : null;
        return "Classificacio{" +
                "id=" + id +
                ", equip=" + (equipId != null ? equipId : "null") +
                ", partitsJugats=" + partitsJugats +
                ", punts=" + punts +
                '}';
    }
}