package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.Clients;
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

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Utils utils;
    private Reservations reservationsUtils = new Reservations();
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.setAttribute("clients",clientService.findAll());
            request.setAttribute("vehicles",vehicleService.findAll());
        }catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Reservation reservation = new Reservation();
            reservation.setVehicle_id(Integer.valueOf(request.getParameter("car")));
            reservation.setClient_id(Integer.valueOf(request.getParameter("client")));
            reservation.setDebut(utils.readDate(request.getParameter("begin")));
            reservation.setFin(utils.readDate(request.getParameter("end")));
            if (reservationsUtils.lessThan7Days(reservation) && reservationsUtils.lessThan30Days(reservation)){
            reservationService.create(reservation);}
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

    }

}
