
package hola;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Movie extends RentItem {
    Calendar fecha;
    boolean estreno;
    
public Movie(int code, String name, double precio){
    super(code, name, precio);
    fecha = Calendar.getInstance();
}

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }


 public String getEstado() {
        int diferencia = (Calendar.getInstance().get(Calendar.YEAR) - fecha.get(Calendar.YEAR)) * 12 + Calendar.getInstance().get(Calendar.MONTH) - fecha.get(Calendar.MONTH);
        return diferencia > 3 ? "NORMAL" : "ESTRENO";
        
        
    }
 
 

    @Override
    public double pagoRenta(int dias){
         double pago = getPrecioRenta();
          String estado = getEstado();
          if (estado.equals("ESTRENO") && dias > 2) {
            pago += 50 * (dias - 2);
            
        }else if   (estado.equals("NORMAL") && dias > 5) {
            pago += 30 * (dias - 5);
            }

        return pago;
        
        
        
    }
    
    
    
    
     @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar date = getFecha();
        String formattedDate = sdf.format(fecha.getTime());
        return super.toString() + " Fecha de Estreno: " + formattedDate + " -Movie";
    }
    
}
