package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService = VehicleService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String manufacturer = request.getParameter("manufacturer");
            int seats = Integer.valueOf(request.getParameter("seats"));
            Vehicle vehicle = new Vehicle();
            vehicle.setConstructeur(manufacturer);
            vehicle.setId(vehicleService.count()+1);
            vehicle.setNb_places(seats);
            vehicleService.create(vehicle);
        } catch (ServiceException e) {
        throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

}
}
