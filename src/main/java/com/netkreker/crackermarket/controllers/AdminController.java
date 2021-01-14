package com.netkreker.crackermarket.controllers;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class AdminController {

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_PLAIN)
    public String adminPage() {
        return "admin";
    }
}
