package stock;
import assetinterfaces.Asset;
import assetinterfaces.SimulatedAsset;
import java.lang.Math;

public class Stock extends Asset {
    public double init_num_shares;
    public double init_share_price;
    
    public double monthly_growth = 0.00797414043;
    public double ann_dividend_per_share = 0.0;
    public int dividend_period = 3;
    public double div_reinvest_frac = 0.0;
    
    
    public Stock(String name, double init_num_shares, double init_share_price) {
        super(
            name, 
            0.0, 
            init_num_shares*init_share_price,
            init_num_shares*init_share_price
        );
        this.init_num_shares = init_num_shares;
        this.init_share_price = init_share_price;
    }
    
    public void set_growth_rate(double apy) {
        this.monthly_growth = Math.pow(1 + apy, 1.0/12.0) - 1;
    }
    
    public void set_dividends_amount(double ann_dividend_per_share) {
        this.ann_dividend_per_share = ann_dividend_per_share;
    }
    
    public void set_dividend_period(int dividend_period_months) {
        this.dividend_period = dividend_period_months;
    }
    
    public void set_dividend_reinvesting_fraction(double fraction) {
        this.div_reinvest_frac = fraction;
    }
    
    
    @Override
    public SimulatedAsset create_simulation(int num_months) {
        return new SimulatedStock(this, num_months);
    }
    
}
