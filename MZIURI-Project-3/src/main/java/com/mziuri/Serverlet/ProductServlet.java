package com.mziuri.Serverlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mziuri.Product.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/store/product")
public class ProductServlet extends HttpServlet {

    private ObjectMapper mapper;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CandyShop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1324";

    @Override
    public void init() throws ServletException {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("name");

        Product product = getProductByName(productName);

        if (product != null) {
            GetProductInfoResponse productInfoResponse = new GetProductInfoResponse(product.getProd_name(), product.getProd_price(), product.getProd_amount());
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), productInfoResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PurchaseRequest purchaseRequest = objectMapper.readValue(request.getReader(), PurchaseRequest.class);

        if (isValidPurchaseRequest(purchaseRequest)) {
            String productName = purchaseRequest.getName();
            int purchaseAmount = purchaseRequest.getAmount();

            Product product = getProductByName(productName);

            if (product != null && product.getProd_amount() >= purchaseAmount) {
                updateProductQuantity(productName, product.getProd_amount() - purchaseAmount);

                PurchaseResponse purchaseResponse = new PurchaseResponse(productName, product.getProd_amount() - purchaseAmount);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                objectMapper.writeValue(response.getOutputStream(), purchaseResponse);
            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();


        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        processPurchase purchaseSuccess = new processPurchase(productId, quantity);

        response.setStatus(HttpServletResponse.SC_OK);
        out.println("{ \"message\": \"Purchase successful\" }");

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.println("{ \"message\": \"Purchase failed\" }");

        out.close();
    }

    private boolean isValidPurchaseRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest == null) {
            return false;
        }

        return purchaseRequest.getName() != null && !purchaseRequest.getName().isEmpty();
    }



    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");

        if (!isValidPassword(password)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
            return;
        }

        AddProductRequest addProductRequest = mapper.readValue(request.getReader(), AddProductRequest.class);

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private boolean isValidPassword(String password) {
        return "your_password".equals(password);
    }

    private Product getProductByName(String productName) {

        return null;
    }

    private void updateProductQuantity(String productName, int newQuantity) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE product SET prod_amount = ? WHERE prod_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, productName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private void addNewProduct(String productName, int quantity, String password) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO product (prod_name, prod_price, prod_amount) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, productName);
                pstmt.setFloat(2, 0.0f);
                pstmt.setInt(3, quantity);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}