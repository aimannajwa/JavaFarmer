package model;

public class Buyer 
{
	private int buyerID;
	private String buyerName;
	private String buyerTelNo;
	private String buyerEmail;
	private String buyerAddress;
	
	public Buyer()
	{}
	
	public int getBuyerID() 
	{
		return buyerID;
	}
	
	public void setBuyerID(int buyerID) 
	{
		this.buyerID = buyerID;
	}
	
	public String getBuyerName() 
	{
		return buyerName;
	}
	
	public void setBuyerName(String buyerName) 
	{
		this.buyerName = buyerName;
	}
	
	public String getBuyerTelNo() 
	{
		return buyerTelNo;
	}
	
	public void setBuyerTelNo(String buyerTelNo) 
	{
		this.buyerTelNo = buyerTelNo;
	}
	
	public String getBuyerEmail() 
	{
		return buyerEmail;
	}
	
	public void setBuyerEmail(String buyerEmail) 
	{
		this.buyerEmail = buyerEmail;
	}
	
	public String getBuyerAddress() 
	{
		return buyerAddress;
	}
	
	public void setBuyerAddress(String buyerAddress) 
	{
		this.buyerAddress = buyerAddress;
	}
	
}
