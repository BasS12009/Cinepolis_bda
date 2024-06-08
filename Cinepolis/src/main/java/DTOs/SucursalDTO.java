/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;


import java.util.List;

/**
 *
 * @author diana
 */
public class SucursalDTO {
  private Long id;
    private String nombre;
    private String ubicacion;
    List<SalaDTO> salaDTO;

    public SucursalDTO() {
    }

    public SucursalDTO(Long id, String nombre, String ubicacion, List<SalaDTO> salaDTO) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.salaDTO = salaDTO;
    }

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<SalaDTO> getSalaDTO() {
        return salaDTO;
    }

    public void setSalaDTO(List<SalaDTO> salas) {
        this.salaDTO = salaDTO;
    }
    
    
}
