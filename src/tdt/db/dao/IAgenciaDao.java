/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Agencia;

/**
 *
 * @author Usuario
 */
public interface IAgenciaDao {

    public ObservableList<Agencia> obtenerAgencias();

    public Agencia obtenerAgencia(int id);

    public int añadirAgencia(Agencia agencia);

    public boolean actualizarAgencia(Agencia agencia);

    public boolean borrarAgencia(int agenciaId);

}
