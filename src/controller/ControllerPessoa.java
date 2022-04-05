package controller;

import java.util.Scanner;
import model.Pessoa;
import utils.Utils;

/**
 *
 * @author fabio
 */
public class ControllerPessoa {
    private String msg = "";
    private Pessoa pessoa;
    private Utils utils;
    Scanner in;       

    public String inserirPessoa() {
        pessoa = new Pessoa();
        utils  = new Utils();
        pessoa = menuInserirPessoa();
        msg    = utils.convertePessoaToJson(pessoa, "1");        
        return msg;
    }
    
    public Pessoa menuInserirPessoa() {
        in    = new Scanner(System.in); 
        
        System.out.println("Favor informar o nome da pessoa: ");
        String nomePessoa = in.nextLine();
        pessoa.setNome(nomePessoa);

        System.out.println("Favor informar o CPF: ");
        String cpf = in.nextLine();
        pessoa.setCpf(cpf);

        System.out.println("Favor informar o endereço: ");
        String enderecoPessoa = in.nextLine();
        pessoa.setEndereco(enderecoPessoa);

//        System.out.println("Deseja vincular a pessoa a uma empresa? [s/n]");
//        String opcao = in.nextLine();
//        String cnpjEmpresa = "";
//        if (opcao.equalsIgnoreCase("s")) {
//            System.out.println("Favor invofrmar um CNPJ: ");
//            cnpjEmpresa = in.nextLine();
//        } else {
//            cnpjEmpresa = "*";
//        }
//        pessoa.setCnpjEmpresa(utils.padronizaInsercao(cnpjEmpresa, 14));

//        msg = "1INSERT";                 //   7 bytes
//        msg += pessoa.getCpf();          //  11 bytes
//        msg += pessoa.getNome();         // 100 bytes
//        msg += pessoa.getEndereco();     // 100 bytes 
//        msg += pessoa.getCnpjEmpresa();  //  14 bytes  => 232 bytes
//        in.close();
        return pessoa;
    } 
    
}
