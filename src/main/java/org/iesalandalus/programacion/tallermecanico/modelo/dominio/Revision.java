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
    }
    public Revision(Revision revision) {
        if (revision == null) {
            throw new IllegalArgumentException("La revisión no puede ser nula.");
        }
        this.cliente = new Cliente(revision.getCliente());
        this.vehiculo = revision.getVehiculo();
        this.fechaInicio = revision.getFechaInicio();
        this.fechaFin = revision.getFechaFin();
        this.horas = revision.getHoras();
        this.precioMaterial = revision.getPrecioMaterial();
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo");
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
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la actual");
        }
        this.fechaInicio = fechaInicio;
    }


    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if (fechaFin.isEqual(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la fecha de inicio");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser posterior a la fecha actual");
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


    public void anadirHoras(int horas) {
        if (fechaFin != null) {
            throw new IllegalStateException("La revisión está cerrada, no se pueden añadir horas.");
        }
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }



    public float anadirPrecioMaterial(float precioMaterial) {
        Objects.requireNonNull(precioMaterial, "El precio material no puede ser nulo");
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
        return this.precioMaterial;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (this.fechaFin != null) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (fechaFin.isEqual(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la fecha de inicio");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }




    public float getPrecio() {
        if (estaCerrada()) {
            throw new IllegalStateException("La revisión ya está cerrada.");
        }
        return horas * PRECIO_HORA + precioMaterial * PRECIO_MATERIAL;
    }
    private float getDias() {
        long horasEnTaller = fechaInicio.until(fechaFin, ChronoUnit.HOURS);
        float diasEnTaller = (float) horasEnTaller / 24.0f;
        return diasEnTaller;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(cliente, revision.cliente) &&
                Objects.equals(vehiculo, revision.vehiculo) &&
                Objects.equals(fechaInicio, revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
    @Override
    public String toString() {
        String cadenaCliente = cliente.toString();
        String cadenaVehiculo = vehiculo.toString();
        String cadenaInicio = fechaInicio.format(FORMATO_FECHA);
        String cadenaFin = (fechaFin != null) ? fechaFin.format(FORMATO_FECHA) : "";

        String cadena = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material",
                cadenaCliente, cadenaVehiculo, cadenaInicio, cadenaFin, horas, precioMaterial);

        if (fechaFin != null) {
            cadena += String.format(", %.2f € total", getPrecio());
        }

        return cadena;
    }





}
