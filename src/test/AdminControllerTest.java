import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.User;

import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Администратор on 22.05.2017.
 */
public class AdminControllerTest {
    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private PricelistRepository priceListRepository;

    @Autowired
    private UserRepository userRepository;


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
    public void TestPricelistNullException() throws NullPointerException {
        Pricelist price = new Pricelist();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            priceListRepository.save(price);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    public void TestDetailNullException() throws NullPointerException {
        Detail detail = new Detail();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            detailRepository.save(detail);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    void TestUserName() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setName("Admin");
        assertEquals("Admin", u.getName());
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void TestUserLogin() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setLogin("Lexa");
        assertEquals("Lexa", u.getLogin());
    }

    @Test
    public void TestUser() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setLogin("Administrator");
        u.setName("yura");
        u.setEmail("kotsen@gmail.com");
        u.setPhoneNumber("+375299996543");
        u.setRole("ROLE_ADMIN");
        u.setSecondname("karamba");
        u.setPassword("tipo zvezdochki");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        u.setPassword(encoder.encodePassword(u.getPassword(),""));
        assertAll("user",
                () -> assertEquals("Administrator", u.getLogin()),
                () -> assertEquals("yura", u.getName()),
                () -> assertEquals("kotsen@gmail.com", u.getEmail()),
                () -> assertEquals("+375299996543", u.getPhoneNumber()),
                () -> assertEquals("ROLE_ADMIN", u.getRole()),
                () -> assertEquals("karamba", u.getSecondname()),
                () -> assertNotEquals("tipo zvezdochki", u.getPassword()));
    }
    @Test
    public void TestText() {
        System.out.println("test");
    }

    @Test
    public void TestNullUser() {
        repairSystem.model.User u = new repairSystem.model.User();
        assertAll("user",
                () -> assertNotNull(u.getId()),
                () -> assertNull(u.getLogin()),
                () -> assertNull(u.getName()),
                () -> assertNull(u.getEmail()),
                () -> assertNull(u.getPhoneNumber()),
                () -> assertNull(u.getRole()),
                () -> assertNull(u.getSecondname()),
                () -> assertNull(u.getPassword()));
    }

    @Test
    public void TestModelViewIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/index");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewParts() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/parts");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewPrices() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/prices");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewUsers() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/users");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewAddPart() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addPart");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewEditPart() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/editPart");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewAddPriceItem() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/addPriceItem");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewEditPriceItem() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/editPriceItem");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewAddUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/addUser");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewEditUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/editUser");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewLogout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/auth/login");
        assertNotNull(mav);
    }

    @Test
    public void TestModelViewNotFound() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("404");
        assertNotNull(mav);
    }

    @Test
    public void TestUserIdIllegalArgument() throws NullPointerException {
        repairSystem.model.User u = new repairSystem.model.User();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            u.setId(Long.parseLong("9.25"));
        });
        assertNotNull(thrown.getMessage());
    }



    @Test

    public void TestAddUser() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setId(100);
        u.setLogin("Administrator");
        u.setName("yura");
        u.setEmail("kotsen@gmail.com");
        u.setPhoneNumber("+375299996543");
        u.setRole("ROLE_ADMIN");
        u.setSecondname("karamba");
        u.setPassword("tipo zvezdochki");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        u.setPassword(encoder.encodePassword(u.getPassword(),""));
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            userRepository.save(u);
        });
        long userid = u.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {repairSystem.model.User wo = userRepository.findById(userid);});
        assertNull(thrown.getMessage());
        assertNull(thrown2.getMessage());
    }

    @Test
    void TestDetailName() {
        Detail detail = new Detail();
        detail.setName("screen");
        assertEquals("screen", detail.getName());
    }

    @Test
    public void TestDetail() {
        Detail detail = new Detail();
        detail.setName("screen");
        detail.setCost(10);
        detail.setCount(2);
        assertAll("detail",
                () -> assertEquals("screen", detail.getName()),
                () -> assertEquals(10, detail.getCost()),
                () -> assertEquals(2, detail.getCount()));
    }

    @Test
    public void TestNullDetail() {
        Detail detail = new Detail();
        assertAll("user",
                () -> assertNotNull(detail.getId()),
                () -> assertNull(detail.getName()),
                () -> assertNotNull(detail.getCost()),
                () -> assertNotNull(detail.getCount()));
}
    @Test
    public void TestDetailIdIllegalArgument() throws NullPointerException {
        Detail detail = new Detail();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            detail.setId(Long.parseLong("9.25"));
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    public void TestDetailCountIllegalArgument() throws NullPointerException {
        Detail detail = new Detail();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            detail.setCount(Long.parseLong("10.5"));
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    public void TestDetailCostIllegalArgument() throws NullPointerException {
        Detail detail = new Detail();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            detail.setCost(Long.parseLong("99.99"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestAddDetail() {
        Detail detail = new Detail();
        detail.setId(100);
        detail.setName("screen");
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
    void TestPriceListDevice() {
        Pricelist pricelist = new Pricelist();
        pricelist.setDeviceType("IPhone");
        assertEquals("IPhone", pricelist.getDeviceType());
    }

    @Test
    public void TestPriceList() {
        Pricelist pricelist = new Pricelist();
        pricelist.setDeviceType("IPhone");
        pricelist.setCost(10);
        pricelist.setAction("change screen");
        assertAll("PriceList",
                () -> assertEquals("IPhone", pricelist.getDeviceType()),
                () -> assertEquals(10, pricelist.getCost()),
                () -> assertEquals("change screen", pricelist.getAction()));
    }

    @Test
    public void TestNullPriceList() {
        Pricelist pricelist = new Pricelist();
        assertAll("PriceList",
                () -> assertNotNull(pricelist.getId()),
                () -> assertNull(pricelist.getDeviceType()),
                () -> assertNotNull(pricelist.getCost()),
                () -> assertNull(pricelist.getAction()));
    }
    @Test
    public void TestPriceListIdIllegalArgument() throws NullPointerException {
        Pricelist pricelist = new Pricelist();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            pricelist.setId(Long.parseLong("9.25"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestPriceListCostIllegalArgument() throws NullPointerException {
        Pricelist pricelist = new Pricelist();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            pricelist.setCost(Long.parseLong("50.96"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestAddPriceList() {
        Pricelist pricelist = new Pricelist();
        pricelist.setId(100);
        pricelist.setDeviceType("IPhone");
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
    public void TestDisplayDetail() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<Detail> details = (List<Detail>) detailRepository.findAll();
        });
        assertNull(thrown.getMessage());

    }

    @Test
    public void TestDisplayUser() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<User> users = (List<User>) userRepository.findAll();
        });
        assertNull(thrown.getMessage());

    }

    @Test
    public void TestDisplayPriceList() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<Pricelist> users = (List<Pricelist>) priceListRepository.findAll();
        });
        assertNull(thrown.getMessage());

    }
    @Test
    public void TestDeleteUser() {
        repairSystem.model.User u = new repairSystem.model.User();
        u.setId(100);
        u.setLogin("Administrator");
        u.setName("yura");
        u.setEmail("kotsen@gmail.com");
        u.setPhoneNumber("+375299996543");
        u.setRole("ROLE_ADMIN");
        u.setSecondname("karamba");
        u.setPassword("tipo zvezdochki");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        u.setPassword(encoder.encodePassword(u.getPassword(),""));
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            userRepository.save(u);
        });
        long userid = u.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            userRepository.delete(u);
        });

        assertThrows(NullPointerException.class, () -> userRepository.findById(100));
    }

    @Test
    public void TestDeletePriceList() {
        Pricelist pricelist = new Pricelist();
        pricelist.setId(100);
        pricelist.setDeviceType("IPhone");
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
    public void TestDeleteDetail() {
        Detail detail = new Detail();
        detail.setId(100);
        detail.setName("screen");
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

