package repairSystem.documentGeneration;




import com.itextpdf.text.DocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.jpa.repository.JpaRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */
public class XLSGeneration {

    public ByteArrayOutputStream generateSome(JpaRepository psr, String sheetName) throws FileNotFoundException, IOException, DocumentException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(sheetName);
        List<String[]> priceList = DataLoad.getPriceCurrentList((PricelistRepository)psr);

        int j =0;
        for (String[] str: priceList) {
            Row row = sheet.createRow(j++);
            int i = 0;
            for (String elem: str) {
                Cell name = row.createCell(i++);
                name.setCellValue(elem);
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        book.write(stream);
        book.close();
        return stream;
    }


}
