package com.epf.rentmanager;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Test {
    public static void main(String[] args){
        try {
            System.out.println(ClientService.getInstance().findAll());
        }catch (ServiceException e){
            e.printStackTrace();
        }

    }
}
