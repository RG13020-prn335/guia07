/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.MetaFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Meta;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoSeccion;




/**
 *
 * @author jenniffer
 */
@Named(value = "metaBean")
@ViewScoped
public class metaBean implements Serializable{

    /**
     * Creates a new instance of metaBean
     */
    public metaBean() {
    }
    @EJB
    MetaFacadeLocal meta;
     private LazyDataModel<Meta> modelo;
     private Meta nuevo;
     private boolean btnagregar= true;
    private boolean botones = false;
    private boolean seleccciones =false;

    public MetaFacadeLocal getMeta() {
        return meta;
    }

    public LazyDataModel<Meta> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Meta> modelo) {
        this.modelo = modelo;
    }

    public boolean isBtnagregar() {
        return btnagregar;
    }

    public void setBtnagregar(boolean btnagregar) {
        this.btnagregar = btnagregar;
    }

    public boolean isBotones() {
        return botones;
    }

    public void setBotones(boolean botones) {
        this.botones = botones;
    }

    public boolean isSeleccciones() {
        return seleccciones;
    }

    public void setSeleccciones(boolean seleccciones) {
        this.seleccciones = seleccciones;
    }

    public Meta getNuevo() {
        return nuevo;
    }

    public void setNuevo(Meta nuevo) {
        this.nuevo = nuevo;
    }

    public void setMeta(MetaFacadeLocal meta) {
        this.meta = meta;
    }

 
    
     public void crear() {
        try {
            if (this.nuevo != null && this.meta != null) {
               if (this.meta.create(nuevo)) {
                    System.out.println("registro agregado");
                    init();
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
      
      @PostConstruct
     public void init() {
    nuevo = new Meta();

        try {
            this.modelo = new LazyDataModel<Meta>() {
                @Override
                public Object getRowKey(Meta object) {
                    if (object != null) {
                        return object.getIdMeta();
                    }
                    return null;
                }

                @Override
                public Meta getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (Meta nue : (List<Meta>) getWrappedData()) {
                                if (nue.getIdMeta().compareTo(buscado) == 0) {
                                    return nue;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }

                @Override
                public List<Meta> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Meta> salida = new ArrayList();
                    try {
                        if (meta != null) {
                            this.setRowCount(meta.count());
                            salida = meta.findRange(first,pageSize);
                            
                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }

            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
                 
    }
     public void Eliminar() {
        try {

            if (nuevo != null && meta != null) {
                if (meta.remove(nuevo)) {
                    nuevo = new Meta();
                    init();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
     public void Modificar() {
        try {
            if (nuevo != null && meta != null) {
                if (meta.edit(nuevo)) {
                    init();
                    
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
     
     public void cancelar() {
        nuevo = new Meta();
        this.botones=false;
        this.btnagregar=true;
     }
     
     public void cambiarSeleccion() {
                this.botones = true;
                this.btnagregar = false;
                  
    }
    
     
    }

