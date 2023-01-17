package assetinterfaces;

import java.io.FileInputStream;

public class AssetUtilities {
    
    public static void simulation2xlsx(SimulatedAsset sim) {
        
        try {
            FileInputStream file = new FileInputStream("ExcelExports\test.xlsx");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
