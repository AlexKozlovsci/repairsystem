package repairSystem.documentGeneration;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import repairSystem.controller.AdminController;
import repairSystem.dao.ClientRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Client;
import repairSystem.model.User;
import repairSystem.model.Workorder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Глеб on 24.05.2017.
 */
public class PDFGeneration  {


    private static int FONT_SIZE_SMALL = 10;
    private static int FONT_SIZE_NORMAL = 14;
    private static int FONT_SIZE_BIG = 20;
    private static int VERTICAL_SPACE_TINY = 5;
    private static int VERTICAL_SPACE_SMALL = 20;
    private static int VERTICAL_SPACE_MEDIUM = 50;
    private static int VERTICAL_SPACE_BIG = 80;
    private static int HEIGHT_SMALL_LINE = FONT_SIZE_SMALL + 1;
    private static int HEIGHT_NORMAL_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY + 2;
    private static int HEIGHT_BIG_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY;

    private static final String TEMPLATE = "templates\\template.jpg";
    private static final String TEMPLATE_LANDSCAPE = "templates\\template-landscape.jpg";
    private static final String FONT = "fonts/times.ttf";

    private final static Font SMALL_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE_SMALL);
    private final static Font NORMAL_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE_NORMAL);
    private final static Font NORMAL_BOLD_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE_NORMAL, Font.BOLD);
    private final static Font BIG_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE_BIG, Font.BOLD);
    private final static Font BIG_BOLD_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE_BIG + 2, Font.BOLD);

    private static final Logger log = Logger.getLogger(AdminController.class);

    public ByteArrayOutputStream gneratePriceList(JpaRepository psr) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, stream);
        //writer.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        writer.createXmpMetadata();


        document.open();
        generateTitle(document);
        generateTable(document, DataLoad.getPriceCurrentList((PricelistRepository)psr));

        document.close();

        return stream;
    }

    private void generateTitle(Document document) throws IOException, DocumentException{
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, FONT_SIZE_BIG + 4));
        paragraph.setSpacingAfter(VERTICAL_SPACE_TINY);
        paragraph.add(new Chunk("Title"));
        document.add(paragraph);
        document.add(new LineSeparator());

        paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.add(new Chunk("Powered by RepairSystem(Kozlovski, Legchilov, Lukynchik, Sych)"));
        document.add(paragraph);

        paragraph = new Paragraph(" ");
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        document.add(paragraph);

    }

    private void generateTable(Document doc, List<String[]> dataToWrite) throws DocumentException {

        PdfPTable t = new PdfPTable(dataToWrite.get(0).length);

        t.setSpacingBefore(25);
        t.setSpacingAfter(25);

        String[] strHead = dataToWrite.get(0);

        dataToWrite.remove(0);
        for (String elem: strHead) {
            PdfPCell c1 = new PdfPCell(new Phrase(elem));
            t.addCell(c1);
        }

        for (String[] str: dataToWrite) {
            for (String elem: str) {
                t.addCell(elem);
            }
        }

        doc.add(t);
    }

    public ByteArrayOutputStream generateOrder(JpaRepository orderRep, JpaRepository userRep, JpaRepository clientRep, int orderId) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, stream);
        //writer.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        writer.createXmpMetadata();
        WorkorderRepository workorderRepository;
        workorderRepository = (WorkorderRepository) orderRep;
        Workorder order = (Workorder) workorderRepository.findById(orderId);
        Integer tempOrder = (int)orderId;
        String orderStr = tempOrder.toString();

        ClientRepository clientRepository;
        clientRepository = (ClientRepository) clientRep;
        Client client = (Client) clientRepository.findById(order.getId_client());

        UserRepository userRepository;
        userRepository = (UserRepository) userRep;
        User manager = (User) userRepository.findById(order.getId_manager());
        User engineer = (User) userRepository.findById(order.getId_engineer());

        document.open();


        generateTitle(document);
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, FONT_SIZE_BIG + 6));
        paragraph.setIndentationLeft(VERTICAL_SPACE_BIG * 2 + 30);
        paragraph.add(new Chunk("Order №".concat(orderStr)));
        document.add(paragraph);

        paragraph = new Paragraph(" ");
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.add(new Chunk("This document states the conclusion of a contract for repair between RepairSystem on the one hand and the client ".concat(client.getSecondname()).concat(" ").concat(client.getName()).concat(" on another hand.")));
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.add(new Chunk("Order accepted ".concat(order.getCreate_at()).concat(". Manager ").concat(manager.getSecondname()).concat(" ").concat(manager.getName()).concat(" to perform the repair. The order was assigned to an engineer ").concat(engineer.getSecondname()).concat(" ").concat(engineer.getName()).concat(". The customer was given a receipt on the acceptance of the device, as well as the conditions for performing repair and maintenance of the repair.")));
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.add(new Chunk("The Contractor undertakes to notify the customer of any faults found and inform about the dates and costs in advance."));
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.add(new Chunk("Client's signature: ________"));
        paragraph.setIndentationRight(VERTICAL_SPACE_SMALL);
        paragraph.setExtraParagraphSpace(VERTICAL_SPACE_SMALL);
        paragraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY);
        paragraph.add(new Chunk("Manager's signature: _________"));
        document.add(paragraph);

        /*paragraph = new Paragraph();
        paragraph.setFont(NORMAL_BOLD_FONT);
        paragraph.setSpacingBefore(VERTICAL_SPACE_TINY + 5);
        paragraph.add(new Chunk("Manager's signature: _________"));
        document.add(paragraph);*/

        document.close();

        return stream;
    }
}
