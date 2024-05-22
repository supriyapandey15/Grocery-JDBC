package edu.jsp.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.jsp.controller.Controller;
import edu.jsp.model.Product;
import edu.jsp.model.Shop;

public class View {
	static Scanner userInput = new Scanner(System.in);
	static Controller controller = new Controller();
	static Shop shop = new Shop();
	static {
		// Ask shop details for 1st run of application.
		// From 2nd run onward check if shop exists, if yes then use existing.
		Shop shopExist = controller.isShopExist();
		if (shopExist.getId() != 0) { // Shop Exist
			// Maintaining only 1 reference for further operations.
			shop = shopExist;
			System.out.println("- - - - WELCOME BACK TO SHOP - - - -");
			System.out.println("Shop details : ");
			System.out.println("Id : " + shop.getId());
			System.out.println("Name : " + shop.getShopName());
			System.out.println("Address : " + shop.getAddress());
			System.out.println("GST : " + shop.getGst());
			System.out.println("Contact : " + shop.getContact());
			System.out.println("Owner : " + shop.getOwnerName());
		} else {
			System.out.println("- - - - WELCOME TO SHOP - - - -");
			System.out.print("Enter id : ");
			shop.setId(userInput.nextInt());
			userInput.nextLine();
			System.out.print("Enter shop name : ");
			shop.setShopName(userInput.nextLine());
			System.out.print("Enter shop address : ");
			shop.setAddress(userInput.nextLine());
			System.out.print("Enter GST number : ");
			shop.setGst(userInput.nextLine());
			System.out.print("Enter contact number : ");
			shop.setContact(userInput.nextLong());
			userInput.nextLine();
			System.out.print("Enter shop owner name : ");
			shop.setOwnerName(userInput.nextLine());
			if (controller.addShop(shop) != 0) {
				System.out.println("Shop added\n");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
			System.out.println("Select operation to perform : ");
			System.out.println("1.Add product/s\n2.Remove product\n3.Update product\n4.Fetch product\n0.Exit");
			System.out.print("Enter digit respective to desired option : ");
			byte userChoice = userInput.nextByte();
			userInput.nextLine();
			switch (userChoice) {
			case 1: // Add product/s
				List<Product> products = new ArrayList<Product>();
				boolean continueToAdd = true;
				do {
					Product product = new Product();
					System.out.print("Enter product id : ");
					product.setId(userInput.nextInt());
					userInput.nextLine();
					System.out.print("Enter product name : ");
					product.setProductName(userInput.nextLine());
					System.out.print("Enter product price : ");
					product.setPrice(userInput.nextDouble());
					userInput.nextLine();
					System.out.print("Enter product quantity : ");
					int quantity = userInput.nextInt();
					if (quantity > 0) {
						// set availability true
						product.setAvailability(true);
					} else {
						// set availability false
						product.setAvailability(false);
					}
					products.add(product);
					System.out.print("Continue to add product ? y/n : ");
					userInput.nextLine();
					String myInput = userInput.nextLine();
					if (myInput.equalsIgnoreCase("n")) {
						continueToAdd = false;
					}
				} while (continueToAdd);
				controller.addProducts(shop, products);
				break;
			case 2:
				ResultSet productExist = controller.fetchAll();
				try {
					if (productExist != null) {
						System.out.println("Product exists.");
						System.out.println("Available products : ");
						System.out.println("Id | Name");
						while (productExist.next()) {
							System.out.print(productExist.getInt(1) + "    ");
							System.out.println(productExist.getString(2));
						}
					} else {
						System.out.println("No product exists.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("Enter product id to remove : ");
				int productId = userInput.nextInt();
				userInput.nextLine();
				int removeProduct = controller.removeProduct(productId);
				if (removeProduct == 0) {
					System.out.println("Product removed.");
				} else {
					System.out.println("Product couldn't be removed.");
				}
				break;
			case 3:
				ResultSet productExist1 = controller.fetchAll();
				Product product = new Product();
				if (productExist1 != null) {
					System.out.println("Product exists.");
					System.out.println("Available products : ");
					System.out.printf("| %-10s | %-8s | %-10s | %-8s | %-8s |%n", "Id", "Name", "Price", "Quantity","Availability");
					// System.out.println("Id | Name | Price | Quantity | Availability");
					try {
						while (productExist1.next()) {
							System.out.printf("| %-10d |", productExist1.getInt(1));
							System.out.printf(" %-8s |", productExist1.getString(2));
							System.out.printf(" %-8f |", productExist1.getDouble(3));
							System.out.printf(" %-8d |", productExist1.getInt(4));
							System.out.printf(" %-12b |%n", productExist1.getBoolean(5));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("No product exists.");
				}
				//int product2 = controller.updateProduct(product);
				System.out.print("Enter product id to be updated : ");
				int productToUpdate = userInput.nextInt();
				userInput.nextLine();
				Product product2 = controller.fetchParticularProduct(productToUpdate);
				System.out.println("What to update?");
				System.out.println("1.Produt Id\n2.Product Name\n3.Product Price\n4.Product Quantity\n5.Product Availability");
				System.out.print("Enter desired option to update product details : ");
				byte myInput = userInput.nextByte();
				userInput.nextLine();
				switch (myInput) {
				case 1:
					System.out.print("Enter product id to update : ");
				    int id = userInput.nextInt();
				    userInput.nextLine();
				    product2.setId(id);
					controller.updateProduct(product2);
					System.out.println("Product id updated.");
					break;
				case 2:
					System.out.print("Enter product name to update : ");
					String name = userInput.nextLine();
					product2.setProductName(name);
					controller.updateProduct(product2);
					System.out.println("Product name updated.");
					break;
				case 3:
					System.out.print("Enter product price to update : ");
					double price = userInput.nextDouble();
					userInput.nextLine();
					product2.setPrice(price);
					controller.updateProduct(product2);
					System.out.println("Product price updated.");
					break;
				case 4:
					System.out.print("Enter product quantity to update : ");
					int quantity = userInput.nextInt();
					userInput.nextLine();
					product2.setQuantity(quantity);
					controller.updateProduct(product2);
					System.out.println("Product quantity updated.");
					break;
				case 5:
					System.out.print("Enter product availability to update : ");
					boolean availability = userInput.nextBoolean();
					userInput.nextLine();
					product2.setAvailability(availability);
					controller.updateProduct(product2);
					System.out.println("Product availability updated.");
					break;
				default:
					break;
				}
				break;
			case 4:
				System.out.print("Enter product id to be fetched : ");
				int idToFetch = userInput.nextInt();
				userInput.nextLine();
				Product product3 = controller.fetchParticularProduct(idToFetch);
				System.out.println("Product fetched.");
				System.out.printf("| %-10s | %-8s | %-11s | %-9s | %-8s |%n", "Id", "Name", "Price", "Quantity","Availability");
				System.out.printf("| %-10d | %-8s |  %-8f |  %-8d |  %-11b |%n", product3.getId(), product3.getProductName(),product3.getPrice(), product3.getQuantity(),product3.isAvailability());
				break;
			case 0: // Exit
				System.exit(0);
				System.out.println("-----EXITED-----");
				break;
			default:
				System.err.println("-----INVALID SELECTION-----");
				break;
			}
		} while (true);
	}

}
