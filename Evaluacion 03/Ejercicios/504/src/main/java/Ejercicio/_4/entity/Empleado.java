package Ejercicio._4.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
//TODO Trabajando aqui
@Entity
@Table(name = "emp")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Relacion 1-1 desde la columna de numjefe
    private int numemp;
    private String nomemp;
    private String puesto;
    private LocalDate feccont;
    private double sal;
    private int comision;
    private int numdep;

    public Empleado(int numemp, String nomemp, String puesto, LocalDate feccont, double sal, int comision, int numdep) {
        this.numemp = numemp;
        this.nomemp = nomemp;
        this.puesto = puesto;
        this.feccont = feccont;
        this.sal = sal;
        this.comision = comision;
        this.numdep = numdep;
    }
}
