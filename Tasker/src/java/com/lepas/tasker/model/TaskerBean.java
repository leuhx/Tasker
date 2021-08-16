/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lepas.tasker.model;

import com.lepas.tasker.entidade.Tasker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author lepas
 */
@Named
@SessionScoped
public class TaskerBean implements Serializable{
    
    private Tasker tarefa = new Tasker();
    private List<Tasker> tarefas = new ArrayList<>();
    private TaskerDAO taskerDAO = new TaskerDAO();
    
    public void adicionar(){
        taskerDAO.salvar(tarefa);
        tarefa = new Tasker();
        listar();
    }
    
    public void listar(){
        tarefas = taskerDAO.buscar();
    }
    
    public void editar(Tasker t){
        tarefa = t;
        listar();
    }
    
    public void apagar(Tasker t){
        //tarefa = t;
        taskerDAO.apagar(t);
        listar();
    }

    public Tasker getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tasker tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tasker> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tasker> tarefas) {
        this.tarefas = tarefas;
    }
    
}
