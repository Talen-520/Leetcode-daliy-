
//Tao Hu project 2
import javax.swing.*;
import java.awt.*;
import java.util.regex.*;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    SortedBulbList sorted = new SortedBulbList();
    UnsortedBulbList unsorted = new UnsortedBulbList();
    File myObj = new File("Bulb.txt");
    try {
      
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        
        //String[] tokens = scanner.nextLine().split(";");
        String[] parts = myReader.nextLine().split(",");
        String manufacturer = parts[0]; 
        String partNumber = parts[1]; 
        int wattage = Integer.parseInt(parts[2]);
        int lumens = Integer.parseInt(parts[3]);
       
        Bulb dataString = new Bulb(manufacturer, partNumber, wattage, lumens);
        
        unsorted.append(dataString);
        sorted.append(dataString);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
   
    
    new BulbGUI(unsorted,sorted);// BulbGUI = new BulbGui();

  }

}
