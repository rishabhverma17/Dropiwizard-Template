/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.configuration;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

@Getter
@Setter
public class YAMLConfig extends Configuration {

  @JsonProperty("appConfig")
  private AppConfig appConfig;

  @JsonProperty("mongoDBConfig")
  private MongoConfig mongoConfig;
}
