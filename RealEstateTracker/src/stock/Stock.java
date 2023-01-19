package stock;
import assetinterfaces.Asset;
import assetinterfaces.SimulatedAsset;
import java.lang.Math;

public class Stock extends Asset {
    double num_shares;
    double share_price;
    
    double monthly_growth = 0.10;
    double ann_dividend_per_share = 0.0;
    int dividend_period = 3;
    boolean reinvesting_dividends = false;
    
    
    public Stock(String name, double init_num_shares, double init_share_price) {
        super(
            name, 
            0.0, 
            init_num_shares*init_share_price,
            init_num_shares*init_share_price
        );
        this.num_shares = init_num_shares;
        this.share_price = init_share_price;
    }
    
    public void set_growth_rate(double apy) {
        this.monthly_growth = Math.pow(apy, 1/12);
    }
    
    public void set_dividends_amount(double ann_dividend_per_share) {
        this.ann_dividend_per_share = ann_dividend_per_share;
    }
    
    public void set_dividend_period(int dividend_period_months) {
        this.dividend_period = dividend_period_months;
    }
    
    public void set_reinvesting_dividends(boolean reinvesting) {
        this.reinvesting_dividends = reinvesting;
    }
    
    
    @Override
    public SimulatedAsset create_simulation(int num_months) {
        return new SimulatedStock(this, num_months);
    }
    
}
