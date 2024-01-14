package server;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/accident")
public class AccidentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String service = request.getParameter("service");

        if ("reason".equals(service)) {
            String reason = request.getParameter("reason");
            // Call the AccidentReasonWebService and get the result
            String result = callAccidentReasonWebService(reason);
            // Set the result in the request
            request.setAttribute("result", result);
            // Forward to a JSP page to display the result
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);
        }

        // Add similar logic for other services
    }

    private String callAccidentReasonWebService(String reason) {
        // Implement the logic to call AccidentReasonWebService
        // Return the result as a string
        return "Result for Accident Reason: " + reason;
    }
}
