class SortedBulbList extends BulbList{
  public SortedBulbList() 
  {
    super(); 
  } 

  public void add(Bulb d) 
  { 
    BulbNode newNode = new BulbNode(d);
    BulbNode nodeThis = first;
    BulbNode nodeNext = first.next;
    
    //while(current.next != null||current.next.next>current.next.next.next ){
      while(nodeNext.next != null|| d.getWattage() > nodeNext.data.getWattage()){
        BulbNode first_Node = nodeNext.next;
        BulbNode second_Node = nodeNext.next.next;
        first_Node.next = second_Node.next;
        nodeNext.next.next = first_Node;
    } // swap node
         newNode.next = nodeNext;
         nodeThis = newNode;
         length++;
        /*last.next = newNode;
        last = newNode;
        length++; */
        
    }
  }