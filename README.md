## Inventory Management System ##

## Overview ##
The Inventory Management System is a robust web application designed to manage warehouses, products, inventory, orders, and transfers efficiently. It leverages Java Servlets, JSP, Hibernate ORM, and PostgreSQL for a scalable, dynamic, and user-friendly solution.

## Features ##
CRUD Operations:
Manage warehouses, products, customers, providers, orders, and inventory.
Search and Pagination:
Search entities and navigate large datasets with paginated views.
Role-based Navigation:
Organized navigation with clear links to relevant sections.
Responsive Design:
Adaptable layouts for desktop and mobile devices.
Reusable Components:
Consistent headers, footers, and tables for scalability.
## Technology Stack ##
Frontend:
JSP (Java Server Pages)
HTML, CSS (Custom responsive styling)
Backend:
Java Servlets
Hibernate ORM
Database:
PostgreSQL
Build Tool:
Maven
IDE:
## IntelliJ IDEA Ultimate ##
Prerequisites
Java Development Kit (JDK): Version 8 or later.
Apache Tomcat: Version 9.0 or later.
PostgreSQL Database: Installed and configured.
Maven: Installed for dependency management.
Hibernate: Integrated via Maven dependencies.

## Installation and Setup ##
1. Clone the Repository
   git clone https://github.com/fabriceCODER/inventory_management.git
   cd inventory-management-system
2. Configure PostgreSQL
   Create a database named inventory_db
   CREATE TABLE warehouse (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    price DECIMAL(10, 2)
);

-- Add other tables based on your entity relationships.
3. Configure hibernate.cfg.xml
   Update the file under src/main/resources with your PostgreSQL details:
   <hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/inventory_db</property>
        <property name="hibernate.connection.username">your Postgresql username</property>
        <property name="hibernate.connection.password">Your postgresql password</property>
        <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
        <property name="hibernate.show_sql">true</property>
    </session-factory>
</hibernate-configuration>

4. Build the Project
   mvn clean install
5. Deploy to Tomcat
a. Package the application into a WAR file:
mvn package
b. Copy the inventory-management-system.war file from target/ to your Tomcat webapps directory.
c. Start Tomcat:
  catalina.bat run (Windows)
 ./catalina.sh run (Linux/Mac)
6. Access the Application
Open your browser and navigate to: http://localhost:8080/inventory-management-system

## Usage ##
Navigation:
Access entities like Warehouses, Products, Inventory, etc., via the navigation menu.
CRUD Operations:
Create, view, update, and delete records for all entities.
Search and Pagination:
Use the search bar and pagination controls to manage large datasets.
Responsive Design:
Use the application seamlessly on both desktop and mobile devices.

Project Structure: 

src/
├── main/
│   ├── java/
│   │   └── com.inventory/
│   │       ├── controller/
│   │       │   └── ProductController.java
│   │       ├── dao/
│   │       │   └── ProductDAO.java
│   │       ├── model/
│   │       │   └── Product.java
│   │       └── util/
│   │           └── HibernateUtil.java
│   ├── resources/
│   │   └── hibernate.cfg.xml
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── styles/
│       │   └── style.css
│       ├── product/
│       │   ├── list.jsp
│       │   ├── create.jsp
│       │   └── update.jsp
│       └── shared/
│           ├── header.jsp
│           └── footer.jsp

## Contributing ##
Fork the repository.
Create a new branch for your feature or fix:
git checkout -b feature-name

Commit your changes:
git commit -m "Add a meaningful commit message"
Push to the branch:
git push origin feature-name
Submit a Pull Request

## License ##
This project is licensed under the MIT License. See the LICENSE file for details.

## Future Enhancements ##

Implement REST APIs for integration with external systems.
Add advanced role-based access control.
Integrate dashboards for analytics and reporting.
Support multi-language localization.











