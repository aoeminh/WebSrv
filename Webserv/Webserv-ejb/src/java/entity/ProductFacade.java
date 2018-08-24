/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "Webserv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> findAll() {
        Query  query = em.createNamedQuery("Product.findAll");
        
        return query.getResultList();
        
    }

    @Override
    public boolean addProduct(String name, double price,int quantity) {
        Product product = new Product(name, price, quantity);
        em.persist(product);
        return true;
    }

    @Override
    public boolean sellProduct(String productID, int quantity) {
        Query query = em.createNamedQuery("Product.findById");
        query.setParameter("id", productID);
        
        Product befProduct = (Product) query.getSingleResult();
        if(befProduct != null){
              int befQuantity = befProduct.getQuantity();
              if(befQuantity > quantity){
                     int newQuantity = befQuantity - quantity;
                     Query query1 = em.createNamedQuery("Product.updateQuantity");
                     query.setParameter("id", productID);
                     query.setParameter("quantity", quantity);
                     query.executeUpdate();
                     return  true;
              }else{
                  return  false;
              }
           
        }else{
            return false;
        }
      
        
            

    }
    
    
    
    
    
    
    
    
}
