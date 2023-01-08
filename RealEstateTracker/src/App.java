import assetclasses.SimulatedAsset;
import portfolioclasses.PortfolioClasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;

public class App {
    
    public static void main(String[] args) throws Exception {
        test_simulated_asset();
    }
    
    private static void test_simulated_asset() {
        
        SimulatedAsset simulation = new SimulatedAsset();
        sim_test_1(simulation);
        
        
        out.println("All tests passed!");
    }
    
    private static void sim_test_1(SimulatedAsset sim) {
        sim.init_equity = 50;
        sim.init_liabilities = 50;
        
        sim.asset_value.addAll(asList(100.0, 100.0, 100.0));
        
        sim.revenue.addAll(asList(10.0, 10.0, 10.0));
        sim.expenses.addAll(asList(0.0, 0.0, 0.0));
        
        sim.liability_payments.addAll(asList(0.0, 0.0, 0.0));
        sim.additional_investments.addAll(asList(0.0, 0.0, 0.0));
        
        for (int i = 0; i < 3; i++) {sim.extend();}
        
        assert false;
    }
    
}
