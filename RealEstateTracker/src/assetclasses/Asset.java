package assetclasses;
import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;


public abstract class Asset {
    SimulatedAsset simulation;
	double init_liabilities;
	double init_equity;
	double init_asset_value;
    
    
    double asset_appreciate_rate; // Fraction
    double additional_monthly_investment;
    
    HashMap<String, Double> static_revenues;     // {"name" : amount}
	HashMap<String, Double> fractional_revenues; // {"name" : fraction_of_assets}
	HashMap<String, Double> static_expenses; // {"name" : amount} 
    HashMap<String, Double> fractional_expenses; // {"name" : fraction_of_assets}
	HashMap<String, Double> liability_repayments; // {"name" : amount} 
    
    
    public Asset(double sum_liabilities, double sum_equity, double asset_value) {
        simulation = new SimulatedAsset();
        init_liabilities = sum_liabilities;
        init_equity = sum_equity;
        init_asset_value = asset_value;
    }
    
    public void run_simulation(int num_months) {
        simulation.init_asset_value = init_asset_value;
        simulation.init_liabilities =  init_liabilities;
	    simulation.init_equity = init_equity;
        
        for (int i = 0; i < num_months; i++) {
            extend_sim_revenue();
            extend_sim_expenses();
            extend_sim_liability_payments();
            extend_sim_additional_investment();
            extend_sim_capital_gains();
            simulation.extend();
        }
        
    }
    
	protected abstract void extend_sim_revenue();
	protected abstract void extend_sim_expenses();
    protected abstract void extend_sim_liability_payments();
    protected abstract void extend_sim_additional_investment();
    protected abstract void extend_sim_capital_gains();
}
