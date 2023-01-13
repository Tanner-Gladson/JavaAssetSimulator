package assetinterfaces;

public class Asset {
    public SimulatedAsset simulation;
    String name;
    double init_equity;
    double init_liabilities;
    double init_asset_value;
    
    
    public Asset(String name, double sum_liabilities, double sum_equity, double asset_value) {
        this.name = name;
        this.init_equity = sum_equity;
        this.init_liabilities = sum_liabilities;
        this.init_asset_value = asset_value;
    }

    
    public void set_init_equity(double equity) {
        simulation.init_equity = equity;
    }
    
    public void set_init_liability(double liability) {
        simulation.init_liabilities = liability;
    }
    
    public void set_init_asset_value(double asset_value) {
        simulation.init_asset_value = asset_value;
    }
    
    
}
