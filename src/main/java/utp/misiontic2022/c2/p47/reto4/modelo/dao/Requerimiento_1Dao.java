package utp.misiontic2022.c2.p47.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utp.misiontic2022.c2.p47.reto4.modelo.vo.Requerimiento_1;
import utp.misiontic2022.c2.p47.reto4.util.JDBCUtilities;

public class Requerimiento_1Dao {

  public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
    ArrayList<Requerimiento_1> consultas1BS = new ArrayList<>();
    Requerimiento_1 consulta1BS = null;

    String sql =
      "SELECT DISTINCT L.Nombre || ' ' || L.Primer_Apellido AS 'Lider',L.Cargo, COUNT(P.ID_Proyecto) AS 'Proyectos' FROM Lider AS L INNER JOIN Proyecto AS P ON P.ID_Lider = L.ID_Lider WHERE P.Constructora = 'Arquitectura S.A.' GROUP BY L.ID_Lider ORDER BY Cargo, Lider";

    try (
      Connection conn = JDBCUtilities.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
    ) {
      while (rs.next()) {
        consulta1BS = new Requerimiento_1();

        consulta1BS.setLider(rs.getString("Lider"));
        consulta1BS.setCargo(rs.getString("Cargo"));
        consulta1BS.setProyectos(rs.getInt("Proyectos"));

        consultas1BS.add(consulta1BS);
      }
    }
    return consultas1BS;
  }
}
