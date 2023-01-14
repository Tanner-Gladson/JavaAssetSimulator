package bond;
import assetinterfaces.Asset;
import assetinterfaces.SimulatedAsset;

public class Bond extends Asset {
    public int term_months;
    public int return_freq = 6;
    public double annual_coupon_yield;
    public double principal;
    
    
    
    public Bond(String name, double purchase_price, double principal, double maturity, double annual_coupon_yield) {
        super(name, 0, purchase_price, purchase_price);
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    public Bond(String name, double purchase_price, double principal, double maturity, double annual_coupon_yield, int payment_freq) {
        super(name, 0, purchase_price, purchase_price);
        this.return_freq = payment_freq;
        set_fields(principal, maturity, annual_coupon_yield);
    }
    
    
    // TODO: THIS IS A VERY POOR SOLUTION. Is there a better way to generate
    // a list of interface objects from another list of interface objects,
    // where different implimentations of the interface functions will
    // have different constructors.
    @Override
    public SimulatedAsset create_simulation(int num_months) {
        return new SimulatedBond(this, num_months);
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
