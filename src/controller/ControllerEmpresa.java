package controller;

import java.util.Scanner;
import model.Empresa;
import org.json.simple.JSONObject;
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
    


    public String inserirEmpresa() {
        empresa = new Empresa();
        utils   = new Utils();
        empresa = menuInserirEmpresa();
        msg     = utils.converteEmpresaToJson(empresa, "1");   
        
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
}
