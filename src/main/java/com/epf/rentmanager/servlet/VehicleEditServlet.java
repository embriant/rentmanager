package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
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

@WebServlet("/vehicles/edit")
public class VehicleEditServlet extends HttpServlet {
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
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("vehicle",vehicleService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            String manufacturer = request.getParameter("manufacturer");
            int seats = utils.readInt(request.getParameter("seats"));
            Vehicle vehicle = new Vehicle();
            vehicle.setConstructeur(manufacturer);
            vehicle.setId(id);
            vehicle.setNb_places(seats);
            if (vehiclesUtils.validVehicle(vehicle)){
                vehicleService.edit(vehicle);}
            request.setAttribute("vehicle", vehicle);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);

}
}
