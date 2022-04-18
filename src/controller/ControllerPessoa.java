package controller;

import java.util.Scanner;
import model.Pessoa;
import org.json.simple.JSONObject;
import utils.Utils;

/**
 *
 * @author Fabio e Lucas Nogueira
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
    
    private Pessoa menuInserirPessoa() {
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

        return pessoa;
    } 

    public String vincularPessoa() {        
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "6");
        empresaJson.put("entidade", "empresa");
        msg = empresaJson.toJSONString();
        
        return msg;
    }

    public String listarPessoas() {
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "5");
        empresaJson.put("entidade", "pessoa");
        msg = empresaJson.toJSONString();
    
        return msg;    
    }

    public String buscarPessoa() {
        String cpfPessoa = menuBuscarPessoa();
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "3");
        empresaJson.put("entidade", "pessoa");
        empresaJson.put("cpf", cpfPessoa);
        msg = empresaJson.toJSONString();
    
        return msg; 
    }

    private String menuBuscarPessoa() {
        in = new Scanner(System.in);  
        System.out.println("Favor informar o CPF da pessoa:");
        msg = in.nextLine();        
        
        return msg;
    }

    

    
}
