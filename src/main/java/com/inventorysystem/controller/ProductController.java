package com.inventorysystem.controller;

import com.inventorysystem.dao.ProductDao;
import com.inventorysystem.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = {"/products", "/product"})
public class ProductController extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("search") != null ? request.getParameter("search") : "";
        List<Product> products;

        if (!searchTerm.isEmpty()) {
            products = productDao.searchProducts(searchTerm);
        } else {
            products = productDao.getPaginatedProducts(page, size);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }


    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.getProduct(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productCode = request.getParameter("productCode");
        String productDescription = request.getParameter("productDescription");

        Product product = new Product();
        product.setProductName(productName);
        product.setProductCode(productCode);
        product.setProductDescription(productDescription);

        if (id == null || id.isEmpty()) {
            productDao.saveProduct(product);
        } else {
            product.setProductId(Integer.parseInt(id));
            productDao.updateProduct(product);
        }
        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDao.deleteProduct(id);
        response.sendRedirect("products");
    }
}

