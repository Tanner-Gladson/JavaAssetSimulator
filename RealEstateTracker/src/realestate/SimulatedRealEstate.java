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
        ledger.add_transaction("Property Taxes", get_property_taxes());
        
        if (month < config.mortgage_maturity_months - 1) {
            ledger.add_transaction("Mortgage Interest", get_mortgage_interest());
        }
        
        for (String name : config.flat_expense_ledger.keySet()) {
            ledger.add_transaction(name, config.flat_expense_ledger.get(name));
        }
    }
    
    private Double get_property_taxes() {
        return get_prev_asset_value() * config.monthly_property_tax_rate;
    }
    
    private Double get_mortgage_interest() {
        return get_prev_liability() * config.mortgage_mpr;
    }
    
    private Double get_manager_fee() {
        Double frac_fee = config.gross_rent * (1 - config.vacancy_rate) * config.frac_manager_fee;
        return Math.max(config.min_manager_fee, frac_fee);
    }
    
    
    protected void append_liability_payments_ledger() {
        Ledger ledger = new Ledger();

        if (month+1 < config.mortgage_maturity_months) {
            ledger.add_transaction("Principal payment", get_principal_payment());
        }
        
        liability_payments_ledgers.add(ledger);
    }
    
    private Double get_principal_payment() {
        return config.monthly_payment - get_mortgage_interest();
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
            ledger.add_transaction("ARV gain", calculate_arv_gain());
        }
        
        ledger.add_transaction("Appreciation", get_appreciation());
    }
    
    private Double calculate_arv_gain() {
        return config.value_after_repair 
            - config.init_asset_value 
            - config.initial_repair_costs;
    }
    
    private Double get_appreciation() {
        return get_prev_asset_value() * config.appreciation_rate;
    }
    
}
