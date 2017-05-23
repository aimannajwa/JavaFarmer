package model;

import java.util.Date;

public class Animal 
{
	public int animalID;
	public float consumptionPerDay;
	public Date dateEntry;
	public float priceBuy;
	public int quantity;
	
	public Animal()
	{}

	public int getAnimalID() 
	{
		return animalID;
	}

	public void setAnimalID(int animalID) 
	{
		this.animalID = animalID;
	}

	public float getConsumptionPerDay() 
	{
		return consumptionPerDay;
	}

	public void setConsumptionPerDay(float consumptionPerDay) 
	{
		this.consumptionPerDay = consumptionPerDay;
	}

	public Date getDateEntry() 
	{
		return dateEntry;
	}

	public void setDateEntry(Date dateEntry) 
	{
		this.dateEntry = dateEntry;
	}

	public float getPriceBuy()
	{
		return priceBuy;
	}

	public void setPriceBuy(float priceBuy) 
	{
		this.priceBuy = priceBuy;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	
}
