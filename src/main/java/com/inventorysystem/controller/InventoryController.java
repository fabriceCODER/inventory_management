package com.inventorysystem.controller;

import com.inventorysystem.dao.InventoryDao;
import com.inventorysystem.model.Inventory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventoryController", urlPatterns = {"/inventories", "/inventory"})
public class InventoryController extends HttpServlet {
    private InventoryDao inventoryDao;

    @Override
    public void init() throws ServletException {
        inventoryDao = new InventoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteInventory(request, response);
                    break;
                default:
                    listInventories(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listInventories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Inventory> inventories = inventoryDao.getAllInventories();
        request.setAttribute("inventories", inventories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inventory-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("inventory-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Inventory inventory = inventoryDao.getInventory(id);
        request.setAttribute("inventory", inventory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inventory-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String quantityAvailable = request.getParameter("quantityAvailable");
        String minimumStockLevel = request.getParameter("minimumStockLevel");
        String maximumStockLevel = request.getParameter("maximumStockLevel");
        String reorderPoint = request.getParameter("reorderPoint");

        Inventory inventory = new Inventory();
        inventory.setQuantityAvailable(Integer.parseInt(quantityAvailable));
        inventory.setMinimumStockLevel(Integer.parseInt(minimumStockLevel));
        inventory.setMaximumStockLevel(Integer.parseInt(maximumStockLevel));
        inventory.setReorderPoint(Integer.parseInt(reorderPoint));

        if (id == null || id.isEmpty()) {
            inventoryDao.saveInventory(inventory);
        } else {
            inventory.setInventoryId(Integer.parseInt(id));
            inventoryDao.updateInventory(inventory);
        }
        response.sendRedirect("inventories");
    }

    private void deleteInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventoryDao.deleteInventory(id);
        response.sendRedirect("inventories");
    }
}
