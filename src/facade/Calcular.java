package facade;

public class Calcular {

    private static double total = 0;

    public void mostrarTotal(double total) {
        Calcular.total = total;
        System.out.println("Valor a ser pago pelo cliente: R$" + total);
    }

    public void calcularTroco(double valorPago) {
        if (Calcular.total == 0) {
            System.out.println("");
        } else if (Calcular.total <= valorPago) {
            System.out.println("Troco: R$" + (valorPago - Calcular.total));
        } else {
            System.out.println("O valor pago está incorreto. O Valor total é R$:" + Calcular.total
                    + " e o valor pago é R$:" + valorPago);
        }
    }
}