package edu.gatech.cs2340.m4faulttolerance.services;

public class SlowService extends SimulatedService {


    public SlowService(String req) {
        super(req);
    }

    @Override
    public String executeTheService() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Finally got a response!";
    }
}
