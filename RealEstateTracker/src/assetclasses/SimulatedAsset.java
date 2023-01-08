package assetclasses;
import java.util.ArrayList;
import java.lang.Math;

public class SimulatedAsset {
        
    public int month = 0;
    // FROM ASSET ASSUMPTIONS
    public double init_equity;
    public double init_liabilities;
    public double init_asset_value;
    
    public ArrayList<Double> revenue;
    public ArrayList<Double> expenses;
    public ArrayList<Double> liability_payments;
    public ArrayList<Double> additional_investments;
    public ArrayList<Double> capital_gains_month; // Aka change in equity not due to liability payment or invesment
    
    
    // CALCULABLE FROM ABOVE FIELDS
    // THESE WILL BE UPDATED BY UNIVERSAL METHODS
    public ArrayList<Double> cash_flow;
    public ArrayList<Double> liabilities;
    public ArrayList<Double> equity;
    public ArrayList<Double> asset_value;
    public ArrayList<Double> invested_capital; // Aka how much income you've invested
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
        capital_gains_month = new ArrayList<Double>();
        effective_income = new ArrayList<Double>();
        annual_ROI_extrapolated = new ArrayList<Double>();
        additional_investments = new ArrayList<Double>();
        invested_capital = new ArrayList<Double>();
        
        init_asset_value = init_equity + init_liabilities;
    }
    
    public void reset() {
        month = 0;
        init_asset_value = init_equity + init_liabilities;
        asset_value.clear();
        revenue.clear();
        expenses.clear();
        liability_payments.clear();
        cash_flow.clear();
        liabilities.clear();
        equity.clear();
        capital_gains_month.clear();
        effective_income.clear();
        annual_ROI_extrapolated.clear();
        additional_investments.clear();
        invested_capital.clear();
    }
    
    public void extend() {
        
        append_cash_flow();
        append_asset_value();
        append_liabilities();
        append_equity();
        append_invested_capital();
        
        append_effective_income();
        append_extrapolated_ROI();
        month += 1;
    }
    
    private void append_cash_flow() {
        double inc = revenue.get(month);
        inc -= expenses.get(month);
        inc -= liability_payments.get(month);
        inc -= additional_investments.get(month); 
        cash_flow.add(inc);
    }
    
    
    private void append_asset_value() {
        asset_value.add(  
            prev_asset_value() 
            + additional_investments.get(month)
            + capital_gains_month.get(month)
        );
    }
    
    private double prev_asset_value() {
        if (month == 0) {
            return init_asset_value;
        } else {
            return asset_value.get(month-1);
        }
    }
    
    
    private void append_liabilities() {
        double old_liability = get_prev_liability();
        liabilities.add(old_liability - liability_payments.get(month));
    }
    
    private double get_prev_liability() {
        if (month == 0) {
            return init_liabilities;
        } else {
            return liabilities.get(month-1);
        } 
    }
    
    
    private void append_equity() { 
        equity.add(get_prev_equity() + get_equity_change());
    }
    
    private double get_prev_equity() {
        if (month == 0) {
            return init_equity;
        } else {
            return equity.get(month-1);
        } 
    }
    
    private double get_equity_change() {
        double change = 0;
        change += additional_investments.get(month);
        change += liability_payments.get(month);
        change += capital_gains_month.get(month);
        return change;
    }
    
    private void append_invested_capital() {
        
        invested_capital.add(
            get_prev_invested_capital()
            + additional_investments.get(month)
            + liability_payments.get(month)
        );
    }
    
    private double get_prev_invested_capital() {
        if (month == 0) {
            return init_equity;
        } else {
            return invested_capital.get(month-1);
        }
    }
    
    private void append_effective_income() {
        effective_income.add(
            cash_flow.get(month) + capital_gains_month.get(month)
        );
    }
    
    private void append_extrapolated_ROI() {
        
        if (equity.get(month) == 0) {
            annual_ROI_extrapolated.add(Double.MAX_VALUE);
            return;
        }
        double monthly_ROI = effective_income.get(month) / equity.get(month);
        annual_ROI_extrapolated.add(Math.pow(1 + monthly_ROI, 12) - 1);
    }
    
    
}
