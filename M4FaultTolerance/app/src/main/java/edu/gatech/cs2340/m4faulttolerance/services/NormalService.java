package edu.gatech.cs2340.m4faulttolerance.services;

public class NormalService extends SimulatedService {

    public NormalService(String req) {
        super(req);
    }

    @Override
    public String executeTheService() {
        //simulate some processing
        for(int i = 0; i < 1000; ++i) ;

        return "This executed normally";
    }

}
