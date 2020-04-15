/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh;

import org.bson.Document;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.rishabh.configuration.YAMLConfig;
import com.rishabh.dao.MongoService;
import com.rishabh.healthcheck.MicroserviceHealth;
import com.rishabh.resource.DemoResource;
import com.rishabh.resource.EmployeeResource;
import com.rishabh.util.MongoManaged;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApplicationMain extends Application<YAMLConfig> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);

  public static void main(final String[] args) throws Exception {
    new ApplicationMain().run(args);
  }

  @Override
  public void run(YAMLConfig configuration, Environment environment) throws Exception {
    environment.jersey().register(new AbstractBinder() {
      @Override
      public void configure() {
        bind(configuration).to(YAMLConfig.class);
      }
    });
    setUpMongoDB(configuration, environment);
    setJerseyConfiguration(environment);
    System.out.println("Hello world, by Dropwizard Microservice!");
    for (int i = 0; i < configuration.getAppConfig().getMessageRepetitions(); i++) {
      System.out.println(configuration.getAppConfig().getMessage());
    }
  }

  @Override
  public void initialize(final Bootstrap<YAMLConfig> bootstrap) {
    /* Below code helps in overriding YAML file values from Dev.env file, development.yml and dev.env must be passed as parameter in build.gradle file */
    bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
  }

  public void setJerseyConfiguration(Environment env) {
    final MicroserviceHealth microserviceHealth = new MicroserviceHealth();
    env.healthChecks().register("Microsevice HealthCheck", microserviceHealth);
    env.jersey().register(DemoResource.class);
  }

  public void setUpMongoDB(YAMLConfig configuration, Environment environment) {
    MongoClient mongoClient = new MongoClient(configuration.getMongoConfig().getMongoHost(), configuration.getMongoConfig().getMongoPort());
    MongoManaged mongoManaged = new MongoManaged(mongoClient);
    environment.lifecycle().manage(mongoManaged);
    MongoDatabase db = mongoClient.getDatabase(configuration.getMongoConfig().getMongoDB());
    MongoCollection<Document> collection = db.getCollection(configuration.getMongoConfig().getCollectionName());
    environment.jersey().register(new EmployeeResource(collection, new MongoService()));
  }

}
