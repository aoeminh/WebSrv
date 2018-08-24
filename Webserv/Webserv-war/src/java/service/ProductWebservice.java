/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Product;
import entity.ProductFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author apple
 */
@WebService(serviceName = "ProductWebservice")
public class ProductWebservice {

    @EJB
    private ProductFacadeLocal productFacadeLocal;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "findAll")
    public List<Product> findAll() {

        return productFacadeLocal.findAll();
    }

    @WebMethod(operationName = "addProduct")
    public boolean addProduct(@WebParam(name = "name") String name,
            @WebParam(name = "price") double price,
            @WebParam(name = "quantity") int quantity) {
        return productFacadeLocal.addProduct(name, price, quantity);
    }

    @WebMethod(operationName = "sellProduct")
    public boolean sellProduct(@WebParam(name = "id") String id, @WebParam(name = "quantity") int quantity) {
        return productFacadeLocal.sellProduct(id, quantity);
    }

}
