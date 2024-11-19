package com.inventorysystem.controller;

import com.inventorysystem.dao.DeliveryDao;
import com.inventorysystem.model.Delivery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeliveryController", urlPatterns = {"/deliveries", "/delivery"})
public class DeliveryController extends HttpServlet {
    private DeliveryDao deliveryDao;

    @Override
    public void init() throws ServletException {
        deliveryDao = new DeliveryDao();
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
                    deleteDelivery(request, response);
                    break;
                default:
                    listDeliveries(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listDeliveries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        request.setAttribute("deliveries", deliveries);
        RequestDispatcher dispatcher = request.getRequestDispatcher("delivery-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("delivery-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Delivery delivery = deliveryDao.getDelivery(id);
        request.setAttribute("delivery", delivery);
        RequestDispatcher dispatcher = request.getRequestDispatcher("delivery-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String salesDate = request.getParameter("salesDate");

        Delivery delivery = new Delivery();
        delivery.setSalesDate(salesDate);

        if (id == null || id.isEmpty()) {
            deliveryDao.saveDelivery(delivery);
        } else {
            delivery.setDeliveryId(Integer.parseInt(id));
            deliveryDao.updateDelivery(delivery);
        }
        response.sendRedirect("deliveries");
    }

    private void deleteDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        deliveryDao.deleteDelivery(id);
        response.sendRedirect("deliveries");
    }
}
