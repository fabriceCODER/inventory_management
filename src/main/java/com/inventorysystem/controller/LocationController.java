package com.inventorysystem.controller;

import com.inventorysystem.dao.LocationDao;
import com.inventorysystem.model.Location;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LocationController", urlPatterns = {"/locations", "/location"})
public class LocationController extends HttpServlet {
    private LocationDao locationDao;

    @Override
    public void init() throws ServletException {
        locationDao = new LocationDao();
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
                    deleteLocation(request, response);
                    break;
                default:
                    listLocations(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listLocations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Location> locations = locationDao.getAllLocations();
        request.setAttribute("locations", locations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("location-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("location-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocation(id);
        request.setAttribute("location", location);
        RequestDispatcher dispatcher = request.getRequestDispatcher("location-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String locationName = request.getParameter("locationName");
        String locationAddress = request.getParameter("locationAddress");

        Location location = new Location();
        location.setLocationName(locationName);
        location.setLocationAddress(locationAddress);

        if (id == null || id.isEmpty()) {
            locationDao.saveLocation(location);
        } else {
            location.setLocationId(Integer.parseInt(id));
            locationDao.updateLocation(location);
        }
        response.sendRedirect("locations");
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        locationDao.deleteLocation(id);
        response.sendRedirect("locations");
    }
}

