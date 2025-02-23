package itic.bcn.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import itic.bcn.Entitats.Lliga;
import itic.bcn.Utils.Entrada;

public class LligaDAO extends GenDAOImpl<Lliga> {

    public LligaDAO(SessionFactory sessionFactory) {
        super(Lliga.class, sessionFactory);
    }

    public Lliga crearLliga() {
        Lliga lliga = questCreaLliga();
        if (lliga != null) {
            try {
                save(lliga);
            } catch (Exception e) {
                System.out.println("Error al crear la lliga: " + e.getMessage());
            }
        } else {
            System.err.println("No es pot crear la lliga perquè l'equip no existeix.");
        }
        return lliga;
    }

    public Lliga consultarLliga() {
        int id = questConsultaLliga();
        Lliga lliga;
        try {
            lliga = get(id);
        } catch (Exception e) {
            System.out.println("Error al consultar la lliga: " + e.getMessage());
            lliga = null;
        }

        return lliga;
    }
    public Lliga consultarLliga(int id) {
        Lliga lliga;
        try {
            lliga = get(id);
        } catch (Exception e) {
            System.out.println("L'id de la lliga no existeix: " + e.getMessage());
            lliga = null;
        }

        return lliga;
    }

    public void modificarLliga() {
        System.out.println("Modificació de la lliga");
        Lliga lliga = consultarLliga();
        if (lliga != null) {
            lliga = questModificarLliga(lliga);
            try {
                update(lliga);
            } catch (Exception e) {
                System.out.println("Error al modificar la lliga: " + e.getMessage());
            }
        } else {
            System.err.println("No es pot modificar la lliga perquè no existeix.");
        }
    }

    public void esborraLliga() {
        Lliga lliga = consultarLliga();
        if (lliga != null) {
            try {
                delete(lliga);
            } catch (Exception e) {
                System.out.println("Error al borrar la lliga: " + e.getMessage());
            }
        } else {
            System.err.println("No es pot borrar la lliga perquè no existeix.");
        }
    }

    public List<Lliga> findAll() {
        List<Lliga> lligaList;
        try {
            lligaList = getAll();
        } catch (Exception e) {
            System.out.println("Error al consultar totes les lligues: " + e.getMessage());
            lligaList = null;
        }

        return lligaList;
    }

    public long countLligues() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("select count(l) from Lliga l", Long.class);
            return query.uniqueResult();
        }
    }

    private Lliga questCreaLliga() {
        System.out.println("Quin és el nom de la lliga?");
        String nom = Entrada.readLine();
        System.out.println("Quina és la temporada de la lliga?");
        String temporada = Entrada.readLine();
        if (!isValidTemporadaFormat(temporada)) {
            System.err.println("El format de la temporada no és correcte, ha de ser yyyy/yy");
            return null;
        }
        if (nom == null || nom.isEmpty()) {
            System.err.println("El nom de la lliga no pot ser buit.");
            return null;
        }
        return new Lliga(nom, temporada);
    }

    private boolean isValidTemporadaFormat(String temporada) {
        return temporada.matches("\\d{4}/\\d{2}");
    }

    private int questConsultaLliga() {
        System.out.println("Quin és l'id de la lliga que vols consultar?");
        return Integer.parseInt(Entrada.readLine());
    }

    private Lliga questModificarLliga(Lliga lliga) {
        System.out.println("Quin és el nou nom de la lliga?");
        lliga.setNom(Entrada.readLine());
        System.out.println("Quina és la nova temporada de la lliga?");
        String temporada = Entrada.readLine();
        if (isValidTemporadaFormat(temporada)) {
            lliga.setTemporada(temporada);
        } else {
            System.err.println("El format de la temporada no és correcte, ha de ser yyyy/yy");
        }
        return lliga;
    }
}
