package facade;

import pojo.Carrinho;

public class Facade {

    private Calcular calcular;

    public Facade(Calcular calcular) {
        this.calcular = calcular;
    }

    public void mostrarTotal(double valor) {
        this.calcular.mostrarTotal(valor);
    }
    
    public void calcularTroco(double pagamento){
        this.calcular.calcularTroco(pagamento);
    }
}
