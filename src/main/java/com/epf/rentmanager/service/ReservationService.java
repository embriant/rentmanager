package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = reservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        try {
            return ReservationDao.getInstance().create(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(int id) throws ServiceException {
        if (id<0){
            throw new ServiceException("l'id est inférieur à 0");
        }
        try{
            return ReservationDao.getInstance().findResaByClientId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(int id) throws ServiceException {
        if (id<0){
            throw new ServiceException("l'id est inférieur à 0");
        }
        try{
            return ReservationDao.getInstance().findResaByVehicleId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try{
            return ReservationDao.getInstance().findAll();
        }catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int count() throws ServiceException {
        return findAll().size();
    }
}
