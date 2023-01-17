package assetinterfaces;

import java.io.FileInputStream;
import org.apache.poi.xssf.*;


public class AssetUtilities {
    
    public static void simulation2xlsx(SimulatedAsset sim) {
        FileInputStream file;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            
            //XSSFSheet summary = workbook.createSheet();
            
            //XSSFRow revenues = summary.createRow(0);
            //XSSFCell c =  revenues.createCell(0);
            //c.setCellValue(true);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
}
