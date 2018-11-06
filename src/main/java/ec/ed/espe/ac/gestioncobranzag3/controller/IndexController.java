package ec.ed.espe.ac.gestioncobranzag3.controller;
import ec.edu.espe.ac.gestioncobranzasg3.ejb.UsuarioFacade;
import ec.edu.espe.ac.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author jhona
 */
@Named
@ViewScoped
public class IndexController implements Serializable {
    @EJB
    private UsuarioFacade EJBUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init (){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        String redireccion="";
        Usuario us;
        try{
            us = EJBUsuario.iniciarSesion(usuario,usuario.getNombre());
            if(us!=null){
                redireccion="vista/now?faces-redirect=true";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales Incorrectas"));
                System.out.println("Fallamos prro :( ");
            }
            
        }catch(Exception e){
            System.out.println("Error: "+usuario.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","ERROR!"));
        }
        return redireccion;
    }
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
