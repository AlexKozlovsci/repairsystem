package repairSystem.controller;


import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repairSystem.documentGeneration.CSVGeneration;
import repairSystem.documentGeneration.PDFGeneration;
import repairSystem.documentGeneration.XLSGeneration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by Глеб on 24.05.2017.
 */

@Controller
public class DocumentController {

    private static final Logger log = Logger.getLogger(AdminController.class);

    private CSVGeneration csvGen = new CSVGeneration();


    @RequestMapping(value = "/document/getcsv", method = RequestMethod.GET)
    public void getCsv(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        ByteArrayOutputStream stream = csvGen.generateCSV();
        response.setContentType("application/csv");
        response.setContentLength(stream.toByteArray().length);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "someFileName.csv");
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        outStream.write(stream.toByteArray());

        outStream.close();
    }

    @RequestMapping(value = "/document/getxls", method = RequestMethod.GET)
    public void getXls(final HttpServletRequest request,
                           final HttpServletResponse response) throws IOException {

        response.setContentType("application/xls");
        response.setContentLength(XLSGeneration.generateSomeXLS().toByteArray().length);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "someFileName.xls");
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        outStream.write(XLSGeneration.generateSomeXLS().toByteArray());

        outStream.close();
    }

    @RequestMapping(value = "/document/getpdf", method = RequestMethod.GET)
    public void getPdf(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException, DocumentException {
        log.info("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        try {


            response.setContentType("application/pdf");
            response.setContentLength(PDFGeneration.generateSomePDF().toByteArray().length);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    "someFileName.pdf");
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();

            outStream.write(PDFGeneration.generateSomePDF().toByteArray());

            outStream.close();
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
    }


}
