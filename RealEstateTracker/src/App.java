import bond.Bond;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import assetinterfaces.*;

import javax.sound.sampled.Port;

import static assetinterfaces.AssetUtilities.simulation2xlsx;

public class App {
    
    public static void main(String[] args) throws Exception {        
        out.println("All tests complete!");
        test_sim_2_xlsx();
    }
    
    
    public static void test_sim_2_xlsx() {
        Portfolio port = new Portfolio("Portfolio 1");
        Bond bond1 = new Bond("Bond 1", 100.0, 100.0, 1.0, .1);
        Bond bond2 = new Bond("Bond 2", 5.0, 10.0, 1.0, .1);

        port.add_asset(bond1);
        port.add_asset(bond2);
        
        SimulatedPortfolio sim = new SimulatedPortfolio(port, 14);
        
        simulation2xlsx(sim);
    }
}