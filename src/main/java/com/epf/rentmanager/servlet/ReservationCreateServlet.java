package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
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
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int client = Integer.valueOf(request.getParameter("client"));
            int vehicle = Integer.valueOf(request.getParameter("car"));
            LocalDate debut = LocalDate.parse(request.getParameter("begin"));
            LocalDate fin = LocalDate.parse(request.getParameter("end"));
            Reservation reservation = new Reservation();
            reservation.setVehicle_id(vehicle);
            reservation.setClient_id(client);
            reservation.setDebut(debut);
            reservation.setFin(fin);
            reservationService.create(reservation);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

    }

}
