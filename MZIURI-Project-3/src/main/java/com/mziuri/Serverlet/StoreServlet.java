package com.mziuri.Serverlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Product.GetProductsResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> productNames = new ArrayList<>();
        GetProductsResponse response = new GetProductsResponse(productNames.toArray(new String[0]));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(response);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}



