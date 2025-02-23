package itic.bcn.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import itic.bcn.Entitats.Equip;
import itic.bcn.Entitats.Lliga;
import itic.bcn.Utils.Entrada;

public class EquipDAO extends GenDAOImpl<Equip> {

    public EquipDAO(SessionFactory sessionFactory) {
        super(Equip.class, sessionFactory);
    }

    public Equip crearEquip(){
        Equip equip = questCreaEquip();
        if (equip != null) {
            try {
                save(equip);
            } catch (Exception e) {
                System.out.println("Error al crear l'equip: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder crear l'equip perquè la lliga no existeix.");
        }
        return equip;
    }

    public Equip consultarEquip(){
        Long id = questConsultaEquip();
        Equip equip;
        try {
            equip = get(id.intValue());
        } catch (Exception e) {
            System.out.println("Error al consultar l'equip: " + e.getMessage());
            equip = null;
        }
        if (equip != null) {
            try (Session session = sessionFactory.openSession()) {
                equip = session.find(Equip.class, id);
            }
        }
        return equip;
    }
    public Equip consultarEquip(int id){
        Equip equip;
        try {
            equip = get(id);
        } catch (Exception e) {
            System.out.println("Error al consultar l'equip: " + e.getMessage());
            equip = null;
        }
        if (equip != null) {
            try (Session session = sessionFactory.openSession()) {
                equip = session.find(Equip.class, id);
            }
        }
        return equip;
    }

    public void modificarEquip(){
        System.out.println("Modificació de l'equip");
        Equip equip = consultarEquip();
        if (equip != null) {
            equip = questModificarEquip(equip);
            try {
                update(equip);
            } catch (Exception e) {
                System.out.println("Error al modificar l'equip: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder modificar l'equip perquè no existeix.");
        }
    }

    public void esborraEquip(){
        Equip equip = consultarEquip();
        if (equip != null) {
            try {
                delete(equip);
            } catch (Exception e) {
                System.out.println("Error al esborrar l'equip: " + e.getMessage());
            }
        } else {
            System.err.println("No es va poder esborrar l'equip perquè no existeix.");
        }
    }

    public List<Equip> findAll(){
        List<Equip> equipList;
        try {
            equipList = getAll();
        } catch (Exception e) {
            System.out.println("Error al consultar tots els equips: " + e.getMessage());
            equipList = null;
        }
        try (Session session = sessionFactory.openSession()) {
            for (Equip equip : equipList) {
                equip = session.find(Equip.class, equip.getId());
            }
        }
        return equipList;
    }

    public long countEquips() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("select count(e) from Equip e", Long.class);
            return query.uniqueResult();
        }
    }

    private Equip questCreaEquip() {
        Lliga lliga = null;
        System.out.print("Quin és el nom de l'equip? ");
        String nomEquip = Entrada.readLine();
        System.out.print("Quina és la ciutat de l'equip? ");
        String equipCiutat = Entrada.readLine();
        System.out.print("Quina és la id de la lliga de l'equip? ");
        int idLliga = Integer.parseInt(Entrada.readLine());

        LligaDAO lligaCRUD = new LligaDAO(sessionFactory);
        try {
            lliga = lligaCRUD.consultarLliga(idLliga);
        } catch (Exception e) {
            System.out.println("Error al crear l'equip: " + e.getMessage());
        }
        
        if (lliga != null) {
            Equip nouEquip = new Equip();
            nouEquip.setNom(nomEquip);
            nouEquip.setCiutat(equipCiutat);
            nouEquip.setLliga(lliga);
            return nouEquip;
        } else {
            System.err.println("La lliga amb id " + idLliga + " no existeix.");
            return null;
        }
    }

    private Long questConsultaEquip() {
        System.out.print("Quina és la id de l'equip? ");
        return Long.parseLong(Entrada.readLine());
    }

    private Equip questModificarEquip(Equip equip) {
        Lliga lliga = null;
        System.out.print("Quin és el nou nom de l'equip? ");
        equip.setNom(Entrada.readLine());
        System.out.print("Quina és la nova ciutat de l'equip? ");
        equip.setCiutat(Entrada.readLine());
        System.out.print("Quina és la nova id de la lliga de l'equip? ");
        int idLliga = Integer.parseInt(Entrada.readLine());

        LligaDAO lligaCRUD = new LligaDAO(sessionFactory);
        try {
            lliga = lligaCRUD.consultarLliga(idLliga);
        } catch (Exception e) {
            System.out.println("Error al modificar l'equip: " + e.getMessage());
        }
        
        if (lliga != null) {
            equip.setLliga(lliga);
        } else {
            System.err.println("La lliga amb id " + idLliga + " no existeix.");
        }
        return equip;
    }
}
