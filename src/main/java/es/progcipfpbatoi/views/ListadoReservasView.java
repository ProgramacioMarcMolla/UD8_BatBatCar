/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.views;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
import java.util.List;

/**
 *
 * @author mark
 */
public class ListadoReservasView {

    private final Reserva reserva;

    private static final int ANCHO_TABLA = 70;

    public ListadoReservasView(Reserva reserva) {
        this.reserva = reserva;
    }

    private AsciiTable buildASCIITableReserva() {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*");
        view.addRule();
        view.addRow(null, "Reserva con cógigo " + this.reserva.getCodigo());
        view.addRule();
        view.addRow("Usuario", this.reserva.getNombreUsuario());
        view.addRule();
        view.addRow("Plazas", this.reserva.getPlazasSolicitadas());
        view.addRule();
        view.setTextAlignment(TextAlignment.CENTER);
        return view;
    }

    

    @Override
    public String toString() {
        return buildASCIITableReserva().render(ANCHO_TABLA);
    }

    public void visualizarReserva() {
        GestorIO.print(buildASCIITableReserva().render(ANCHO_TABLA));
    }

    

}
