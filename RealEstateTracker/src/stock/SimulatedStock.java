package stock;

import assetinterfaces.*;
import java.util.ArrayList;

public class SimulatedStock extends SimulatedAsset {
    Stock config;
    
    ArrayList<Double> num_shares = new ArrayList<Double>();
    ArrayList<Double> share_price = new ArrayList<Double>();
    ArrayList<Double> dividend_income = new ArrayList<Double>();
    
    public SimulatedStock(Stock config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    @Override
    public void simulate_month() {
        
        
        append_share_price();
        append_capital_gains_ledger();
        
        append_dividend_income();
        reinvest_dividends();
        
        
        super.simulate_month();
    }
    
    
   
    
    
    public void append_share_price(double new_price) {
        share_price.add(new_price);
    }
    
    
    public void purchase_shares(double value);
    
    public void change_num_shares(double num_new_shares) {
        num_shares.add(get_prev_num_shares() + num_new_shares);
    }
    
    public double get_prev_share_price() {
        if (share_price.size() == 0) {
            return config.init_share_price;
        } else {
            return share_price.get(month-1);
        }
    }
    
    public double get_prev_num_shares() {
        if (num_shares.size() == 0) {
            return config.init_num_shares;
        } else {
            return num_shares.get(month-1);
        }
    }
    
    
    protected void append_capital_gains_ledger();
    
    
    protected void append_revenue_ledger();
    
    
    protected void append_additional_investment_ledger();
    
    
    
    private boolean dividend_month() {
        return (month + 1) % config.dividend_period == 0;
    }
    
    private double get_dividend_yield() {
        return config.ann_dividend_per_share 
        * (config.dividend_period / 12.0)
        * config.init_num_shares;
    }
    
    
    
    
    
    
    protected void append_expenses_ledger() {
        expense_ledgers.add(new Ledger()); 
    }
    
    protected void append_liability_payments_ledger() {
        liability_payments_ledgers.add(new Ledger()); 
    }
}
