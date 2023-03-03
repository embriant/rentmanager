package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}

	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}

		return instance;
	}


	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			return ClientDao.getInstance().create(client);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		if (id < 0) {
			throw new ServiceException("l'id est inférieur à 0");
		}
		try {
			return ClientDao.getInstance().findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}


	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}

	}

	public int count() throws ServiceException {
		return findAll().size();
	}
}

