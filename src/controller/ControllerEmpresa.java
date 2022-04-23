package controller;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Empresa;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.Utils;

/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class ControllerEmpresa {
    private String msg = "";
    Empresa empresa;
    Utils utils;
    Scanner in;  

    public ControllerEmpresa() {
        utils   = new Utils();
    }
    
    public String inserirEmpresa() {
        empresa = new Empresa();
        utils   = new Utils();
        empresa = menuInserirEmpresa();
        msg     = utils.converteEmpresaToJson(empresa, "1");   
        
        return msg;
    }

    public String listarEmpresas() {
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "5");
        empresaJson.put("entidade", "empresa");
        msg = empresaJson.toJSONString();
    
        return msg;  
    }

    public String listarTodos() {
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "5");
        empresaJson.put("entidade", "todos");
        msg = empresaJson.toJSONString();
    
        return msg;
    }
    
    public String buscarEmpresa() {
        String cnpjEmpresa = menuBuscarEmpresa();
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "3");
        empresaJson.put("entidade", "empresa");
        empresaJson.put("cnpj", cnpjEmpresa);
        msg = empresaJson.toJSONString();
    
        return msg;    
    }
    
    private String menuBuscarEmpresa() {  
        in = new Scanner(System.in);  
        System.out.println("Favor informar o CNPJ da empresa:");
        msg = in.nextLine();        
        
        return msg;
    }
    
    private Empresa menuInserirEmpresa() {
        in = new Scanner(System.in);         
        System.out.println("Favor informar o nome da empresa: ");
        String nomeEmpresa = in.nextLine();
        empresa.setNome(nomeEmpresa);

        System.out.println("Favor informar o CNPJ: ");
        String cnpj = in.nextLine();
        empresa.setCnpj(cnpj);
        
        return empresa;
    }
    
    private Empresa menuAtualizaEmpresa() {
        in = new Scanner(System.in);   
        System.out.println("Favor informar o CNPJ da empresa: ");
        String cnpj = in.nextLine();
        empresa.setCnpj(cnpj);
        
        System.out.println("Favor informar o nome da empresa: ");
        String nomeEmpresa = in.nextLine();
        empresa.setNome(nomeEmpresa);
        
        return empresa; 
    }

    public String deletarEmpresa() {
        String cnpjEmpresa = menuBuscarEmpresa();
        JSONObject empresaJson = new JSONObject();  
        empresaJson.put("operacao", "4");
        empresaJson.put("entidade", "empresa");
        empresaJson.put("cnpj", cnpjEmpresa);
        msg = empresaJson.toJSONString();
    
        return msg;
    }

    public String atualizaEmpresa() {
        empresa = new Empresa();
        utils   = new Utils();
        empresa = menuAtualizaEmpresa();
        msg     = utils.converteEmpresaToJson(empresa, "2");   
        
        return msg;
    }

    public String getMenuEmpresaByMsg(String msg){
        String menu = "";
        
        try {
            List<Empresa> listaEmpresa = utils.converteJsonToEmpresas(msg);
            menu = "Menu Empresa \n";

            for(int i = 0; i < listaEmpresa.size(); i++){
                Empresa empresa = listaEmpresa.get(i);
                int k = i+1;

                menu += k + ". - Nome: " + empresa.getNome() + "\n";
                menu +=     "     Cnpj: " + empresa.getCnpj() + "\n";
                menu +=     "     Qtde Funcionarios: " + empresa.getQtdeFuncionarios()+ "\n";
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControllerEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return menu;
    }
}
