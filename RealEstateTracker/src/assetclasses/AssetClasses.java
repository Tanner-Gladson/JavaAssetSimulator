package assetclasses;
import java.util.ArrayList;

public class AssetClasses {
    
    abstract public class Asset {
        
    }
    
    public class SimulatedAsset {
        
        public int num_months;
        // FROM ASSET ASSUMPTIONS
        public ArrayList<Double> asset_value;
        public ArrayList<Double> revenue;
        public ArrayList<Double> expenses;
        public ArrayList<Double> liability_payments;
        
        // CALCULABLE FROM ABOVE FIELDS
        // THESE WILL BE UPDATED BY UNIVERSAL METHODS
        public ArrayList<Double> liquid_income;
        public ArrayList<Double> liabilities;
        public ArrayList<Double> equity;
        public ArrayList<Double> unrealized_appreciation; // Aka change in equity not due to liability payment or invesment
        public ArrayList<Double> effective_income;
        public ArrayList<Double> annual_ROI_extrapolated;
        
        public SimulatedAsset() {
            asset_value = new ArrayList<Double>();
            revenue = new ArrayList<Double>();
            expenses = new ArrayList<Double>();
            liability_payments = new ArrayList<Double>();
            liquid_income = new ArrayList<Double>();
            liabilities = new ArrayList<Double>();
            equity = new ArrayList<Double>();
            unrealized_appreciation = new ArrayList<Double>();
            effective_income = new ArrayList<Double>();
            annual_ROI_extrapolated = new ArrayList<Double>();
        }
        
        public void reset() {
            asset_value.clear();
            revenue.clear();
            expenses.clear();
            liability_payments.clear();
            liquid_income.clear();
            liabilities.clear();
            equity.clear();
            unrealized_appreciation.clear();
            effective_income.clear();
            annual_ROI_extrapolated.clear();
        }
        
        public void extend() {
            
        }
        
        private void append_liquid_income() {
            
        }
        
        private void append_liabilities() {
            
        }
        
        private void append_unrealized_appreciation() {
            
        }
        
        private void append_equity() {
            
        }
        
        private void append_effective_income() {
            
        }
        
        private void append_extrapolated_ROI() {
            
        }
        
        
    }
}
