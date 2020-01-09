// Product.java

public class Product {

    public int getId () { return id; }
    public String getName() { return name; }
    public float getPrice() { return price; }
    public int getQunatity () { return quantity; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }    
    public void setPrice(float price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getDetail () {
        return getName() + " ($" + getPrice() + ")" ;
    }
    
    public String toString () {
        return getId() + ", " + getName() + ", " + getPrice() + ", " + getQunatity();
    }

    public Product(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getQunatity());
    }

    public Product(int id, String name, float price, int quantity) {
        setId(id);
        setName(name);       
        setPrice(price);
        setQuantity(quantity);
    }

    public Product() {
        this(0,"", 0.0f, 0);
    }
    
    private int id ;
    private String name;
    private float price;
    private int quantity;
}
