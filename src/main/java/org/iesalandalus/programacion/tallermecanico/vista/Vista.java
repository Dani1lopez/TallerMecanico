package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo");
        this.controlador = controlador;
    }
    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
        } while (opcion != Opcion.SALIR);
    }
    public void terminar() {
        System.out.print("Hasta luego!!!");
    }
    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case SALIR -> salir();
            case INSERTAR_CLIENTE -> insertarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_REVISION -> borrarRevision();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case BUSCAR_REVISION -> buscarRevision();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case CERRAR_REVISION -> cerrarRevision();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case LISTAR_REVISIONES -> listarRevisiones();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADUIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterialRevision();
            default -> throw new IllegalArgumentException("La opción no es valida");
        }
    }

    private void anadirPrecioMaterialRevision() {
        Consola.mostrarCabecera("AÑADIR PRECIO MATERIAL REVISION");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL VEHICULO");
        List<Revision> revisionesVehiculos = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (revisionesVehiculos.isEmpty()) {
            System.out.println("La lista de revisiones esta vacia");
        }
        System.out.println(revisionesVehiculos);
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL CLIENTE");
        List<Revision> revisionesClientes = controlador.getRevisiones(Consola.leerClienteDni());
        if (revisionesClientes.isEmpty()) {
            System.out.println("La lista de revisiones esta vacia");
        }
        System.out.println(revisionesClientes);
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("MODIFICAR CLIENTE");
        boolean modificado = true;
        try {
            modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (modificado) {
            System.out.println("El cliente se ha modificado con exito");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("LISTAR REVISIONES");
        List<Revision> revisiones = controlador.getRevisiones();
        if (revisiones.isEmpty()) {
            System.out.println("La lista de revisiones esta vacia");
        }
        System.out.println(revisiones);
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("INSERTAR VEHICULO");
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("INSERTAR REVISION");
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("LISTAR VEHICULOS");
        List<Vehiculo> listaVehivulos = controlador.getVehiculos();
        if(listaVehivulos.isEmpty()){
            System.out.println("La lista de vehiculos ");
        } else{
            System.out.println(listaVehivulos);
        }
    }

    private void cerrarRevision() {
    }

    private void buscarVehiculo() {
    }

    private void buscarRevision() {
    }

    private void borrarVehiculo() {
    }

    private void borrarRevision() {
    }

    private void buscarCliente() {
    }

    private void borrarCliente() {
    }

    private void insertarCliente() {
    }

    private void salir() {
    }
}