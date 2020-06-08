/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Exclusion;

/**
 *
 * @author Usuario
 */
public interface IExclusionesDao {
    
    
    public ObservableList<Exclusion> obtenerExclusiones();
    
    public Exclusion obtenerExclusion(int id);
    
    public Exclusion obtenerExclusionPorCP(String cp);

    public int añadirExclusion(Exclusion exclusion);

    public boolean actualizarExclusiones(Exclusion exclusion);

    public boolean borrarExclusion(int exclusionId); 
}
