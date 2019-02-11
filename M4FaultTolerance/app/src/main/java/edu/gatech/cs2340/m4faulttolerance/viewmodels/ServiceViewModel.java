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

        try {
            badService.executeTheService();
            badService.executeTheService();
            badService.executeTheService();
            return normalService.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String makeBadRequest(String request) {
        SimulatedService service = new BadService(request);
        try {
            return service.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Bad result";
    }

    public String makeSlowRequest(String request) {
        SimulatedService service = new SlowService(request);
        try {
            return service.executeTheService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Bad Result";
    }

    public String makeRetryRequest(String request) {
        SimulatedService service = new RetryService(request);
        try {
            return service.executeTheService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Bad Result";
    }
}
