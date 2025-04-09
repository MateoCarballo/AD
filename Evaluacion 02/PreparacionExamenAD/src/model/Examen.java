package model;

import java.time.LocalDate;

public class Examen {
    private String nombre;
    private LocalDate fecha;
    private int horas;

    public Examen(String materia, LocalDate fecha, int horas) {
        this.nombre = materia;
        this.fecha = fecha;
        this.horas = horas;
    }

    public String formatExamenToXML() {
        return String.format(
                "<examen fecha=\"%s\">\n" +
                        "    <materia>%s</materia>\n" +
                        "    <horas-semanales>%d</horas-semanales>\n" +
                        "</examen>",
                this.getFecha(), this.getNombre(), this.getHoras()
        );
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getHoras() {
        return horas;
    }
}
