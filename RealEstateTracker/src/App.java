import assetclasses.*;
import portfolioclasses.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class App {
    
    public static void main(String[] args) throws Exception {        
        out.println("All tests complete!");
        test_bond();
    }
    
    public static void test_bond() {
        test1();
    }
    
    public static void test1() {
        out.println("Beggining test 1");
        Bond bond = new Bond(1000, 1000, 100, 0.10);
        
        bond.run_simulation(24);
        
        
        out.println("Test 1 complete");
    }
    
}