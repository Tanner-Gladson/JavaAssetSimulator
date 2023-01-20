package stock;

import assetinterfaces.*;
import java.util.ArrayList;

public class SimulatedStock extends SimulatedAsset {
    public Stock config;
    
    public ArrayList<Double> num_shares = new ArrayList<Double>();
    public ArrayList<Double> share_price = new ArrayList<Double>();
    public ArrayList<Double> dividend_income = new ArrayList<Double>();
    
    public SimulatedStock(Stock config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    @Override
    public void simulate_month() {
        append_share_price();
        append_dividend_income();
        reinvest_dividends();
        super.simulate_month();
    }
    
    
    
    protected void append_share_price() {
        share_price.add(get_prev_share_price() + calculate_appreciation());
    }
    
    protected double calculate_appreciation() {
        return get_prev_share_price() * config.monthly_growth * get_prev_num_shares();
    }
    
    
    
    protected void append_dividend_income() {
        
        if ( (month+1) % config.dividend_period == 0) {
            dividend_income.add(calculate_div_distribution());
        } else {
            dividend_income.add(0.0);
        }
    }
    
    protected double calculate_div_distribution() {
        double percent = (double) config.dividend_period / 12;
        return get_prev_num_shares() * (config.ann_dividend_per_share) * percent;
    }
    
    
    protected void reinvest_dividends() {
        purchase_shares(calculate_reinvestment_dollars());
    }
    
    protected double calculate_reinvestment_dollars() {
        return dividend_income.get(month) * config.div_reinvest_frac;
    }
    
    protected void purchase_shares(double dollars) {
        change_num_shares(dollars / share_price.get(month));
    }
    
    protected void change_num_shares(double num_new_shares) {
        num_shares.add(get_prev_num_shares() + num_new_shares);
    }
    
    
    
    protected double get_prev_share_price() {
        if (month == 0) {
            return config.init_share_price;
        } else {
            return share_price.get(month-1);
        }
    }
    
    protected double get_prev_num_shares() {
        if (month == 0) {
            return config.init_num_shares;
        } else {
            return num_shares.get(month-1);
        }
    }
    
    
    protected void append_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        ledger.add_transaction("Market Growth", calculate_appreciation());
        capital_gains_ledgers.add(ledger);
    }
    
    
    protected void append_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        ledger.add_transaction(
            "Dividend", 
            dividend_income.get(month) - calculate_reinvestment_dollars()
        );
        
        revenue_ledgers.add(ledger);
    }
    
    
    protected void append_additional_investment_ledger() {
        Ledger ledger = new Ledger();
        ledger.add_transaction("Dividend Reinvestment", calculate_reinvestment_dollars());
        additional_investments_ledgers.add(ledger);
    }
    
    
    protected void append_expenses_ledger() {
        expense_ledgers.add(new Ledger()); 
    }
    
    
    protected void append_liability_payments_ledger() {
        liability_payments_ledgers.add(new Ledger()); 
    }
}
