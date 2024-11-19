package com.inventorysystem.controller;

import com.inventorysystem.dao.TransferDao;
import com.inventorysystem.model.Transfer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TransferController", urlPatterns = {"/transfers", "/transfer"})
public class TransferController extends HttpServlet {
    private TransferDao transferDao;

    @Override
    public void init() throws ServletException {
        transferDao = new TransferDao();
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
                    deleteTransfer(request, response);
                    break;
                default:
                    listTransfers(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listTransfers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transfer> transfers = transferDao.getAllTransfers();
        request.setAttribute("transfers", transfers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transfer transfer = transferDao.getTransfer(id);
        request.setAttribute("transfer", transfer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String transferQuantity = request.getParameter("transferQuantity");
        String sentDate = request.getParameter("sentDate");
        String receivedDate = request.getParameter("receivedDate");

        Transfer transfer = new Transfer();
        transfer.setTransferQuantity(Integer.parseInt(transferQuantity));
        transfer.setSentDate(sentDate);
        transfer.setReceivedDate(receivedDate);

        if (id == null || id.isEmpty()) {
            transferDao.saveTransfer(transfer);
        } else {
            transfer.setTransferId(Integer.parseInt(id));
            transferDao.updateTransfer(transfer);
        }
        response.sendRedirect("transfers");
    }

    private void deleteTransfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        transferDao.deleteTransfer(id);
        response.sendRedirect("transfers");
    }
}
