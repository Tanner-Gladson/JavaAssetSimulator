package portfolioclasses;
import java.util.ArrayList;

public class PortfolioCollection {
    String name;
    ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
    ArrayList<SimulatedPortfolio> simulations = new ArrayList<SimulatedPortfolio>();
    
    public PortfolioCollection(String name) {
        this.name = name;
    }
     
    
    public void add_portfolio(Portfolio p) {
        portfolios.add(p);
    }
    
    public void simulate_portfolios(int num_months) {
        for (Portfolio p : portfolios) {
            simulations.add(new SimulatedPortfolio(p, num_months));
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s, a portfolio collection", name);
    }
}
