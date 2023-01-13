package portfolioclasses;
import javax.sound.sampled.Port;

import assetinterfaces.*;

import java.util.ArrayList;

public class SimulatedPortfolio extends SimulatedAsset {
    public Portfolio portfolio;
    public ArrayList<SimulatedAsset> simulations = new ArrayList<SimulatedAsset>();
    
    public SimulatedPortfolio(Portfolio portfolio, int num_months) {
        super(portfolio.name);
        this.portfolio = portfolio;
        simulate_self(num_months);
    }
    
    private void simulate_self(int num_months) {
    }
    
    
    private void simulate_assets(int num_months) {
        for (Asset asset : portfolio.assets) {
            asset.run_simulation(num_months);
        }
    }
    
    private void copy_simulations() {
        for (Asset asset : portfolio.assets) {
            simulations.add(asset.simulation);
        }
    }
    
    private void set_init_values() {
        this.init_equity = portfolio.get_init_equity_sum();
        this.init_liabilities = portfolio.get_init_liability_sum();
        this.init_asset_value = portfolio.get_init_asset_value_sum();
    }
    
    
    
    
    protected void append_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.revenue.get(month));
        }
        
        revenue_ledgers.add(ledger);
    }
    
	protected void append_expenses_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.expenses.get(month));
        }
        
        expense_ledgers.add(ledger);
    }
    
    protected void append_liability_payments_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.liability_payments.get(month));
        }
        
        liability_payments_ledgers.add(ledger);
    }
    
    protected void append_additional_investment_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.additional_investments.get(month));
        }
        
        additional_investments_ledgers.add(ledger);
    }
    
    protected void append_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        for (SimulatedAsset sim : simulations) {
            ledger.add_transaction(sim.name, sim.capital_gains_month.get(month));
        }
        
        capital_gains_ledgers.add(ledger);
    }
    
    @Override
    public String toString() {
        return String.format("%s, a simulated portfolio", name);
    }
    
}
