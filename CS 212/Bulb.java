public class Bulb{

public String manufacturer;
public String partNumber;
public int wattage;
public int lumens;
void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}
	
	String getManufacturer() 
	{
		return this.manufacturer;
	}
	
	void setPartNum(String partNumber) 
	{
		this.partNumber = partNumber;
	}
	
	String getPartNum() 
	{
		return this.partNumber;
	}
	
	void setWattage(int wattage) 
	{
		this.wattage = wattage;
	}
	
	int getWattage() 
	{
		return this.wattage;
	}
	
	void setLumens(int lumens) 
	{
		this.lumens = lumens;
	}
	
	int getLumens() 
	{
		return this.lumens;
	}

  public Bulb(String a,String b,int c,int d){
    manufacturer = a;
		partNumber = b;
		wattage = c;
		lumens = d;
  }
  public String toString(){
    return  manufacturer+","+partNumber+","+wattage+","+lumens;
  }
public boolean equals(Object other){
    if (getClass() != other.getClass() || other == null){
      return false;
    }
    return true;

  }
  

}



  /*public boolean equals(Object other) {
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
