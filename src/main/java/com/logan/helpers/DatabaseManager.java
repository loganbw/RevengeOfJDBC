package com.logan.helpers;

import com.logan.models.Stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection conn;
    private Statement statement;

    public DatabaseManager(Connection conn) throws SQLException{
        this.conn = conn;
        this.statement = conn.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void dropStatTable() throws SQLException{
        statement.executeUpdate("DROP TABLE IF EXISTS stats; ");
    }
    public void createStatTable() throws  SQLException{
        statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY,  name STRING, wins INTEGER, losses INTEGER);");

    }
    public void insertIntoStatTable(String name, int wins, int losses) throws SQLException{
        //vulnerable to sql injections
        String sqlString = String.format("INSERT  INTO stats (name, wins, losses) VALUES ('%s', %d, %d);", name, wins, losses);
        statement.executeUpdate(sqlString);

    }

    public List<Stat> getStats()throws SQLException {
        ResultSet rs = statement.executeQuery(" SELECT * FROM stats");
        List<Stat> retVal = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("name");
            int wins = rs.getInt("wins");
            int losses = rs.getInt("losses");
            System.out.printf("%s %s %s\n", name, wins, losses);

            Stat tempStat = new Stat("logan", 10, 50);
            retVal.add(tempStat);
        }
        return retVal;
    }

}
