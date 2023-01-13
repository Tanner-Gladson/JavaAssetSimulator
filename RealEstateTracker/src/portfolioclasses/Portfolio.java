package portfolioclasses;
import java.util.ArrayList;
import assetclasses.*;

public class Portfolio {
    
    public ArrayList<BasicAsset> assets = new ArrayList<BasicAsset>();
    
    
    public void add_asset(BasicAsset asset) {
        assets.add(asset);
    }
    
    public double get_init_equity_sum() {
        double sum = 0;
        for (BasicAsset asset : assets) {
            sum += asset.simulation.init_equity;
        }
        return sum;
    }
    
    public double get_init_liability_sum() {
        double sum = 0;
        for (BasicAsset asset : assets) {
            sum += asset.simulation.init_liabilities;
        }
        return sum;
    }
    
    public double get_init_asset_value_sum() {
        double sum = 0;
        for (BasicAsset asset : assets) {
            sum += asset.simulation.init_asset_value;
        }
        return sum;
    }
    
}
