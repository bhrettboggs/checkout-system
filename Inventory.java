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

// Removed unused ReadLinkedList class

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
        c.setId(102);

        Clothing c1 = new Clothing();
        c1.setClothingType("Shirt");
        c1.setBrand("Nike");
        c1.setColor("Black");
        c1.setMaterial("Cotton");
        c1.setPrice(20.00);
        c1.setQuantity(10);
        c1.setSize("L");
        c1.setDescription("Nike Shirt");
        c1.setCategory("Clothing");
        c1.setName("Nike Shirt");
        c1.setId(103);

        Clothing c2 = new Clothing();
        c2.setClothingType("Shirt");
        c2.setBrand("Nike");
        c2.setColor("Black");
        c2.setMaterial("Cotton");
        c2.setPrice(20.00);
        c2.setQuantity(10);
        c2.setSize("S");
        c2.setDescription("Nike Shirt");
        c2.setCategory("Clothing");
        c2.setName("Nike Shirt");
        c2.setId(101);

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
        e.setId(201);

        Electronics e1 = new Electronics();
        e1.setElectronicsType("Phone");
        e1.setBrand("Apple");
        e1.setColor("Black");
        e1.setMaterial("Glass");
        e1.setPrice(1300.00);
        e1.setQuantity(5);
        e1.setSize("12 inch");
        e1.setDescription("MacBook Pro");
        e1.setCategory("Electronics");
        e1.setName("MacBook Pro");
        e1.setId(202);

        Electronics e2 = new Electronics();
        e2.setElectronicsType("Phone");
        e2.setBrand("Apple");
        e2.setColor("Black");
        e2.setMaterial("Glass");
        e2.setPrice(455.00);
        e2.setQuantity(5);
        e2.setSize("2 inch");
        e2.setDescription("apple watch");
        e2.setCategory("Electronics");
        e2.setName("Apple Watch");
        e2.setId(203);

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
        f.setId(301);

        Food f1 = new Food();
        f1.setFoodType("Fruit");
        f1.setBrand("Banana");
        f1.setColor("Yellow");
        f1.setColor("yellow");
        f1.setMaterial("Organic");
        f1.setPrice(1.00);
        f1.setQuantity(100);
        f1.setSize("Small");
        f1.setDescription("Fresh Banana");
        f1.setCategory("Food");
        f1.setName("Banana");    
        f1.setId(302);

        Food f2 = new Food();
        f2.setFoodType("Fruit");
        f2.setBrand("Apple");
        f2.setColor("Green");
        f2.setMaterial("Organic");
        f2.setPrice(1.00);
        f2.setQuantity(100);
        f2.setSize("Small");
        f2.setDescription("Fresh Apple");
        f2.setCategory("Food");
        f2.setName("Green Apple");
        f2.setId(303);

        productList.add(c);
        productList.add(c1);
        productList.add(c2);
        productList.add(e);
        productList.add(e1);
        productList.add(e2);
        productList.add(f);
        productList.add(f1);
        productList.add(f2);

        return productList;
    }
}

