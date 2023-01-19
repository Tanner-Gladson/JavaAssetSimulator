package assetinterfaces;
import java.util.ArrayList;
import java.lang.Math;


public abstract class SimulatedAsset {
    
    
    public String name;
    public int month = 0;
    
    public double init_equity;
    public double init_liabilities;
    public double init_asset_value;
    
    public ArrayList<Ledger> revenue_ledgers;
    public ArrayList<Ledger> expense_ledgers;
    public ArrayList<Ledger> liability_payments_ledgers;
    public ArrayList<Ledger> additional_investments_ledgers;
    public ArrayList<Ledger> capital_gains_ledgers;
    
    
    public ArrayList<Double> revenue;
    public ArrayList<Double> expenses;
    public ArrayList<Double> liability_payments;
    public ArrayList<Double> additional_investments;
    public ArrayList<Double> capital_gains_month; // Aka change in equity not due to liability payment or invesment
    
    public ArrayList<Double> cash_flow;
    public ArrayList<Double> liabilities;
    public ArrayList<Double> equity;
    public ArrayList<Double> asset_value;
    public ArrayList<Double> invested_capital; // Aka how much income you've invested
    public ArrayList<Double> effective_income;
    public ArrayList<Double> annual_ROI_extrapolated;
    
    public SimulatedAsset(Asset config) {
        this.name = config.name;
        this.init_equity = config.init_equity;
        this.init_liabilities = config.init_liabilities;
        this.init_asset_value = config.init_asset_value;
        
        revenue_ledgers = new ArrayList<Ledger>();
        expense_ledgers = new ArrayList<Ledger>();
        liability_payments_ledgers = new ArrayList<Ledger>();
        additional_investments_ledgers = new ArrayList<Ledger>();
        capital_gains_ledgers = new ArrayList<Ledger>();
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
        
    }
    
    protected abstract void append_revenue_ledger();
	protected abstract void append_expenses_ledger();
    protected abstract void append_liability_payments_ledger();
    protected abstract void append_additional_investment_ledger();
    protected abstract void append_capital_gains_ledger();
    
    protected void run_simulation(int num_months) {
        for (int i = 0; i < num_months; i++) {
            append_revenue_ledger();
            append_expenses_ledger();
            append_liability_payments_ledger();
            append_additional_investment_ledger();
            append_capital_gains_ledger();
            
            append_revenue();
            append_expenses();
            append_liability_payments();
            append_additional_investment();
            append_capital_gains();
            
            append_cash_flow();
            append_asset_value();
            append_liabilities();
            append_equity();
            append_invested_capital();
            
            append_effective_income();
            append_extrapolated_ROI();
            month += 1;
        }
    }
    
    private void append_revenue() {
        revenue.add(revenue_ledgers.get(month).total());
    }
    
	private void append_expenses() {
        expenses.add(expense_ledgers.get(month).total());
    }
    
    private void append_liability_payments() {
        liability_payments.add(liability_payments_ledgers.get(month).total());
    }
    
    private void append_additional_investment() {
        additional_investments.add(additional_investments_ledgers.get(month).total());
    }
    
    private void append_capital_gains() {
        capital_gains_month.add(capital_gains_ledgers.get(month).total());
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
            get_prev_asset_value() 
            + additional_investments.get(month)
            + capital_gains_month.get(month)
        );
    }
    
    public double get_prev_asset_value() {
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
        double base = get_prev_invested_capital()
                    + additional_investments.get(month)
                    + liability_payments.get(month);
                    
        if (base < 0) {
            invested_capital.add(0.0);
        } else {
            invested_capital.add(base);
        }
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
            capital_gains_month.get(month) 
            + revenue.get(month) 
            - expenses.get(month)
            - liability_payments.get(month)
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
