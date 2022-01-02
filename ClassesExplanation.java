public class Store {
  // instance fields
  String productType;
  int inventoryCount;
  double inventoryPrice;
  
  // constructor method
  public Store(String product, int count, double price) {   // (String product, int count, double price) are Constructor Parameters
    productType = product;
    inventoryCount = count;
    inventoryPrice = price;
  }
  public Store(boolean secondConstructor) { // because of constructor overloading, a class can have multiple constructors as long as they have different parameter values.
  }
  // main method
  public static void main(String[] args) {
    Store lemonadeStand = new Store("lemonade", 42, .99); 
    Store cookieShop = new Store("cookies", 12, 3.75);
    
    System.out.println("Our first shop sells " + lemonadeStand.productType + " at " + lemonadeStand.inventoryPrice + " per unit.");//lemonadeStand.productType  = lemonade
    
    System.out.println("Our second shop has " + cookieShop.inventoryCount + " units remaining.");
  }
}
