package itic.bcn.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import itic.bcn.Entitats.Classificacio;
import itic.bcn.Entitats.Equip;
import itic.bcn.Utils.Entrada;

public class ClassificacioDAO extends GenDAOImpl<Classificacio> {

    public ClassificacioDAO(SessionFactory sessionFactory) {
        super(Classificacio.class, sessionFactory);
    }
    EquipDAO equipCRUD = new EquipDAO(sessionFactory);

    public Classificacio crearClassificacio() {
        Classificacio classificacio = questCreaClassificacio();
        if (classificacio != null) {
            try {
                save(classificacio);
            } catch (Exception e) {
                System.out.println("Error al crear la classificació: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder crear la classificació perquè l'equip no existeix.");
        }
        return classificacio;
    }

    public Classificacio consultarClassificacio() {
        Long id = questConsultaClassificacio();
        Classificacio classificacio;
        try {
            classificacio = get(id.intValue());
        } catch (Exception e) {
            System.out.println("Error al consultar la classificació: " + e.getMessage());
            classificacio = null;
        }
        if (classificacio != null) {
            try (Session session = sessionFactory.openSession()) {
                classificacio = session.find(Classificacio.class, id);
            }
        }
        return classificacio;
    }

    public void modificarClassificacio() {
        System.out.println("Modificació de la classificació");
        Classificacio classificacio = consultarClassificacio();
        if (classificacio != null) {
            classificacio = questModificarClassificacio(classificacio);
            try {
                update(classificacio);
            } catch (Exception e) {
                System.out.println("Error al modificar la classificació: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder modificar la classificació perquè no existeix.");
        }
    }

    public void esborraClassificacio() {
        Classificacio classificacio = consultarClassificacio();
        if (classificacio != null) {
            try {
                delete(classificacio);
            } catch (Exception e) {
                System.out.println("Error al borrar la classificació: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder borrar la classificació perquè no existeix.");
        }
    }

    public List<Classificacio> findAll() {
        List<Classificacio> classificacioList;
        try {
            classificacioList = getAll();
        } catch (Exception e) {
            System.out.println("Error al consultar totes les classificacions: " + e.getMessage());
            classificacioList = null;
        }
        try (Session session = sessionFactory.openSession()) {
            for (Classificacio classificacio : classificacioList) {
                classificacio = session.find(Classificacio.class, classificacio.getId());
            }
        }
        return classificacioList;
    }

    public long countClassificacions() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("select count(c) from Classificacio c", Long.class);
            return query.uniqueResult();
        }
    }

    private Classificacio questCreaClassificacio() {
        Equip equip = null;
        
        try {
            equip = equipCRUD.consultarEquip();
        } catch (Exception e) {
            System.out.println("Error al crear la classificació: " + e.getMessage());
        }

        if (equip != null) {
            System.out.println("Quants punts té l'equip dins la classificació? ");
            int punts = Integer.parseInt(Entrada.readLine());
            System.out.println("Quants partits ha jugat l'equip? ");
            int partitsJugats = Integer.parseInt(Entrada.readLine());
            Classificacio novaClassificacio = new Classificacio();
            novaClassificacio.setPunts(punts);
            novaClassificacio.setPartitsJugats(partitsJugats);
            novaClassificacio.setEquip(equip);
            return novaClassificacio;
        } else {
            System.out.println("No es pot crear la classificació perquè l'equip no existeix.");
            return null;
        }
    }

    private Long questConsultaClassificacio() {
        System.out.print("Quina és la id de la classificació? ");
        return Long.parseLong(Entrada.readLine());
    }

    private Classificacio questModificarClassificacio(Classificacio classificacio) {
        System.out.println("Quants punts té l'equip dins la classificació? ");
        int punts = Integer.parseInt(Entrada.readLine());
        System.out.println("Quants partits ha jugat l'equip? ");
        int partitsJugats = Integer.parseInt(Entrada.readLine());
        classificacio.setPunts(punts);
        classificacio.setPartitsJugats(partitsJugats);
        return classificacio;
    }
}
