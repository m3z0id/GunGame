package com.m3z0id.gunGame.database;

import com.m3z0id.gunGame.GunGame;

import java.io.File;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Database {
    private Connection connection;
    private String url;

    public Database(String url) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            this.url = url;

            String query = "CREATE TABLE IF NOT EXISTS stats (player TEXT UNIQUE NOT NULL, killCount INTEGER NOT NULL, deathCount INTEGER NOT NULL, highestLevel INTEGER NOT NULL)";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not connect to database: " + e.getMessage());
        }
    }

    public boolean playerExists(String player) {
        String query = "SELECT 1 FROM stats WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Unable to retrieve from database: " + e.getMessage());
            return false;
        }
    }
    public int getPlayerKills(String player) {
        String query = "SELECT killCount FROM stats WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("killCount");
            }
            return 0;
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve player kills: " + e.getMessage());
            return 0;
        }
    }
    public int getPlayerDeaths(String player) {
        String query = "SELECT deathCount FROM stats WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("deathCount");
            }
            return 0;
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve player deaths: " + e.getMessage());
            return 0;
        }
    }
    public int getPlayerHighestLevel(String player) {
        String query = "SELECT highestLevel FROM stats WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("highestLevel");
            }
            return 1;
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve player highest level: " + e.getMessage());
            return 1;
        }
    }

    public void addPlayer(String player) {
        String query = "INSERT INTO stats (player, killCount, deathCount, highestLevel) VALUES (?, 0, 0, 1)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not add player to stats: " + e.getMessage());
        }
    }

    public void setPlayerHighestLevel(String player, int level) {
        String query = "UPDATE stats SET highestLevel = ? WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, level);
            statement.setString(2, player);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not update highest level: " + e.getMessage());
        }
    }
    public void setPlayerKills(String player, int level) {
        String query = "UPDATE stats SET killCount = ? WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, level);
            statement.setString(2, player);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not update highest level: " + e.getMessage());
        }
    }
    public void setPlayerDeaths(String player, int level) {
        String query = "UPDATE stats SET deathCount = ? WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, level);
            statement.setString(2, player);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not update highest level: " + e.getMessage());
        }
    }

    public void addPlayerHighestLevel(String player) {
        addPlayerHighestLevels(player, 1);
    }
    public void addPlayerHighestLevels(String player, int amount) {
        setPlayerHighestLevel(player, amount);
    }

    public void addPlayerKill(String player) {
        addPlayerKills(player, 1);
    }
    public void addPlayerKills(String player, int amount) {
        setPlayerKills(player, getPlayerKills(player) + amount);
    }

    public void addPlayerDeath(String player) {
        addPlayerDeaths(player, 1);
    }
    public void addPlayerDeaths(String player, int level) {
        setPlayerDeaths(player, getPlayerDeaths(player) + level);
    }

    public Map<Integer, String> getTopNKillers(int n){
        String query = "SELECT player FROM stats ORDER BY killCount DESC LIMIT ?";
        Map<Integer, String> result = new LinkedHashMap<>();
        try {
            return getIntegerStringMap(n, query, result);
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve top Kills: " + e.getMessage());
            return null;
        }
    }
    public Map<Integer, String> getTopNDeaths(int n){
        String query = "SELECT player FROM stats ORDER BY deathCount DESC LIMIT ?";
        Map<Integer, String> result = new LinkedHashMap<>();
        try {
            return getIntegerStringMap(n, query, result);
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve top deaths: " + e.getMessage());
            return null;
        }
    }
    public Map<Integer, String> getTopNHighestLevels(int n){
        String query = "SELECT player FROM stats ORDER BY highestLevel DESC LIMIT ?";
        Map<Integer, String> result = new LinkedHashMap<>();
        try {
            return getIntegerStringMap(n, query, result);
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not retrieve top highest levels: " + e.getMessage());
            return null;
        }
    }

    private Map<Integer, String> getIntegerStringMap(int n, String query, Map<Integer, String> result) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, n);
        ResultSet resultSet = statement.executeQuery();
        int rank = 0;
        while (resultSet.next()) {
            rank++;
            if(rank >= n) break;
            result.put(rank, resultSet.getString("player"));
        }
        return result;
    }

    public void reset(){
        String query = "DELETE FROM stats";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not reset stats: " + e.getMessage());
        }
    }

    public void removePlayer(String player) {
        String query = "DELETE FROM stats WHERE player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player);
            statement.executeUpdate();
        } catch (SQLException e) {
            GunGame.instance.getLogger().severe("Could not remove player: " + e.getMessage());
        }
    }

    public static Database get(){
        File databaseFile = new File(GunGame.instance.getDataFolder(), "stats.db");
        return new Database(databaseFile.toString());
    }
}
