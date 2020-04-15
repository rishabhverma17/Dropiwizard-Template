/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.model;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class Employee {
  @JsonProperty
  private String Name;

  @JsonProperty
  private String Salary;

  @JsonProperty
  private String Department;
}
