import bond.Bond;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.sound.sampled.Port;

import assetinterfaces.*;

public class App {
    
    public static void main(String[] args) throws Exception {        
        test_simulated_portfolio();
        test_portfolio_collection();
        out.println("All tests complete!");
    }
    
    public static void test_simulated_portfolio() {
        out.println("Beginning test 1");
        
        Portfolio port = new Portfolio("Portfolio 1");
        Bond bond1 = new Bond("Bond 1", 100.0, 100.0, 1.0, .1);
        Bond bond2 = new Bond("Bond 2", 5.0, 10.0, 1.0, .1);

        port.add_asset(bond1);
        port.add_asset(bond2);
        
        SimulatedPortfolio sim = new SimulatedPortfolio(port, 14);
        
        /*
        out.println("Simulations:");
        out.println(sim.simulations);
        for (Ledger ledger : sim.revenue_ledgers) {
            out.println(ledger);
        }
        
        out.println();
        out.println("Bond 1 revenue:");
        for (Ledger ledger : sim.simulations.get(0).revenue_ledgers) {
            out.println(ledger);
        }
        
        
        out.println("Revenue:");
        out.println(sim.revenue);
        for (Ledger ledger : sim.revenue_ledgers) {
            out.println(ledger);
        }
        
        out.println();
        out.println("Additional Investments:");
        for (Ledger ledger : sim.additional_investments_ledgers) {
            out.println(ledger);
        }
        
        out.println();
        out.println("Capital Gains:");
        for (Ledger ledger : sim.capital_gains_ledgers) {
            out.println(ledger);
        }
        
        
        out.println();
        out.println("Expenses:");
        for (Ledger ledger : sim.expense_ledgers) {
            out.println(ledger);
        }
        */
        
        out.println("Test 1 complete!");
    }
    
    public static void test_portfolio_collection() {
        Portfolio port1 = new Portfolio("Portfolio 1");
        Bond bond1a = new Bond("Bond 1a", 100.0, 100.0, 1.0, .1);
        Bond bond2a = new Bond("Bond 2a", 5.0, 10.0, 1.0, .1);

        port1.add_asset(bond1a);
        port1.add_asset(bond2a);
        
        
        Portfolio port2 = new Portfolio("Portfolio 2");
        Bond bond1b = new Bond("Bond 1b", 100.0, 100.0, 1.0, .1);
        Bond bond2b = new Bond("Bond 2b", 5.0, 10.0, 1.0, .1);

        port2.add_asset(bond1b);
        port2.add_asset(bond2b);
        
        PortfolioCollection collection = new PortfolioCollection("Collection 1");
        
        collection.add_portfolio(port1);
        collection.add_portfolio(port2);
        
        collection.simulate_portfolios(14);
    }
    
}