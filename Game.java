
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Game extends RentItem implements MenuActions {
      
    Calendar fecha;
    ArrayList<String>  especificaciones;
    
    public Game(int code, String name, double precio){
        super(code,name,precio);
        especificaciones=new ArrayList<>();
        fecha=Calendar.getInstance();
        precioRenta=20;
    }
    
    public String toString(){
       return super.toString()+" Fecha Publicacion: "+fecha+"-PS3 Game";
    }
    
    public void setFechaPublicacion(int year,int mes,int dia){
        fecha.set(year, mes-1, dia);
    }
    
    public void listEspecificaciones() {
        listEspecificacionesRecursiva(0); 
    }
    
    private void listEspecificacionesRecursiva(int index) {
        if (index < especificaciones.size()) {
            System.out.println(especificaciones.get(index));
            listEspecificacionesRecursiva(index + 1); 
        }
    }
    
    public void actualizarFechaPublicacion() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el año de publicación: ");
        int year = scanner.nextInt();
        
        System.out.print("Ingrese el mes de publicación (1-12): ");
        int mes = scanner.nextInt();
        
        System.out.print("Ingrese el día de publicación: ");
        int dia = scanner.nextInt();
        
        setFechaPublicacion(year, mes, dia);
        System.out.println("Fecha de publicación actualizada correctamente.");
    }
    
    public double pagoRenta(int dias) {
        return dias * 20;
    }
    
    public void ejecutarOpcion(int opcion) {
        switch(opcion) {
            case 1:
              actualizarFechaPublicacion();  
              break;
            case 2:
                agregarEspecificacion();
                break;
            case 3:
                listEspecificaciones(); 
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    
    
    public void submenu() {
        System.out.println("1. Actualizar Fecha de Publicación");
        System.out.println("2. Agregar Especificación");
        System.out.println("3. Ver Especificaciones");
    }
    
    public void agregarEspecificacion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la especificación: ");
        String especificacion = scanner.nextLine();
        especificaciones.add(especificacion);
        System.out.println("Especificación agregada correctamente.");
    }
    
}
