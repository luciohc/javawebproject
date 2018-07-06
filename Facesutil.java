/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.util;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
/**
 *
 * @author Lucio Henrique da Costa
 */
public class Facesutil {
    
    public static void adicionarMsgInfo(String mensagem){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        ExternalContext externalContext = facesContext.getExternalContext();
        
        Flash flash  = externalContext.getFlash();
        
        flash. setKeepMessages(true);
        
        facesContext.addMessage(null, facesMessage);
    }
    
    public static void adicionarMsgErro(String mensagem){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
    public static String getParam(String nome){
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        
        ExternalContext  externalContext = facesContext.getExternalContext();
        
        Map<String, String> parametros = externalContext.getRequestParameterMap();
                
        String valor = parametros.get(nome);
        
        return valor;      
        
        
    }
    
    
}
