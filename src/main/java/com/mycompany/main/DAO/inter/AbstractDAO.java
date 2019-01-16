/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main.DAO.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public abstract class AbstractDAO {

    public Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String user = "root";
        String pass = "12345";
        String url = "jdbc:mysql://localhost:3306/resume";
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

}
