package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.Clients;
import com.epf.rentmanager.utils.Utils;
import com.epf.rentmanager.utils.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {
    Utils utils;
    private Vehicles vehiclesUtils = new Vehicles();
    private static final long serialVersionUID = 1L;
    @Autowired
    ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Vehicle vehicle = vehicleService.findById(id);
            vehiclesUtils.delete(vehicle);
            request.setAttribute("vehicles", vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../cars");    }
}
