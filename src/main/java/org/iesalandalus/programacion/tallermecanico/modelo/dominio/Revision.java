package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }
    public Revision(Revision revision) {
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente.getNombre(),revision.cliente.getDni(), revision.cliente.getTelefono());
        vehiculo = revision.vehiculo;
        horas = revision.horas;
        fechaFin = revision.fechaFin;
        fechaInicio = revision.fechaInicio;
        precioMaterial = revision.precioMaterial;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }


    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }else if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }


    public int getHoras() {
        return horas;
    }

    private void setHoras(int horas) {
        if (horas < 0) {
            throw new IllegalArgumentException("Las horas no pueden ser negativas.");
        }
        this.horas = horas;
    }


    public float getPrecioMaterial() {
        return precioMaterial;
    }

    private void setPrecioMaterial(float precioMaterial) {
        if (precioMaterial < 0) {
            throw new IllegalArgumentException("El precio del material no puede ser negativo.");
        }
        this.precioMaterial = precioMaterial;
    }


    public void anadirHoras(int horas) throws OperationNotSupportedException{
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }



    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }




    public float getPrecio() {
        float precioFijo = PRECIO_DIA * getDias() + PRECIO_HORA * getHoras();
        float precioEspecificado = PRECIO_MATERIAL * precioMaterial;
        return precioFijo + precioEspecificado;
    }
    private float getDias() {
       return (estaCerrada()) ? ChronoUnit.DAYS.between(fechaInicio,fechaFin) : 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
    @Override
    public String toString() {
        String cadenaADevolver;
        if (!estaCerrada()) {
            cadenaADevolver = String.format("%s - %s: (%s - ), %d horas, %.2f € en material", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas, getPrecio());
        } else {
            cadenaADevolver = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, getPrecioMaterial(), getPrecio());
        }
        return cadenaADevolver;
    }




}
