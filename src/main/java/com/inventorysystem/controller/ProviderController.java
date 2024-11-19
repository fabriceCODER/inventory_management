package com.inventorysystem.controller;


import com.inventorysystem.dao.ProviderDao;
import com.inventorysystem.model.Provider;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProviderController", urlPatterns = {"/providers", "/provider"})
public class ProviderController extends HttpServlet {
    private ProviderDao providerDao;

    @Override
    public void init() throws ServletException {
        providerDao = new ProviderDao();
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
                    deleteProvider(request, response);
                    break;
                default:
                    listProviders(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProviders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Provider> providers = providerDao.getAllProviders();
        request.setAttribute("providers", providers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("provider-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("provider-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Provider provider = providerDao.getProvider(id);
        request.setAttribute("provider", provider);
        RequestDispatcher dispatcher = request.getRequestDispatcher("provider-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String providerName = request.getParameter("providerName");
        String providerAddress = request.getParameter("providerAddress");

        Provider provider = new Provider();
        provider.setProviderName(providerName);
        provider.setProviderAddress(providerAddress);

        if (id == null || id.isEmpty()) {
            providerDao.saveProvider(provider);
        } else {
            provider.setProviderId(Integer.parseInt(id));
            providerDao.updateProvider(provider);
        }
        response.sendRedirect("providers");
    }

    private void deleteProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        providerDao.deleteProvider(id);
        response.sendRedirect("providers");
    }
}

