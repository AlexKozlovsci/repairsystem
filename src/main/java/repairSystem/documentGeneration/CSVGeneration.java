package repairSystem.documentGeneration;

import com.itextpdf.text.DocumentException;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */

public class CSVGeneration {


    private final Logger log = Logger.getLogger(CSVGeneration.class);


    public ByteArrayOutputStream generatePricelist(JpaRepository psr, String fileName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getPriceCurrentList((PricelistRepository)psr);
        return generate(fileName, dataToWrite);
    }

    public ByteArrayOutputStream generateMonthReport(JpaRepository psr, JpaRepository usr, String fileName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getMonthReportList((WorkorderRepository)psr, (UserRepository)usr);
        return generate(fileName, dataToWrite);
    }

    public ByteArrayOutputStream generateProcurementSheet(JpaRepository psr, String fileName) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getProcurementSheetList((DetailRepository)psr);
        return generate(fileName, dataToWrite);
    }

    public ByteArrayOutputStream generatePaymentRecipe(JpaRepository psr, String fileName, int id) throws IOException, DocumentException {
        List<String[]> dataToWrite = DataLoad.getPaymentRecipeList((WorkorderRepository)psr, id);
        return generate(fileName, dataToWrite);
    }

    private ByteArrayOutputStream generate(String fileName, List<String[]> dataToWrite) throws IOException, DocumentException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("cp1251"));
        CSVWriter writer = new CSVWriter(osw, ',');
        writer.writeNext(new String[]{fileName});
        writer.writeAll(dataToWrite);
        writer.close();
        return stream;
    }

}
