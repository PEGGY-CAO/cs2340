package edu.gatech.cs2340.m4faulttolerance.services;

public class BadService extends SimulatedService {

    public BadService(String req) {
        super(req);
    }

    @Override
    public String executeTheService(){
        throw new RuntimeException("Bad Service Call!");

    }
}
