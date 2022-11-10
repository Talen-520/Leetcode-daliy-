abstract  class  BulbList {
  protected BulbNode first = new BulbNode(null);
  protected BulbNode last = first; 
  protected int length = 0; 

  public void append(Bulb d)
    {
        BulbNode newNode = new BulbNode(d);
        last.next = newNode;
        last = newNode;
        length++;
	}

  public String toString() {
		BulbNode p = first.next;
		String returnString = " ";
		while (p != null) {
			returnString += p.data + " ";
			p = p.next;
		}
		return returnString;
	}

  }
/*class MoneyList {
	private MoneyNode first = new MoneyNode(null);
	private MoneyNode last = first;
	private int length = 0;

	public int getLength() {
		return length;
	}

	public void append(Money d)
    {
        MoneyNode newNode = new MoneyNode(d);
        last.next = newNode;
        last = newNode;
        length++;
	}

	public void prepend(Money d)
    {
        MoneyNode newNode = new MoneyNode(d);
        newNode.next = first.next;
        first.next = newNode;
        length++;
	}

	public String toString() {
		MoneyNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + " ";
			p = p.next;
		}
		return returnString;
	}

    public int getValue()
    {
		MoneyNode p = first.next;
		int total = 0;
		while ( p != null ) {
            if ( p.data instanceof Bill ) total += ((Bill) p.data).getValue() * 100;
            else                          total += ((Coin) p.data).getValue();
			p = p.next;
		}
		return total;
	}

	public boolean equals(Object other) {
		if (other == null || getClass() != other.getClass()
				|| length != ((MoneyList) other).length)
			return false;

		MoneyNode nodeThis = first;
		MoneyNode nodeOther = ((MoneyList) other).first;
		while (nodeThis != null) {
			if (nodeThis.data != nodeOther.data)
				return false;

			nodeThis = nodeThis.next;
			nodeOther = nodeOther.next;
		} 

		return true;
	}

}*/