package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    private final List<Vehiculo> listaVehiculos;

    public Vehiculos() {
        listaVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(listaVehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws NullPointerException, OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (listaVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        listaVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) throws NullPointerException {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede buscar un vehículo nulo.");
        }
        for (Vehiculo v : listaVehiculos) {
            if (Objects.equals(v, vehiculo)) {
                return v;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws NullPointerException, OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        if (!listaVehiculos.remove(vehiculo)) {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
    }

}
