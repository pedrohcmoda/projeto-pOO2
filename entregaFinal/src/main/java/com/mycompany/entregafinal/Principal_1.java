//Nome: Pedro Henrique Cunha Moda RA: 2476576

public class Principal implements Intfc {
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.opccs();
    }

    public void opccs() {
        Leitura l = Leitura.getLeitura();
        Banco banco = Banco.getBanco();
        int opc = 0;
        do {
            interFc();
            opc = Integer.parseInt(l.entDados("Digite a opcao desejada: "));
            
            switch (opc) {
                case 1:
                    banco.opcoes(1);
                    break;
                case 2:
                    banco.opcoes(2);
                    break;
                case 3:
                    banco.opcoes(3);
                    break;
                case 4:
                    banco.opcoes(4);
                    break;
                case 5:
                    banco.opcoes(5);
                    break;
                case 6:
                    banco.opcoes(6);
                    break;                
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao inválida.");
                    break;
            }
        } while (opc != 7);
    }

    @Override
    public void interFc() {
        System.out.println("Selecione uma opcao:");
        System.out.println("1 - Adicionar livro");
        System.out.println("2 - Adicionar DVD");
        System.out.println("3 - Adicionar CD");
        System.out.println("4 - Adicionar manuscrito");
        System.out.println("5 - Adicionar cartografia");
        System.out.println("6 - Pesquisar");
        System.out.println("7 - Sair");
    }
}



