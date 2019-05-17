package pojo;

import java.util.ArrayList;
import java.util.List;



public class Carrinho {
    
    private List<String> itensCar = new ArrayList<>();
    private static Carrinho instance;
    private double valorCar;
    private double valorPago;
   

    private Carrinho() {
    }

    public static Carrinho getInstance() {
        if (Carrinho.instance == null) {
            Carrinho.instance = new Carrinho();
        }
        return instance;
    }

    public List<String> getItensCar() {
        return itensCar;
    }

    public void setItensCar(List<String> itensCar) {
        this.itensCar = itensCar;
    }
    
    public double getValorCar() {
        return valorCar;
    }

    public void setValorCar(double valorCar) {
        this.valorCar = valorCar;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
        
}
