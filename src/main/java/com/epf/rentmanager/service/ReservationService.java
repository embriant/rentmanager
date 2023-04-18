package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }


    public long create(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(int id) throws ServiceException {
        if (id<0){
            throw new ServiceException("l'id est inférieur à 0");
        }
        try{
            return reservationDao.findResaByClientId(id);
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
            return reservationDao.findResaByVehicleId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try{
            return reservationDao.findAll();
        }catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int count() throws ServiceException {
        return findAll().size();
    }

    public Reservation findById(long id) throws ServiceException {
        // TODO: récupérer un client par son id
        if (id < 0) {
            throw new ServiceException("l'id est inférieur à 0");
        }
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
