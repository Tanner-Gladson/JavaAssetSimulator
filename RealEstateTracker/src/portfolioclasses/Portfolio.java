package portfolioclasses;
import java.util.ArrayList;

import assetinterfaces.*;

public class Portfolio extends Asset {
    String name;
    public ArrayList<Asset> assets = new ArrayList<Asset>();
    
    public Portfolio(String name) {
        super(name, 0.0, 0.0, 0.0);
        this.name = name;
    }
    
    public void add_asset(Asset asset) {
        assets.add(asset);
        udpate_inits();
    }
    
    
    @Override
    public SimulatedAsset create_simulation(int num_months) {
        return new SimulatedPortfolio(this, num_months);
    }
    
    protected void udpate_inits() {
        this.init_equity = get_init_equity_sum();
        this.init_liabilities = get_init_liability_sum();
        this.init_asset_value = get_init_asset_value_sum();
    }
    
    public double get_init_equity_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.init_equity;
        }
        return sum;
    }
    
    public double get_init_liability_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.init_liabilities;
        }
        return sum;
    }
    
    public double get_init_asset_value_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.init_asset_value;
        }
        return sum;
    }
    
    @Override
    public String toString() {
        return String.format("%s, a portfolio", name);
    }
    
    public String summary() {
        String s = "";
        
        s += name + ":\n";
        for (Asset asset : assets) {
            s += "  " + asset.name + "\n";
        }
        
        return s;
    }
    
}
