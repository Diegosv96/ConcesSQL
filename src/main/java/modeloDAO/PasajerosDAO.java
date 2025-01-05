package modeloDAO;

import database.DBConnection;
import database.SchemaDB;
import modelo.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PasajerosDAO {

    private Connection connection;
    private PreparedStatement ps;
    private String query;
    public PasajerosDAO(Connection con){
        this.connection = con;
    }

    public void insertPasajero(Pasajero pasajero, int cocheId) throws SQLException {
        query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_NOM, SchemaDB.COL_ED, SchemaDB.COL_PES, SchemaDB.FK_COCH);
        ps = connection.prepareStatement(query);
        ps.setString(1,pasajero.getNombre());
        ps.setInt(2,pasajero.getEdad());
        ps.setInt(3,pasajero.getPeso());
        ps.setInt(4,cocheId);

        ps.execute();
    }

    public void deletePasajero(int id) throws SQLException {
        query = String.format("DELETE FROM %s WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1,id);

        ps.execute();
    }

    public Pasajero selectPasajero(int id) throws SQLException {
        query = String.format("SELECT * FROM %s WHERE %s=? LIMIT 1",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1,id);

        ResultSet resultSet = ps.executeQuery();

        if(resultSet.next()){
            Pasajero pas = new Pasajero();
            pas.setId(resultSet.getInt(SchemaDB.COL_ID));
            pas.setNombre(resultSet.getString(SchemaDB.COL_NOM));
            pas.setEdad(resultSet.getInt(SchemaDB.COL_ED));
            pas.setPeso(resultSet.getInt(SchemaDB.COL_PES));
            pas.setCocheId(resultSet.getInt(SchemaDB.FK_COCH));
            return pas;
        }
        return null;
    }

    public List<Pasajero> selectAllPasajeros() throws SQLException{
        query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PAS);
        ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Pasajero> lista = new LinkedList<>();

        while(rs.next()){
            Pasajero pas = new Pasajero(
                    rs.getInt(SchemaDB.COL_ID),
                    rs.getInt(SchemaDB.COL_ED),
                    rs.getInt(SchemaDB.COL_PES),
                    rs.getString(SchemaDB.COL_NOM),
                    rs.getInt(SchemaDB.FK_COCH)
            );
            lista.add(pas);
        }
        return lista;
    }

    public void updatePasajeroFK(int pasajeroId, int cocheId) throws SQLException {
        query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.FK_COCH,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1,cocheId);
        ps.setInt(2,pasajeroId);

        ps.execute();
    }

    public void updatePasajeroFKNull(int pasajeroId) throws SQLException {
        query = String.format("UPDATE %s SET %s=null WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.FK_COCH,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1,pasajeroId);

        ps.execute();
    }

    public List<Pasajero> selectPasajerosCoche(int cocheId) throws SQLException {
        query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.FK_COCH);
        ps = connection.prepareStatement(query);
        ps.setInt(1,cocheId);
        ResultSet result = ps.executeQuery();

        List<Pasajero> pasajeros = new LinkedList<>();
        while (result.next()){
            Pasajero pas = new Pasajero(
                    result.getInt(SchemaDB.COL_ID),
                    result.getInt(SchemaDB.COL_ED),
                    result.getInt(SchemaDB.COL_PES),
                    result.getString(SchemaDB.COL_NOM),
                    result.getInt(SchemaDB.FK_COCH)
            );
            pasajeros.add(pas);
        }
        return pasajeros;
    }
}
