/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import com.rishabh.configuration.YAMLConfig;
import com.rishabh.model.DirectoryModel;

@Path("/v1/resource")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

  @Inject
  private YAMLConfig yamlConfig;

  @GET
  @Timed
  @Path("/{firstName}")
  public DirectoryModel getMessage(@PathParam("firstName") String firstName) {
    final String value = String.format("Hi!, " + firstName);
    return new DirectoryModel(yamlConfig.getAppConfig().getMessage(), value);
  }
}
