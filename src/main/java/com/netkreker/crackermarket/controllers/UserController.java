package com.netkreker.crackermarket.controllers;


import com.netkreker.crackermarket.models.user.User;
import com.netkreker.crackermarket.services.UserService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("/all")
    public Response getAllUsers(){
        List<User> users = userService.findAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam(value = "id") String id) {
        if(userService.findById(id) == null) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("Error", String.format("User with id: '%s' not found", id));
            return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        }
        return Response.status(Response.Status.FOUND).entity(userService.findById(id)).build();
    }

    @GET
    public Response getUserByUsername(@QueryParam(value = "username") String username) {
        if(userService.findByUsername(username) == null) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("Error", String.format("User with username: '%s' not found", username));
            return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        }
        return Response.status(Response.Status.FOUND).entity(userService.findByUsername(username)).build();
    }

    @GET
    public Response findByEmail(@QueryParam(value = "email") String email) {
        if(userService.findByEmail(email) == null) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("Error", String.format("User with email: '%s' not found", email));
            return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        }
        return Response.ok(userService.findByEmail(email)).build();
    }

    @POST
    @Path("/save")
    public Response saveUser(User user) {
        if(userService.findByUsername(user.getUsername()) != null) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("Error", String.format("User with username: '%s' already exists", user.getUsername()));
            return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
        }
        userService.persist(user);
        return Response.ok(userService.findByUsername(user.getUsername())).build();
    }

}
