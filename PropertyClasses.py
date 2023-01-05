import numpy as np

class Mortgage:
    def __init__(self, starting_principal: float, apr: float, mortgage_length_years: float):
        self.starting_principal = starting_principal
        self.apr = apr
        self.mpr = apr/12
        self.mortgage_length_months = mortgage_length_years*12
        self.pmt = self.find_monthly_payment()
        
    
    def sunset_mortgage(self):
        self.apr = None
        self.mpr = None
        self.mortgage_length_months = 0
        self.pmt = 0
    
    def find_monthly_payment(self):
        num = (self.starting_principal * self.mpr * (1 + self.mpr)**(self.mortgage_length_months))
        denom = ((1 + self.mpr)**(self.mortgage_length_months) - 1)
        return num / denom


    def find_true_monthly_cost_array(self, monthly_maintenance=500, annual_property_tax_rate=0.01):
        x = np.arange(0, self.mortgage_length_months)
        
        remaining_princ = (self.pmt / self.mpr) * (1 - (1/(1+self.mpr))**(self.mortgage_length_months - x))
        
        total = remaining_princ * self.mpr
        total += monthly_maintenance
        total += self.starting_principal * annual_property_tax_rate/12
        return total

''' 
Methods:
    set_....() -> None
    simulate() -> None
    
'''    
class Property:
    
    def __init__(self):
        #----SET---#
        self.purchase_price = 0
        self.annual_percent_market_change = 0
        self.mortgage = Mortgage(None)
        
        self.gross_monthly_rent_income = 0
        self.static_monthly_fees = 0
        self.percentage_monthly_fees = 0
        
        #----SIMULATE BY MONTH (Lists)---#
        self.market_value = []
        self.gross_monthly_loss = []
        self.net_realized_monthly_income = []
        
        self.mortgage_balance = []
        self.net_monnthy_liquid_income_after_mortgage_pmt = []
        self.investment_base = []
        
        self.cumulative_realized_income = []
        self.unrealized_capital_gains = []
        self.monthly_roi = []
        self.extrapolated_annual_roi = []
    
    def set_purchase_price(self, price: float):
        self.purchase_price = price
    
    def set_annual_percent_market_change(self, rate: float):
        self.annual_percent_market_change = rate
    
    def set_mortage(self, starting_principal: float, apr: float, mortgage_length_years: float):
        self.mortgage = Mortgage(starting_principal, apr, mortgage_length_years)
    
    def set_gross_monthly_rent_income(self, rent: float):
        self.gross_monthly_rent_income = rent
    
    def set_static_monthly_costs(self, maintenance_costs: dict):
        self.monthly_maintenance = maintenance_costs
    
    def set_percentage_monthly_costs(self, rate: dict):
        self.annual_property_tax_rate = rate
        
        
    def simulate(self, number_of_years: int):
        self.reset_simulations()
        
        for i in range(0, number_of_years):
            self.extend_market_value()
            self.extend_gross_monthly_loss()
            self.extend_net_realized_monthly_income()
            
            self.extend_mortgage_balance()
            self.extend_net_monnthy_liquid_income_after_mortgage_pmt()
            self.extend_investment_base()
            
            self.extend_cumulative_realized_income()
            self.extend_unrealized_capital_gains()
            self.extend_monthly_roi()
            self.extend_extrapolated_annual_roi()
        
    
    
    def reset_simulations(self):
        self.market_value = []
        self.gross_monthly_loss = []
        self.net_realized_monthly_income = []
        
        self.mortgage_balance = []
        self.net_monnthy_liquid_income_after_mortgage_pmt = []
        self.investment_base = []
        
        self.cumulative_realized_income = []
        self.unrealized_capital_gains = []
        self.monthly_roi = []
        self.running_annual_roi = []
    
    def extend_market_value(self):
        if len(self.market_value) == 0:
            self.market_value.append(self.purchase_price)
        else:
            self.market_value.append(self.market_value[-1] * (1 + self.annual_percent_market_change))
    
    def extend_gross_monthly_loss(self):
        # Mortgage Interest
        # Percentage monthly fees
        # Static monthly fees
        
        pass
    
    def extend_net_realized_monthly_income(self):
        pass
    
    
    def extend_mortgage_balance(self):
        pass
    
    def extend_net_monnthy_liquid_income_after_mortgage_pmt(self):
        pass
    
    def extend_investment_base(self):
        pass
    
    
    def extend_cumulative_realized_income(self):
        pass
    
    def extend_unrealized_capital_gains(self):
        pass
    
    def extend_monthly_roi(self):
        pass
    
    def extend_extrapolated_annual_roi(self):
        pass
    
        
