package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {
	
	private ClientDao() {}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		//voir p19 du cours
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);

			ps.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}


		public long delete(Client client) throws DaoException {
		//p17
			try {
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement ps =
						connection.prepareStatement(DELETE_CLIENT_QUERY);
				ps.setInt(1,(int) client.getId());
				ps.execute();

				ps.close();
				connection.close();
				return client.getId();
			} catch (SQLException e) {
				throw new DaoException();
			}
	}

	public Client findById(long id) throws DaoException {
		//p22
		try{
			Connection connection =ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			pstatement.setLong(1, id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate date = rs.getDate("naissance").toLocalDate();
			Client client = new Client(id, nom, prenom,email,date);

			return client;

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
			while(rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate date = rs.getDate("naissance").toLocalDate();

				clients.add(new Client(id, nom, prenom, email, date));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return clients;
	}
}


