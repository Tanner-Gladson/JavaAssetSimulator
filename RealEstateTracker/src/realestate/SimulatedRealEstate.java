package realestate;

import assetinterfaces.SimulatedAsset;
import assetinterfaces.*;
import java.util.ArrayList;

public class SimulatedRealEstate extends SimulatedAsset {
    RealEstate config;
    
    public SimulatedRealEstate(RealEstate config, int num_months) {
        super(config);
        this.config = config;
        run_simulation(num_months);
    }
    
    @Override
    public void simulate_month() {
        super.simulate_month();
    }
    
    
    
    protected void append_revenue_ledger() {
        Ledger ledger = new Ledger();
        Double rent = config.gross_rent * (1 - config.vacancy_rate);
        ledger.add_transaction("Rent", rent);
    }
    
    
	protected void append_expenses_ledger() {
        Ledger ledger = new Ledger();
        
        if (month == 0) {
            ledger.add_transaction("Closing Costs", config.closing_costs);
        }
        
        ledger.add_transaction("Manager fee", get_manager_fee());
        ledger.add_transaction("Property Taxes", get_prev_asset_value() * config.monthly_property_tax_rate);
        
        
        // mortgage interest
        
        for (String name : config.flat_expense_ledger.keySet()) {
            ledger.add_transaction(name, config.flat_expense_ledger.get(name));
        }
    }
    
    private Double get_manager_fee() {
        Double frac_fee = config.gross_rent * (1 - config.vacancy_rate) * config.frac_manager_fee;
        return Math.max(config.min_manager_fee, frac_fee);
    }
    
    
    protected void append_liability_payments_ledger() {
        // monthly payment directed towards principal
    }
    
    
    protected void append_additional_investment_ledger() {
        Ledger ledger = new Ledger();
        if (month == 0) {
            ledger.add_transaction("Initial Repairs", config.initial_repair_costs);
        }
        additional_investments_ledgers.add(ledger);
    }
    
    
    protected void append_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        if (month == 0) {
            Double gain = config.value_after_repair 
                        - config.init_asset_value 
                        - config.initial_repair_costs;
            
            ledger.add_transaction("ARV gain", gain);
        }
        
        ledger.add_transaction(
            "Appreciation", 
            get_prev_asset_value() * config.appreciation_rate
        );
    }
    
    
}
