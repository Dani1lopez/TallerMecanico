package org.iesalandalus.programacion.tallermecanico.modelo.dominio.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private final List<Cliente> listaClientes;

    public Clientes() {
        listaClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(listaClientes);
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede insertar un cliente nulo.");
        }
        if (listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        listaClientes.add(cliente);
    }




    public boolean modificar(Cliente cliente, String nuevoNombre, String nuevoTelefono) throws NullPointerException, OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede modificar un cliente nulo.");
        }
        if (!listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            cliente.setNombre(nuevoNombre);
        }
        if (nuevoTelefono != null && !nuevoTelefono.isBlank()) {
            cliente.setTelefono(nuevoTelefono);
        }
        return true;
    }


    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No se puede buscar un cliente nulo.");
        }
        if (listaClientes.contains(cliente)) {
            int indice = listaClientes.indexOf(cliente);
            return listaClientes.get(indice);
        }
        return null;
    }


    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede borrar un cliente nulo.");
        }
        if (listaClientes.contains(cliente)) {
            listaClientes.remove(cliente);
        } else {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
    }

}

