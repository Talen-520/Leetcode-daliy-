//CS212 Project 2 by Tao Hu
import javax.swing.*;
import java.awt.*;
import java.util.regex.*;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class BulbGUI extends JFrame{
  public BulbGUI(UnsortedBulbList unsorted, SortedBulbList sorted)
  {
    
    
    setTitle("Unsorted BulbList(left) and Sorted BulbList(Right)");
    setSize(300, 300);  
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    setLayout(new GridLayout(1,2));
    setLocation(100,100);
    setVisible(true);
  
       Container box = this.getContentPane();
       
       TextArea unsortedText = new TextArea(); 
       TextArea sortedText = new TextArea();
        
        box.add(unsortedText);
        box.add(sortedText);
       BulbNode current = unsorted.first;
       while (current.next != null) {
       current = current.next;
       unsortedText.append(current.data.toString() + "\n");
    }
      BulbNode Current = sorted.first;
      while (Current.next != null) {
      Current = Current.next;
      sortedText.append(Current.data.toString() + "\n");
    }
       
       //unsortedBulbs.append(SortedBulbList+"\n");
       //sortedBulbs.append(UnSortedBulbList+"\n");
  }
}