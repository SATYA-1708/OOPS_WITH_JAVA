package com.bank.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAO.AccountDetails;

@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            AccountDAO dao = new AccountDAO();

            if (action.equals("add")) {
                int accNo = Integer.parseInt(req.getParameter("accNo"));
                String accountType = req.getParameter("accountType");
                double balance = Double.parseDouble(req.getParameter("amount"));
                
                dao.addAccount(accNo, accountType, balance);
                req.setAttribute("message", "Account Created Successfully!");
                req.setAttribute("accNo", accNo);
                req.setAttribute("accountType", accountType);
                req.setAttribute("balance", balance);
                req.setAttribute("action", "add");
            } else if (action.equals("details")) {
                int accNo = Integer.parseInt(req.getParameter("accNo"));
                AccountDetails details = dao.getAccountDetails(accNo);
                
                req.setAttribute("accountNumber", details.getAccountNumber());
                req.setAttribute("accountType", details.getAccountType());
                req.setAttribute("balance", details.getBalance());
                req.setAttribute("action", "details");
            } else {
                int accNo = Integer.parseInt(req.getParameter("accNo"));
                double amount = 0;

                if (!action.equals("check")) {
                    amount = Double.parseDouble(req.getParameter("amount"));
                }

                if (action.equals("deposit")) {
                    dao.deposit(accNo, amount);
                    req.setAttribute("message", "Amount Deposited Successfully!");
                } else if (action.equals("withdraw")) {
                    dao.withdraw(accNo, amount);
                    req.setAttribute("message", "Amount Withdrawn Successfully!");
                } else if (action.equals("check")) {
                    req.setAttribute("message", "Balance Inquired Successfully!");
                }

                double balance = dao.getBalance(accNo);
                req.setAttribute("balance", balance);
                req.setAttribute("accNo", accNo);
                req.setAttribute("action", action);
            }

            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, res);
        }
    }
}