package realestate;

import assetinterfaces.SimulatedAsset;
import assetinterfaces.*;
import java.util.ArrayList;

public class SimulatedRealEstate extends SimulatedAsset {
    RealEstate config;
    
    public SimulatedRealEstate(RealEstate config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    @Override
    public void simulate_month() {
        super.simulate_month();
    }
    
    
}
