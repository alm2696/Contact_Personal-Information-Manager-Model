package mod09_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PIMModel class is for managing Personal Information Manager data. This
 * class follows the singleton pattern to ensure only one instance exists,
 * and it handles database interactions for retrieving and adding contacts.
 * 
 * @author angel
 */
public class PIMModel {

	// Internal static instance of the model
	private static PIMModel instance = null;

	/**
	 * Returns the single instance of PIMModel, creating it if necessary.
	 * 
	 * @return The singleton instance of PIMModel
	 * @throws ClassNotFoundException If the database driver class is not found
	 * @throws SQLException           If there is an error connecting to the database
	 */
	synchronized public static PIMModel getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new PIMModel();
		return instance;
	}

	// Database connection details
	String DBDriver = "com.mysql.cj.jdbc.Driver"; // MySQL JDBC Driver
	String DBURL = "jdbc:mysql://localhost:3306/CMSC230"; // Database URL
	String DBUser = "root"; // Database username
	String DBPassword = "Password!!"; // Database password

	// Static variables
	private static int counter = 0; // Number of instances created
	private static Connection DBConn = null; // Current database connection
	private static PreparedStatement queryContactList = null; // Prepared statement for retrieving contact list
	private static PreparedStatement queryContactAdd = null; // Prepared statement for adding a new contact

	/**
	 * Main constructor. Creates a connection to the database if it hasn't been established yet.
	 * 
	 * @throws SQLException           If an error occurs while establishing the connection
	 * @throws ClassNotFoundException If the database driver class is not found
	 */
	private PIMModel() throws SQLException, ClassNotFoundException {

		// Check if a connection already exists
		if (PIMModel.DBConn == null) {

			// Load the MySQL database driver
			Class.forName(this.DBDriver);

			// Establish the connection to the database
			PIMModel.DBConn = DriverManager.getConnection(this.DBURL, this.DBUser, this.DBPassword);
		}
	}

	/**
	 * Closes the data model by cleaning up open resources. Decrements the instance
	 * count and closes the database connection if this is the last instance.
	 * 
	 * @throws SQLException If an error occurs while closing the connection
	 */
	public void close() throws SQLException {

		// Decrement the instance counter
		PIMModel.counter--;

		// Close the connection if this is the last instance
		if (PIMModel.counter == 0) {
			PIMModel.DBConn.close();
		}
	}

	/**
	 * Retrieves the list of contacts from the database.
	 * 
	 * @return A list of Contact objects
	 * @throws SQLException If an error occurs during the query execution
	 */
	public List<Contact> getContacts() throws SQLException {

		// Check if the query for retrieving contacts has been prepared
		if (PIMModel.queryContactList == null) {

			// Prepare the SQL query for retrieving contact data
			PIMModel.queryContactList = PIMModel.DBConn.prepareStatement(
					"""
					select contactId, 
					       firstName, 
					       lastName, 
					       company, 
					       email 
					from contacts
					order by lastName, firstName
					"""
					);
		}

		// Execute the query and retrieve the results
		ResultSet results = queryContactList.executeQuery();

		// Create an empty list to store the contact objects
		ArrayList<Contact> contacts = new ArrayList<>();

		// Iterate over the result set and build the list of contacts
		while (results.next()) {
			// Retrieve each field from the result set
			int contactId = results.getInt(1);
			String lastName = results.getString(2);
			String firstName = results.getString(3);
			String company = results.getString(4);
			String email = results.getString(5);

			// Create a new Contact object and add it to the list
			Contact contact = new Contact(contactId, firstName, lastName, company, email);
			contacts.add(contact);
		}

		// Return the list of contacts
		return contacts;
	}

	/**
	 * Adds a new contact to the database.
	 * 
	 * @param contact       The Contact object to be added
	 * @throws SQLException If an error occurs during the insertion
	 */
	public void addContact(Contact contact) throws SQLException {

		// Check if the query for adding contacts has been prepared
		if (PIMModel.queryContactAdd == null) {

			// Prepare the SQL query for inserting a new contact
			PIMModel.queryContactAdd = PIMModel.DBConn.prepareStatement(
					"insert into contacts(contactId, lastName, firstName, company, email) values(?, ?, ?, ?, ?)"
					);
		}

		// Set the parameters for the query from the contact object
		PIMModel.queryContactAdd.setInt(1, contact.getContactId());
		PIMModel.queryContactAdd.setString(2, contact.getLastName());
		PIMModel.queryContactAdd.setString(3, contact.getFirstName());
		PIMModel.queryContactAdd.setString(4, contact.getCompany());
		PIMModel.queryContactAdd.setString(5, contact.getEmail());

		// Execute the update query
		PIMModel.queryContactAdd.executeUpdate();
	}
}
