package net.eegg.crudthymeleaf.entities;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 


@Entity
@Table(name = "Juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private UUID foto;

 

    private String descripcion;
    
    private String dificultad;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public UUID getFoto() {
        return foto;
    }

 

    public void setFoto(UUID foto) {
        this.foto = foto;
    }    
    
    public String getdescripcion() {
        return descripcion;
    }
    
    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDificultad() {
        return dificultad;
    }
    
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

 

}