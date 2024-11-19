package com.inventorysystem.controller;

import com.inventorysystem.dao.WarehouseDao;
import com.inventorysystem.model.Warehouse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WarehouseController", urlPatterns = {"/warehouses", "/warehouse"})
public class WarehouseController extends HttpServlet {
    private WarehouseDao warehouseDao;

    @Override
    public void init() throws ServletException {
        warehouseDao = new WarehouseDao();
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
                    deleteWarehouse(request, response);
                    break;
                default:
                    listWarehouses(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listWarehouses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Warehouse> warehouses = warehouseDao.getAllWarehouses();
        request.setAttribute("warehouses", warehouses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("warehouse-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("warehouse-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Warehouse warehouse = warehouseDao.getWarehouse(id);
        request.setAttribute("warehouse", warehouse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("warehouse-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String warehouseName = request.getParameter("warehouseName");
        String isRefrigerated = request.getParameter("isRefrigerated");

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseName);
        warehouse.setIsRefrigerated(Boolean.parseBoolean(isRefrigerated));

        if (id == null || id.isEmpty()) {
            warehouseDao.saveWarehouse(warehouse);
        } else {
            warehouse.setWarehouseId(Integer.parseInt(id));
            warehouseDao.updateWarehouse(warehouse);
        }
        response.sendRedirect("warehouses");
    }

    private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        warehouseDao.deleteWarehouse(id);
        response.sendRedirect("warehouses");
    }
}
