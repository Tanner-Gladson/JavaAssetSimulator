package portfolioclasses;
import java.util.ArrayList;

import assetinterfaces.*;

public class Portfolio {
    String name;
    
    public ArrayList<Asset> assets = new ArrayList<Asset>();
    
    public Portfolio(String name) {
        this.name = name;
    }
    
    public void add_asset(Asset asset) {
        assets.add(asset);
    }
    
    public double get_init_equity_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.simulation.init_equity;
        }
        return sum;
    }
    
    public double get_init_liability_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.simulation.init_liabilities;
        }
        return sum;
    }
    
    public double get_init_asset_value_sum() {
        double sum = 0;
        for (Asset asset : assets) {
            sum += asset.simulation.init_asset_value;
        }
        return sum;
    }
    
    @Override
    public String toString() {
        return String.format("%s, a portfolio", name);
    }
    
}
