package bond;

import assetinterfaces.Asset;
import assetinterfaces.Ledger;

public class Bond extends Asset {
    int term_months;
    int return_freq = 6;
    double annual_coupon_yield;
    double principal;
    
    
    
    public Bond(String name, double purchase_price, double principal, double maturity, double annual_coupon_yield) {
        super(name, 0, purchase_price, purchase_price);
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    public Bond(String name, double purchase_price, double principal, double maturity, double annual_coupon_yield, int payment_freq) {
        super(name, 0, purchase_price, purchase_price);
        this.return_freq = payment_freq;
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    
    protected void set_fields(double principal, double maturity, double annual_coupon_yield) {
        this.term_months = (int) maturity * 12;
        this.annual_coupon_yield = annual_coupon_yield;
        this.principal = principal;
    }
    
    @Override
    public String toString() {
        return String.format("%s, a bond", simulation.name);
    }
}
