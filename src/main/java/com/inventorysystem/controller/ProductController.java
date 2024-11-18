package com.inventorysystem.controller;

import com.inventorysystem.dao.ProductDao;
import com.inventorysystem.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() {
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productList", productDao.getAllProducts());
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/product-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productCode = request.getParameter("productCode");
        BigDecimal packedWeight = new BigDecimal(request.getParameter("packedWeight"));

        Product product = new Product();
        product.setProductName(productName);
        product.setProductCode(productCode);
        product.setPackedWeight(packedWeight);

        productDao.saveProduct(product);
        response.sendRedirect("products");
    }
}
