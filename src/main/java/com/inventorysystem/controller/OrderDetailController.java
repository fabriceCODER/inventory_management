package com.inventorysystem.controller;

import com.inventorysystem.dao.OrderDetailDao;
import com.inventorysystem.model.OrderDetail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/orderDetails", "/orderDetail"})
public class OrderDetailController extends HttpServlet {
    private OrderDetailDao orderDetailDao;

    @Override
    public void init() throws ServletException {
        orderDetailDao = new OrderDetailDao

