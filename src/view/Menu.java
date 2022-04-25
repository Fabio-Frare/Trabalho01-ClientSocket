package view;

import controller.ControllerEmpresa;
import controller.ControllerPessoa;
import java.util.Scanner;
import utils.SocketSpeaker;


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
    
    public void menuIniciar() {
        sc = new Scanner(System.in);
        
        System.out.println("Indique o caminho da aplicação servidor (endereço-porta)\n"
                         + "Informe o endereço:");
        String address = sc.nextLine();
        System.out.println("Informe a porta");
        int port = sc.nextInt();
        
        SocketSpeaker.getInstance().init(address, port);
        
        while(true){
            controllerPessoa  = new ControllerPessoa();      
            controllerEmpresa = new ControllerEmpresa();
            selecionaOperacao();
            selecionaEntidade();        

            switch (operacao) {
                case 1: // INSERT
                    if (entidade == 1) {
                        controllerPessoa.inserirPessoa();
                    }
                    if (entidade == 2) {
                        controllerEmpresa.inserirEmpresa();
                    }  
                    break;
                case 2: // UPDATE
                    if (entidade == 1) {
                        controllerPessoa.atualizaPessoa();
                    }
                    if (entidade == 2) {
                        controllerEmpresa.atualizaEmpresa();
                    }
                    break;
                case 3: // GET
                    if (entidade == 1) {
                        controllerPessoa.buscarPessoa();
                    }
                    if (entidade == 2) {
                        controllerEmpresa.buscarEmpresa();
                    }
                    break;
                case 4: //DELETE
                    if (entidade == 1) {
                        controllerPessoa.deletarPessoa();
                    }
                    if (entidade == 2) {
                        controllerEmpresa.deletarEmpresa();
                    }
                    break;
                case 5: //LIST
                    if (entidade == 1) {
                        controllerPessoa.listarPessoas();
                    }
                    if (entidade == 2) {
                        controllerEmpresa.listarEmpresas();
                    }
                     if (entidade == 3) {
                        controllerEmpresa.listarTodos();
                    }
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
        }
    }
    
   private void selecionaOperacao() { 
        System.out.println("O que você deseja fazer?"
                + "\n1 - Inserir "
                + "\n2 - Atualizar "
                + "\n3 - Buscar"
                + "\n4 - Deletar"
                + "\n5 - Listar"
                + "\n7 - sair");
        operacao = sc.nextInt();
   }

   private void selecionaEntidade(){
        if(operacao <= 5){
            String[] operacoes  = {"inserir", "atualizar", "buscar", "deletar", "listar"};
            String menuEntidade = "Selecione a entidade que você deseja " + operacoes[operacao - 1] + ": "
                                + "\n1 - Pessoa"
                                + "\n2 - Empresa";
            if(operacao == 5){
                menuEntidade += "\n3 - Todos";
            }
            
            System.out.println(menuEntidade);
            entidade = sc.nextInt();
        }
   }
}
