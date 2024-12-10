package Ejercicios_MySQL.Ejercicio306;

public class Empleado {
    private String numeroSS;
    private String nombre;
    private String apel1;
    private String apel2;
    private String sexo;
    private String direccion;
    private String fechaNac;
    private int salario;
    private int numeroDepartamento;
    private int numeroSSExtra;

    public Empleado(String numeroSS, String nombre, String apel1, String apel2, String sexo, String direccion, String fechaNac, int salario, int numeroDepartamento, int numeroSSExtra) {
        this.numeroSS = numeroSS;
        this.nombre = nombre;
        this.apel1 = apel1;
        this.apel2 = apel2;
        this.sexo = sexo;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.salario = salario;
        this.numeroDepartamento = numeroDepartamento;
        this.numeroSSExtra = numeroSSExtra;
    }

    public String getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApel1() {
        return apel1;
    }

    public void setApel1(String apel1) {
        this.apel1 = apel1;
    }

    public String getApel2() {
        return apel2;
    }

    public void setApel2(String apel2) {
        this.apel2 = apel2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(int numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public int getNumeroSSExtra() {
        return numeroSSExtra;
    }

    public void setNumeroSSExtra(int numeroSSExtra) {
        this.numeroSSExtra = numeroSSExtra;
    }
}
