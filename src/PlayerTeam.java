
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import model.Player;
import model.Team;
import persistence.PlayerTeamDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xavimanzano
 */
public class PlayerTeam {

    public static void main(String[] args) throws SQLException {
        PlayerTeamDAO pteamDAO = new PlayerTeamDAO();
        System.out.println("Testeando conexión con la base de datos...");

        pteamDAO.conectar();
        System.out.println("Conexión OK");
        try {
            System.out.println("Cocinero dado de alta");
            Team t3 = new Team("Lords1", "BCN", LocalDate.of(1995, Month.DECEMBER, 4));
            Team t4 = new Team("Lords3", "BCN", LocalDate.of(1995, Month.DECEMBER, 4));

            System.out.println("Testeando insert team " + t3.getName());
            System.out.println("");
            System.out.println("Testeando insert team " + t4.getName());
            try {
                pteamDAO.insertTeam(t3);
                pteamDAO.insertTeam(t4);
                System.out.println("TEAM OK");
            } catch (Exception ex) {
                System.out.println("INSERT TEAM ERROR " + ex.getMessage());

            }
            Player p3 = new Player("Jugador3", LocalDate.of(1995, Month.JANUARY, 2), 4, 5, 6, "ALE", t3);
            Player p4 = new Player("Jugador4", LocalDate.of(1995, Month.JANUARY, 2), 4, 5, 6, "ALE", t4);
            System.out.println("Testeando insert Player" + p3.getName());
            System.out.println("");
            System.out.println("Testeando insert Player" + p4.getName());
            try {
                pteamDAO.insertPlayer(p3);
                pteamDAO.insertPlayer(p4);
                System.out.println("PLAYER OK");
            } catch (Exception ex) {
                System.out.println("INSERT PLAYER ERROR " + ex.getMessage());

            }
            System.out.println("TESTEANDO MODIFICAR STATS JUGADOR");

            try {
                pteamDAO.updateStadistics(p3, 5, 30, 56);
                pteamDAO.updateStadistics(p4, 65, 80, 96);
                System.out.println("STATS MODIFICADOS OK");
            } catch (Exception ex) {
                System.out.println("ERROR MODIFICAR STATS" + ex.getMessage());
            }

            System.out.println("TESTEANDO MODIFICAR STATS JUGADOR");

            try {
                pteamDAO.updateTeamPlayer(t3, p3);
                System.out.println("MODIFICAR EQUIPO OK");
            } catch (Exception ex) {
                System.out.println("ERROR MODIFICAR EQUIPO" + ex.getMessage());
            }
            try {
                pteamDAO.deletePlayer(p3);
                System.out.println("PLAYER 2 BORRADO CON ÉXITO");
            } catch (Exception ex) {
                System.out.println("ERROR DELETE PLAYER" + ex.getMessage());
            }

        } catch (Exception ex) {
            System.out.println("Error al conectar con la bbdd: " + ex.getMessage());

        }
    }

}
