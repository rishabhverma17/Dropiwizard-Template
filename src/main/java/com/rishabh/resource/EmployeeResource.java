/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.resource;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.wordnik.swagger.annotations.Api;

import com.rishabh.dao.MongoService;
import com.rishabh.model.Employee;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "employee", description = "Employee Resource for performing CRUD operations on employees Collection")
public class EmployeeResource {

  private MongoCollection<Document> collection;
  private final MongoService mongoService;

  public EmployeeResource(MongoCollection<Document> collection, MongoService mongoService) {
    this.collection = collection;
    this.mongoService = mongoService;
  }

  @POST
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createEmployee(@NotNull @Valid final Employee employee) {
    Gson gson = new Gson();
    String json = gson.toJson(employee);
    mongoService.insertOne(collection, new Document(BasicDBObject.parse(json)));
    Map<String, String> response = new HashMap<>();
    response.put("message", "Employee created successfully");
    return Response.ok(response).build();
  }

  @POST
  @Timed
  @Path("/createEmployees")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createEmployees(@NotNull final List<Employee> employees) {
    List<Document> employeeDocuments = new ArrayList<>();
    Gson gson = new Gson();
    String json;
    for (Employee employee : employees) {
      json = gson.toJson(employee);
      employeeDocuments.add(new Document(BasicDBObject.parse(json)));
    }
    mongoService.insertMany(collection, employeeDocuments);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Employees created successfully");
    return Response.ok(response).build();
  }

  @GET
  @Timed
  public Response getEmployees() {
    List<Document> documents = mongoService.find(collection);
    return Response.ok(documents).build();
  }

  @GET
  @Timed
  @Path("{name}")
  public Response getEmployee(@PathParam("name") final String name) {
    List<Document> documents = mongoService.findByKey(collection, "name", name);
    return Response.ok(documents).build();
  }

  @GET
  @Timed
  @Path("/salary/sort")
  public Response getEmployee() {
    List<Document> documents = mongoService.findByCriteria(collection, "salary", 25000, 1000, 1);
    return Response.ok(documents).build();
  }

  @PUT
  @Timed
  public Response editEmployee(@NotNull @Valid final Employee employee) {
    mongoService.updateOneEmployee(collection, "name", "department", "salary", employee);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Employee with Name: " + employee.getName() + " updated successfully");
    return Response.ok(response).build();
  }

  @DELETE
  @Timed
  @Path("{name}")
  public Response deleteEmployee(@PathParam("name") final String name) {
    mongoService.deleteOne(collection, "name", name);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Employee with Name: " + name + " deleted successfully");
    return Response.ok(response).build();
  }
}
