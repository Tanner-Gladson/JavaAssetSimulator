package portfolioclasses;
import javax.sound.sampled.Port;
import java.util.ArrayList;

import assetclasses.*;

public class SimulatedPortfolio extends SimulatedAsset {
    Portfolio portfolio = new Portfolio();
    ArrayList<SimulatedAsset> simulations = new ArrayList<SimulatedAsset>();
    int month = 0;
    
    SimulatedPortfolio(String name, Portfolio portfolio, int num_months) {
        super(name);
        this.portfolio = portfolio;
        simulate_assets(num_months);
        copy_simulations();
        set_init_values();
        
        run_simulation(num_months);
    }
    
    private void simulate_assets(int num_months) {
        for (BasicAsset asset : portfolio.assets) {
            asset.run_simulation(num_months);
        }
    }
    
    private void copy_simulations() {
        for (BasicAsset asset : portfolio.assets) {
            simulations.add(asset.simulation);
        }
    }
    
    private void set_init_values() {
        this.init_equity = portfolio.get_init_equity_sum();
        this.init_liabilities = portfolio.get_init_liability_sum();
        this.init_asset_value = portfolio.get_init_asset_value_sum();
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
    
    protected void extend_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.revenue.get(month));
        }
        
        revenue_ledger.add(ledger);
    }
    
	protected void extend_expenses_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.expenses.get(month));
        }
        
        expense_ledger.add(ledger);
    }
    
    protected void extend_liability_payments_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.liability_payments.get(month));
        }
        
        liability_payments_ledger.add(ledger);
    }
    
    protected void extend_additional_investment_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.additional_investments.get(month));
        }
        
        additional_investments_ledger.add(ledger);
    }
    
    protected void extend_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.capital_gains_month.get(month));
        }
        
        capital_gains_ledger.add(ledger);
    }
    
}
