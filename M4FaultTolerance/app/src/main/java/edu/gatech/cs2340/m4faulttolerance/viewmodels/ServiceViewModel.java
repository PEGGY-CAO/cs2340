package edu.gatech.cs2340.m4faulttolerance.viewmodels;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import edu.gatech.cs2340.m4faulttolerance.services.BadService;
import edu.gatech.cs2340.m4faulttolerance.services.NormalService;
import edu.gatech.cs2340.m4faulttolerance.services.RetryService;
import edu.gatech.cs2340.m4faulttolerance.services.SimulatedService;
import edu.gatech.cs2340.m4faulttolerance.services.SlowService;
//import io.github.jolly.*;
import io.github.jolly.circuitbreaker.CircuitBreakerPolicy;
import io.github.jolly.circuitbreaker.CircuitBreakerPolicyBuilder;
import io.github.jolly.timeout.TimeoutPolicy;
import io.github.jolly.timeout.TimeoutPolicyBuilder;
import io.github.jolly.fallback.FallbackPolicy;
import io.github.jolly.fallback.FallbackPolicyBuilder;
import io.github.jolly.retry.RetryPolicy;
import io.github.jolly.retry.RetryPolicyBuilder;



/**
 * View Model for our fault tolerant app
 */
public class ServiceViewModel extends AndroidViewModel {

    /**
     * Make a new view model,  system should call this automatically for us
     * @param application
     */
    public ServiceViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Normal request for service, always succeeds
     *
     * @param request  the service request
     *
     * @return whatever comes back from the service
     */
    public String makeNormalRequest(String request) {
        SimulatedService normalService = new NormalService(request);
        SimulatedService badService = new BadService(request);

        CircuitBreakerPolicy<String> pol = new CircuitBreakerPolicyBuilder<>()
                .rateThreshold(100)
                .sizeRingBufferHalfOpen(2)
                .sizeRingBufferClosed(4)
                .build();


        try {
            //badService.executeTheService();
            pol.exec(badService::executeTheService);
            //badService.executeTheService();
            //badService.executeTheService();
//            return normalService.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //badService.executeTheService();
            pol.exec(badService::executeTheService);
//            badService.executeTheService();
//            badService.executeTheService();
//            return normalService.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //badService.executeTheService();
            pol.exec(badService::executeTheService);
//            badService.executeTheService();
//            badService.executeTheService();
//            return normalService.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return normalService.executeTheService();
    }

    public String makeBadRequest(String request) {
        SimulatedService service = new BadService(request);
        SimulatedService normalService = new NormalService(request);
        FallbackPolicy<String> pol = new FallbackPolicyBuilder<>(normalService::executeTheService)
                .build();

        try {

            String res = pol.exec(service::executeTheService);
            return res;
            //return service.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Bad result";
    }

    public String makeSlowRequest(String request) {
        SimulatedService service = new SlowService(request);

        TimeoutPolicy pol = new TimeoutPolicyBuilder()
                .duration(1000)
                .cancelFuture(false)
                .build();


        try {
            pol.exec(service::executeTheService);

            //return result;
            return "Timeout Happened";
        } catch (Exception e) {

            e.printStackTrace();
            //return "Timeout happened";
        }
        return "Bad Result";
    }

    public String makeRetryRequest(String request) {
        SimulatedService service = new RetryService(request);
        RetryPolicy pol = new RetryPolicyBuilder()
                .attempts(4)
                .waitDuration(500)
                .build();

        try {
            return (String)pol.exec(service::executeTheService);
            //return service.executeTheService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Bad Result";
    }
}
