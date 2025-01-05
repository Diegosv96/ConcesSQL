package modeloDAO;

import database.DBConnection;
import database.SchemaDB;
import modelo.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochesDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private String query;

    public CochesDAO(Connection con){
        connection = con;
    }
    public void insertarCoche(Coche coche) throws SQLException {
        query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_MAT,SchemaDB.COL_CH_MAR, SchemaDB.COL_CH_MOD, SchemaDB.COL_CH_COL);
        ps = connection.prepareStatement(query);
        ps.setString(1,coche.getMatricula());
        ps.setString(2,coche.getMarca());
        ps.setString(3,coche.getModelo());
        ps.setString(4,coche.getColor());

        ps.execute();
    }

    public void eliminarCoche(int id) throws SQLException {
        query = String.format("DELETE FROM %s WHERE %s=?",
                SchemaDB.TAB_CH,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ps.execute();
    }

    public Coche buscarCoche(int id) throws SQLException {
        query = String.format("SELECT * FROM %s WHERE %s=? LIMIT 1",
                SchemaDB.TAB_CH,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setInt(1,id);
        resultSet = ps.executeQuery();

        if(resultSet.next()){
            int idRes = resultSet.getInt(SchemaDB.COL_ID);
            String matricula = resultSet.getString(SchemaDB.COL_CH_MAT);
            String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
            String color = resultSet.getString(SchemaDB.COL_CH_COL);
            return new Coche(idRes,matricula,marca,modelo,color);
        }
        return null;
    }

    public ArrayList<Coche> listaCoches() throws SQLException {
        query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_CH);
        ps = connection.prepareStatement(query);
        resultSet = ps.executeQuery();

        ArrayList<Coche> coches = new ArrayList<>();

        while(resultSet.next()){
            int idRes = resultSet.getInt(SchemaDB.COL_ID);
            String matricula = resultSet.getString(SchemaDB.COL_CH_MAT);
            String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
            String color = resultSet.getString(SchemaDB.COL_CH_COL);
            coches.add(new Coche(idRes,matricula,marca,modelo,color));
        }

        return coches;
    }

    public void updateCoche(Coche coche) throws SQLException {
        query = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=? WHERE %s=?",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_COL, SchemaDB.COL_CH_MAT, SchemaDB.COL_CH_MAR, SchemaDB.COL_CH_MOD,
                SchemaDB.COL_ID);
        ps = connection.prepareStatement(query);
        ps.setString(1,coche.getColor());
        ps.setString(2,coche.getMatricula());
        ps.setString(3,coche.getMarca());
        ps.setString(4,coche.getModelo());
        ps.setInt(5,coche.getId());

        ps.execute();
    }
}
