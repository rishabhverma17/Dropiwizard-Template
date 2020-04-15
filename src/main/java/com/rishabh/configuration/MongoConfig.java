/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.configuration;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class MongoConfig {
  @JsonProperty
  private String mongoHost;

  @JsonProperty
  private int mongoPort;

  @JsonProperty
  private String mongoDB;

  @JsonProperty
  private String collectionName;
}
