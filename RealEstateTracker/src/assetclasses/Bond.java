package assetclasses;

public class Bond extends StdAsset {
    int term_months;
    int return_freq = 6;
    
    Bond(double principal, double maturity, double annual_coupon_yield) {
        super(0, principal, principal);
        term_months = (int) maturity * 12;
        set_fields();
    }
    
    Bond(double principal, double maturity, double annual_coupon_yield, int payment_freq) {
        super(0, principal, principal);
        term_months = (int) maturity * 12;
        return_freq = payment_freq;
    }
    
    private void set_fields() {
        
    }
    
    // Need to overload the extend_revenue function because frac_revenue is
    // only applied every 6 months.
    
    protected void extend_simulation_revenue() {
        double revenue = get_frac_revenue_sum() + get_static_revenue_sum();
        simulation.revenue.add(revenue);
    }
}
