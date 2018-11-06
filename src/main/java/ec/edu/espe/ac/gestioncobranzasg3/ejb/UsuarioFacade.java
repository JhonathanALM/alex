package ec.edu.espe.ac.gestioncobranzasg3.ejb;

import ec.edu.espe.ac.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author jhona
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ec.edu.espe.ac_GestionCobranzasG3_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario iniciarSesion(Usuario us, String usu) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.nombre =?1";
            Query query = em.createQuery(consulta);
            System.out.println(usu + " ---- ");
            query.setParameter(1, usu);
            List<Usuario> lista = query.getResultList();

            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }

        } catch (Exception e) {
            System.out.println("ERROR!!!" + e);
        } finally {
            //
        }
        return usuario;
    }

}
