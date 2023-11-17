package com.example.lab5gui.dao;

import com.example.lab5gui.entities.ferret.Ferret;
import com.example.lab5gui.entities.FerretDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import myLibrary.console.Console;

import java.sql.*;

public class DB {

    public static final String JDBC_MYSQL_LOCALHOST = "jdbc:mysql://localhost:3306/";
    // Данные для подключения к локальной базе данных
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "java_ferrets";
    private final String LOGIN = "root";
    private final String PASS = "Zhenua0334";
    private Connection dbConn = null;

    // Метод для подключения к БД с использованием значений выше
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = JDBC_MYSQL_LOCALHOST + DB_NAME;
        Class.forName("com.mysql.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    // Метод для добавления новых заданий внуть таблицы `ferrets`
    public void insertFerret(String name, String type) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `ferrets` (name,type)" +
                                    "VALUES (?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.setString(2, type);
        prSt.executeUpdate();

    }
    //PREPARED/CALLABEL STATEMENTS
    //Метод для получения последнего хорька
    public Ferret getLastFerret() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM ferrets ORDER BY ferretID DESC LIMIT 1";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        ResultSet res = prSt.executeQuery();
        if(res.next()) {
            return new Ferret(res.getString("name"), res.getString("breed"),
                    res.getInt("age"), res.getString("city"), res.getInt("levelOfTraining"),
                    res.getString("type"), res.getInt("ferretID"));
        }
        return null;
    }
    // Метод для получения всех хорьков из таблицы ferrets
    public ObservableList<FerretDB> getMartens() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ferretID, name FROM ferrets WHERE type='Marten' ORDER BY `ferretID` DESC";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ObservableList<FerretDB> maretens = FXCollections.observableArrayList();
        while(res.next()) {
            maretens.add(new FerretDB(res.getInt("ferretID"), res.getString("name")));
        }
        return maretens;
    }
    public ObservableList<FerretDB> getOtters() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ferretID, name FROM ferrets WHERE type='Otter' ORDER BY `ferretID` DESC";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ObservableList<FerretDB> otters = FXCollections.observableArrayList();
        while(res.next())
            otters.add(new FerretDB(res.getInt("ferretID"),res.getString("name")));

        return otters;
    }
    public  void  deleteFerret(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM ferrets WHERE ferretID=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

// get a connection and then in your try catch for executing your delete...

        prSt.setInt(1, id);
        prSt.executeUpdate();
    }
    public void updateFerret(Ferret ferret) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ferrets SET name=?, breed=?, age=?, city=?, levelOfTraining=? WHERE ferretID=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, ferret.getName().getValue());
        prSt.setString(2, ferret.getBreed().getValue());
        prSt.setInt(3, ferret.getAge().getValue());
        prSt.setString(4, ferret.getCity().getValue());
        prSt.setInt(5, ferret.getLevelOfTraining().getValue());
        Console.log(ferret.getIndex());
        prSt.setInt(6, ferret.getIndex());
        prSt.executeUpdate();
    }
    public Ferret getFerret(Integer index) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM ferrets WHERE ferretID = (?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, index);

        ResultSet res = prSt.executeQuery();
        Ferret ferret = null;
        if (res.next()) {
            ferret = new Ferret(res.getString("name"), res.getString("breed"),
                    res.getInt("age"), res.getString("city"), res.getInt("levelOfTraining"),
                    res.getString("type"), res.getInt("ferretID"));
        }
        return ferret;
    }
}
