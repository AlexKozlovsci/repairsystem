package repairSystem.documentGeneration;

import com.itextpdf.text.DocumentException;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Pricelist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */

public class CSVGeneration {


    private final Logger log = Logger.getLogger(CSVGeneration.class);


    public ByteArrayOutputStream generatePricelist(JpaRepository psr) throws IOException, DocumentException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("cp1251"));
        CSVWriter writer = new CSVWriter(osw, ',');

        List<String[]> dataToWrite = DataLoad.getPriceCurrentList((PricelistRepository)psr);
        writer.writeNext(new String[]{"PriceList"});

        writer.writeAll(dataToWrite);
        writer.close();
        return stream;
    }

    public ByteArrayOutputStream generateDetailsList(JpaRepository psr,int orderid) throws IOException, DocumentException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("cp1251"));
        CSVWriter writer = new CSVWriter(osw, ',');

        List<String[]> dataToWrite = DataLoad.getDetailList((WorkorderRepository)psr,orderid);
        writer.writeNext(new String[]{"Detail List"});
        writer.writeNext(new String[]{"Order number: ",String.valueOf(orderid)});
        writer.writeAll(dataToWrite);
        writer.close();
        return stream;
    }

}
