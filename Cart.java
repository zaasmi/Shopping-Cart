// Cart.java

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
                        
            int tQuantity = tProduct.getQunatity() + mProduct.getQunatity();
            
            if (tQuantity <= max) {
            
                tProduct.setQuantity(tQuantity);
            
                cartItems.set(index, tProduct);
                
                isDone = true ;
            }
        
        } else {
            isDone = cartItems.add(mProduct);
        }
        
        return isDone ;
    }

    public void removeItem(Product mProduct) {
        
        int index = getProductIndex(mProduct);
        
        if (index > -1) {
        
            cartItems.remove(index);
        }
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
}
