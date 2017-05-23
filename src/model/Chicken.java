package model;

public class Chicken extends Animal
{
	private int chickenID;
	private String type; 
	
	public Chicken()
	{}

	public int getChickenID() 
	{
		return chickenID;
	}

	public void setChickenID(int chickenID)
	{
		this.chickenID = chickenID;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
