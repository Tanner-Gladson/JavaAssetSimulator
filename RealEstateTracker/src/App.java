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
    }
    
    
    public static void test_sim_2_xlsx() {
        Portfolio port = new Portfolio("Port 1");
        
        Bond bond1 = new Bond("Bond 1", );
        
        
        
        
        simulation2xlsx();
    }
}