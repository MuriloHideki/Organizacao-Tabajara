import java.time.LocalDate;
import java.util.List;

public class Compra {
    private List<ItemCompra> itensCompra;
    private String identificador;
    private LocalDate data;
    private float valorTotal;
    private int cpfCnpj;
    private float totalPago;

    public Compra() {
    }

    public Compra(List<ItemCompra> itensCompra, String identificador, LocalDate data, float valorTotal, int cpfCnpj,
            float totalPago) {
        this.itensCompra = itensCompra;
        this.identificador = identificador;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cpfCnpj = cpfCnpj;
        this.totalPago = totalPago;
    }

    public List<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<ItemCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(int cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public float getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(float totalPago) {
        this.totalPago = totalPago;
    }

    public float faltaPagar() {
        return this.valorTotal - this.totalPago;
    }

}
