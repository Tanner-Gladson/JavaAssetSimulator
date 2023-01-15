package assetinterfaces;

public abstract class Asset {
    public String name;
    public double init_equity;
    public double init_liabilities;
    public double init_asset_value;
    
    
    public Asset(String name, double sum_liabilities, double sum_equity, double asset_value) {
        this.name = name;
        this.init_equity = sum_equity;
        this.init_liabilities = sum_liabilities;
        this.init_asset_value = asset_value;
    }
    
    public abstract SimulatedAsset create_simulation(int num_months);
}
