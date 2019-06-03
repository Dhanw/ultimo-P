/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.API;

import Activos.Logic.Solicitud;
import Activos.Models.Model_Solicitud;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author wizard
 */
@Path("/Solicitud")
public class SolicitudRest {

    @Context
    HttpServletRequest request;
    Model_Solicitud modelSolicitud = new Model_Solicitud();

    @PUT
    @Path("/aceptar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void aceptarSolicitud(Solicitud s) {
        s.setEstado(Solicitud.POR_VERIFICAR);
        s.setMotivoRechazo("");
        modelSolicitud.setestadoSolicitud(s);
    }

    @PUT
    @Path("/rechazar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void rechazarSolicitud(Solicitud s) {
        s.setEstado(Solicitud.RECHAZADA);
        s.setMotivoRechazo(s.getMotivoRechazo());
        modelSolicitud.setestadoSolicitud(s);
    }
}
