package edu.jsp.model;

import java.util.List;

public class Shop {
	private int id;
	private String shopName;
	private String address;
	private String gst;
	private long contact;
	private String ownerName;
	
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String name) {
		this.shopName = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String owner) {
		this.ownerName = owner;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", shopName=" + shopName + ", address=" + address + ", gst=" + gst + ", contact="
				+ contact + ", ownerName=" + ownerName + "]";
	}
}
//in this program we will make all the attribute as non static because we can make many copies of it
//