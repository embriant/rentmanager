package com.epf.rentmanager;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

    }
}
