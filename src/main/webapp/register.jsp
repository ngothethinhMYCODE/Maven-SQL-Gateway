<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>

    <h1>Create Account</h1>
	<%
	    String error = (String) request.getAttribute("error");
	
	    if (error != null) {
	%>
	
	    <p style="color: red;">
	        <%= error %>
	    </p>
	
	<%
	    }
	%>
    <form action="RegisterServlet" method="post">

        <div>
            <label for="email">Email:</label>
            <input type="email"
                   id="email"
                   name="email"
                   required>
        </div>

        <br>

        <div>
            <label for="firstName">First Name:</label>
            <input type="text"
                   id="firstName"
                   name="firstName">
        </div>

        <br>

        <div>
            <label for="lastName">Last Name:</label>
            <input type="text"
                   id="lastName"
                   name="lastName">
        </div>

        <br>

        <button type="submit">
            Register
        </button>

    </form>

</body>
</html>