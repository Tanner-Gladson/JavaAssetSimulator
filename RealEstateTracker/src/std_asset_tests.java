import assetclasses.*;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class std_asset_tests {
    
    public static void test_std_asset_class() {
        test_all_null();
        test_appreciation_rate();
        test_monthly_investments();
        test_revenues();
        test_expenses();
        test_liabalities();
    }
    
    public static void test_all_null() {
        out.println("Commencing Test 1");
        StdAsset asset = new StdAsset(40.0, 60.0, 100.0);
        
        // Run the simulation once.
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 60;
        assert sim.init_liabilities == 40;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        
        // Run the simulation again.
        asset.run_simulation(2);
        sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 60;
        assert sim.init_liabilities == 40;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        out.println("--Test 1 complete");
    }
    
    
    public static void test_appreciation_rate() {
        out.println("Commencing Test 1");
        
        // Set up parameters
        StdAsset asset = new StdAsset(50.0, 
                                      50.0, 
                                      100.0);
        
        asset.set_asset_appreciation_rate(.01);
                                      
        // Run the simulation once.
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(1.0, 1.01));
        
        out.println("--Test 1 complete");
    }
    
    public static void test_monthly_investments() {
        out.println("Commencing Test 2");
        
        // Set up parameters
        StdAsset asset = new StdAsset(50.0, 
                                      50.0, 
                                      100.0);
        
        asset.set_additional_monthly_investment(10);
        asset.set_asset_appreciation_rate(.1);
                                      
        // Run the simulation once.
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(10.0, 10.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(10.0, 12.0));
        
        out.println("--Test 2 complete");
    }
    
    public static void test_liabalities() {
        out.println("Commencing Test 5");
        
        
        // Set up parameters
        StdAsset asset = new StdAsset(50.0, 50.0, 100.0);
        
        HashMap<String, Double> liab = new HashMap<String, Double>();
        liab.put("Mortgage Payment", 10.0);
        liab.put("Unkown", 2.0);
        
        asset.set_liability_repayments(liab);
                                      
        // Run the simulation once.
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(12.0, 12.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        out.println("--Test 5 complete");
    }
    
    public static void test_revenues() {
        out.println("Commencing Test 3");
        
        // STATIC REVENUES
        StdAsset asset = new StdAsset(50.0, 50.0, 100.0);
        
        HashMap<String, Double> static_rev = new HashMap<String, Double>();
        static_rev.put("Rent", 11.0);
        
        HashMap<String, Double> frac_rev = new HashMap<String, Double>();
        frac_rev.put("Rent", .01);
        frac_rev.put("Storage Income", .09);
        
        asset.set_static_revenues(static_rev);
        asset.set_fractional_revenues(frac_rev);
                                      
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(21.0, 21.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        // COMBINED + ADDITIONAL INVESTMENTS
        
        HashMap<String, Double> static_rev2 = new HashMap<String, Double>();
        static_rev2.put("Rent", 11.0);
        
        HashMap<String, Double> frac_rev2 = new HashMap<String, Double>();
        frac_rev2.put("Rent", .01);
        frac_rev2.put("Storage Income", .09);
        
        asset.set_static_revenues(static_rev2);
        asset.set_fractional_revenues(frac_rev2);
        asset.set_additional_monthly_investment(100);
                                      
        asset.run_simulation(2);
        sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(21.0, 31.0));
        assert sim.expenses.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(100.0, 100.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        
        out.println("--Test 3 complete");
    }
    
    public static void test_expenses() {
        out.println("Commencing Test 4");
        
        
        // EXPENSES
        StdAsset asset = new StdAsset(50.0, 50.0, 100.0);
        
        HashMap<String, Double> static_exp = new HashMap<String, Double>();
        static_exp.put("Insurance", 10.0);
        static_exp.put("Repairs", 1.0);
        
        HashMap<String, Double> frac_exp = new HashMap<String, Double>();
        frac_exp.put("Taxes", .01);
        frac_exp.put("Fees", .09);
        
        asset.set_static_expenses(static_exp);
        asset.set_fractional_expenses(frac_exp);
                                      
        asset.run_simulation(2);
        SimulatedAsset sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(21.0, 21.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(0.0, 0.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        // COMBINED + ADDITIONAL INVESTMENTS
        
        HashMap<String, Double> static_exp2 = new HashMap<String, Double>();
        static_exp2.put("Insurance", 10.0);
        static_exp2.put("Repairs", 1.0);
        
        HashMap<String, Double> frac_exp2 = new HashMap<String, Double>();
        frac_exp2.put("Taxes", .01);
        frac_exp2.put("Fees", .09);
        
        asset.set_static_expenses(static_exp2);
        asset.set_fractional_expenses(frac_exp2);
        asset.set_additional_monthly_investment(100);
                                      
        asset.run_simulation(2);
        sim = asset.simulation;
        
        assert sim.init_asset_value == 100;
        assert sim.init_equity == 50;
        assert sim.init_liabilities == 50;
        
        assert sim.revenue.equals(Arrays.asList(0.0, 0.0));
        assert sim.expenses.equals(Arrays.asList(21.0, 31.0));
        
        assert sim.liability_payments.equals(Arrays.asList(0.0, 0.0));
        assert sim.additional_investments.equals(Arrays.asList(100.0, 100.0));
        
        assert sim.capital_gains_month.equals(Arrays.asList(0.0, 0.0));
        
        out.println("--Test 4 complete");
    }
}
