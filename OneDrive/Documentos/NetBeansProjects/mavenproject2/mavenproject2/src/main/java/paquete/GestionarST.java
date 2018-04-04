package paquete;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionarST {

    public static ArrayList<DocentePAAE> getAllDocentes() {
        ArrayList<DocentePAAE> arr = null;
        try {
            PostgresAccess dao = new PostgresAccess();

            arr = dao.getAllDocentes_BD();
            if (arr.isEmpty()) {
                System.out.println("NO hubo resultados");
            }
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(arr.get(i).getNombre());
            }

        } catch (Exception ex) {
            Logger.getLogger(GestionarST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    

    


    public static double calcularCosto(Lugar lgr, String personas) {
        double aux0 = Integer.parseInt(personas);
        double aux = Double.parseDouble(lgr.getPrecio());
        return (aux * aux0);
    }
}
