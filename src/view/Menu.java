package view;

import controller.ControllerEmpresa;
import controller.ControllerPessoa;
import java.util.Scanner;


/**
 *
 * @author fabio
 */
public class Menu {
    private static  Scanner sc;
    private int operacao;
    private int entidade;
    
    private static ControllerPessoa controllerPessoa;
    private static ControllerEmpresa controllerEmpresa;
    
    public String menuIniciar() {
        controllerPessoa  = new ControllerPessoa();      
        controllerEmpresa = new ControllerEmpresa();
        sc = new Scanner(System.in);
        selecionaOperacao();
        selecionaEntidade();        
        
        String msg = "";
        switch (operacao) {
            case 1: // INSERT
                if (entidade == 1) {
                    msg = controllerPessoa.inserirPessoa();
                    System.out.println("mensagem controller.inserirPessoa: " + msg);
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.inserirEmpresa();
                    System.out.println("mensagem controller.inserirEmpresa: " + msg);
                }  
//                menu();
                break;
            case 2: // UPDATE
                if (entidade == 1) {
//                    msg = controllerPessoa.listarPessoas();
//                    enviarDados(msg);
//                    receberDados();
//                    msg = controllerPessoa.atualizarPessoa();
//                    enviarDados(msg);
//                    receberDados();
                }
                if (entidade == 2) {
//                    msg = controllerEmpresa.listarEmpresas();
//                    enviarDados(msg);
//                    receberDados();
//                    msg = controllerEmpresa.atualizarEmpresa();
//                    enviarDados(msg);
//                    receberDados();
                }
//                menu();
                break;
            case 3: // GET
                if (entidade == 1) {
//                    msg = controllerPessoa.buscarPessoaPorCpf();
//                    enviarDados(msg);
//                    receberDados(); // retorno da pessoa
                }
                if (entidade == 2) {
//                    msg = controllerEmpresa.buscarEmpresaporCnpj();
//                    enviarDados(msg);
//                    receberDados();
                }
//                menu();
                break;
            case 4: //DELETE
                if (entidade == 1) {
//                    msg = controllerPessoa.deletarPessoaPorCpf();
//                    enviarDados(msg);
//                    receberDados();
                }
                if (entidade == 2) {
//                    msg = controllerEmpresa.deletarEmpresaporCnpj();
//                    enviarDados(msg);
//                    receberDados();
                }
//                menu();
                break;
            case 5: //LIST
                if (entidade == 1) {
//                    msg = controllerPessoa.listarPessoas();
//                    enviarDados(msg);
//                    receberDados();
                }
                if (entidade == 2) {
//                    msg = controllerEmpresa.listarEmpresas();
//                    enviarDados(msg);
//                    receberDados();
                }
//                menu();
                break;
            case 6: // SAIR DA APLICAÇÃO
                System.out.println("Você optou por sair da aplicação.");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
                menuIniciar();
                break;
        }  
//        sc.close();
        return msg;
    }
    
   private void selecionaOperacao() { 
        System.out.println("O que você deseja fazer?"
                + "\n1 - Inserir "
                + "\n2 - Atualizar "
                + "\n3 - Buscar"
                + "\n4 - Deletar"
                + "\n5 - Listar"
                + "\n6 - sair");
        operacao = sc.nextInt();
//        sc.close();
   } 
   
   private void selecionaEntidade() {
       if(operacao < 6) { // while
            String[] operacoes = {"inserir", "atualizar", "buscar", "deletar", "listar"};
            System.out.println("Selecione a entidade que você deseja " + operacoes[operacao - 1] + ": "
                + "\n1 - Pessoa"
                + "\n2 - Empresa");
            entidade = sc.nextInt();
       }   
//       sc.close();
   }
    
}
