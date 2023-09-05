package th.mfu;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String wStr = request.getParameter("weight");
        String hStr = request.getParameter("height");

        //TODO: calculate bmi
        if(wStr != null && !wStr.isEmpty() && hStr != null && !hStr.isEmpty()){
        try {
        Double weight = Double.parseDouble(wStr);
        Double height = Double.parseDouble(hStr);
        // TODO: handle exception
        Double bmi = calculateBMI(weight, height);
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

    private Double calculateBMI(Double weight, Double height){
        return weight/(height*height);
    }
    
    private String determineBuild(Double bmi) {
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