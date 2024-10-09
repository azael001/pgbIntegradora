import java.io.Serializable;

public class Seguros implements Serializable {
    private int Poliza;
    private String Cliente;
    private String Matricula;
    private Double Cuota;

    public Seguros(int poliza, String cliente, String matricula, Double cuota) {
        Poliza = poliza;
        Cliente = cliente;
        Matricula = matricula;
        Cuota = cuota;
    }

    public int getPoliza() {
        return Poliza;
    }

    public void setPoliza(int poliza) {
        Poliza = poliza;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public Double getCuota() {
        return Cuota;
    }

    public void setCuota(Double cuota) {
        Cuota = cuota;
    }

    @Override
    public String toString() {
        return "Seguros{" +
                "Poliza=" + Poliza +
                ", Cliente='" + Cliente + '\'' +
                ", Matricula='" + Matricula + '\'' +
                ", Cuota=" + Cuota +
                '}';
    }
}
