import java.time.LocalDate;
import java.util.ArrayList;

public class Compra {
    private ArrayList<ItemCompra> itensCompra;
    private String identificador;
    private LocalDate data;
    private float valorTotal;
    private Long cpfCnpj;
    private float totalPago;

    public Compra() {
    }

    public Compra(ArrayList<ItemCompra> itensCompra, String identificador, LocalDate data, Long cpfCnpj,
            float totalPago) {
        this.itensCompra = itensCompra;
        this.identificador = identificador;
        this.data = data;
        this.cpfCnpj = cpfCnpj;
        this.totalPago = totalPago;
        calculaValorTotal(itensCompra);
        for (ItemCompra itemCompra : itensCompra) {
            itemCompra.toString();
        }
    }

    public ArrayList<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ArrayList<ItemCompra> itensCompra) {
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

    public Long getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Long cpfCnpj) {
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

    private void calculaValorTotal(ArrayList<ItemCompra> itensCompra) {
        for (ItemCompra item : itensCompra) {
            this.valorTotal += item.getValorTotal();
        }
    }

    public String paraString() {
        String itensCompra = "";
        for (ItemCompra item : this.itensCompra) {
            itensCompra += item.getNomeProduto() + "," + item.getQuantidade() + "," + item.getPrecoUnitario() + ","
                    + item.getValorTotal() + ",";
        }
        return identificador + "," + data + "," + cpfCnpj + "," + totalPago + "," + itensCompra;
    }
}
