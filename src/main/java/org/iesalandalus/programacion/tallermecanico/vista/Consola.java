package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class Consola {
    private String CADENA_FORMATO_FECHA ="\\d{2}/\\d{2}/\\d{4}";

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("_".repeat(mensaje.length()));
    }
    public static void mostrarMenu() {
        mostrarCabecera("----OPCIONES----");
        for (int i = 0; i < Opcion.values().length; i++) {
            System.out.println(Opcion.values()[i]);
        }
    }
    private static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }
    private static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }
    private static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }
    private static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        String fechaTexto = null;
        while (fecha == null) {
            System.out.println(mensaje);
            System.out.println("Introduce una fecha en el formato dd/MM/yyyy");
            fechaTexto = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaTexto, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no valida.");
            }
        }
        return fecha;
    }
    public static Opcion elegirOpcion() {
        int numeroOpcion;
        while (true) {
            System.out.print("Introduce un numero entero: ");
            numeroOpcion = Entrada.entero();
            if (Opcion.esValida(numeroOpcion)) {
                return Opcion.opciones.get(numeroOpcion);
            } else {
                System.out.println("Opcion no valida. Vuelva a intentarlo");
            }
        }
    }
    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String dni = leerCadena("Introduce el dni del cliente: ");
        String telefono = leerCadena("Introduce el telefono del cliente: ");
        return new Cliente(nombre, dni, telefono);
    }
    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduce el dni: ");
        return Cliente.get(dni);
    }
    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre del cliente: ");
    }
    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo telefono del cliente: ");
    }
    public static Vehiculo leerVehiculo() {
        String matricula = leerCadena("Introduce la matricula del vehiculo: ");
        String marca = leerCadena("Introduce la marca del vehiculo: ");
        String modelo = leerCadena("Introduce el modelo del vehiculo: ");
        return new Vehiculo(marca, modelo, matricula);
    }
    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matricula del vehiculo: ");
        return Vehiculo.get(matricula);
    }
    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la revision: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }
    public static int leerHoras() {
        return leerEntero("Introduce el numero de horas que ha durado la revision: ");
    }
    public static float leerPrecioMaterial() {
        System.out.print("Introduce el precio real del material utilizado: ");
        return Entrada.real();
    }
    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha final de la revision: ");
    }
}