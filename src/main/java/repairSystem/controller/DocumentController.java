package repairSystem.controller;


import com.itextpdf.text.DocumentException;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.documentGeneration.CSVGeneration;
import repairSystem.documentGeneration.XLSGeneration;
import repairSystem.documentGeneration.PDFGeneration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Глеб on 24.05.2017.
 */

@Controller
public class DocumentController {

    private static final Logger log = Logger.getLogger(AdminController.class);


        @RequestMapping(value = "/document/getcsv", method = RequestMethod.GET)
        public void getCsv(final HttpServletRequest request,
                final HttpServletResponse response) throws IOException {

            response.setContentType("application/csv");
            response.setContentLength(CSVGeneration.generateSomeCSV().toByteArray().length);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    "someFileName.csv");
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();

            outStream.write(CSVGeneration.generateSomeCSV().toByteArray());

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
