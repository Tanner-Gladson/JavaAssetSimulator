package assetclasses;

import java.util.HashMap;

public class StdAsset extends BasicAsset {
    
    
    double asset_appreciate_rate; // Fraction
    double additional_monthly_investment;
    
    HashMap<String, Double> static_revenues;      // {"name" : amount}
	HashMap<String, Double> fractional_revenues;  // {"name" : fraction_of_assets}
	HashMap<String, Double> static_expenses;      // {"name" : amount} 
    HashMap<String, Double> fractional_expenses;  // {"name" : fraction_of_assets}
	HashMap<String, Double> liability_repayments; // {"name" : amount} 
    
    StdAsset(double sum_liabilities, double sum_equity, double asset_value) {
        super(sum_liabilities, sum_equity, asset_value);
        asset_appreciate_rate = 0;
        additional_monthly_investment = 0;
        
        static_revenues = new HashMap<String, Double>();
        fractional_revenues = new HashMap<String, Double>();
        static_expenses = new HashMap<String, Double>();
        fractional_expenses = new HashMap<String, Double>();
        liability_repayments = new HashMap<String, Double>();
    }
    
    
    public void set_asset_appreciation_rate(double rate) {
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
        simulation.reset();
        set_init_sim_vals();
        for (int i = 0; i < num_months; i++) {
            extend_simulation_revenue();
            extend_simulation_expenses();
            extend_simulation_liability_payments();
            extend_simulation_additional_investment();
            extend_simulation_capital_gains();
            simulation.extend();
        }
        
    }
    
    
    private double get_simulated_asset_value() {
        if (simulation.month == 0) {
            return init_asset_value;
        } else {
            return simulation.asset_value.get(simulation.month);
        }
    }
    
    
    protected void extend_simulation_revenue() {
        double revenue = get_frac_revenue_sum() + get_static_revenue_sum();
        simulation.revenue.add(revenue);
    }
    
    private double get_frac_revenue_sum() {
        double frac_sum = 1;
        for (Double val : fractional_revenues.values()) {
            frac_sum += val;
        }
        
        return frac_sum * get_simulated_asset_value();
    }
    
    private double get_static_revenue_sum() {
        double static_sum = 0;
        for (Double val : static_revenues.values() ) {
            static_sum += val;
        }
        return static_sum;
    }
    
        
	protected void extend_simulation_expenses() {
        double expenses = get_static_expense_sum() + get_frac_expense_sum();
        simulation.expenses.add(expenses);
    }
    
    private double get_frac_expense_sum() {
        double frac_sum = 1;
        for (Double val : fractional_expenses.values()) {
            frac_sum += val;
        }
        
        return frac_sum * get_simulated_asset_value();
    }
    
    private double get_static_expense_sum() {
        double static_sum = 0;
        for (Double val : static_expenses.values() ) {
            static_sum += val;
        }
        return static_sum;
    }
    
        
    protected void extend_simulation_liability_payments() {
        simulation.liability_payments.add(get_liability_payments_sum());
    }
    
    private double get_liability_payments_sum() {
        double sum = 0;
        for (Double val : liability_repayments.values()) {
            sum += val;
        }
        return sum;
    }
        
    
    protected void extend_simulation_additional_investment() {
        simulation.additional_investments.add(additional_monthly_investment);
    }
    
        
    protected void extend_simulation_capital_gains() {
        double gains = get_simulated_asset_value() * asset_appreciate_rate;
        simulation.capital_gains_month.add(gains);
    }
}