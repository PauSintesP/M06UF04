package itic.bcn.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import itic.bcn.Entitats.Jugador;
import itic.bcn.Entitats.Equip;
import itic.bcn.Utils.Entrada;

public class JugadorDAO extends GenDAOImpl<Jugador> {

    public JugadorDAO(SessionFactory sessionFactory) {
        super(Jugador.class, sessionFactory);
    }

    public Jugador crearJugador()  {
        Jugador jugador = questCreaJugador();
        if (jugador != null) {
            try {
                save(jugador);
                System.out.println("Jugador creat correctament.");
            } catch (Exception e) {
                System.out.println("Error al crear el jugador: " + e.getMessage());
                jugador = null;
            }
        } else {
            System.err.println("No s'ha pogut crear el jugador perquè l'equip no existeix.");
        }
        return jugador;
    }

    public Jugador consultarJugador()  {
        int id = questConsultaJugador();
        Jugador jugador;
        try {
            jugador = get(id);
        } catch (Exception e) {
            System.out.println("Error al consultar el jugador: " + e.getMessage());
            jugador = null;
        }
        if (jugador != null) {
            try (Session session = sessionFactory.openSession()) {
                jugador = session.find(Jugador.class, id);
            }
        }
        return jugador;
    }

    public void modificarJugador()  {
        System.out.println("Modificació del jugador");
        Jugador jugador = consultarJugador();
        if (jugador != null) {
            jugador = questModificarJugador(jugador);
            try {
                update(jugador);
                System.out.println("Jugador modificat correctament.");
            } catch (Exception e) {
                System.out.println("Error al modificar el jugador: " + e.getMessage());
            }
        } else {
            System.err.println("No s'ha pogut modificar el jugador perquè no existeix.");
        }
    }

    public void esborraJugador() {
        Jugador jugador;
        try {
            jugador = consultarJugador();
        } catch (Exception e) {
            System.out.println("Error al consultar el jugador: " + e.getMessage());
            jugador = null;
        }
        if (jugador != null) {
            try {
                delete(jugador);
                System.out.println("Jugador esborrat correctament.");
            } catch (Exception e) {
                System.out.println("Error al borrar el jugador: " + e.getMessage());
            }
        } else {
            System.err.println("No s'ha pogut borrar el jugador perquè no existeix.");
        }
    }

    public List<Jugador> findAll(){
        List<Jugador> jugadorList;
        try {
            jugadorList = getAll();
        } catch (Exception e) {
            System.out.println("Error al consultar els jugadors: " + e.getMessage());
            jugadorList = null;
        }
        try (Session session = sessionFactory.openSession()) {
            for (Jugador jugador : jugadorList) {
                jugador = session.find(Jugador.class, jugador.getId());
            }
        }
        return jugadorList;
    }

    public long countJugadors() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("select count(j) from Jugador j", Long.class);
            return query.uniqueResult();
        }
    }

    private Jugador questCreaJugador() {
        Equip equip;
        System.out.print("Quin és el nom del jugador? ");
        String nomJugador = Entrada.readLine();
        System.out.print("Quins són els cognoms? ");
        String cognoms = Entrada.readLine();
        System.out.print("Quina és la id de l'equip del jugador? ");
        Long idEquip = Long.parseLong(Entrada.readLine());

        EquipDAO equipCRUD = new EquipDAO(sessionFactory);
        
        try {
            equip = equipCRUD.get(idEquip.intValue());
        } catch (Exception e) {
            System.err.println("Error al consultar l'equip: " + e.getMessage());
            return null;
        }
        if (equip != null) {
            Jugador nouJugador = new Jugador();
            nouJugador.setNom(nomJugador);
            nouJugador.setCognoms(cognoms);
            nouJugador.setEquip(equip);
            return nouJugador;
        } else {
            System.err.println("L'equip amb id " + idEquip + " no existeix.");
            return null;
        }
    }

    private int questConsultaJugador() {
        System.out.print("Quina és la id del jugador? ");
        return Integer.parseInt(Entrada.readLine());
    }

    private Jugador questModificarJugador(Jugador jugador)  {
        System.out.print("Quin és el nou nom del jugador? ");
        jugador.setNom(Entrada.readLine());
        System.out.print("Quins són els nous cognoms del jugador? ");
        jugador.setCognoms(Entrada.readLine());
        System.out.print("Quina és la nova id de l'equip del jugador? ");
        int idEquip = Integer.parseInt(Entrada.readLine());

        EquipDAO equipCRUD = new EquipDAO(sessionFactory);
        Equip equip = equipCRUD.consultarEquip(idEquip);
        if (equip != null) {
            jugador.setEquip(equip);
        } else {
            System.err.println("L'equip amb id " + idEquip + " no existeix.");
        }
        return jugador;
    }
}
