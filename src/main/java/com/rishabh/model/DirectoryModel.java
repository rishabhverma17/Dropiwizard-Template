/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.model;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class DirectoryModel {
  public DirectoryModel(String content, String message) {
    this.content = content;
    this.message = message;
  }

  @JsonProperty
  private String content;

  @JsonProperty
  private String message;

  public DirectoryModel() {
    // Jackson deserialization
  }
}
