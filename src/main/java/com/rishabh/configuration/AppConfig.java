/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.configuration;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class AppConfig {
  @JsonProperty
  private String appName;

  @JsonProperty
  private String message;

  @JsonProperty
  private int messageRepetitions;
}
