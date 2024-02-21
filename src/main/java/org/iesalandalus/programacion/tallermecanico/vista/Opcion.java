package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar Cliente"),
    BUSCAR_CLIENTE(2, "Buscar Cliente"),
    BORRAR_CLIENTE(3, "Borrar Cliente"),
    MODIFICAR_CLIENTE(4, "Modificar Cliente"),
    INSERTAR_VEHICULO(5,"Insertar Vehiculo"),
    BUSCAR_VEHICULO(6, "Buscar Vehiculo"),
    BORRAR_VEHICULO(7, "Borrar Vehiculo"),
    LISTAR_VEHICULOS(8, "Listar Vehiculos"),
    INSERTAR_REVISION(9, "Insertar Revision"),
    BUSCAR_REVISION(10, "Buscar Revision"),
    BORRAR_REVISION(11, "Borrar Revision"),
    LISTAR_REVISIONES(12, "Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(13, "Listar Revisiones Cliente"),
    LISTAR_REVISIONES_VEHICULO(14, "Listar Revisiones Vehiculo"),
    ANADIR_HORAS_REVISION(15, "Añadir Horas Revision"),
    ANADUIR_PRECIO_MATERIAL_REVISION(15, "Añadir Precio Material Revision"),
    CERRAR_REVISION(16, "Cerrar Revision"),
    SALIR(17, "Salir");

    private int numeroOpcion;
    private String mensaje;
    static final Map<Integer, Opcion> opciones;

    static {
        opciones = new HashMap<>();
        for (int i = 0; i < Opcion.values().length; i++) {
            opciones.put(i, Opcion.values()[i]);
        }
    }
    private Opcion(int numeroOpcion, String mensaje) {
        this.mensaje = mensaje;
        esValida(numeroOpcion);
    }
    public static boolean esValida(int numeroOpcion) {
        if (!opciones.containsKey(numeroOpcion)) {
            throw new IllegalArgumentException("Numero no valido.");
        }
        return true;
    }
    public static Opcion get(int numeroOpcion) {
        return opciones.get(numeroOpcion);
    }
    public String toString() {
        return String.format ("%d-%s", this.numeroOpcion, this.mensaje);
    }
}