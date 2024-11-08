
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Game extends RentItem implements MenuActions{
    
    private Calendar fecha;
    private ArrayList<String> especificaciones;

    public Game(int code, String name, double precio) {
        super(code, name, precio);
        especificaciones = new ArrayList<>();
        fecha = Calendar.getInstance();
        precioRenta = 20;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        String formattedDate = sdf.format(fecha.getTime());
        return super.toString() + " Fecha Publicacion: " + formattedDate + " - PS3 Game";
    }

    public void setFechaPublicacion(int year, int mes, int dia) {
        fecha.set(year, mes - 1, dia);
    }

    public void listEspecificaciones() {
        if (especificaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay especificaciones.");
        } else {
            String especificacionesTexto = "Especificaciones:\n" + listEspecificacionesRecursivo(0);
            JOptionPane.showMessageDialog(null, especificacionesTexto);
        }
    }

    private String listEspecificacionesRecursivo(int index) {
        if (index >= especificaciones.size()) {
            return "";
        }
        return "- " + especificaciones.get(index) + "\n" + listEspecificacionesRecursivo(index + 1);
    }

    public void actualizarFechaPublicacion() {
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicacion: "));
            if(year<=1958 || year>2024){
                JOptionPane.showMessageDialog(null, "ENTRADA NO VALIDA, SOLO SE PUEDE INGRESAR AÑO de 1958 a 2024.","Dato los videojuegos se crearon en el año 1958",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de publicacion (1-12): "));
            if(mes <1 || mes>12 ){
                JOptionPane.showMessageDialog(null, "ENTRADA NO VALIDA");
                return;
            }
            int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dia de publicacion: "));
            if(dia<1 || dia>31){
                JOptionPane.showMessageDialog(null, "ENTRADA NO VALIDA ","SOLO DIAS DEL 1 AL 31",JOptionPane.ERROR_MESSAGE);
                return;
            }

            setFechaPublicacion(year, mes, dia);
            JOptionPane.showMessageDialog(null, "Fecha de publicacion actualizada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dato no valido, ingrese de nuevo.");
        }
    }

    @Override
    public double pagoRenta(int dias) {
        return dias * 20;
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> actualizarFechaPublicacion();
            case 2 -> agregarEspecificacion();
            case 3 -> listEspecificaciones();
            default -> JOptionPane.showMessageDialog(null, "Opcion no valida.");
        }
    }

    @Override
    public void submenu() {
        JOptionPane.showMessageDialog(null,
                "Opciones de Juego:\n1. Actualizar Fecha de Publicacion\n2. Agregar Especificacion\n3. Ver Especificaciones");
    }

    public void agregarEspecificacion() {
        String especificacion = JOptionPane.showInputDialog("Ingrese la especificacion:");
        if (especificacion != null && !especificacion.isEmpty()) {
            especificaciones.add(especificacion);
            JOptionPane.showMessageDialog(null, "Especificacion agregada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Especificacion no valida.");
        }
    }
}