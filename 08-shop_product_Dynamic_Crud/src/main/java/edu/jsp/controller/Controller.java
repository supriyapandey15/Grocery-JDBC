package edu.jsp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.postgresql.Driver;

import edu.jsp.model.Product;
import edu.jsp.model.Shop;
import edu.jsp.view.View;

public class Controller {
	static String dbUrl = "jdbc:postgresql://localhost:5432/shop-product";
	static Connection connection = null; //to establish connection only once
	static {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fis = new FileInputStream("dbConfig.properties");
			Properties properties = new Properties();
			properties.load(fis);
			connection = DriverManager.getConnection(dbUrl, properties);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int addShop(Shop shop)
	{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shop VALUES (?,?,?,?,?,?)");
			preparedStatement.setInt(1, shop.getId());
			preparedStatement.setString(2, shop.getShopName());
			preparedStatement.setString(3, shop.getAddress());
			preparedStatement.setString(4, shop.getGst());
			preparedStatement.setLong(5, shop.getContact());
			preparedStatement.setString(6, shop.getOwnerName());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public void addProducts(Shop shop, List<Product> products) {
		for (Product product : products) {
			//Insert product into product table
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
				preparedStatement.setInt(1, product.getId());
				preparedStatement.setString(2, product.getProductName());
				preparedStatement.setDouble(3, product.getPrice());
				preparedStatement.setInt(4, product.getQuantity());
				preparedStatement.setBoolean(5, product.isAvailability());
				preparedStatement.executeUpdate();
				//Insert shopId and productId into shop-product table
				PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO shop_product VALUES(?,?)");
				preparedStatement2.setInt(1, shop.getId());
				preparedStatement2.setInt(2, product.getId());
				preparedStatement2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Insert shopId and productId into shop-product table
		}
	}
	public Shop isShopExist() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM shop;");
			Shop isShopExist = new Shop();
			while (resultSet.next()) {
				isShopExist.setId(resultSet.getInt(1));
				isShopExist.setShopName(resultSet.getString(2));
				isShopExist.setAddress(resultSet.getString(3));
				isShopExist.setGst(resultSet.getString(4));
				isShopExist.setContact(resultSet.getLong(5));
				isShopExist.setOwnerName(resultSet.getString(6));
			}
			return isShopExist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet fetchAll() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
			Product fetchAll = new Product();
			return statement.executeQuery("SELECT * FROM product;");
//			byte count=0;
//			while (resultSet.next()) {
//				if(++count>0) {
//					break;
//				}
//			}
//			if (count==1) {
//				return statement.executeQuery("SELECT * FROM product;");
//			}
//			else {
//				return null;
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int removeProduct(int productId) {
		try {
			CallableStatement removeProduct = connection.prepareCall("call remove_product(?,?,?)");
			removeProduct.registerOutParameter(1, Types.INTEGER);
			removeProduct.setInt(2, productId);
			removeProduct.registerOutParameter(3, Types.INTEGER);
			//
			removeProduct.executeUpdate();
			int before = removeProduct.getInt(1);
			int after = removeProduct.getInt(3);
			if (after < before) {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public Product fetchParticularProduct(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE product_id = ?");
			preparedStatement.setInt(1, id);
			ResultSet productResultSet = checkProductResultSet(preparedStatement.executeQuery());
			Product product = new Product();
			while (productResultSet.next()) {
				product.setId(productResultSet.getInt(1));
				product.setProductName(productResultSet.getString(2));
				product.setPrice(productResultSet.getDouble(3));
				product.setQuantity(productResultSet.getInt(4));
				product.setAvailability(productResultSet.getBoolean(5));
			}
			return product;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int updateProduct(Product product) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name=?,price=?,quantity=?,availability=? WHERE product_id=?");
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setBoolean(4, product.isAvailability());
			preparedStatement.setInt(5, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet checkProductResultSet(ResultSet resultSet) {
		Statement statement;
		try {
			statement = connection.createStatement();
			byte count=0;
			while (resultSet.next()) {
				if(++count>0) {
					break;
				}
			}
			if (count==1) {
				return statement.executeQuery("SELECT * FROM product;");
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
