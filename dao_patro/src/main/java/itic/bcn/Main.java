package itic.bcn;

import org.hibernate.SessionFactory;

import itic.bcn.DAO.ClassificacioDAO;
import itic.bcn.DAO.EquipDAO;
import itic.bcn.DAO.JugadorDAO;
import itic.bcn.DAO.LligaDAO;
import itic.bcn.Entitats.Classificacio;
import itic.bcn.Entitats.Equip;
import itic.bcn.Entitats.Jugador;
import itic.bcn.Entitats.Lliga;
import itic.bcn.Utils.Entrada;
import itic.bcn.Utils.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int entitat;
        System.out.println("Benvingut a la gestió de la lliga");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        ClassificacioDAO ClassificacioDAO = new ClassificacioDAO(sessionFactory);
        EquipDAO EquipDAO = new EquipDAO(sessionFactory);
        JugadorDAO JugadorDAO = new JugadorDAO(sessionFactory);
        LligaDAO LligaDAO = new LligaDAO(sessionFactory);

        while (true) {
            System.out.println("A quina entitat vols accedir\n1. Equip\n2. Jugador\n3. Classificació\n4. Lliga\n5. Sortir");
            String entitatInput = Entrada.readLine();

            if (entitatInput.isEmpty()) {
                System.out.println("L'entrada no pot estar buida. Si us plau, introdueix una opció vàlida.");
                continue;
            }
            try {
                entitat = Integer.parseInt(entitatInput);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            if (entitat == 5) {
                System.out.println("Sortint del programa...");
                break;
            }

            if (entitat < 1 || entitat > 5) {
                System.out.println("Opció no vàlida. Si us plau, introdueix una opció vàlida.");
                continue;
            }

            switch (entitat) {
                case 1:
                    mostrarMenuEquip(EquipDAO);
                    break;
                case 2:
                    mostrarMenuJugador(EquipDAO, JugadorDAO);
                    break;
                case 3:
                    mostrarMenuClassificacio(EquipDAO, ClassificacioDAO);
                    break;
                case 4:
                    mostrarMenuLliga(LligaDAO);
                    break;
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuEquip(EquipDAO EquipDAO) {
        int opcio;
        while (true) {
            System.out.println("1. Crear\n2. Consultar\n3. Modificar\n4. Esborrar\n5. Llistar\n6. Comptar\n7. Enrere");
            String opcioInput = Entrada.readLine();

            if (opcioInput.isEmpty()) {
                System.out.println("L'entrada no pot estar buida. Si us plau, introdueix una opció vàlida.");
                continue;
            }
            try {
                opcio = Integer.parseInt(opcioInput);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            if (opcio == 7) {
                break;
            }

            if (opcio < 1 || opcio > 7) {
                System.out.println("Opció no vàlida. Si us plau, introdueix una opció vàlida.");
                continue;
            }

            switch (opcio) {
                case 1:
                    EquipDAO.crearEquip();
                    break;
                case 2:
                    Equip equip = EquipDAO.consultarEquip();
                    System.out.println(equip);
                    break;
                case 3:
                    EquipDAO.modificarEquip();
                    break;
                case 4:
                    EquipDAO.esborraEquip();
                    break;
                case 5:
                    System.out.println("Llistat d'equips");
                    List<Equip> equips = EquipDAO.findAll();
                    for (Equip e : equips) {
                        System.out.println(e);
                    }
                    System.out.println();
                    break;
                case 6:
                    long countEquips = EquipDAO.countEquips();
                    System.out.println("Número total d'equips: " + countEquips);
                    break;
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuJugador(EquipDAO EquipDAO, JugadorDAO JugadorDAO) {
        int opcio;
        while (true) {
            System.out.println("1. Crear\n2. Consultar\n3. Modificar\n4. Esborrar\n5. Llistar\n6. Comptar\n7. Enrere");
            String opcioInput = Entrada.readLine();

            if (opcioInput.isEmpty()) {
                System.out.println("L'entrada no pot estar buida. Si us plau, introdueix una opció vàlida.");
                continue;
            }
            try {
                opcio = Integer.parseInt(opcioInput);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            if (opcio == 7) {
                break;
            }

            if (opcio < 1 || opcio > 7) {
                System.out.println("Opció no vàlida. Si us plau, introdueix una opció vàlida.");
                continue;
            }

            switch (opcio) {
                case 1:
                    JugadorDAO.crearJugador();
                    break;
                case 2:
                    System.out.println(JugadorDAO.consultarJugador());
                    break;
                case 3:
                    JugadorDAO.modificarJugador();
                    break;
                case 4:
                    JugadorDAO.esborraJugador();
                    break;
                case 5:
                    System.out.println("Llistat de jugadors");
                    List<Jugador> jugadors = JugadorDAO.findAll();
                    for (Jugador j : jugadors) {
                        System.out.println(j);
                    }
                    System.out.println();
                    break;
                case 6:
                    long countJugadors = JugadorDAO.countJugadors();
                    System.out.println("Número total de jugadors: " + countJugadors);
                    break;
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuClassificacio(EquipDAO EquipDAO, ClassificacioDAO ClassificacioDAO) {
        int opcio;
        while (true) {
            System.out.println("1. Crear\n2. Consultar\n3. Modificar\n4. Esborrar\n5. Llistar\n6. Comptar\n7. Enrere");
            String opcioInput = Entrada.readLine();

            if (opcioInput.isEmpty()) {
                System.out.println("L'entrada no pot estar buida. Si us plau, introdueix una opció vàlida.");
                continue;
            }
            try {
                opcio = Integer.parseInt(opcioInput);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            if (opcio == 7) {
                break;
            }

            if (opcio < 1 || opcio > 7) {
                System.out.println("Opció no vàlida. Si us plau, introdueix una opció vàlida.");
                continue;
            }

            switch (opcio) {
                case 1:
                    ClassificacioDAO.crearClassificacio();
                    break;
                case 2:
                    System.out.println(ClassificacioDAO.consultarClassificacio());
                    break;
                case 3:
                    ClassificacioDAO.modificarClassificacio();
                    break;
                case 4:
                    ClassificacioDAO.esborraClassificacio();
                    break;
                case 5:
                    System.out.println("Llistat de classificacions");
                    List<Classificacio> classificacions = ClassificacioDAO.findAll();
                    for (Classificacio c : classificacions) {
                        System.out.println(c);
                    }
                    System.out.println();
                    break;
                case 6:
                    long countClassificacions = ClassificacioDAO.countClassificacions();
                    System.out.println("Número total de classificacions: " + countClassificacions);
                    break;
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuLliga(LligaDAO LligaDAO) {
        int opcio;
        while (true) {
            System.out.println("1. Crear\n2. Consultar\n3. Modificar\n4. Esborrar\n5. Llistar\n6. Comptar\n7. Enrere");
            String opcioInput = Entrada.readLine();

            if (opcioInput.isEmpty()) {
                System.out.println("L'entrada no pot estar buida. Si us plau, introdueix una opció vàlida.");
                continue;
            }
            try {
                opcio = Integer.parseInt(opcioInput);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            if (opcio == 7) {
                break;
            }

            if (opcio < 1 || opcio > 7) {
                System.out.println("Opció no vàlida. Si us plau, introdueix una opció vàlida.");
                continue;
            }

            switch (opcio) {
                case 1:
                    LligaDAO.crearLliga();
                    break;
                case 2:
                    System.out.println(LligaDAO.consultarLliga());
                    break;
                case 3:
                    LligaDAO.modificarLliga();
                    break;
                case 4:
                    LligaDAO.esborraLliga();
                    break;
                case 5:
                    System.out.println("Llistat de lligues");
                    List<Lliga> lligues = LligaDAO.findAll();
                    for (Lliga l : lligues) {
                        System.out.println(l);
                    }
                    System.out.println();
                    break;
                case 6:
                    long countLligues = LligaDAO.countLligues();
                    System.out.println("Número total de lligues: " + countLligues);
                    break;
                default:
                    break;
            }
        }
    }
}
