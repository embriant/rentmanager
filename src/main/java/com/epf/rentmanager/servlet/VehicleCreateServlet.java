package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.Reservations;
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

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {
    Utils utils;
    private Vehicles vehiclesUtils = new Vehicles();
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String constructeur = request.getParameter("manufacturer");
            int nb_places = utils.readInt(request.getParameter("seats"));
            long id = vehicleService.count() + 1;
            Vehicle vehicle = new Vehicle(id, constructeur, nb_places);
            if (vehiclesUtils.validVehicle(vehicle)){
            vehicleService.create(vehicle);}
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

}
}
