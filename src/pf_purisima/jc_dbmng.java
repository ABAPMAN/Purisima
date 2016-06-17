/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pf_purisima;
import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LhaaR
 */
public class jc_dbmng {   
    //Variables de coneccion
    private MysqlDataSource ds;
    private Connection con;
//    private ResultSet rs;
    
    //Asigna los datos de la bd que se va a conectar
    public jc_dbmng(String host, String uname, String upass, String dbname) {
        ds = new MysqlDataSource();
        ds.setServerName(host);
        ds.setUser(uname);
        ds.setPassword(upass);
        ds.setDatabaseName(dbname);
    }
    
    private boolean connect(jc_log log){
        boolean r = true;
        try{
            con = ds.getConnection();
        }
        catch(SQLException ex){
            jc_error error = new jc_error(ex.getErrorCode(),ex.getMessage());
            log.add(error);
            r = false;
        }
        return r;
    }
    
    private void close_con(jc_log log){
        try {
            con.close();
        } catch (SQLException ex) {
            jc_error error = new jc_error(ex.getErrorCode(),ex.getMessage());
            log.add(error);
        }
    }
    
    public DefaultTableModel execute(String qry, jc_log log){
        //Variables a utilizar
        Statement stmt = null;
        ResultSet rs = null;
        DefaultTableModel data = null;
        //Conexion
        if (connect(log)) {
            try {
                //Consulta
                stmt = con.createStatement();
                rs = stmt.executeQuery(qry);
                //Creacion de modelo de tabla
                data = buildTableModel(rs);
            } 
            catch (SQLException ex) {
                jc_error error = new jc_error(ex.getErrorCode(),ex.getMessage());
                log.add(error);
            }
            try{
                //Cerrar conexiones
                rs.close();
                stmt.close();
                close_con(log);
            } 
            catch (SQLException ex) {
                jc_error error = new jc_error(ex.getErrorCode(),ex.getMessage());
                log.add(error);
            }
        }
        return data;
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // Nombres de las columnas
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // datos de la tabla
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    
    
}
