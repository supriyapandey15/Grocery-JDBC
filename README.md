# Grocery-JDBC
This project is a comphrehensive Shop Product Management System implemented using Model-View-Controller(MVC) architectural pattern and Java Database Connectivity(JDBC).
Thhe system facilitates efficient management of shop products, enabling users to perform various operations such as adding, updating, deleting, and fetching product details. Additionally, it prompts users to enter shop details on first run, which are then stored and automatically displayeed on subsequent runs.

# Features
* Product Management
  
  ** Add Product: Add new products to the shop.
  
  ** Delete Product : Remove existing products from the shop by ID.
  
  ** Fetch Product Details : Retrieve and display details of all products.

  ** Update Product : Modify Product details such as name, price, quantity based on product ID.

* Shop Details Management

** Prompt for shop details for first run.

** Automatically fetch the details and display it from the second run ownwards.

* MVC Architecture : Ensures a clean separation of concerns, enhancing, ensuring persistent storage of shop and product information.

# Components

* Model
   The model represents the data layer of the application:
** Product : A class representinh a product with attributes like 'id', 'name', 'price', 'quantity' and 'availability'.
