package th.mfu;

import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet(urlPatterns = "/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String w1 = request.getParameter("weight");
        String h1 = request.getParameter("height");

        //TODO: calculate bmi
        if(w1 != null && !w1.isEmpty() && h1 != null && !h1.isEmpty()){
        try {
        double weight = Double.parseDouble("w1");
        double height = Double.parseDouble("h1");
        // TODO: handle exception
        double bmi = (weight/(height*height));
        String build = determineBuild(bmi);

        request.setAttribute("BMI",Math.round(bmi));
        request.setAttribute("build", build);
            
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("Invalid weight or height values.");
        }
    }else {
        response.getWriter().println("Please provide weight and height parameters.");
    }
}
        
        //TODO: determine the built from BMI
        
        //TODO: add bmi and built to the request's attribute

        //TODO: forward to jsp

    
    private String determineBuild(double bmi) {
        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "normal weight";
        } else if (bmi < 30) {
            return "overweight";
        } else if (bmi < 35) {
            return "obese";
        } else {
            return "extremely obese";
        }
    }
}