package bond;

import assetinterfaces.*;

public class SimulatedBond extends SimulatedAsset {
    
    public Bond config;
    
    public SimulatedBond(Bond config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
     
    protected void append_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        if ( (month + 1) % config.return_freq == 0 && 
            !(month >= config.term_months)) 
        {
            double interest_rate = config.annual_coupon_yield * ( (double) config.return_freq / 12);
            ledger.add_transaction("Coupon Yield", interest_rate * config.principal);
        }
        
        revenue_ledgers.add(ledger); 
        
    }
    
	protected void append_expenses_ledger() {
        expense_ledgers.add(new Ledger());
    }
    
    
    protected void append_liability_payments_ledger() {
        liability_payments_ledgers.add(new Ledger());
    }
    
    
    protected void append_additional_investment_ledger() {
        
        Ledger ledger = new Ledger();
        
        if (month + 1 == config.term_months) {
            ledger.add_transaction("Sale at Maturity", -config.principal);
        }
        
        additional_investments_ledgers.add(ledger);
    }
    
    
    protected void append_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        if (month + 1 == config.term_months) {
            ledger.add_transaction("Maturity", config.principal - config.simulation.get_prev_asset_value());
        }
        
        capital_gains_ledgers.add(ledger);
    }
}
