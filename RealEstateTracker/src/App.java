import bond.Bond;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import assetinterfaces.*;
import stock.*;
import realestate.*;

import javax.sound.sampled.Port;

import static assetinterfaces.AssetUtilities.simulation2xlsx;

public class App {
    
    public static void main(String[] args) throws Exception {        
        //test_sim_2_xlsx();
        //test_stock1();
        //test_stock2();
        //test_stock3();
        
        test_realestate1();
        test_realestate2();
        test_realestate3();
        example_property1();
        example_property2();

        out.println("All tests complete!");
    }
    
    public static void example_property2() {
        RealEstate house = new RealEstate("Example House", .00, 460000.0);
        house.set_mortgage_maturity(30);
        
        house.set_gross_rent(3600.0);
        
        house.set_monthly_utilities(250.0);
        house.set_monthly_insurance(1610/12.0);
        house.set_monthly_maintenance(2000/12.0);
        house.set_monthly_hoa_Fees(200.0);
        
        
        SimulatedAsset sim = house.create_simulation(30 * 12 + 2);
        
        simulation2xlsx(sim);
    }
    
    
    public static void example_property1() {
        RealEstate house = new RealEstate("Uni One Condo", .035, 100000.0);
        house.set_mortgage_maturity(30);
        
        house.set_gross_rent(1200.0);
        
        house.set_monthly_utilities(150.0);
        house.set_monthly_insurance(350/12.0);
        house.set_monthly_hoa_Fees(400.0);
        
        
        SimulatedAsset sim = house.create_simulation(30 * 12 + 2);
        
        simulation2xlsx(sim);
    }
    
    public static void test_realestate3() {
        RealEstate house = new RealEstate("House", .035, 100000.0);
        house.set_mortgage_maturity(30);
        house.set_monthly_maintenance(300.0);
        house.set_gross_rent(1400.0);
        house.set_min_manager_fee(100.0);
        house.set_fractional_manager_fee(.10);
        
        
        SimulatedAsset sim = house.create_simulation(30 * 12 + 1);
        
        simulation2xlsx(sim);
    }
    
    public static void test_realestate2() {
        RealEstate house = new RealEstate("House", .20, 100000.0);
        house.set_mortgage_maturity(10);
        
        SimulatedAsset sim = house.create_simulation(30 * 12 + 1);
        
        simulation2xlsx(sim);
        // Tests passed!
        
        // ADDITIONAL TESTS NEED TO EVALUATE
        // changed closing costs
        // an additional flat expense
        // rent & vacancy rate
        // property manager fees (min vs percent)
    }
    
    public static void test_realestate1() {
        RealEstate house = new RealEstate("House", .20, 100000.0);
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