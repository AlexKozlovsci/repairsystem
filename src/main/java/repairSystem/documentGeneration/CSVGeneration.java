package repairSystem.documentGeneration;

import com.opencsv.CSVWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */
public class CSVGeneration {

    public static ByteArrayOutputStream generateSomeCSV() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("cp1251"));
        CSVWriter writer = new CSVWriter(osw, ';');
        String[] csvHeader = {"some colum 1", "some colum 2", "..."};
        writer.writeNext(csvHeader);
        List<String[]> dataToWrite = new ArrayList<>();

        //Add some data to dataWrite

        writer.writeAll(dataToWrite);
        writer.close();

        return stream;
    }

}
