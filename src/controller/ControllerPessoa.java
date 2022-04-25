package controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;
import org.json.simple.JSONObject;
import utils.SocketSpeaker;
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

    public void inserirPessoa() {
        Pessoa p = executaInsercaoPessoa();
        vinculaPessoaEmpresa(p);
    }
    
    private Pessoa executaInsercaoPessoa(){
        pessoa = new Pessoa();
        utils  = new Utils();
        pessoa = menuInserirPessoa();
        msg    = utils.convertePessoaToJson(pessoa, "1");
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            String resposta = ss.call();
            System.out.println(resposta);
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoa;
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
    
    private void vinculaPessoaEmpresa(Pessoa pessoa){
        Scanner in = new Scanner(System.in); 
        System.out.println("Deseja vincular a pessoa a uma empresa? [s/n]");
        String opcao = in.nextLine();
        if(opcao.equalsIgnoreCase("s")){
            ControllerEmpresa controlEmpresa = new ControllerEmpresa();
            String msgListaEmpresa           = controlEmpresa.listarEmpresasMsg();
            
            try {
                SocketSpeaker ss = SocketSpeaker.getInstance();
                ss.setMensagem(msgListaEmpresa);
                String resposta    = ss.call();
                String menuEmpresa = controlEmpresa.getMenuEmpresaByMsg(resposta);
                
                System.out.println("Escolha uma empresa digitando o seu cnpj");
                System.out.println(menuEmpresa);
                String cnpjEmpresa = in.nextLine();
                System.out.println(vincularPessoa(pessoa.getCpf(), cnpjEmpresa));
            } catch (IOException ex) {
                Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String vincularPessoa(String cpfPessoa, String cnpjEmpresa) {        
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "6");
        empresaJson.put("entidade", "pessoa");
        empresaJson.put("cpf"     , cpfPessoa);
        empresaJson.put("cnpj"    , cnpjEmpresa);
        msg = empresaJson.toJSONString();
        String resposta = "";
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            resposta         = ss.call();
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resposta;
    }

    public void listarPessoas() {
        JSONObject pessoaJson = new JSONObject();  
        pessoaJson.put("operacao", "5");
        pessoaJson.put("entidade", "pessoa");
        msg = pessoaJson.toJSONString();
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            String resposta  = ss.call();
            
            System.out.println(resposta);
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarPessoa() {
        String cpfPessoa = menuBuscarPessoa();
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "3");
        empresaJson.put("entidade", "pessoa");
        empresaJson.put("cpf", cpfPessoa);
        msg = empresaJson.toJSONString();
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            String resposta  = ss.call();
            
            System.out.println(resposta);
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String menuBuscarPessoa() {
        in = new Scanner(System.in);  
        System.out.println("Favor informar o CPF da pessoa:");
        msg = in.nextLine();        
        
        return msg;
    }

    public void deletarPessoa() {
        String cpfPessoa = menuBuscarPessoa();
        JSONObject pessoaJson = new JSONObject();  
        pessoaJson.put("operacao", "4");
        pessoaJson.put("entidade", "pessoa");
        pessoaJson.put("cpf", cpfPessoa);
        msg = pessoaJson.toJSONString();
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            String resposta  = ss.call();
            
            System.out.println(resposta);
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizaPessoa() {
        pessoa = executaAtualizacaoPessoa();
        if(pessoa.getCpf() != null){
            vinculaPessoaEmpresa(pessoa);
        }
    }
    
    private Pessoa executaAtualizacaoPessoa(){
        pessoa = new Pessoa();
        pessoa = menuAtualizaPessoa();
        utils  = new Utils();
        msg    = utils.convertePessoaToJson(pessoa, "2");
        
        try {
            SocketSpeaker ss = SocketSpeaker.getInstance();
            ss.setMensagem(msg);
            String resposta  = ss.call();
            
            System.out.println(resposta);
            if(!resposta.equalsIgnoreCase("Pessoa com o CPF: " + pessoa.getCpf() + " atualizada.")){
                pessoa = new Pessoa();
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoa;
    }

    private Pessoa menuAtualizaPessoa() {
         in    = new Scanner(System.in); 
        
        System.out.println("Favor informar o CPF: ");
        String cpf = in.nextLine();
        pessoa.setCpf(cpf);
        
        System.out.println("Favor informar o nome da pessoa: ");
        String nomePessoa = in.nextLine();
        pessoa.setNome(nomePessoa);

        System.out.println("Favor informar o endereço: ");
        String enderecoPessoa = in.nextLine();
        pessoa.setEndereco(enderecoPessoa);

        return pessoa;  
    }
    
}
