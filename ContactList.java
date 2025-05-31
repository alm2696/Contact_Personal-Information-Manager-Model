package mod09_02;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactList. This servlet
 * handles GET requests to display a list of contacts.
 * 
 * @author angel
 */
@WebServlet("/ContactList")
public class ContactList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the ContactList servlet.
	 */
	public ContactList() {
		super();
	}

	/**
	 * Handles the HTTP GET request to retrieve and display the contact list.
	 * 
	 * @param request           the HttpServletRequest object that contains the request from the client
	 * @param response          the HttpServletResponse object that contains the response from the servlet
	 * @throws ServletException if an error occurs during the request handling
	 * @throws IOException      if an input or output error occurs while the servlet is handling the request
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Access the model instance to retrieve contact data
			PIMModel model = PIMModel.getInstance();

			// Fetch the list of contacts from the model
			List<Contact> contacts = model.getContacts();

			// Store the contacts list as a request attribute to be accessed in the JSP
			request.setAttribute("contacts", contacts);

			// Forward the request to the ContactList JSP to display the contacts
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/ContactList.jsp");
			dispatcher.forward(request, response);

		} catch (Exception exception) {
			// Handle any errors by wrapping the exception in a ServletException
			ServletException error = new ServletException("Error while generating the Contact List", exception);

			// Store the error as a request attribute to be accessed in the Error JSP
			request.setAttribute("exception", error);

			// Forward the request to the Error JSP to display the error message
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
