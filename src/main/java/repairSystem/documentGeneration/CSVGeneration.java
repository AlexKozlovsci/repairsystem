package repairSystem.documentGeneration;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import repairSystem.dao.PricelistRepository;
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

    private PricelistRepository pricelistRepository;

    private static final Logger log = Logger.getLogger(CSVGeneration.class);


    public ByteArrayOutputStream generateCSV(PricelistRepository psr) throws IOException {
        pricelistRepository = psr;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("cp1251"));
        CSVWriter writer = new CSVWriter(osw, ',');

        List<String[]> dataToWrite = getPriceCurrentList();

        writer.writeAll(dataToWrite);
        writer.close();
        return stream;
    }



    private  List<String[]> getPriceCurrentList(){
        List<String[]> priceCurrentList = new ArrayList<>();
        priceCurrentList.add(new String[]{"Device", "Action", "Cost"});
        List<Pricelist> prices = pricelistRepository.findAll();
        for (Iterator<Pricelist> i = prices.iterator(); i.hasNext();) {
            Pricelist item = i.next();
            Integer temp = (int)item.getCost();
            priceCurrentList.add(new String[]{item.getDeviceType(), item.getAction(), temp.toString()});
        }
        log.info(4);
        return priceCurrentList;
    }

}
