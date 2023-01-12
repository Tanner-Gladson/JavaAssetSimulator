package assetclasses;

public class Bond extends BasicAsset {
    int term_months;
    int return_freq = 6;
    double annual_coupon_yield;
    double principal;
    
    
    
    public Bond(double purchase_price, double principal, double maturity, double annual_coupon_yield) {
        super(0, purchase_price, purchase_price);
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    public Bond(double purchase_price, double principal, double maturity, double annual_coupon_yield, int payment_freq) {
        super(0, purchase_price, purchase_price);
        this.return_freq = payment_freq;
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    
    protected void set_fields(double principal, double maturity, double annual_coupon_yield) {
        this.term_months = (int) maturity * 12;
        this.annual_coupon_yield = annual_coupon_yield;
        this.principal = principal;
    }
    
    
    protected void extend_revenue_ledger() {
        Ledger ledger = new Ledger();
        
        if ( (simulation.month + 1) % return_freq == 0 && 
            !(simulation.month >= term_months)) 
        {
            double interest_rate = annual_coupon_yield * ( (double) return_freq / 12);
            ledger.add_transaction("Coupon Yield", interest_rate * principal);
        }
        
        simulation.revenue_ledger.add(ledger); 
        
    }
    
	protected void extend_expenses_ledger() {
        simulation.expense_ledger.add(new Ledger());
    }
    
    
    protected void extend_liability_payments_ledger() {
        simulation.liability_payments_ledger.add(new Ledger());
    }
    
    
    protected void extend_additional_investment_ledger() {
        
        Ledger ledger = new Ledger();
        
        if (simulation.month + 1 == term_months) {
            ledger.add_transaction("Sale at Maturity", -principal);
        }
        
        simulation.additional_investments_ledger.add(ledger);
    }
    
    
    protected void extend_capital_gains_ledger() {
        Ledger ledger = new Ledger();
        
        if (simulation.month + 1 == term_months) {
            ledger.add_transaction("Maturity", principal - simulation.get_prev_asset_value());
        }
        
        simulation.capital_gains_ledger.add(ledger);
    }
    
}
