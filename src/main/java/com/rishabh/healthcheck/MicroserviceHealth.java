/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class MicroserviceHealth extends HealthCheck {
  @Override
  protected Result check() throws Exception {
    try {
      return Result.healthy("This Microservice is working fine.");
    } catch (Exception e) {
      return Result.unhealthy(e);
    }
  }
}
