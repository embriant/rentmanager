package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.Reservations;
import com.epf.rentmanager.utils.Utils;
import org.h2.engine.SysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/rents/edit")
public class ReservationEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Utils utils;
    private Reservations reservationsUtils = new Reservations();
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("rent", reservationService.findById(id));
            request.setAttribute("vehicles", vehicleService.findAll());
            request.setAttribute("clients", clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Reservation reservation = new Reservation();
            reservation.setId(Integer.valueOf(request.getParameter("id")));
            reservation.setClient_id(Integer.valueOf(request.getParameter("client")));
            reservation.setVehicle_id(Integer.valueOf(request.getParameter("car")));
            reservation.setDebut(utils.readDate(request.getParameter("begin")));
            reservation.setFin(utils.readDate(request.getParameter("end")));
            request.setAttribute("rent", reservation);
            request.setAttribute("vehicles", vehicleService.findAll());
            request.setAttribute("clients", clientService.findAll());
            if (reservationsUtils.lessThan7Days(reservation) && reservationsUtils.lessThan30Days(reservation)){
                reservationService.edit(reservation);}        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);

    }
}