import java.util.Scanner;

public class Correntista {
    private String nome;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;
    private ContaAplicacao contaAplicacao;

    public Correntista(String nome) {
        this.nome = nome;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public void setContaAplicacao(ContaAplicacao contaAplicacao) {
        this.contaAplicacao = contaAplicacao;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public ContaAplicacao getContaAplicacao() {
        return contaAplicacao;
    }

    public void mostrarContas() {
        System.out.println("Correntista: " + nome);
        if (contaCorrente != null) {
            System.out.println("Conta Corrente: " + contaCorrente.getSaldo());
        }
        if (contaPoupanca != null) {
            System.out.println("Conta Poupança: " + contaPoupanca.getSaldo());
        }
        if (contaAplicacao != null) {
            System.out.println("Conta Aplicação: " + contaAplicacao.getSaldo());
        }
    }
}

abstract class Conta {
    protected double saldo;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (this.saldo >= valor) {
            this.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente para transferência!");
        }
    }
}

class ContaCorrente extends Conta {
    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }
}

class ContaAplicacao extends Conta {
    public ContaAplicacao(double saldoInicial) {
        super(saldoInicial);
    }
}

class Menu {
    private Scanner scanner;
    private Correntista correntista;

    public Menu(Correntista correntista) {
        this.scanner = new Scanner(System.in);
        this.correntista = correntista;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Mostrar contas");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    correntista.mostrarContas();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private void depositar() {
        System.out.println("\n--- Depositar ---");
        System.out.print("Escolha a conta (1: Corrente, 2: Poupança, 3: Aplicação): ");
        int conta = scanner.nextInt();
        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        switch (conta) {
            case 1:
                correntista.getContaCorrente().depositar(valor);
                break;
            case 2:
                correntista.getContaPoupanca().depositar(valor);
                break;
            case 3:
                correntista.getContaAplicacao().depositar(valor);
                break;
            default:
                System.out.println("Conta inválida!");
        }
    }

    private void sacar() {
        System.out.println("\n--- Sacar ---");
        System.out.print("Escolha a conta (1: Corrente, 2: Poupança, 3: Aplicação): ");
        int conta = scanner.nextInt();
        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        switch (conta) {
            case 1:
                correntista.getContaCorrente().sacar(valor);
                break;
            case 2:
                correntista.getContaPoupanca().sacar(valor);
                break;
            case 3:
                correntista.getContaAplicacao().sacar(valor);
                break;
            default:
                System.out.println("Conta inválida!");
        }
    }

    private void transferir() {
        System.out.println("\n--- Transferir ---");
        System.out.print("Escolha a conta de origem (1: Corrente, 2: Poupança, 3: Aplicação): ");
        int contaOrigem = scanner.nextInt();
        System.out.print("Escolha a conta de destino (1: Corrente, 2: Poupança, 3: Aplicação): ");
        int contaDestino = scanner.nextInt();
        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        Conta origem = null, destino = null;

        switch (contaOrigem) {
            case 1:
                origem = correntista.getContaCorrente();
                break;
            case 2:
                origem = correntista.getContaPoupanca();
                break;
            case 3:
                origem = correntista.getContaAplicacao();
                break;
            default:
                System.out.println("Conta de origem inválida!");
                return;
        }

        switch (contaDestino) {
            case 1:
                destino = correntista.getContaCorrente();
                break;
            case 2:
                destino = correntista.getContaPoupanca();
                break;
            case 3:
                destino = correntista.getContaAplicacao();
                break;
            default:
                System.out.println("Conta de destino inválida!");
                return;
        }

        if (origem != null && destino != null) {
            origem.transferir(destino, valor);
        }
    }
}