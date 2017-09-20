package com.logan;

import com.logan.helpers.DatabaseManager;
import com.logan.models.Stat;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:stats.db")){
            DatabaseManager dbm = new DatabaseManager(conn);
            //Statement statement = dbm.getStatement();
            dbm.dropStatTable();
            dbm.createStatTable();
            dbm.insertIntoStatTable("logan", 40, 10);
            List<Stat> results = dbm.getStats();

        }catch ( SQLException ex){
            ex.printStackTrace();
            System.out.println("we encounted a problem connecting to the database");
        }

        System.out.println("you did it nerd!");
    }
}
