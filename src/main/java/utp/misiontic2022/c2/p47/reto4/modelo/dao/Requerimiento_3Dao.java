package utp.misiontic2022.c2.p47.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p47.reto4.modelo.vo.Requerimiento_3;
import utp.misiontic2022.c2.p47.reto4.util.JDBCUtilities;

public class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        ArrayList<Requerimiento_3> consultas3BS = new ArrayList<>();
    Requerimiento_3 consulta3BS = null;

    String sql ="SELECT DISTINCT P.ID_Proyecto, P.Ciudad, P.Clasificacion, SUM(C.Cantidad * MC.Precio_Unidad) AS 'Costo_Proyecto' FROM Proyecto AS P INNER JOIN MaterialConstruccion AS MC ON MC.ID_MaterialConstruccion = C.ID_MaterialConstruccion INNER JOIN Compra AS C ON C.ID_Proyecto = P.ID_Proyecto WHERE P.Ciudad IN ('Monteria','Santa Marta') GROUP BY P.ID_Proyecto HAVING SUM(C.Cantidad * MC.Precio_Unidad) > 70000 ORDER BY P.Ciudad , P.Clasificacion, P.ID_Proyecto";

    try (
      Connection conn = JDBCUtilities.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
    ) {
      while (rs.next()) {
        consulta3BS = new Requerimiento_3();

        consulta3BS.setIdProyecto(rs.getInt("ID_Proyecto"));
        consulta3BS.setCiudad(rs.getString("Ciudad"));
        consulta3BS.setClasificacion(rs.getString("Clasificacion"));
        consulta3BS.setCostoProyecto(rs.getInt("Costo_Proyecto"));

        consultas3BS.add(consulta3BS);
      }
    }
    return consultas3BS;
    }
}