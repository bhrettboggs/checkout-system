package GroupProject;

import java.util.LinkedList;

class Inventory {
    private int id; 
    private String name; 

    // Constructors
    public Inventory() {}

    public Inventory(int id, String name) { 
        this.id = id; 
        this.name = name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Product extends Inventory { 
    private int quantity; 
    private double price;
    private String description;
    private String category;
    private String brand;
    private String color;
    private String size;
    private String material;

    // Getters and setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
 

    @Override 
    public String toString() { 
        // Return product information
        return "Product [name=" + getName() + ", id=" + getId() + ", quantity=" + quantity + ", price=" + price +
                ", description=" + description + ", category=" + category + ", brand=" + brand +
                ", color=" + color + ", size=" + size + ", material=" + material + "]";
    }
}

class Clothing extends Product {
    private String clothingType;

    public String getClothingType() {
        return clothingType;
    }

    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    @Override
    public String toString() {
        return super.toString() + ", clothingType=" + clothingType;
    }
}

class Electronics extends Product {
    private String electronicsType;

    public String getElectronicsType() {
        return electronicsType;
    }

    public void setElectronicsType(String electronicsType) {
        this.electronicsType = electronicsType;
    }

    @Override
    public String toString() {
        return super.toString() + ", electronicsType=" + electronicsType;
    }
}

class Food extends Product {
    private String foodType;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return super.toString() + ", foodType=" + foodType;
    }
}

class ReadLinkedList {
    private LinkedList<String> theList = new LinkedList<>();

    public void addToList(String item) {
        theList.add(item);
    }

    public void readLinkedList() {
        while (!theList.isEmpty()) {
            System.out.println(theList.removeFirst());
        }
    }
}

class HardCode {
    // Initialize products
    public static LinkedList<Product> initialize() {
        LinkedList<Product> productList = new LinkedList<>();

        Clothing c = new Clothing();
        c.setClothingType("Shirt");
        c.setBrand("Nike");
        c.setColor("Black");
        c.setMaterial("Cotton");
        c.setPrice(20.00);
        c.setQuantity(10);
        c.setSize("M");
        c.setDescription("Nike Shirt");
        c.setCategory("Clothing");
        c.setName("Nike Shirt");
        c.setId(1);

        Electronics e = new Electronics();
        e.setElectronicsType("Phone");
        e.setBrand("Apple");
        e.setColor("Black");
        e.setMaterial("Glass");
        e.setPrice(1000.00);
        e.setQuantity(5);
        e.setSize("6.1 inch");
        e.setDescription("iPhone 12");
        e.setCategory("Electronics");
        e.setName("iPhone 12");
        e.setId(2);

        Food f = new Food();
        f.setFoodType("Fruit");
        f.setBrand("Apple");
        f.setColor("Red");
        f.setMaterial("Organic");
        f.setPrice(1.00);
        f.setQuantity(100);
        f.setSize("Small");
        f.setDescription("Fresh Apple");
        f.setCategory("Food");
        f.setName("Red Apple");
        f.setId(3);

        productList.add(c);
        productList.add(e);
        productList.add(f);

        return productList;
    }
}

