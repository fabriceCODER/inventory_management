package com.inventorysystem.controller;

import com.inventorysystem.dao.OrderDetailDao;
import com.inventorysystem.model.OrderDetails;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/orderDetails", "/orderDetail"})
public class OrderDetailController extends HttpServlet {
    private OrderDetailDao orderDetailDao;

    @Override
    public void init() throws ServletException {
        orderDetailDao = new OrderDetailDao();
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
                    deleteOrderDetail(request, response);
                    break;
                default:
                    listOrderDetails(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderDetail> orderDetails = orderDetailDao.getAllOrderDetails();
        request.setAttribute("orderDetails", orderDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetail-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetail-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDetails orderDetail = orderDetailDao.getOrderDetail(id);
        request.setAttribute("orderDetails", orderDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetail-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String quantityOrdered = request.getParameter("quantityOrdered");
        String priceEach = request.getParameter("priceEach");

        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setQuantityOrdered(Integer.parseInt(quantityOrdered));
        orderDetail.setPriceEach(Double.parseDouble(priceEach));

        if (id == null || id.isEmpty()) {
            orderDetailDao.saveOrderDetail(orderDetail);
        } else {
            orderDetail.setOrderDetailId(Integer.parseInt(id));
            orderDetailDao.updateOrderDetail(orderDetail);
        }
        response.sendRedirect("orderDetails");
    }

    private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDetailDao.deleteOrderDetail(id);
        response.sendRedirect("orderDetails");
    }
}
