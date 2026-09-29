package murach.web;

import java.io.IOException;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import murach.business.User;
import murach.util.MailUtilResend;
import murach.util.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Get data from register.jsp
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // 2. Create User object
        User user = new User(
                email,
                firstName,
                lastName
        );

        // 3. Save user to PostgreSQL
        UserDAO.insert(user);

        // 4. Prepare email
        String to = email;

        String from = "ngothethinh7364@gmail.com";

        String subject = "Registration Successful";

        String body =
                "Dear " + firstName + ",\n\n"
                + "Thank you for registering!\n\n"
                + "Your registration was successful.\n\n"
                + "Information:\n"
                + "Email: " + email + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n\n"
                + "Best regards,\n"
                + "Maven SQL Gateway";

        boolean bodyIsHTML = false;

        // 5. Send email
        try {

        	MailUtilResend.sendMail(
                    to,
                    from,
                    subject,
                    body,
                    bodyIsHTML
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    "User registered successfully, but email could not be sent."
            );

            request.setAttribute("user", user);

            request.getRequestDispatcher(
                    "/register-success.jsp"
            ).forward(request, response);

            return;
        }

        // 6. Send user information to success page
        request.setAttribute("user", user);

        request.getRequestDispatcher(
                "/register-success.jsp"
        ).forward(request, response);
    }
}