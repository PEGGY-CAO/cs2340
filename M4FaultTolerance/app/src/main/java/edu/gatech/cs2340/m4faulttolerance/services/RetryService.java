package edu.gatech.cs2340.m4faulttolerance.services;

public class RetryService extends SimulatedService {

    private int count = 0;

    public RetryService(String req) {
        super(req);
    }

    @Override
    public String executeTheService() {
        count++;
        if (count < 3) throw new RuntimeException("Bad Retry Result");
        return "Success from RetryService";
    }
}
