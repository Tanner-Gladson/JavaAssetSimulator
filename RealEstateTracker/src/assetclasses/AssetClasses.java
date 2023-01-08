package assetclasses;
import java.util.ArrayList;

public class AssetClasses {
    
    abstract public class Asset {
        
    }
    
    public class SimulatedAsset {
        
        public int num_months = 0;
        // FROM ASSET ASSUMPTIONS
        public double init_equity;
        public double init_liabilities;
        public double init_asset_value;
        
        public ArrayList<Double> asset_value;
        public ArrayList<Double> revenue;
        public ArrayList<Double> expenses;
        public ArrayList<Double> liability_payments;
        public ArrayList<Double> additional_investments;
        
        // CALCULABLE FROM ABOVE FIELDS
        // THESE WILL BE UPDATED BY UNIVERSAL METHODS
        public ArrayList<Double> cash_flow;
        public ArrayList<Double> liabilities;
        public ArrayList<Double> equity;
        public ArrayList<Double> investment_base; // Aka how much income you've invested
        public ArrayList<Double> unrealized_appreciation; // Aka change in equity not due to liability payment or invesment
        public ArrayList<Double> effective_income;
        public ArrayList<Double> annual_ROI_extrapolated;
        
        public SimulatedAsset() {
            asset_value = new ArrayList<Double>();
            revenue = new ArrayList<Double>();
            expenses = new ArrayList<Double>();
            liability_payments = new ArrayList<Double>();
            cash_flow = new ArrayList<Double>();
            liabilities = new ArrayList<Double>();
            equity = new ArrayList<Double>();
            unrealized_appreciation = new ArrayList<Double>();
            effective_income = new ArrayList<Double>();
            annual_ROI_extrapolated = new ArrayList<Double>();
        }
        
        public void reset() {
            num_months = 0;
            asset_value.clear();
            revenue.clear();
            expenses.clear();
            liability_payments.clear();
            cash_flow.clear();
            liabilities.clear();
            equity.clear();
            unrealized_appreciation.clear();
            effective_income.clear();
            annual_ROI_extrapolated.clear();
        }
        
        public void extend() {
            
            append_cash_flow();
            append_liabilities();
            append_unrealized_appreciation();
            append_equity();
            append_effective_income();
            append_extrapolated_ROI();
            num_months += 1;
        }
        
        private void append_cash_flow() {
            double inc = revenue.get(num_months);
            inc -= expenses.get(num_months);
            inc -= liability_payments.get(num_months);
            inc -= additional_investments.get(num_months); 
            cash_flow.add(inc);
        }
        
        private void append_liabilities() {
            double old_liability;
            if (num_months == 0) {
                old_liability = init_liabilities;
            } else {
                old_liability = liabilities.get(num_months-1);
            }
            
            liabilities.add(old_liability - liability_payments.get(num_months));
        }
        
        private void append_unrealized_appreciation() {
            
        }
        
        
        private void append_equity() { 
            double old_equity = get_prev_equity();
            double equity_change = get_equity_change();
            
            equity.add(old_equity + equity_change);
        }
        
        private double get_prev_equity() {
            if (num_months == 0) {
                return init_equity;
            } else {
                return equity.get(num_months-1);
            } 
        }
        
        private double get_equity_change() {
            double change = 0;
            change += additional_investments.get(num_months);
            change += liability_payments.get(num_months);
            change += unrealized_appreciation.get(num_months);
            return change;
        }
        
        
        private void append_effective_income() {
            
        }
        
        private void append_extrapolated_ROI() {
            
        }
        
        
    }
}
