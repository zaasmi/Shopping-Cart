// MainClass.java

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainClass {
    
    private static final int MAX = 10 ;

    private Cart myCart;

    public MainClass() {

        myCart = new Cart();

        showGUI();
    }

    private void showGUI() {

        String options = "Please enter\n"
                + "1 for 'Add Item(s) to Cart'\n"
                + "2 for 'Remove an Item from Cart'\n"
                + "3 for 'Go to Checkout'\n"
                + "4 for 'Empty the Cart'\n"
                + "5 for 'Exit the Program'";

        boolean isTerminated = false;

        while (!isTerminated) {

            String input = JOptionPane.showInputDialog(null, options, "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

            if (input == null) {

                isTerminated = true;

            } else {

                int num = 0;

                try {
                    num = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }

                switch (num) {

                    case 1:
                        addItem();
                        break;
                    case 2:
                        removeItem();
                        break;
                    case 3:
                        checkOutCart();
                        break;
                    case 4:
                        removeAll();
                        break;
                    case 5:
                        developerInfo();
                        isTerminated = true;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid selection!");
                }
            }
        }
    }

    private void addItem() {

        int num = 0;

        String options = "Please enter\n"
                + ++num + " to add 'Orange ($2.0)'\n"
                + ++num + " to add 'Grapes ($2.5)'\n"
                + ++num + " to add 'Banana ($1.5)'\n"
                + ++num + " to add 'Mango ($3.0)'\n"
                + ++num + " to add 'Done'\n";
        
        boolean isTerminated = false;

        while (!isTerminated) {

            String sProduct = JOptionPane.showInputDialog(null, options, "Add Item(s) in Cart", JOptionPane.INFORMATION_MESSAGE);

            if (sProduct == null) {

                isTerminated = true;

            } else {

                int numProduct = 0;

                try {
                    numProduct = Integer.parseInt(sProduct);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }

                if (numProduct > 0 && numProduct < num) {

                    String sQuantity = JOptionPane.showInputDialog(null, "Please specify the quantity (1 to 10):", "Quantity", JOptionPane.INFORMATION_MESSAGE);

                    if (sQuantity == null) {

                        isTerminated = true;

                    } else {

                        int numQuantity = 0;

                        try {
                            numQuantity = Integer.parseInt(sQuantity);
                        } catch (NumberFormatException ex) {
                            System.out.println(ex);
                        }

                        if (numQuantity > 0 && numQuantity <= MAX) {

                            Product product = null;

                            switch (numProduct) {

                                case 1:
                                    product = new Product(1, "Orange", 2.0f, 0);
                                    break;
                                case 2:
                                    product = new Product(2, "Grapes", 2.5f, 0);
                                    break;
                                case 3:
                                    product = new Product(3, "Banana", 1.5f, 0);
                                    break;
                                case 4:
                                    product = new Product(4, "Mango", 3.0f, 0);
                                    break;
                            }

                            if (product != null) {

                                product.setQuantity(numQuantity);

                                boolean isDone = myCart.addItem(product, MAX);

                                if (!isDone) {
                                    JOptionPane.showMessageDialog(null, "Invalid Quantity!");
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Quantity!");
                        }
                    }

                } else if (numProduct == num) {

                    isTerminated = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Selection!");
                }
            }
        }
    }

    private void removeItem() {

        ArrayList<Product> list = myCart.getItemsList();

        if (list.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Cart is empty!!!");

        } else {
            
            String options = "Please enter\n" ;
            int num = 0 ;

            for (int i = 0; i < list.size(); i++) {

                Product product = list.get(i);

                num = i + 1;
                String name = product.getName();
                
                options += num + " to remove '" + name + "'\n" ;
            }

            String sProduct = JOptionPane.showInputDialog(null, options, "Remove an Item", JOptionPane.INFORMATION_MESSAGE);

            if (sProduct != null) {

                int numProduct = 0;

                try {
                    numProduct = Integer.parseInt(sProduct);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }

                if (numProduct > 0 && numProduct <= list.size()) {

                    Product product = list.get(numProduct-1);
                    myCart.removeItem(product);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Selection!!!");
                }
            }
        }
    }

    private void checkOutCart() {

        ArrayList<Product> list = myCart.getItemsList();

        if (list.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Cart is empty!!!");

        } else {
            
            String message = "";
            int noItems = 0 ;
            double total = 0.0;

            for (int i = 0; i < list.size(); i++) {

                Product product = list.get(i);

                int num = i + 1;
                String name = product.getName();
                float price = product.getPrice();
                int quantity = product.getQunatity();

                double ammount = price * quantity;

                noItems += quantity ;
                total += ammount;

                String order = num + ". " + name + ": $" + price + " x " + quantity + " = $" + ammount;

                message += order + "\n";
            }

            message += "\nNo. of Items: " + noItems + " - Total Bill: $" + total;

            JOptionPane.showMessageDialog(null, message, "Go to CheckOut", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void removeAll() {

        ArrayList<Product> list = myCart.getItemsList();

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty!!!");
        } else {
            
            boolean isDone = myCart.emptyCart();
            if (isDone) {
                JOptionPane.showMessageDialog(null, "All items removed successfully!", "Empty the Cart", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void developerInfo() {

        JOptionPane.showMessageDialog(null, "Developed By: Name (BSxxxxxxxx)", "Developer Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainClass();
    }
}
