import assetclasses.SimulatedAsset;
import portfolioclasses.PortfolioClasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;

public class simulation_tests {
    private static void test_simulated_asset() {
        
        SimulatedAsset simulation = new SimulatedAsset();
        sim_test_1(simulation);
        simulation.reset();
        sim_test_2(simulation);
        simulation.reset();
        sim_test_3(simulation);
        simulation.reset();
        sim_test_4(simulation);
        simulation.reset();
        sim_test_5(simulation);
        simulation.reset();

        
        out.println("All tests passed!");
    }
    
    
    private static void sim_test_5(SimulatedAsset sim) {
        
        
        sim.init_equity = 50;
        sim.init_liabilities = 50;

        sim.capital_gains_month.addAll(asList(5.0, 5.0));
        
        sim.revenue.addAll(asList(10.0, 10.0));
        sim.expenses.addAll(asList(1.0, 1.0));
        
        sim.liability_payments.addAll(asList(4.0, 4.0));
        sim.additional_investments.addAll(asList(20.0, 20.0));
        
        for (int i = 0; i < 2; i++) {sim.extend();}
        
        assert sim.cash_flow.equals(asList(-15.0, -15.0));
        assert sim.capital_gains_month.equals(asList(5.0, 5.0));
                
        assert sim.invested_capital.equals(asList(74.0, 74.0 + 24.0));
        
        assert sim.liabilities.equals(asList(46.0, 42.0));
        assert sim.equity.equals(asList(79.0, 79.0 + 29.0));
        out.println("Test 5 passed");
    }
    
    private static void sim_test_4(SimulatedAsset sim) {
        
        
        sim.init_equity = 50;
        sim.init_liabilities = 50;
        
        sim.capital_gains_month.addAll(asList(5.0, 5.0));
        
        sim.revenue.addAll(asList(10.0, 10.0));
        sim.expenses.addAll(asList(1.0, 1.0));
        
        sim.liability_payments.addAll(asList(4.0, 4.0));
        sim.additional_investments.addAll(asList(0.0, 0.0));
        
        for (int i = 0; i < 2; i++) {sim.extend();}
        
        assert sim.cash_flow.equals(asList(5.0, 5.0));
        assert sim.capital_gains_month.equals(asList(5.0, 5.0));
                
        assert sim.invested_capital.equals(asList(54.0, 58.0));
        
        assert sim.liabilities.equals(asList(46.0, 42.0));
        assert sim.equity.equals(asList(59.0, 68.0));
        out.println("Test 4 passed");
    }
    
    
    private static void sim_test_3(SimulatedAsset sim) {
        
        
        sim.init_equity = 50;
        sim.init_liabilities = 50;
        
        sim.capital_gains_month.addAll(asList(5.0, 5.0));
        
        sim.revenue.addAll(asList(10.0, 10.0));
        sim.expenses.addAll(asList(1.0, 1.0));
        
        sim.liability_payments.addAll(asList(0.0, 0.0));
        sim.additional_investments.addAll(asList(0.0, 0.0));
        
        for (int i = 0; i < 2; i++) {sim.extend();}
        
        assert sim.cash_flow.equals(asList(9.0, 9.0));
        assert sim.capital_gains_month.equals(asList(5.0, 5.0));
                
        assert sim.invested_capital.equals(asList(50.0, 50.0));
        
        assert sim.liabilities.equals(asList(50.0, 50.0));
        assert sim.equity.equals(asList(55.0, 60.0));
        out.println("Test 3 passed");
    }
    
    
    private static void sim_test_2(SimulatedAsset sim) {
        
        
        sim.init_equity = 50;
        sim.init_liabilities = 50;
        
        sim.capital_gains_month.addAll(asList(5.0, 5.0, 5.0));
        
        sim.revenue.addAll(asList(10.0, 10.0, 10.0));
        sim.expenses.addAll(asList(0.0, 0.0, 0.0));
        
        sim.liability_payments.addAll(asList(0.0, 0.0, 0.0));
        sim.additional_investments.addAll(asList(0.0, 0.0, 0.0));
        
        for (int i = 0; i < 3; i++) {sim.extend();}
        
        assert sim.cash_flow.equals(asList(10.0, 10.0, 10.0));
        assert sim.capital_gains_month.equals(asList(5.0, 5.0, 5.0));
        assert sim.effective_income.equals(asList(15.0, 15.0, 15.0));
        
        assert sim.invested_capital.equals(asList(50.0, 50.0, 50.0));
        
        assert sim.liabilities.equals(asList(50.0, 50.0, 50.0));
        assert sim.equity.equals(asList(55.0, 60.0, 65.0));
        out.println("Test 2 passed");
    }
    
    
    private static void sim_test_1(SimulatedAsset sim) {
        sim.init_equity = 50;
        sim.init_liabilities = 50;
        
        sim.capital_gains_month.addAll(asList(5.0, 5.0, 5.0));
        
        sim.revenue.addAll(asList(10.0, 10.0, 10.0));
        sim.expenses.addAll(asList(0.0, 0.0, 0.0));
        
        sim.liability_payments.addAll(asList(0.0, 0.0, 0.0));
        sim.additional_investments.addAll(asList(0.0, 0.0, 0.0));
        
        for (int i = 0; i < 3; i++) {sim.extend();}
        
        assert sim.cash_flow.equals(asList(10.0, 10.0, 10.0));
        out.println("Test 1 passed");
    }
}
