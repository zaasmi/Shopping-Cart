/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vu.cs506.assignment.two.shoppingcart;

/**
 *
 * @author CS506 Team
 */

import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> cartItems ;
    
    public Cart () {
        
        cartItems = new ArrayList<>();
    }
    
    public boolean addItem(Product mProduct, int max) {
        
        boolean isDone = false ;
        
        int index = getProductIndex(mProduct);
        
        if (index > -1) {
            
            Product tProduct = cartItems.get(index);
                        
            int tQuantity = tProduct.getQunatity() + 1 ;
            
            if (tQuantity <= max) {
            
                tProduct.setQuantity(tQuantity);
            
                cartItems.set(index, tProduct);
                
                isDone = true ;
            }
        
        } else {
            
            mProduct.setQuantity(1);
            isDone = cartItems.add(mProduct);
        }
        
        return isDone ;
    }

    public boolean removeItem(Product mProduct) {
        
        int index = getProductIndex(mProduct);
        
        boolean isDone = false ;
        
        if (index > -1) {
            
            Product tProduct = cartItems.get(index);
                        
            int tQuantity = tProduct.getQunatity();
            
            if (tQuantity > 0) {
                
                tProduct.setQuantity(tQuantity-1);
            
                cartItems.set(index, tProduct);
                
                isDone = true ;         
                
            } else {
                
                cartItems.remove(index);   
                isDone = true ;         
            }
        }
        
        return isDone ;
    }

    public ArrayList<Product> getItemsList() {
        return cartItems;
    }

    public boolean emptyCart() {
        
        cartItems.clear();
        return true ;
    }
    
    private int getProductIndex (Product mProduct) {
    
        int index = -1 ;
                
        for (int i = 0 ; i < cartItems.size() ; i++) {
        
            Product tProduct = cartItems.get(i);
            
            if (tProduct.getId() == mProduct.getId()) {
            
                index = i ;
                break ;
            }
        }
        
        return index ;
    }
    
    public int getQuantity (Product mProduct) {
    
        int quantity = 0 ;
                
        for (int i = 0 ; i < cartItems.size() ; i++) {
        
            Product tProduct = cartItems.get(i);
            
            if (tProduct.getId() == mProduct.getId()) {
            
                quantity = tProduct.getQunatity() ;
                break ;
            }
        }
        
        return quantity ;
    }
}