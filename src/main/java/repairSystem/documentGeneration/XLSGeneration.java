package repairSystem.documentGeneration;


import com.itextpdf.text.DocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.jpa.repository.JpaRepository;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;

import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;

import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */
public class XLSGeneration {

    public ByteArrayOutputStream generatePricelist(JpaRepository psr, String sheetName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getPriceCurrentList((PricelistRepository)psr);
        return generate(sheetName, dataToWrite);
    }

    public ByteArrayOutputStream generateMonthReport(JpaRepository psr, JpaRepository usr, String sheetName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getMonthReportList((WorkorderRepository)psr, (UserRepository)usr);
        return generate(sheetName, dataToWrite);
    }

    public ByteArrayOutputStream generateProcurementSheet(JpaRepository psr, String sheetName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getProcurementSheetList((DetailRepository)psr);
        return generate(sheetName, dataToWrite);
    }

    public ByteArrayOutputStream generatePaymentRecipe(JpaRepository psr, String fileName, int id) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getPaymentRecipeList((WorkorderRepository)psr, id);
        return generate(fileName, dataToWrite);
    }


    public ByteArrayOutputStream generate(String sheetName, List<String[]> dataToWrite) throws FileNotFoundException, IOException, DocumentException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(sheetName);

        int j = 0;
        int i = 0;
        for (String[] str: dataToWrite) {
            Row row = sheet.createRow(j++);
            i = 0;
            for (String elem: str) {
                Cell name = row.createCell(i++);
                name.setCellValue(elem);
            }
        }

        for (int g = 0; g < i; g++){
            sheet.autoSizeColumn(g);

        }
        book.write(stream);
        book.close();
        return stream;
    }

    public ByteArrayOutputStream generateDetailList(JpaRepository psr, String sheetName, int orderid) throws FileNotFoundException, IOException, DocumentException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(sheetName);
        List<String[]> detail = DataLoad.getDetailList( (WorkorderRepository)psr,orderid);
        int j = 0;
        int i = 0;
        Row row = sheet.createRow(j++);
        Cell name = row.createCell(i++);
        name.setCellValue("Order number ");
        name = row.createCell(i++);
        name.setCellValue(orderid);
        name = row.createCell(i++);
        for (String[] str: detail) {
            row = sheet.createRow(j++);
            i = 0;
            for (String elem: str){
                name = row.createCell(i++);
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
