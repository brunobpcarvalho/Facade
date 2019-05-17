package pacote;

import facade.Calcular;
import facade.Facade;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pojo.Carrinho;
import pojo.Hortalicas;

public class Menu {

    public static List<String> itens = new ArrayList();
    public static Carrinho carrinho = Carrinho.getInstance();
    public static Scanner scanner = new Scanner(System.in);
    Calcular calcular = new Calcular();
    Facade facade = new Facade(calcular);

    public static int i;

    public Menu() {

        List<Hortalicas> hortalicas = new ArrayList<>();

        int opMenu = 0;

        do {
            System.out.println("MENU:");
            System.out.println("1 - HORTALICAS");
            System.out.println("2 - CARRINHO");
            System.out.println("3 - PAGAMENTO");
            System.out.println("0 - SAIR");

            opMenu = scanner.nextInt();

            switch (opMenu) {
                case 1: //HORTALICAS
                    int opMenu2 = 0;

                    do {
                        limpar();
                        System.out.println("HORTALIÇAS");
                        System.out.println("1 - ADICIONAR HORTALIÇAS");
                        System.out.println("2 - VER HORTALIÇAS CADASTRADAS");
                        System.out.println("0 - VOLTAR");
                        opMenu2 = scanner.nextInt();

                        switch (opMenu2) {
                            case 1: //ADICIONAR HORTALIÇAS
                                scanner.reset();
                                int resposta = 1;
                                while (resposta == 1) {
                                    System.out.println("Digite o nome:");
                                    String nome = scanner.next();

                                    System.out.println("Digite a quantidade:");
                                    int quant = scanner.nextInt();

                                    System.out.println("Digite o valor do Produto");
                                    double preco = scanner.nextDouble();

                                    hortalicas.add(new Hortalicas(nome, quant, preco));

                                    System.out.println("Deseja cadastrar outra Hortaliça?");
                                    System.out.println("1 = SIM / 2 = NÃO");
                                    resposta = scanner.nextInt();
                                    limpar();
                                }
                                break;
                            case 2: //VER HORTALIÇAS CADASTRADAS
                                limpar();
                                int resposta2 = 2;
                                do {
                                    listarHortalicas(hortalicas);
                                    System.out.println("DIGITE 1 PARA SAIR");
                                    resposta2 = scanner.nextInt();
                                    limpar();
                                } while (resposta2 == 2);
                                break;
                        }
                    } while (opMenu2 != 0);

                    break;

                case 2: //CARRINHO
                    int opMenu3 = 0;

                    do {
                        System.out.println("CARRINHO");
                        System.out.println("1 - ADICIONAR PRODUTO NO CARRINHO");
                        System.out.println("2 - VER CARRINHO");
                        System.out.println("0 - VOLTAR");
                        opMenu3 = scanner.nextInt();

                        switch (opMenu3) {
                            case 1: //ADICIONAR PRODUTO NO CARRINHO
                                limpar();
                                int resposta1 = 1;
                                int posicao;
                                double valor = 0.0;
                                do {
                                    listarHortalicas(hortalicas);
                                    System.out.println("SELECIONE OS PRODUTOS PELO ID:");
                                    posicao = scanner.nextInt();

                                    if (hortalicas.get(i).getQuantidade() == 0) {
                                        System.out.println("Produto Indisponivel");
                                    } else {
                                        valor = valor + hortalicas.get(i).getPreco();
                                        adicionarCarrinho(posicao, hortalicas);
                                    }
                                    System.out.println("DESEJA ADICIONAR OUTRO PRODUTO?");
                                    System.out.println("1 = SIM / 2 = NÃO");
                                    resposta1 = scanner.nextInt();
                                    limpar();
                                } while (resposta1 == 1);

                                carrinho.setItensCar(itens);
                                carrinho.setValorCar(valor);

                                break;
                            case 2: //VER CARRINHO
                                listarCarrinho();
                                break;
                        }
                    } while (opMenu3 != 0);

                    break;
                case 3:

                    int opMenu4 = 0;
                    do {
                        System.out.println("PAGAMENTO");
                        System.out.println("1 - VER TOTAL A PAGAR");
                        System.out.println("2 REALIZAR PAGAMENTO");
                        opMenu4 = scanner.nextInt();

                        switch (opMenu4) {
                            case 1:
                                int resposta3 = 0;
                                do {
                                    facade.mostrarTotal(carrinho.getValorCar());
                                    System.out.println("DIGITE 0 PARA VOLTAR");
                                    resposta3 = scanner.nextInt();

                                } while (resposta3 != 0);

                                break;
                            case 2:
                                int resposta4 = 0;
                                double pagamento;
                                System.out.println("DIGITE O VALOR DADO");
                                pagamento = scanner.nextDouble();
                                carrinho.setValorPago(pagamento);

                                do {
                                    facade.calcularTroco(carrinho.getValorPago());
                                    System.out.println("DIGITE 1 PARA VOLTAR");
                                    resposta4 = scanner.nextInt();

                                } while (resposta4 != 1);

                                break;
                        }

                    } while (opMenu4 != 0);

                    break;

                default:
                    System.out.println("Digite apenas os números informados");
                    break;
            }
        } while (opMenu != 0);
    }

    public static void listarCarrinho() {
        System.out.printf("Itens do Carrinho\n");
        int n = carrinho.getItensCar().size();
        for (i = 0; i < n; i++) {
            System.out.printf("Produo:" + carrinho.getItensCar().get(i) + "\n");
        }
        System.out.println("Valor do Carrinho:" + carrinho.getValorCar());
    }

    public static void listarHortalicas(List<Hortalicas> hortalicas) {
        System.out.println("Lista das Hortaliças:");
        for (int i = 0; i < hortalicas.size(); i++) {
            System.out.println("ID:" + i + " - " + hortalicas.get(i).getNome()
                    + " - Quantidade:" + hortalicas.get(i).getQuantidade()
                    + " - Preço:" + hortalicas.get(i).getPreco());
        }
    }

    public static void adicionarCarrinho(int i, List<Hortalicas> hortalicas) {
        if (hortalicas.get(i).getQuantidade() != 0) {
            int quant = hortalicas.get(i).getQuantidade();
            quant = quant - 1;
            hortalicas.get(i).setQuantidade(quant);
            itens.add(hortalicas.get(i).getNome());
        }
    }

    public final static void limpar() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
        }
    }
}
