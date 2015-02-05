package ws.customers;

/**
 *
 * @author jsimmonds
 * it351 Advanced Java
 * Instructor: Tony Lowe
 * 
 * week 5 CRUD application with SOAP based messaging
 * This is the server side application created for the client application SvcClient
 */

import ejb.CustomerFacade;
import entity.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "CustomerService") // these service files tie in with the web services and use SOAP to pass messages to xml web pages (xhtml)
public class CustomerService {
    @EJB 
    private CustomerFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Customer entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Customer entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Customer entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Customer find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Customer> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Customer> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }
    
}
