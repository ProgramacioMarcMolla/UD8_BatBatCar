package es.progcipfpbatoi.views;

/**
 * Vista dedicada a los listados de viajes. De cada viaje se muestra su código,
 * ruta, precio, propietario, tipo de viaje, plazas disponibles y si está
 * cancelado.
 */
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
import java.util.List;

public class ListadoViajesView {

    private final List<Viaje> viajes;

    private static final int ANCHO_TABLA = 100;

    public ListadoViajesView(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    private AsciiTable buildASCIITable() {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*", "*", "*", "*", "*", "*", "*","*");
        view.addRule();
        view.addRow(null, null, null, null, null,null, null, null, "Listado Viajes");
        view.addRule();
        view.addRow("Cod. Viaje", null, "Ruta","Fecha salida", "Precio", "Propietario", "Tipo", "Plazas Disponibles", "Cancelado");
        view.addRule();
        generarFilasViajes(view);
        view.setTextAlignment(TextAlignment.CENTER);
        return view;
    }

    @Override
    public String toString() {
        return buildASCIITable().render(ANCHO_TABLA);
    }

    public void visualizar() {
        GestorIO.print(buildASCIITable().render(ANCHO_TABLA));
    }
    
    public void visualizarReservasViajes() {
        GestorIO.print(buildASCIITableReservaDeViajes().render(ANCHO_TABLA));
    }

    private void generarFilasViajes(AsciiTable tabla) {
        for (Viaje viaje : this.viajes) {
        tabla.addRow(viaje.getCodigo(), null, viaje.getRuta(),viaje.getFechaToString(), viaje.getPrecio(), (String)viaje.getUsernamePropietario(), viaje.getTIPO(), viaje.getPlazasDisponibles(), viaje.getIsCancelado()? "Sí" : "No");
        tabla.addRule(); 
    }

    }
    
    private AsciiTable buildASCIITableReservaDeViajes() {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*", "*", "*", "*","*");
        view.addRule();
        view.addRow(null, null, null, null, null,"Reservas de viajes");
        view.addRule();
        view.addRow("Cod. Reserva", "Cod. Viaje", "Propietario Viaje", null, "Plazas Reservadas","fecha");
        view.addRule();
        generarFilasReservasViajes(view);
        view.setTextAlignment(TextAlignment.CENTER);
        return view;
    }

    private void generarFilasReservasViajes(AsciiTable view) {
        for (Viaje viaje : this.viajes){
            for(Reserva reserva: viaje.getReservas()){
                view.addRow(reserva.getCodigo(),viaje.getCodigo(), viaje.getUsernamePropietario(), null, reserva.getPlazasSolicitadas(),reserva.getFechaToString());
                view.addRule();
            }
        }
    }

}
