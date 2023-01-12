import assetclasses.*;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class bond_tests {
    public static void test_bond() {
        test1();
        test2();
        test3();
    }
    
    public static void test1() {
        out.println("Beggining test 1");
        Bond bond = new Bond(100, 100, 1, 0.10);
        
        bond.run_simulation(16);
        
        out.println(bond.simulation.revenue);
        
        out.println("Test 1 complete");
    }
    
    public static void test2() {
        out.println("Beggining test 2");
        Bond bond = new Bond(50, 100, 1, 0.10);
        
        bond.run_simulation(16);
        
        out.println(bond.simulation.revenue);
        out.println(bond.simulation.capital_gains_month);
        out.println(bond.simulation.additional_investments);
        
        out.println();
        out.println(bond.simulation.cash_flow);
        
        
        out.println("Test 2 complete");
    }
    
    
    public static void test3() {
        out.println("Beggining test 2");
        Bond bond = new Bond(50, 100, 1, 0.10, 3);
        
        bond.run_simulation(14);
        
        out.print("Revenue:             ");
        out.println(bond.simulation.revenue);
        out.print("Capital Gains:       ");
        out.println(bond.simulation.capital_gains_month);
        out.print("Additional Invested: ");
        out.println(bond.simulation.additional_investments);
        
        out.println();
        out.print("Cash Flow:           ");
        out.println(bond.simulation.cash_flow);
        
        
        out.println("Test 2 complete");
    }
}
