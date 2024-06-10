/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;


import java.util.Date;
import java.util.List;

/**
 *
 * @author diana
 */
public class FuncionDTO {
      private Long id;
    private Date fecha;
    private double horaInicio;
    private PeliculaDTO peliculaDTO;
    private double horaFin;
    
    public FuncionDTO() {
    }

    public FuncionDTO(Long id, Date fecha, double horaInicio, List<PeliculaDTO> peliculas) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.peliculaDTO = peliculaDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(double horaInicio) {
        this.horaInicio = horaInicio;
    }

    public PeliculaDTO getPeliculaDTO() {
        return peliculaDTO;
    }

    public void setPeliculaDTO(PeliculaDTO peliculaDTO) {
        this.peliculaDTO = peliculaDTO;
    }

        public String calcularHoraFin() {
        String horaFinStr = "";

        if (peliculaDTO != null) {
            double duracionPelicula = peliculaDTO.getDuracion();

            // Convertir la duración de la película a horas y minutos
            int horas = (int) (duracionPelicula / 60);
            int minutos = (int) (duracionPelicula % 60);

            // Sumar la duración a la hora de inicio
            double horaFinEnHoras = this.horaInicio + horas + (minutos / 60.0);

            // Obtener la parte entera (horas) y la parte decimal (minutos)
            int horasFin = (int) Math.floor(horaFinEnHoras);
            int minutosFin = (int) Math.round((horaFinEnHoras - horasFin) * 60);

            // Formatear la hora de finalización en una cadena de texto en formato HH:mm
            horaFinStr = String.format("%02d:%02d", horasFin, minutosFin);
        }

        return horaFinStr;
    }
    
}
