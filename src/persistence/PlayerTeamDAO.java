/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Player;
import model.Team;

/**
 *
 * @author xavimanzano
 */
public class PlayerTeamDAO {

    private Connection conexion;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:8889/basket";
        String usr = "root";
        String pass = "root";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    //1) Insertar un equipo nuevo en la bbdd.
    public void insertTeam(Team t) throws SQLException {
        String insert = "insert into team values (?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, t.getName());
        ps.setString(2, t.getCity());
        ps.setDate(3, java.sql.Date.valueOf(t.getCreation()));
        ps.executeUpdate();
        ps.close();
    }

    //2) Insertar un nuevo jugador a la bbdd.
    public void insertPlayer(Player p) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, p.getName());
        ps.setDate(2, java.sql.Date.valueOf(p.getBirth()));
        ps.setInt(3, p.getNbaskets());
        ps.setInt(4, p.getNassists());
        ps.setInt(5, p.getNrebounds());
        ps.setString(6, p.getPosition());
        ps.setString(7, p.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }

    //3) Modificar canastas , asistencias y rebotes de un jugador determinado.
    public void updateStadistics(Player p, int Nbaskets, int Nassists, int Nrebounds) throws SQLException {
        String update = "update player set nbaskets = ?, nassists = ?, nrebounds =? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, p.getName());
        ps.setDate(2, java.sql.Date.valueOf(p.getBirth()));
        ps.setInt(1, Nbaskets);
        ps.setInt(2, Nassists);
        ps.setInt(3, Nrebounds);
        ps.setString(4, p.getName());
        ps.executeUpdate();
        ps.close();

    }
    // 4) Modificar el equipo de un jugador determinado .

    public void updateTeamPlayer(Team t, Player p) throws SQLException {
        String update = "update player set team = ? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, t.getName());
        ps.setString(2, p.getName());
        ps.executeUpdate();
        ps.close();

    }
    // 5) Borrar un jugador de la base de datos.

    public void deletePlayer(Player p) throws SQLException {
        String delete = "delete from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.setString(1, p.getName());
        ps.executeUpdate();
        ps.close();
    }
}
