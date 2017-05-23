package model;

import java.util.Date;
public class Transaction 
{
	public int farmerID;
	public int buyerID;
	private int transactionID;
	private Date transactionDate;
	private Date transactionTime;
	private int quantityChicken;
	private int quantityTurkey;
	
	public Transaction()
	{}
	
	public int getFarmerID() 
	{
		return farmerID;
	}
	
	public void setFarmerID(int farmerID) 
	{
		this.farmerID = farmerID;
	}
	
	public int getBuyerID() 
	{
		return buyerID;
	}
	
	public void setBuyerID(int buyerID) 
	{
		this.buyerID = buyerID;
	}
	
	public int getTransactionID() 
	{
		return transactionID;
	}
	
	public void setTransactionID(int transactionID)
	{
		this.transactionID = transactionID;
	}
	
	public Date getTransactionDate() 
	{
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) 
	{
		this.transactionDate = transactionDate;
	}
	
	public Date getTransactionTime() 
	{
		return transactionTime;
	}
	
	public void setTransactionTime(Date transactionTime)
	{
		this.transactionTime = transactionTime;
	}
	
	public int getQuantityChicken() 
	{
		return quantityChicken;
	}
	
	public void setQuantityChicken(int quantityChicken) 
	{
		this.quantityChicken = quantityChicken;
	}
	
	public int getQuantityTurkey() 
	{
		return quantityTurkey;
	}
	
	public void setQuantityTurkey(int quantityTurkey) 
	{
		this.quantityTurkey = quantityTurkey;
	}

}
