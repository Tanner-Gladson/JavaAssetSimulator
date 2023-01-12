package assetclasses;

/** 
 * May need to know the breakdown by source for any asset
 * 
 *  1) Revenues
 *  2) Expenses
 *  3) Liability Repayments
 *  4) Capital Gains
 *  5) Additional Investments
 * 
 * Instead of how you have it below, you should create a generic
 * format for how to track each category by month. This should
 * actually be stored in the SimulatedAsset.
 * 
 * The implications of this is that how a BasicAsset provides
 * month-by-month data for each of the 5 categories will be different
 * than just a double. Additional methods will be needed in SimulatedAsset
 * to handle this.
 * */ 


public abstract class BasicAsset {
    public SimulatedAsset simulation;
    
    
    public BasicAsset(double sum_liabilities, double sum_equity, double asset_value) {
        simulation = new SimulatedAsset();
        simulation.init_equity = sum_equity;
        simulation.init_liabilities = sum_liabilities;
        simulation.init_asset_value = asset_value;
    }
    
    public void run_simulation(int num_months) {
        simulation.reset();
        for (int i = 0; i < num_months; i++) {
            extend_revenue_ledger();
            extend_expenses_ledger();
            extend_liability_payments_ledger();
            extend_additional_investment_ledger();
            extend_capital_gains_ledger();
            simulation.extend();
        }
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
    
    protected double get_simulated_asset_value() {
        if (simulation.month == 0) {
            return simulation.init_asset_value;
        } else {
            return simulation.asset_value.get(simulation.month-1);
        }
    }
    
    
    protected abstract void extend_revenue_ledger();
	protected abstract void extend_expenses_ledger();
    protected abstract void extend_liability_payments_ledger();
    protected abstract void extend_additional_investment_ledger();
    protected abstract void extend_capital_gains_ledger();
}
