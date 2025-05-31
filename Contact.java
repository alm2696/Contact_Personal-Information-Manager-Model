package mod09_02;

/**
 * Class representing a contact in the system. Each contact has
 * a unique ID, name, company affiliation, and email address.
 * 
 * @author angel
 */
public class Contact {

	// Instance variables representing contact details
	private int contactId;  // Unique identifier for the contact
	private String lastName;  // Contact's last name
	private String firstName;  // Contact's first name
	private String company;  // Company the contact is associated with
	private String email;  // Contact's email address

	/**
	 * Constructor to initialize a Contact object with all attributes.
	 * 
	 * @param contactId Unique ID of the contact
	 * @param lastName  Contact's last name
	 * @param firstName Contact's first name
	 * @param company   Company the contact is affiliated with
	 * @param email     Contact's email address
	 */
	public Contact(int contactId, String lastName, String firstName, String company, String email) {
		super();
		this.contactId = contactId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.company = company;
		this.email = email;
	}

	/**
	 * Get the unique ID of the contact.
	 * 
	 * @return contactId The contact's unique identifier.
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * Get the contact's last name.
	 * 
	 * @return lastName The contact's last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the contact's first name.
	 * 
	 * @return firstName The contact's first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get the contact's company affiliation.
	 * 
	 * @return company The company the contact is affiliated with.
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Get the contact's email address.
	 * 
	 * @return email The contact's email address.
	 */
	public String getEmail() {
		return email;
	}
}
