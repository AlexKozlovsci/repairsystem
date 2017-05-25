package repairSystem.controller;


import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.ClientRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.documentGeneration.CSVGeneration;
import repairSystem.documentGeneration.PDFGeneration;
import repairSystem.documentGeneration.XLSGeneration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Глеб on 24.05.2017.
 */

@Controller
public class DocumentController {

    private static final Logger log = Logger.getLogger(AdminController.class);

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private WorkorderRepository workorderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailRepository detailRepository;


    @Autowired
    private ClientRepository clientRepository;


    private CSVGeneration csvGen = new CSVGeneration();
    private XLSGeneration xlsGen = new XLSGeneration();
    private PDFGeneration pdfGen = new PDFGeneration();
    private String curTime;

    public DocumentController()
    {
        Date curDate = new Date();
        curTime = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
    }

    @RequestMapping(value = "/document/pdf/getOrder", method = RequestMethod.GET)
    public void getPDFOrder(final HttpServletRequest request,
                           final HttpServletResponse response, int workorderId) throws IOException, DocumentException {
        Integer temp = (int)workorderId;
        String orderStr = temp.toString();
        String fileName = "Order".concat("_").concat(orderStr);
        getPdf(response, pdfGen.generateOrder(workorderRepository, userRepository, clientRepository, workorderId), fileName.concat(".pdf"));

    }

    @RequestMapping(value = "/document/csv/getpricelist", method = RequestMethod.GET)
    public void getPriceListcsv(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException, DocumentException {

        String fileName = "PriceList";
        getCsv(response, csvGen.generatePricelist(pricelistRepository, fileName), fileName.concat(" ").concat(curTime).concat(".csv"));
    }

    @RequestMapping(value = "/document/csv/getmonthreport", method = RequestMethod.GET)
    public void getMonthReportcsv(final HttpServletRequest request,
                             final HttpServletResponse response) throws IOException, DocumentException {

        String fileName =  "Month report";
        getCsv(response, csvGen.generateMonthReport(workorderRepository, userRepository, fileName), fileName.concat(" ").concat(curTime).concat(".csv"));
    }

    @RequestMapping(value = "/document/csv/getprocurementsheet", method = RequestMethod.GET)
    public void getProcurementSheetcsv(final HttpServletRequest request,
                                  final HttpServletResponse response) throws IOException, DocumentException {

        String fileName =  "Procurement sheet";
        getCsv(response, csvGen.generateProcurementSheet(detailRepository, fileName), fileName.concat(" ").concat(curTime).concat(".csv"));
    }

    @RequestMapping(value = "/document/csv/getpaymentrecipe", method = RequestMethod.GET, params = {"id"})
    public void getPaymentRecipecsv(final HttpServletRequest request,
                                       final HttpServletResponse response) throws IOException, DocumentException {
        final Integer id = Integer.valueOf(request.getParameter("id"));
        String fileName =  "Payment recipe";
        getCsv(response, csvGen.generatePaymentRecipe(workorderRepository, fileName, id), fileName.concat(" ").concat(curTime).concat(".csv"));
    }


    private void getCsv (final HttpServletResponse response, ByteArrayOutputStream stream, String fileName) throws IOException {

        response.setContentType("application/csv");
        response.setContentLength(stream.toByteArray().length);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        outStream.write(stream.toByteArray());
        outStream.close();
    }


    @RequestMapping(value =  "/document/xls/getpricelist", method = RequestMethod.GET)
    public void getPriceListxls(final HttpServletRequest request,
                           final HttpServletResponse response) throws IOException, DocumentException {

        String fileName = "PriceList";
        getXls(response, xlsGen.generatePricelist(pricelistRepository, fileName), fileName.concat(" ").concat(curTime).concat(".xls"));
    }

    @RequestMapping(value =  "/document/xls/getmonthreport", method = RequestMethod.GET)
    public void getMonthReportxsl(final HttpServletRequest request,
                           final HttpServletResponse response) throws IOException, DocumentException {

        String fileName = "Month report";
        getXls(response, xlsGen.generateMonthReport(workorderRepository, userRepository, fileName), fileName.concat(" ").concat(curTime).concat(".xls"));
    }

    @RequestMapping(value = "/document/xls/getprocurementsheet", method = RequestMethod.GET)
    public void getProcurementSheetxls(final HttpServletRequest request,
                                       final HttpServletResponse response) throws IOException, DocumentException {

        String fileName =  "Procurement sheet";
        getXls(response, xlsGen.generateProcurementSheet(detailRepository, fileName), fileName.concat(" ").concat(curTime).concat(".xls"));
    }

    @RequestMapping(value = "/document/xls/getpaymentrecipe", method = RequestMethod.GET, params = {"id"})
    public void getPaymentRecipexls(final HttpServletRequest request,
                                    final HttpServletResponse response) throws IOException, DocumentException {
        final Integer id = Integer.valueOf(request.getParameter("id"));
        String fileName =  "Payment recipe";
        getCsv(response, xlsGen.generatePaymentRecipe(workorderRepository, fileName, id), fileName.concat(" ").concat(curTime).concat(".xls"));
    }

    private void getXls (final HttpServletResponse response, ByteArrayOutputStream stream, String fileName) throws IOException {



        response.setContentLength(stream.toByteArray().length);
        response.setContentType("application/xls");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        outStream.write(stream.toByteArray());
        outStream.close();
    }

    @RequestMapping(value = "/document/pdf/getpricelist", method = RequestMethod.GET)
    public void getPriceListPdf(final HttpServletRequest request,
                           final HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "PriceList".concat("_").concat(curTime);
        getPdf(response, pdfGen.gneratePriceList(pricelistRepository), fileName.concat(".pdf"));

    }

    @RequestMapping(value = "/document/pdf/getreport", method = RequestMethod.GET)
    public void getReportPdf(final HttpServletRequest request,
                           final HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "Progress Report".concat("_").concat(curTime);





        getPdf(response, pdfGen.generateReport(pricelistRepository, null), fileName.concat(".pdf"));

    }

    @RequestMapping(value = "/document/pdf/getwarrantycard", method = RequestMethod.GET)
    public void getWarrantyCard(final HttpServletRequest request,
                             final HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "Warranty Card".concat("_").concat(curTime);

        //Replace null with data
        getPdf(response, pdfGen.generateWarrantyCard(pricelistRepository, null), fileName.concat(".pdf"));

    }

    @RequestMapping(value = "/document/pdf/getreceipt", method = RequestMethod.GET)
    public void getgetReceiptPdf(final HttpServletRequest request,
                             final HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "Receipt".concat("_").concat(curTime);







        getPdf(response, pdfGen.generateReceipt(pricelistRepository, null), fileName.concat(".pdf"));

    }

    private void getPdf(final HttpServletResponse response, ByteArrayOutputStream stream, String fileName) throws IOException, DocumentException {
        try {

            response.setContentType("application/pdf");
            response.setContentLength(stream.toByteArray().length);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            outStream.write(stream.toByteArray());
            outStream.close();
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
    }




}
