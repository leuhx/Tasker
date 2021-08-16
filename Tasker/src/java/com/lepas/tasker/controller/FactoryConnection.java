/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lepas.tasker.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lepas
 */
public class FactoryConnection {
    
    private static Connection conexao;
    private static final String URL = "jdbc:postgresql://localhost:5432/tasker";
    private static final String USR = "postgres";
    private static final String PASSWORD = "swifty365";
    

    public static Connection getConexao() {
        if(conexao == null){
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(URL, USR, PASSWORD);
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FactoryConnection.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
        return conexao;
    }
    
    public static void fecharConexao(){
        if(conexao!=null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                Logger.getLogger(FactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
