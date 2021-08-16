/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lepas.tasker.model;

import com.lepas.tasker.controller.FactoryConnection;
import com.lepas.tasker.entidade.Tasker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lepas
 */
public class TaskerDAO {
    
    public void salvar(Tasker t){
        try {
            Connection conexao = FactoryConnection.getConexao();
            PreparedStatement ps;
            if(t.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO tarefas (titulo, descricao, responsavel, prioridade, deadline, status)\n" +
                    "VALUES (?,?,?,?,?,?);");
            } else{
                ps = conexao.prepareStatement("UPDATE tarefas SET titulo=?, descricao=?, responsavel=?, prioridade=?, deadline=?, status=? \n" +
                        "WHERE id=?;");
                ps.setInt(6, t.getId());
            }
                
            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescricao());
            ps.setString(3, t.getResponsavel());
            ps.setString(4, t.getPrioridade());
            ps.setString(5, t.getDeadline());
            ps.setString(6, t.getStatus());
            ps.execute();
            FactoryConnection.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public List<Tasker> buscar(){
         List<Tasker> tarefas = new ArrayList<>();
        try {
            Connection conexao = FactoryConnection.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM tarefas");
            ResultSet set = ps.executeQuery();

            while(set.next()){
                Tasker tarefa = new Tasker();
                tarefa.setId(set.getInt("id"));
                tarefa.setTitulo(set.getString("titulo"));
                tarefa.setDescricao(set.getString("descricao"));
                tarefa.setResponsavel(set.getString("responsavel"));
                tarefa.setPrioridade(set.getString("prioridade"));
                tarefa.setDeadline(set.getString("deadline"));
                tarefa.setStatus(set.getString("status"));
                tarefas.add(tarefa);
            }
            FactoryConnection.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tarefas;
        
    }
    
    public void apagar(Tasker t){
        try {
            Connection conexao = FactoryConnection.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM tarefas WHERE id=?;");
            ps.setInt(1, t.getId());
            ps.execute();
            FactoryConnection.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
