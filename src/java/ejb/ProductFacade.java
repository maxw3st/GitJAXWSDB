package ejb;

/**
 *
 * @author jsimmonds
 * it351 Advanced Java
 * Instructor: Tony Lowe
 * 
 * week 5 CRUD application with SOAP based messaging
 * This is the server side application created for the client application SvcClient
 */

import entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "SvcDemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
}
