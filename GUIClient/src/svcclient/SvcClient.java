package svcclient;

/**
 *
 * jsimmonds
 * it351 Advanced Java
 * Instructor: Tony Lowe
 * 
 * week 5 CRUD application with SOAP based messaging
 * This is the client application created for the server side application SvcDemo
 * v0.0.3
 */

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import ws.customers.Customer;
import ws.customers.CustomerService;
import ws.customers.CustomerService_Service;
import ws.products.Product;
import ws.products.ProductServices;
import ws.products.ProductServices_Service;

public class SvcClient {
    
    private static int menuChoice;
    private static Scanner inInt = new Scanner( System.in );
    private static Scanner inStr = new Scanner( System.in );    
    private static int ciD; // new customer id
    private static String cnme; // new customer name
    private static String clgin; // new customer login
    private static String cssWrd; // new customer password
    private static int piD; // new product id
    private static String pnme; // new product name
    private static String pdesc; // new product description
    private static float prc; // new product price 
    private static List< Customer > crange;
    private static List< Product > prange; 
    private static int c1iD;
    private static int c2iD;
    private static int p1iD;
    private static int p2iD;
    private static String strPrc;
    
   /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) { // this client uses SOAP to communicate with the web services app "SvcDemo"            
        
        System.out.println( "\nUsing the set & get methods of the "
            + "two web services will allow the user to edit, create, find 1"
            + "find all, find range, count, and remove.\n");
        System.out.println( "Use the menu to choose a CRUD function to perform\n"
            + "Available methods are; read all, read by id, create, update, "
            + "range and delete. Read all is a good place to start.\n");
        
        menu();
    } // end main
    
    public static void menu() {        
        
        /************************************** main menu ***********************************************/
        
        String custAll      = "List all customers............enter  1: ";
        String prodAll      = "List all products.............enter  2: ";
        String createCust   = "Create a new customer.........enter  3: ";
        String updateCust   = "Update an existing customer...enter  4: ";
        String cdelete      = "Delete a customer.............enter  5: ";
        String curange      = "List a range of customers.....enter  6: ";
        String cfind        = "Find a customer by ID.........enter  7: ";
        String ccount       = "Count of all Customers in DB..enter  8: ";
        String createProd   = "Create a new product..........enter  9: ";
        String updateProd   = "Update an existing product....enter 10: ";
        String pdelete      = "Delete a product..............enter 11: ";
        String prrange      = "List a range of products......enter 12: ";        
        String pfind        = "Find a product by ID..........enter 13: ";        
        String pcount       = "Count of all Products in DB...enter 14: ";
        String endit        = "EXIT the program .............enter 15: ";      
        
        String strMenu = String.format( "%-40s\n%-40s\n%-40s\n%-40s\n%-40s\n"
            + "%-40s\n%-40s\n%-40s\n%-40s\n%-40s\n%-40s\n%-40s\n%-40s\n"
            + "%-40s\n%-40s\n", custAll, prodAll, createCust, updateCust, 
            cdelete, curange, cfind, ccount, createProd, updateProd, pdelete, 
            prrange, pfind, pcount, endit );
        System.out.println( strMenu );
        
        /************************* switch used by the menu to call CRUD methods ********************/
        
        menuChoice = inInt.nextInt();        
        
        switch( menuChoice ) { // entering the desired integer from the console menu runs one of the following
            
            case 1: 
                allCustomers(); // tests the WS findAll for customers 
                menu();
                break;
            case 2:
                allProducts(); // tests the WS findAll for products 
                menu();
                break;
            case 3:                
                System.out.println( "Please enter an unused ID for your new Customer" );
                ciD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Please enter an unused Name for your new Customer" );
                cnme = inStr.nextLine();
                System.out.println( "Please enter a Login for your new Customer" );   
                clgin = inStr.nextLine();
                System.out.println( "Please enter an Password for your new Customer" );
                cssWrd = inStr.nextLine();                
                newCustomer(); // tests new customer creation from Client using the WS create method
                menu();
                break;
            case 4:
                System.out.println( "Please enter an ID for a current Customer" );
                ciD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Edit name or press enter" );
                cnme = inStr.nextLine();                
                System.out.println( "Edit Login or press enter" );   
                clgin = inStr.nextLine();
                System.out.println( "Edit password or press enter" );
                cssWrd = inStr.nextLine();
                cUpdate(); // updates a customer using the WS edit method
                menu();
                break;
            case 5:
                System.out.println( "Please enter an ID to Delete a Customer" );
                ciD = Integer.parseInt( inStr.nextLine() );
                cDelete(); // deletes the selected customer from the database, using the WS remove()
                menu();
                break;
            case 6:
                System.out.println( "Please enter an ID for the first Customer in the range" );
                c1iD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Please enter an ID for the second Customer in the range" );
                c2iD = Integer.parseInt( inStr.nextLine() );
                customerRange(); // creates a table of the range of customers selected
                menu();
                break;
            case 7:
                System.out.println( "Please enter an ID to display a Customer" );
                ciD = Integer.parseInt( inStr.nextLine() );
                findCustomer();
                menu();
                break;
            case 8:
                customerCount();
                menu();
                break;
            case 9:
                System.out.println( "Please enter an unused ID for your new Product" );
                piD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Please enter an unused Name for the Product" );
                pnme = inStr.nextLine();
                System.out.println( "Please enter a description of the Product" );
                pdesc = inStr.nextLine();
                System.out.println( "Please enter a price for the Product" );
                prc = Float.parseFloat( inStr.nextLine() );
                newProduct(); // tests new product creation via Client
                menu();
                break;
            case 10:
                System.out.println( "Please enter an ID for a Product to update" );
                piD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Please enter a Product Name to update" );
                pnme = inStr.nextLine();
                System.out.println( "Please enter a Description to update" );   
                pdesc  = inStr.nextLine();
                System.out.println( "Please enter price to update" );
                strPrc = inStr.nextLine(); 
                pUpdate(); // updates a product using the WS edit method
                menu();
                break;
            case 11:
                System.out.println( "Please enter an ID to Delete a Product" );
                piD = Integer.parseInt( inStr.nextLine() );
                pDelete(); // deletes the selected customer from the database, using the WS remove()
                menu();
                break;
            case 12:
                System.out.println( "Please enter an ID for the first Product in the range" );
                p1iD = Integer.parseInt( inStr.nextLine() );
                System.out.println( "Please enter an ID for the second Product in the range" );
                p2iD = Integer.parseInt( inStr.nextLine() );
                productRange(); // creates a table of the range of customers selected
                menu();
                break;
            case 13:
                System.out.println( "Please enter an ID to display a Product" );
                piD = Integer.parseInt( inStr.nextLine() );
                findProduct();
                menu();
                break;
            case 14:
                productCount();
                menu();
                break;
            case 15:
                System.exit( 0 );
            default:
                System.out.println( "To exit enter 11" ); 
                
        } // end switch       
    } // end menu
    
    /************************************* CRUD methods called by the switch ****************************/
    /*                                                                                                                                                    */
    /*          the methods use the Web Services to communicate, via SOAP, with the application      */
    /*                                                                                                                                                    */
    /***********************************************************************************************************/
    
    public static void allCustomers() {  // findAll
        // menu 1.        
        // connecting to service on localhost (could be any URL with the Web Service deployed)        
        CustomerService cs = new CustomerService_Service().getCustomerServicePort();
        
        // header for Customer table
        System.out.println( "Customers\nID\t\tName\t\t\tLogin\t\t\tPassword\n" );
        for( Customer cst : cs.findAll() ) { // prints a table of all customers in the database table
            
            // could just use the field calls in the output string, but this makes a formatted string easier to read 
            int crID = cst.getId();
            String nm = cst.getName();
            String lgn = cst.getLogin();
            String pw = cst.getPassword();
            
            // format output into a table
            String cConsole = String.format(
                "%-10d\t%-20s\t%-10s\t\t%-15s", crID, nm, lgn, pw );
            System.out.println( cConsole );
        }
        System.out.println(); // line space 
        
    } // end method allCustomers
    
    public static void allProducts() { // findAll        
        // menu 2.
        // connecting to service on localhost (could be any URL with the Web Service deployed)
        ProductServices ps = new ProductServices_Service().getProductServicesPort(); 
        
        // header for Product table
        System.out.println( "Products\nID\t\tName\t\t\tDescription\t\t\tPrice\n" );
        for( Product prod : ps.findAll() ) { //  the findAll() initiates a SOAP data transfer                    
            
            int pID = prod.getId();
            String nam = prod.getName();
            String desc = prod.getDescription();
            float pric = prod.getPrice();
            
            // format output into a table
            String pConsole = String.format( 
                "%-10d\t%-24s%-32s$ %,10.2f", pID, nam, desc, pric );
            System.out.println( pConsole );
        } 
        System.out.println(); // line space
    } // end method allProducts
    
    public static void newCustomer() { // create Customer
        // menu 3.
        CustomerService nc = new CustomerService_Service().getCustomerServicePort();
        
        Customer cst = new Customer(); // create a customer object for assigning values       
        
        cst.setId( ciD ); // assign the values for the customer
        cst.setLogin( clgin );
        cst.setName( cnme );
        cst.setPassword( cssWrd );
        
        nc.create( cst ); // use the web service to create a new customer 
        System.out.println();
        allCustomers();
    } // end method newCustomer
    
    public static void cUpdate() { // update Customer 
        // menu 4.
        CustomerService nc = new CustomerService_Service().getCustomerServicePort();
        
        Customer cst = nc.find( ciD ); // find the customer in the object array, by id   
        
        // if the user doesn't change a value, use the current value 
        if( cnme.equals( "" ) )
            cnme = cst.getName();
        
        if( clgin.equals( "" ) )
            clgin = cst.getLogin();
        
        if( cssWrd.equals( "" ) ) 
            cssWrd = cst.getPassword();      
            
        cst.setId( ciD ); // assign the values of an existing customer        
        cst.setName( cnme );
        cst.setLogin( clgin );        
        cst.setPassword( cssWrd );
        
        nc.edit( cst ); // use the web service to update the customer in the database
        
        allCustomers();
        System.out.println();
        
    } // end method cUpdate
    
    public static void cDelete() {       
        // menu 5.        
        CustomerService nc = new CustomerService_Service().getCustomerServicePort();        
        
        Customer cst = nc.find( ciD ); // find the customer in the object array, by id
        
        nc.remove( cst ); // delete the customer from the table  
        
        allCustomers();
        System.out.println();
        
    } // end method cDelete
    
    public static void customerRange() { // it's not using the WS findRange, but it works
        // menu 6.
        CustomerService nc = new CustomerService_Service().getCustomerServicePort(); 
                
        // create a sublist of customers from index c1iD to c2iD
        crange = nc.findAll().subList( c1iD - 1, c2iD );
        
        // print out a header for the customer table output
        System.out.println( "Customers\nID\t\tName\t\t\tLogin\t\t\tPassword\n" );
        
        // iterate over the sublist of customer objects: crange, and print them out 
        for ( Iterator< Customer > it = crange.iterator(); it.hasNext(); ) {
            
            Customer ct = it.next(); // assign the next customer object
            
            int crID = ct.getId(); // assign the object values, just to keep the output string readable            
            String nm = ct.getName();
            String lgn = ct.getLogin();
            String pw = ct.getPassword();
            
            String cConsole = String.format( // print out the object formatted  for columns
                "%-10d\t%-20s\t%-10s\t\t%-15s", crID, nm, lgn, pw );
            System.out.println( cConsole );
        }
        System.out.println( "\n" );
        crange.clear(); // clear the List so it doesn't get extended every time the method is run
    } // end method customerRange
    
    public static void findCustomer() {
        // menu 7.
        CustomerService nc = new CustomerService_Service().getCustomerServicePort(); 
        
        Customer cst = nc.find( ciD ); // find the customer in the object array, by id
        
        int crID = cst.getId(); // assign the object values, just to keep the output string readable            
            String nm = cst.getName();
            String lgn = cst.getLogin();
            String pw = cst.getPassword();
            
            System.out.println( "Customers\nID\t\tName\t\t\tLogin\t\t\tPassword\n" );
            String cConsole = String.format( // print out the object formatted  for columns
                "%-10d\t%-20s\t%-10s\t\t%-15s\n", crID, nm, lgn, pw );
            System.out.println( cConsole );
    }
    
    public static void customerCount() {
        // menu 8.
        CustomerService nc = new CustomerService_Service().getCustomerServicePort(); 
        
        int cnt = nc.count(); // return the number of customers in the database
        
        System.out.println( "Customer count: " + String.valueOf( cnt ) + "\n");
    }
    
    public static void newProduct() { // create Product
        // menu 9.
        ProductServices ps = new ProductServices_Service().getProductServicesPort();
        
        Product prod = new Product();
        prod.setId( piD );        
        prod.setName( pnme );
        prod.setDescription( pdesc );
        prod.setPrice( prc );
        
        ps.create( prod ); 
        
        allProducts();
        System.out.println();
        
    } // end method newProduct
    
    public static void pUpdate() {
    // menu 10.
        ProductServices ps = new ProductServices_Service().getProductServicesPort();
        
        Product prd = ps.find( piD ); // find the customer in the object array, by id   
        
        // if the user hits enter without inputting a value, use the current, stored value 
        if( pnme.equals( "" ) )
            pnme = prd.getName();
        
        if( pdesc.equals( "" ) )
            pdesc = prd.getDescription();        
        
        if( strPrc.equals( "" ) )
            prc = prd.getPrice();      
        else
            prc = Float.parseFloat( strPrc );        
        
        prd.setId( piD ); // assign the values of an existing product
        prd.setDescription( pdesc );
        prd.setName( pnme );
        prd.setPrice( prc );
        
        ps.edit( prd ); // use the web service to update the product in the database 
        
        allProducts();
        System.out.println();
        
    } // end method pUpdate
    
    public static void pDelete() {
    // menu 11,
        ProductServices ps = new ProductServices_Service().getProductServicesPort();           
        
        Product prd = ps.find( piD ); // find the product in the object array, by id       
        
        ps.remove( prd ); // delete the customer from the table   
        
        allProducts();
        System.out.println();
    } // end method pDelete
    
    // iterate over the sublist of product objects: prange, and print them out 
    public static void productRange() {
    // menu 12.
        ProductServices ps = new ProductServices_Service().getProductServicesPort();       
                
        // create a sublist of products from index p1iD to p2iD
        prange = ps.findAll().subList( p1iD - 1, p2iD );
        
        // print out a header for the product table output
        System.out.println( "Products\nID\t\tName\t\t\tDescription\t\t\tPrice\n" );
        
        // iterate over the sublist of product objects: crange and print them out 
        for ( Iterator< Product > it = prange.iterator(); it.hasNext(); ) {
            
            Product prd = it.next(); // assign the next customer object
            
            int prID = prd.getId(); // assign the object values, just to keep the output string readable            
            String nm = prd.getName();
            String dsc = prd.getDescription();
            float pric = prd.getPrice();
            
            // format output into a table
            String pConsole = String.format( 
                "%-10d\t%-24s%-32s$ %,10.2f", prID, nm, dsc, pric );
            System.out.println( pConsole );
        }
        
        System.out.println( "\n" );
        prange.clear(); // clear the List so it doesn't get extended every time the method is run
    } // end method productRange
    
    public static void findProduct() {
        // menu 13
        ProductServices ps = new ProductServices_Service().getProductServicesPort();
        
        Product prd = ps.find( piD ); // find the product in the object array, by id   
        
        int prID = prd.getId(); // assign the object values, just to keep the output string readable            
        String nm = prd.getName();
        String dsc = prd.getDescription();
        float pric = prd.getPrice();
            
        System.out.println( "Products\nID\t\tName\t\t\tDescription\t\t\tPrice\n" );
        // format output
        String pConsole = String.format( 
            "%-10d\t%-24s%-32s$ %,10.2f\n", prID, nm, dsc, pric );
        System.out.println( pConsole );        
    } // end method findProduct
    
    public static void productCount() {
        // menu 14
        ProductServices ps = new ProductServices_Service().getProductServicesPort();
        
        int pcnt = ps.count(); // return the # of products in the database
        
        System.out.println( "Product count: " + String.valueOf( pcnt ) + "\n" );
    } // end method productCount
    
} // end class SvcClient
