package com.netkreker.crackermarket.controllers;


import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/admin")
public class AdminController {

    @Inject
    Template admin;

    @GET
    @Produces(MediaType.TEXT_HTML)
//    @Authenticated
    @RolesAllowed("admin")
    public TemplateInstance adminPage() {
        String a = "";

        return admin.data("username", "sadasd");
    }
}
