package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, reservation.getClient_id());
			ps.setInt(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
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
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(DELETE_RESERVATION_QUERY);
			ps.setInt(1,(int) reservation.getId());
			ps.execute();
			ps.close();
			connection.close();
			return reservation.getId();
		} catch (SQLException e) {
			throw new DaoException();
		}	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try{
			Connection connection =ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstatement.setLong(1, clientId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				long id = rs.getLong("id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,clientId,vehicle_id, debut, fin));
			}

			return reservations;

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try{
			Connection connection =ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstatement.setLong(1, vehicleId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				long id = rs.getLong("id");
				int client_id = rs.getInt("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client_id,vehicleId, debut, fin));
			}

			return reservations;

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}		}

	public Reservation findById(long id) throws DaoException {
		//p22
		try{
			Connection connection =ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			pstatement.setLong(1, id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			int client_id = rs.getInt("client_id");
			int vehicle_id = rs.getInt("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			Reservation reservation = new Reservation(id, client_id, vehicle_id,debut,fin);

			return reservation;

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while(rs.next()){
				long id = rs.getLong("id");
				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, debut, fin));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return reservations;
	}	}

