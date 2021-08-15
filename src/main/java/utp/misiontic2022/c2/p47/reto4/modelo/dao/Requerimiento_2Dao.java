package utp.misiontic2022.c2.p47.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utp.misiontic2022.c2.p47.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p47.reto4.util.JDBCUtilities;

public class Requerimiento_2Dao {

  public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
    ArrayList<Requerimiento_2> consultas2BS = new ArrayList<>();
    Requerimiento_2 consulta2BS = null;

    String sql =
      "SELECT MC.ID_MaterialConstruccion, MC.Nombre_Material, C.Cantidad, MC.Precio_Unidad, (C.Cantidad * MC.Precio_Unidad) AS 'Precio_Total' FROM Compra AS C INNER JOIN MaterialConstruccion AS MC ON MC.ID_MaterialConstruccion = C.ID_MaterialConstruccion INNER JOIN Proyecto AS P ON P.ID_Proyecto  = C.ID_Proyecto WHERE P.ID_Proyecto IN (10,14,23,24,38,50,29) ORDER BY P.ID_Proyecto ASC, MC.Precio_Unidad DESC";

    try (
      Connection conn = JDBCUtilities.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
    ) {
      while (rs.next()) {
        consulta2BS = new Requerimiento_2();

        consulta2BS.setIdMaterial(rs.getInt("ID_MaterialConstruccion"));
        consulta2BS.setNombreMaterial(rs.getString("Nombre_Material"));
        consulta2BS.setCantidad(rs.getInt("Cantidad"));
        consulta2BS.setPrecioUnidad(rs.getInt("Precio_Unidad"));
        consulta2BS.setPrecioTotal(rs.getInt("Precio_Total"));

        consultas2BS.add(consulta2BS);
      }
    }
    return consultas2BS;
  }
}
