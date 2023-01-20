import bond.Bond;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import assetinterfaces.*;
import stock.*;

import javax.sound.sampled.Port;

import static assetinterfaces.AssetUtilities.simulation2xlsx;

public class App {
    
    public static void main(String[] args) throws Exception {        
        //test_sim_2_xlsx();
        //test_stock1();
        //test_stock2();
        //test_stock3();
        
        // TODO: Create a special realestate2xlsx function
        // so that you can track market value, too.

        out.println("All tests complete!");
    }
    
    
    
    
    
    
    
    
    public static void test_stock1() {
        // No dividends
        
        Stock stock = new Stock("Stock 1", 2.0, 5.0);
        
        SimulatedAsset sim = stock.create_simulation(14);
        
        simulation2xlsx(sim);
    }
    
    public static void test_stock2() {
        // With dividends, not reinvesting
        
        Stock stock = new Stock("Stock 2", 2.0, 5.0);
        stock.set_dividends_amount(8);
        
        SimulatedAsset sim = stock.create_simulation(14);
        simulation2xlsx(sim);
    }
    
    public static void test_stock3() {
        // With dividends and reinvesting
        
        Stock stock = new Stock("Stock 2", 2.0, 5.0);
        
        stock.set_dividends_amount(8);
        stock.set_dividend_reinvesting_fraction(.50);
        
        SimulatedAsset sim = stock.create_simulation(14);
        
        simulation2xlsx(sim);
    }
    
    
    public static void test_sim_2_xlsx() {
        Portfolio port = new Portfolio("Portfolio 1");
        Bond bond1 = new Bond("Bond 1", 100.0, 100.0, 1.0, .1);
        Bond bond2 = new Bond("Bond 2", 5.0, 10.0, 1.0, 0);

        port.add_asset(bond1);
        port.add_asset(bond2);
        
        SimulatedPortfolio sim = new SimulatedPortfolio(port, 14);
        
        simulation2xlsx(sim);
        
        simulation2xlsx(bond2.create_simulation(14));
    }
    
    
}