/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Meta;

/**
 *
 * @author jenniffer
 */
@Local
public interface MetaFacadeLocal {

    boolean create(Meta meta);

    boolean edit(Meta meta);

    boolean remove(Meta meta);

    Meta find(Object id);

    List<Meta> findAll();

    List<Meta> findRange(int[] range);

    int count();

    public List<Meta> findRange(int first, int pageSize);
    
}
