package assetclasses;

import java.security.Principal;

public class Bond extends StdAsset {
    int term_months;
    int return_freq = 6;
    double coupon_yield;
    double principal;
    
    
    // TODO: Purchase price & Asset Value != principal
    Bond(double principal, double maturity, double annual_coupon_yield) {
        super(0, principal, principal);
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    Bond(double principal, double maturity, double annual_coupon_yield, int payment_freq) {
        super(0, principal, principal);
        return_freq = payment_freq;
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    
    protected void set_fields(double principal, double maturity, double annual_coupon_yield) {
        term_months = (int) maturity * 12;
        coupon_yield = annual_coupon_yield;
        this.principal = principal;
        
        double interest_rate = coupon_yield * (12 / return_freq);
        fractional_revenues.put("Coupon Yield", interest_rate);
    }
    
    
    protected void extend_simulation_revenue() {
        
        if (simulation.month == 0 && return_freq != 1) {
            simulation.revenue.add(0.0);
        }
        
        if (simulation.month % return_freq == 0) {
            double revenue = get_frac_revenue_sum() * principal;
            simulation.revenue.add(revenue);
        } else {
            simulation.revenue.add(0.0);
        }
        
    }
}
