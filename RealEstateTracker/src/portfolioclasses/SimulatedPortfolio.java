package portfolioclasses;
import javax.sound.sampled.Port;
import java.util.ArrayList;

import assetclasses.*;

public class SimulatedPortfolio extends SimulatedAsset {
    Portfolio portfolio = new Portfolio();
    ArrayList<SimulatedAsset> simulations = new ArrayList<SimulatedAsset>();
    int month = 0;
    
    SimulatedPortfolio(Portfolio portfolio, int num_months) {
        super();
        this.init_equity = portfolio.get_init_equity_sum();
        this.init_liabilities = portfolio.get_init_liability_sum();
        this.init_asset_value = portfolio.get_init_asset_value_sum();
        
        run_simulation(num_months);
    }
    
    public void run_simulation(int num_months) {
        for (int i = 0; i < num_months; i++) {
            extend_revenue_ledger();
            extend_expenses_ledger();
            extend_liability_payments_ledger();
            extend_additional_investment_ledger();
            extend_capital_gains_ledger();
            
            this.extend();
        }
    }
    
    protected void extend_revenue_ledger();
	protected void extend_expenses_ledger();
    protected void extend_liability_payments_ledger();
    protected void extend_additional_investment_ledger();
    protected void extend_capital_gains_ledger();
}
