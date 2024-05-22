# Grocery-JDBC
This project is a comphrehensive Shop Product Management System implemented using Model-View-Controller(MVC) architectural pattern and Java Database Connectivity(JDBC).
Thhe system facilitates efficient management of shop products, enabling users to perform various operations such as adding, updating, deleting, and fetching product details. Additionally, it prompts users to enter shop details on first run, which are then stored and automatically displayeed on subsequent runs.

# Features
* Product Management
  
  1.Add Product: Add new products to the shop.
  
  2.Delete Product : Remove existing products from the shop by ID.
  
  3.Fetch Product Details : Retrieve and display details of all products.

  4.Update Product : Modify Product details such as name, price, quantity based on product ID.

* Shop Details Management

1.Prompt for shop details for first run.

2.Automatically fetch the details and display it from the second run ownwards.

* MVC Architecture : Ensures a clean separation of concerns, enhancing, ensuring persistent storage of shop and product information.

# Components

* Model
   The model represents the data layer of the application:
  
1.Product : A class representinh a product with attributes like 'id', 'name', 'price', 'quantity' and 'availability'.

2.Shop : A class representing shop details with attributes like 'id', 'shop_Name','shop_Address','Gstno', 'contactNo', 'ownerName'.

* View
   The view is responsible for displaying information to  the user:

     1. ProductView : Handles the display of product details and user prompts.
 
     2. ShopView : Manages the display of shop details.

# Controller

  The Controller handles user inputs and updates the model and view :

   1.ProductController : Manages the flow of product-related operations, processing user inputs and invoking model and view methods.

   2.ShopController : Handles the flow of shop details management, ensuring shop details/information is entered and displayed correctly.

   # Getting Started 
   ##### Prerequisites
   * Java Development Kit(JDK) 8 or higher.

   * A relational database(eg.,MySQL, Postgresql)

   * JDBC driver for your chosen database

# Setup Instructions 

 #### 1. Add PostgresSQL Dependency
 
 Ensure you have maven installed. Then, add the PostgreSQL dependency to your 'pom.xml' file
 <img width="436" alt="pomfile" src="https://github.com/supriyapandey15/Grocery-JDBC/assets/113895235/10bebdec-15e1-4ce7-aa84-5ca3976555d2">

 #### 2. Configure Database 

* Set up your postgreSQL database and create the necessary tables.

* Update the database configuration by providing proper URL, username, password.

  # Usage

  ###### 1. Intial Setup

  * On the first run, the system will prompt you to enter shop details (name, address, contact, gstno, ownerName, Id). These details will be stored and automatically 
    dispalyed//fetched on subsequent runs.


     
