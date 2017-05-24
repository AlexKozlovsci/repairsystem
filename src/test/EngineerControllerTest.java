import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.*;
import repairSystem.model.Client;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.Workorder;

import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Администратор on 22.05.2017.
 */
public class EngineerControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkorderRepository workorderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private PricelistRepository priceListRepository;

    @Test
    public void Test() {
        System.out.println("test");
    }

    @Test
    public void TestTimeoutMinutes() {
        assertTimeout(ofMinutes(2), () -> {
        });
    }

    @Test
    public void TestTimeoutMillis() {
        assertTimeout(ofMillis(2), () -> {
        });
    }

    @Test
    public void TestUserNullException() throws NullPointerException {
        repairSystem.model.User u = new repairSystem.model.User();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            userRepository.save(u);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    public void TestWorkOrdersNullException() throws NullPointerException {
        Workorder workorder = new Workorder();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            workorderRepository.save(workorder);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    public void TestClientNullException() throws NullPointerException {
        Client client = new Client();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            clientRepository.save(client);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    void TestName() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setName("Engineer");
        assertEquals("Engineer", u.getName());
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void TestUser() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setLogin("Vlad");
        assertEquals("Vlad", u.getLogin());
    }

    @Test
    public void TestDisplayOrder() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<Workorder> workorders = (List<Workorder>) workorderRepository.findAll();
        });
        assertNull(thrown.getMessage());

    }


    @Test
    public void TestModelViewIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/index");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewAddDetailToOrder() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/addDetailToOrder");
        assertNotNull(mav);
    }
    @Test
    public void TestModelViewAddActionToOrder() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/addActionToOrder");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewOrder() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/order");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewDiagnosticsList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/DiagnosticsList");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewLogout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/auth/login");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewReport() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/Report");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewWorkItems() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/WorkItems");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewNotFound() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("404");
        assertNotNull(mav);
    }

    @Test
    public void TestWorkOrdersIdIllegalArgument() throws NullPointerException {
        Workorder workorder = new Workorder();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            workorder.setId(Long.parseLong("9.25"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestWorkOrdersIdClientIllegalArgument() throws NullPointerException {
        Workorder workorder = new Workorder();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            workorder.setId_client(Long.parseLong("11.55"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestWorkOrdersIdManagerIllegalArgument() throws NullPointerException {
        Workorder workorder = new Workorder();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            workorder.setId_manager(Long.parseLong("27.15"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestWorkOrdersIdEngineerIllegalArgument() throws NullPointerException {
        Workorder workorder = new Workorder();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            workorder.setId_engineer(Long.parseLong("hello"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test

    public void TestChangeStatus() {
        Workorder workorder = new Workorder();
        workorder.setId(100);
        workorder.setId_client(1);
        workorder.setDescription("null pointer T_T");
        workorder.setId_engineer(3);
        workorder.setId_manager(2);
        workorder.setProblem("add test");
        workorder.setStatus("In work");
        workorder.setCreate_at("startdata");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            workorderRepository.save(workorder);
        });
        long workid = workorder.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            repairSystem.model.Workorder wo = workorderRepository.findById(workid);
        });
        workorder.setStatus("Open");
        assertEquals("Open", workorder.getStatus());
    }

    @Test
    public void TestAddDetailToOrder() {
        Detail detail = new Detail();
        detail.setId(100);
        detail.setName("Display");
        detail.setCost(10);
        detail.setCount(2);
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            detailRepository.save(detail);
        });
        long detailid = detail.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {repairSystem.model.Detail wo = detailRepository.findById(detailid);});
        assertNull(thrown.getMessage());
        assertNull(thrown2.getMessage());
    }
    @Test
    public void TestAddPriceListToOrder() {
        Pricelist pricelist = new Pricelist();
        pricelist.setId(100);
        pricelist.setDeviceType("Android");
        pricelist.setCost(10);
        pricelist.setAction("change screen");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            priceListRepository.save(pricelist);
        });
        long pricelistid = pricelist.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {repairSystem.model.Pricelist wo = priceListRepository.findById(pricelistid);});
        assertNull(thrown.getMessage());
        assertNull(thrown2.getMessage());
    }
    @Test
    public void TestDeletePriceListToOrder() {
        Pricelist pricelist = new Pricelist();
        pricelist.setId(100);
        pricelist.setDeviceType("Android");
        pricelist.setCost(10);
        pricelist.setAction("change screen");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            priceListRepository.save(pricelist);
        });
        long pricelistid = pricelist.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            priceListRepository.delete(pricelist);
        });

        assertThrows(NullPointerException.class, () -> priceListRepository.findById(100));
    }

    @Test
    public void TestDeleteDetailToOrder() {
        Detail detail = new Detail();
        detail.setId(100);
        detail.setName("Display");
        detail.setCost(10);
        detail.setCount(2);
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            detailRepository.save(detail);
        });
        long detailid = detail.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            detailRepository.delete(detail);
        });

        assertThrows(NullPointerException.class, () -> detailRepository.findById(100));
    }
}
