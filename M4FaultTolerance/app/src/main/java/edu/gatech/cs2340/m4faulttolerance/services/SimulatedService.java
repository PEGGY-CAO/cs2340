package edu.gatech.cs2340.m4faulttolerance.services;


public abstract class SimulatedService {

   protected String request;

   public SimulatedService(String req) {
      request = req;
   }

   public abstract String executeTheService();
}

