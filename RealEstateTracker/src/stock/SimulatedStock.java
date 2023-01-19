package stock;

import assetinterfaces.*;
import java.util.ArrayList;

public class SimulatedStock extends SimulatedAsset {
    Stock config;
    
    ArrayList<Double> num_shares = new ArrayList<Double>();
    ArrayList<Double> share_price = new ArrayList<Double>();
    
    public SimulatedStock(Stock config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    @Override
    public void run_simulation(int num_months) {
        
        // Update the share price/
        
        // Calculate dividend income
        // Update the number of shares (e.g, dividend reinvesting)
        
        // => calculate revenue
        // => find additional investments
        // => calculate capital gains
        
        super.run_simulation(num_months);
    }
    
    
    public void change_share_price(double new_price) {
        share_price.add(new_price);
    }
    
    
    public void purchase_shares(double value);
    
    public void change_num_shares(double num_new_shares) {
        num_shares.add(get_prev_num_shares() + num_new_shares);
    }
    
    public double get_prev_share_price();
    
    public double get_prev_num_shares();
    
    
    protected void append_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        
        if (dividend_month() && !config.reinvesting_dividends) 
        {
            ledger.add_transaction("Dividends", get_dividend_yield());
        }
        
        revenue_ledgers.add(ledger); 
    }
    
    
    protected void append_additional_investment_ledger() {
        Ledger ledger = new Ledger();
        
        if (dividend_month() && config.reinvesting_dividends) 
        {               
            ledger.add_transaction("Dividends Reinvestment", get_dividend_yield());
        }
        
        additional_investments_ledgers.add(ledger); 
    }
    
    private boolean dividend_month() {
        return (month + 1) % config.dividend_period == 0;
    }
    
    private double get_dividend_yield() {
        return config.ann_dividend_per_share 
        * (config.dividend_period / 12.0)
        * config.init_num_shares;
    }
    
    
    // TODO: Should derive capital gains from the change in stock price
    protected void append_capital_gains_ledger();
    
    
    protected void append_expenses_ledger() {
        expense_ledgers.add(new Ledger()); 
    }
    
    protected void append_liability_payments_ledger() {
        liability_payments_ledgers.add(new Ledger()); 
    }
}
