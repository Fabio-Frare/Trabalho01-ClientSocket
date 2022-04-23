package view;

import controller.ControllerEmpresa;
import controller.ControllerPessoa;
import java.util.Scanner;


/**
 *
 * @author Fabio e Lucas Nogueira
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
                    controllerPessoa.inserirPessoa();
//                    System.out.println("mensagem controller.inserirPessoa: " + msg);
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.inserirEmpresa();
//                    System.out.println("mensagem controller.inserirEmpresa: " + msg);
                }  
                break;
            case 2: // UPDATE
                if (entidade == 1) {
                    msg = controllerPessoa.atualizaPessoa();
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.atualizaEmpresa();
                }
                break;
            case 3: // GET
                if (entidade == 1) {
                    msg = controllerPessoa.buscarPessoa();
//                    System.out.println("mensagem controller.buscarPessoa: " + msg);
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.buscarEmpresa();
//                    System.out.println("mensagem controller.buscarEmpresa: " + msg);
                }
                break;
            case 4: //DELETE
                if (entidade == 1) {
                    msg = controllerPessoa.deletarPessoa();
//                  System.out.println("mensagem controller.deletarPessoa: " + msg);
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.deletarEmpresa();
//                  System.out.println("mensagem controller.deletarEmpresa: " + msg);
                }
                break;
            case 5: //LIST
                if (entidade == 1) {
                    msg = controllerPessoa.listarPessoas();
//                    System.out.println("mensagem controller.listarPessoa: " + msg);
                }
                if (entidade == 2) {
                    msg = controllerEmpresa.listarEmpresas();
//                    System.out.println("mensagem controller.listarEmpresas: " + msg);
                }
                 if (entidade == 3) {
                    msg = controllerEmpresa.listarTodos();
                    System.out.println("mensagem controller.listarTodos: " + msg);
                }
                break;
            case 6: //VINCULAR
//                msg = controllerPessoa.vincularPessoa();                
//                System.out.println("mensagem controller.vincular: " + msg);
      
                break;
            case 7: // SAIR DA APLICAÇÃO
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
                + "\n6 - Vincular"
                + "\n7 - sair");
        operacao = sc.nextInt();
//        sc.close();
   } 
   
   private void selecionaEntidade() {
       if(operacao < 6) { // while
            String[] operacoes = {"inserir", "atualizar", "buscar", "deletar", "listar"};
            System.out.println("Selecione a entidade que você deseja " + operacoes[operacao - 1] + ": "
                + "\n1 - Pessoa"
                + "\n2 - Empresa"
                + "\n3 - Todos");
            entidade = sc.nextInt();
       }  
//       sc.close();
   }

    
}
