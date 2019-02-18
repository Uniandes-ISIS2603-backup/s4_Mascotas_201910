/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;


import co.edu.uniandes.csw.mascotas.dtos.ArticuloDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("articulos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ArticuloResource {
    
    private static final Logger LOGGER = Logger.getLogger(ArticuloResource.class.getName());
    
    @POST
    public ArticuloDTO crearArticulo(ArticuloDTO articulo){
     
        return articulo;
    }
    
    @GET 
    public List<ArticuloDTO> darArticulos(){
        //TODO
        return null;
    }
   
     @GET 
     @Path("{id: \\d+}")
    public ArticuloDTO darArticuloPorId(@PathParam("id") Long id){
        //TODO
        return null;
    }
    
    @DELETE
     @Path("{id: \\d+}")
    public void eliminarArticuloPorId(@PathParam("id") Long id){
        
    }
    
    
    
}