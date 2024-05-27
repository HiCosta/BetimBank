public class App {
    public static void main(String[] args) {
        Correntista correntista = new Correntista("Higor");

        ContaCorrente cc = new ContaCorrente(1000.0);
        ContaPoupanca cp = new ContaPoupanca(500.0);
        ContaAplicacao ca = new ContaAplicacao(2000.0);

        correntista.setContaCorrente(cc);
        correntista.setContaPoupanca(cp);
        correntista.setContaAplicacao(ca);

        Menu menu = new Menu(correntista);
        menu.exibirMenu();
    }
}