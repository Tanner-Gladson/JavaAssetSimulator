package assetclasses;
import java.util.HashMap;

public class Ledger extends HashMap<String, Double> {
    
    public Double total() {
        Double sum = 0.0;
        for (Double val : values()) {
            sum += val;
        }
        return sum;
    }
    
    public void add_transaction(String name, Double amount) {
        put(name, amount);
    }
}
