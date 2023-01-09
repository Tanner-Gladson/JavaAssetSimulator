package assetclasses;

import java.util.HashMap;

public class Bond extends Asset {
    
    Bond(double bond_principal) {
        super(0, bond_principal, bond_principal);
    }
    
    
    private double get_simulated_asset_value() {
        if (simulation.month == 0) {
            return init_asset_value;
        } else {
            return simulation.asset_value.get(simulation.month);
        }
    }
    
    
    protected void extend_simulation_revenue() {
        double revenue = get_frac_revenue() + get_static_revenue();
        simulation.revenue.add(revenue);
    }
    
    private double get_frac_revenue() {
        double frac_sum = 1;
        for (Double val : fractional_revenues.values()) {
            frac_sum += val;
        }
        
        return frac_sum * get_simulated_asset_value();
    }
    
    private double get_static_revenue() {
        double static_sum = 0;
        for (Double val : static_revenues.values() ) {
            static_sum += val;
        }
        return static_sum;
    }
    
        
	protected void extend_simulation_expenses() {
        double expenses = get_static_expenses() + get_frac_expenses();
        simulation.expenses.add(expenses);
    }
    
    private double get_frac_expenses() {
        double frac_sum = 1;
        for (Double val : fractional_expenses.values()) {
            frac_sum += val;
        }
        
        return frac_sum * get_simulated_asset_value();
    }
    
    private double get_static_expenses() {
        double static_sum = 0;
        for (Double val : static_expenses.values() ) {
            static_sum += val;
        }
        return static_sum;
    }
    
        
    protected void extend_simulation_liability_payments() {
        
    }
        
    protected void extend_simulation_additional_investment() {
        
    }
        
    protected void extend_simulation_capital_gains() {
        
    }
}