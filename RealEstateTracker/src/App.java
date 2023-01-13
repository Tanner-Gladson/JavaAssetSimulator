import assetclasses.*;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.sound.sampled.Port;

public class App {
    
    public static void main(String[] args) throws Exception {        
        out.println("All tests complete!");
         test_portfolio();
    }
    
    public static void test_portfolio() {
        test1();
    }
    
    
    public static void test1() {
        out.println("Beggining test 1");
        
        // Test if you can get correct init equity, liab, and asset val from
        // a portfolio.
        Bond bond1 = new Bond("Bond1", 10.0, 10.0, 1.0, .1);
        Bond bond2 = new Bond("Bond2", 20.0, 30.0, 1.0, .1);
        
        Portfolio myPort = new Portfolio();
        myPort.add_asset(bond1);
        myPort.add_asset(bond2);
        
        SimulatedPortfolio portsim = new SimulatedPortfolio("Port1", myPort, 14);
        
        out.println(portsim.init_asset_value);
        out.println();
        
        for (int i = 0; i < 14; i++) {
            out.println(portsim.capital_gains_ledger.get(i));
        }
        
        out.println("-- completed test");
    }
    
}