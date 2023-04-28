package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
//import com.epf.rentmanager.validator.ValidateurClient;
import com.epf.rentmanager.utils.Reservations;
import com.epf.rentmanager.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/rents/delete")
public class ReservationDeleteServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Utils utils;
    private Reservations reservationsUtils = new Reservations();
    @Autowired
    ReservationService reservationService;
    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException
    {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Reservation reservation = reservationService.findById(id);
            reservationService.delete(reservation);
            request.setAttribute("rents", reservationService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../rents");    }




}