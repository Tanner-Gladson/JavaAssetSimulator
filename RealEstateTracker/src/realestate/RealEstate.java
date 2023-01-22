package realestate;
import assetinterfaces.*;

public class RealEstate extends Asset {
    
    public Double closing_costs;
    public Double initial_repair_costs = 0.0;
    public Double value_after_repair = null;
    
    public Double mortgage_maturity_months = 30.0 * 12;
    public Double mortgage_mpr = 0.064 / 12;
    public Double frac_manager_fee = 0.10;
    public Double min_manager_fee = 100.0;
    public Double monthly_property_tax_rate = 0.02248 / 12.0;
    
    public Ledger flat_expense_ledger = new Ledger();
    
    public Double gross_rent = 0.0;
    public Double vacancy_rate = 0.10;
    
    public Double appreciation_rate = Math.pow(1.04, 1.0/12.0) - 1;
    public Double monthly_payment;
    
    public RealEstate(String name, Double down_payment_frac, Double purchase_price) {
        super(
            name, 
            purchase_price * (1 - down_payment_frac), 
            purchase_price * (down_payment_frac),
            purchase_price
        );
        
        this.closing_costs = purchase_price * 0.06;
        update_fields();
    }
    
    public void update_fields() {
        update_monthly_payment();
    }
    
    public void update_monthly_payment() {
        Double n = mortgage_maturity_months;
        Double r = mortgage_mpr;
        this.monthly_payment = init_liabilities * (r * Math.pow(1+r, n)) / (Math.pow(1+r, n) - 1);
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
    
    
    public void set_mortgage_apr(Double apr) {
        this.mortgage_mpr = apr / 12.0;
        update_fields();
    }
    
    public void set_mortgage_maturity(int years) {
        this.mortgage_maturity_months = years * 12.0;
        update_fields();
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
    
    public void set_min_manager_fee(Double total_flat_fee) {
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
