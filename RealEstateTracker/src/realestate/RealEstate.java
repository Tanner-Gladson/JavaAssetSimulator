package realestate;
import assetinterfaces.*;

public class RealEstate extends Asset {
    
    Double closing_costs;
    Double initial_repair_costs = 0.0;
    Double value_after_repair = null;
    
    Double mortgage_maturity_months = 30.0 * 12;
    Double mortgage_mpr = 0.064;
    Double frac_manager_fee = 0.0;
    Double min_manager_fee = 0.0;
    Double monthly_property_tax_rate = 0.02248;
    
    Ledger flat_expense_ledger = new Ledger();
    
    Double gross_rent = 0.0;
    Double vacancy_rate = 0.10;
    
    Double appreciation_rate = .04;
    
    
    public RealEstate(String name, Double down_payment_percent, Double purchase_price) {
        super(
            name, 
            purchase_price * (1 - down_payment_percent), 
            purchase_price * (down_payment_percent),
            purchase_price
        );
        
        this.closing_costs = purchase_price * 0.06;
    }
    
    @Override
    public SimulatedAsset create_simulation(int num_months) {
        return new SimulatedRealEstate(this, num_months);
    }
    
    
    public void set_closing_costs_as_percent(Double percent) {
        this.closing_costs = init_asset_value * percent;
    }
    
    public void set_closing_cost_in_dollars(Double dollars) {
        this.closing_costs = dollars;
    }
    
    public void set_initial_repair(Double repair_costs, Double arv) {
        this.initial_repair_costs = repair_costs;
        this.value_after_repair = arv;
    }
    
    public void set_fractional_manager_fee(Double percent_rent_fee) {
        this.frac_manager_fee = percent_rent_fee;
    }
    
    
    public void set_monthly_maintenance(Double maintenance) {
        flat_expense_ledger.add_transaction("Maintenance", maintenance);
    }
    
    public void set_avg_monthly_capital_expenses(Double cost) {
        flat_expense_ledger.add_transaction("Capital Expenses", cost);
    }
    
    public void set_monthly_utilities(Double total) {
        flat_expense_ledger.add_transaction("Utilities", total);
    }
    
    public void set_monthly_insurance(Double cost) {
        flat_expense_ledger.add_transaction("Insurance", cost);
    }
    
    public void set_monthly_hoa_Fees(Double fee) {
        flat_expense_ledger.add_transaction("HOA Fees", fee);
    }
    
    public void set_monthly_flat_manager_fee(Double total_flat_fee) {
        this.min_manager_fee = total_flat_fee;
    }
    
    
    public void set_gross_rent(Double maximum_monthly_rent) {
        gross_rent = maximum_monthly_rent;
    }
    
    public void set_vacancy_rate(Double rate) {
        vacancy_rate = rate;
    }
    
    public void set_annual_appreciation_rate(Double apy) {
        appreciation_rate = Math.pow(apy, 1.0/12.0);
    }

}
