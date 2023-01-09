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
    
    HashMap<String, Double> static_revenues;      // {"name" : amount}
	HashMap<String, Double> fractional_revenues;  // {"name" : fraction_of_assets}
	HashMap<String, Double> static_expenses;      // {"name" : amount} 
    HashMap<String, Double> fractional_expenses;  // {"name" : fraction_of_assets}
	HashMap<String, Double> liability_repayments; // {"name" : amount} 
    
    
    public Asset(double sum_liabilities, double sum_equity, double asset_value) {
        simulation = new SimulatedAsset();
        init_liabilities = sum_liabilities;
        init_equity = sum_equity;
        init_asset_value = asset_value;
    }
    
    public void set_asset_appreciate_rate(double rate) {
        asset_appreciate_rate = rate;
    }
    
    public void set_additional_monthly_investment(double monthly_investment) {
        additional_monthly_investment = monthly_investment;
    }
    
    public void set_static_revenues(HashMap<String, Double> revenues) {
        static_revenues = revenues;
    }
    
    public void set_fractional_revenues(HashMap<String, Double> revenues) {
        fractional_revenues = revenues;
    }
    
    public void set_static_expenses(HashMap<String, Double> expenses) {
        static_expenses = expenses;
    }
    
    public void set_fractional_expenses(HashMap<String, Double> expenses) {
        fractional_expenses = expenses;
    }
    
    public void set_liability_repayments(HashMap<String, Double> repayments) {
        liability_repayments = repayments;
    }
    
    
    
    public void run_simulation(int num_months) {
        simulation.init_asset_value = init_asset_value;
        simulation.init_liabilities = init_liabilities;
	    simulation.init_equity = init_equity;
        
        for (int i = 0; i < num_months; i++) {
            extend_simulation_revenue();
            extend_simulation_expenses();
            extend_simulation_liability_payments();
            extend_simulation_additional_investment();
            extend_simulation_capital_gains();
            simulation.extend();
        }
        
    }
    
	protected abstract void extend_simulation_revenue();
	protected abstract void extend_simulation_expenses();
    protected abstract void extend_simulation_liability_payments();
    protected abstract void extend_simulation_additional_investment();
    protected abstract void extend_simulation_capital_gains();
}
