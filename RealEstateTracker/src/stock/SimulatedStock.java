package stock;

import assetinterfaces.*;

public class SimulatedStock extends SimulatedAsset {
    Stock config;
    
    public SimulatedStock(Stock config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    
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
        * config.num_shares;
    }
    
    
    protected void append_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        ledger.add_transaction(
            "Capital Appreciation",
            config.num_shares * config.share_price * config.monthly_growth
        );
        
        liability_payments_ledgers.add(ledger); 
    }
    
    
    protected void append_expenses_ledger() {
        expense_ledgers.add(new Ledger()); 
    }
    
    protected void append_liability_payments_ledger() {
        liability_payments_ledgers.add(new Ledger()); 
    }
}
