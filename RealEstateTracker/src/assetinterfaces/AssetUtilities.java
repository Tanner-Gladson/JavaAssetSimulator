package assetinterfaces;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;


public class AssetUtilities {
    
    public static void simulation2xlsx(SimulatedAsset sim) {
        try {
            
            XSSFWorkbook workbook = new XSSFWorkbook();
            write_data(workbook, sim);
            

            XSSFSheet revenue = workbook.createSheet("Revenue Ledger");
            XSSFSheet expense = workbook.createSheet("Expense Ledger");
            XSSFSheet liability_payments = workbook.createSheet("Liability Ledger");
            XSSFSheet additional_investments = workbook.createSheet("Investments Ledger");
            XSSFSheet capital_gains = workbook.createSheet("Capital Gains Ledger");
            
            ledgerOn2Sheet(sim.revenue_ledgers, revenue, workbook);
            ledgerOn2Sheet(sim.expense_ledgers, expense, workbook);
            ledgerOn2Sheet(sim.liability_payments_ledgers, liability_payments, workbook);
            ledgerOn2Sheet(sim.additional_investments_ledgers, additional_investments, workbook);
            ledgerOn2Sheet(sim.capital_gains_ledgers, capital_gains, workbook);
            

            FileOutputStream outfile = new FileOutputStream("ExcelExports/temp.xlsx");
            workbook.write(outfile);
            workbook.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    private static void ledgerOn2Sheet(ArrayList<Ledger> ledgers, XSSFSheet sheet, XSSFWorkbook wb) {
        
        int max_ledger_length = 0;
        for (Ledger ledger : ledgers) {
            max_ledger_length = Math.max(ledger.size(), max_ledger_length);
        }
        
        
        // Create the header row
        XSSFCellStyle style = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        XSSFRow header_row = sheet.createRow(0);
        for (int month = 0; month < ledgers.size(); month++) {
            XSSFCell cell = header_row.createCell(month * 3);
            cell.setCellValue(String.format("Month: %d", month));
            cell.setCellStyle(style);
            
        }
        
        
        // Iterate through remaining rows, adding where necessary.
        ArrayList<XSSFRow> all_rows = new ArrayList<XSSFRow>();
        for (int i = 0; i < max_ledger_length; i++) {
            all_rows.add(sheet.createRow(i+1));
        }
        
        
        for (int month = 0; month < ledgers.size(); month++) {
            int row_num = 0;
            
            for (String key : ledgers.get(month).keySet()) {
                
                XSSFCell name_cell = all_rows.get(row_num).createCell(month*3);
                name_cell.setCellValue(key);
                
                XSSFCell value_cell = all_rows.get(row_num).createCell(month*3 + 1);
                value_cell.setCellValue(ledgers.get(month).get(key));
                row_num++;
            }
            
        }
    }
    
    private static void write_data(XSSFWorkbook workbook, SimulatedAsset sim) {
        ArrayList<String> header_names = get_header_names();
        ArrayList<ArrayList<Double>> sim_data_fields = get_data_fields(sim);
        XSSFSheet data = workbook.createSheet("Data");
        
        // Write the header
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        
        XSSFRow header = data.createRow(0);
        for (int i = 0; i < header_names.size(); i++) {
            XSSFCell cell = header.createCell(i, CellType.STRING);
            cell.setCellStyle(style);
            cell.setCellValue(header_names.get(i));
        }
        
        
        // For every month, write every data field.
        for (int month = 0; month < sim.month; month++) {
            XSSFRow row = data.createRow(month+1);
            XSSFCell c = row.createCell(0, CellType.NUMERIC);
            c.setCellValue(month);
            
            for (int i = 0; i < sim_data_fields.size(); i ++) {
                XSSFCell cell = row.createCell(i+1, CellType.NUMERIC);
                cell.setCellValue(sim_data_fields.get(i).get(month));
            }
        }
    }
    
    private static ArrayList<String> get_header_names() {
        ArrayList<String> header_names = new ArrayList<String>();
        header_names.add("month");
        header_names.add("revenue");
        header_names.add("expenses");
        header_names.add("liability_payments");
        header_names.add("additional_investments");
        header_names.add("capital_gains_month");
        header_names.add("cash_flow");
        header_names.add("liabilities");
        header_names.add("equity");
        header_names.add("asset_value");
        header_names.add("invested_capital");
        header_names.add("effective_income");
        header_names.add("annual_ROI_extrapolated");
        return header_names;
    }
    
    private static ArrayList<ArrayList<Double>> get_data_fields(SimulatedAsset sim) {
        ArrayList<ArrayList<Double>> sim_data_fields = new ArrayList<ArrayList<Double>>();
        sim_data_fields.add(sim.revenue);
        sim_data_fields.add(sim.expenses);
        sim_data_fields.add(sim.liability_payments);
        sim_data_fields.add(sim.additional_investments);
        sim_data_fields.add(sim.capital_gains_month);
        sim_data_fields.add(sim.cash_flow);
        sim_data_fields.add(sim.liabilities);
        sim_data_fields.add(sim.equity);
        sim_data_fields.add(sim.asset_value);
        sim_data_fields.add(sim.invested_capital);
        sim_data_fields.add(sim.effective_income);
        sim_data_fields.add(sim.annual_ROI_extrapolated);
        return sim_data_fields;
    }
}
