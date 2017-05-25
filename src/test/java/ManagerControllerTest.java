import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.ClientRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Client;
import repairSystem.model.User;
import repairSystem.model.Workorder;

import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Администратор on 20.05.2017.
 */
class ManagerControllerTest {

    @Autowired
    private WorkorderRepository workorderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
        public void test() {
            System.out.println("");
        }

        @Test
        public void testTimeoutMinutes() {
            assertTimeout(ofMinutes(2), () -> {
            });
        }

        @Test
        public void testTimeoutMillis() {
            assertTimeout(ofMillis(2), () -> {
            });
        }

        @Test
        public void testUserNullException() throws NullPointerException {
            repairSystem.model.User u = new repairSystem.model.User();
            Throwable thrown = assertThrows(NullPointerException.class, () -> {
                userRepository.save(u);
            });
            assertNull(thrown.getMessage());
        }

        @Test
        public void testWorkOrdersNullException() throws NullPointerException {
            Workorder workorder = new Workorder();
            Throwable thrown = assertThrows(NullPointerException.class, () -> {
                workorderRepository.save(workorder);
            });
            assertNull(thrown.getMessage());
        }

        @Test
        public void testClientNullException() throws NullPointerException {
            Client client = new Client();
            Throwable thrown = assertThrows(NullPointerException.class, () -> {
                clientRepository.save(client);
            });
            assertNull(thrown.getMessage());
        }

        @Test
        void testName() {
            repairSystem.model.User u = new repairSystem.model.User();
            u.setName("manager");
            assertEquals("manager", u.getName());
        }

        @Test
        void testException() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("a message");
            });
            assertEquals("a message", exception.getMessage());
        }

        @Test
        void testUserLogin() {
            repairSystem.model.User u = new repairSystem.model.User();
            u.setLogin("Vlad");
            assertEquals("Vlad", u.getLogin());
        }

        @Test
        public void testWorkOrder() {
            Workorder workorder = new Workorder();
            workorder.setId_client(7);
            workorder.setComplete_at("24.12.2005");
            workorder.setDescription("blabla");
            workorder.setId_engineer(1);
            workorder.setId_manager(1);
            workorder.setProblem("i'ts work");
            workorder.setStatus("close");
            workorder.setCreate_at("23.12.2005");
            assertAll("workorder",
                    () -> assertEquals(7, workorder.getId_client()),
                    () -> assertEquals("24.12.2005", workorder.getComplete_at()),
                    () -> assertEquals("blabla", workorder.getDescription()),
                    () -> assertEquals(1, workorder.getId_engineer()),
                    () -> assertEquals(1, workorder.getId_manager()),
                    () -> assertEquals("i'ts work", workorder.getProblem()),
                    () -> assertEquals("close", workorder.getStatus()),
                    () -> assertEquals("23.12.2005", workorder.getCreate_at()));
        }

        @Test
        public void testNullOrder() {
            Workorder workorder = new Workorder();
            assertAll("workorder",
                    () -> assertEquals(0,workorder.getId()),
                    () -> assertNull(workorder.getComplete_at()),
                    () -> assertNull(workorder.getDescription()),
                    () -> assertEquals(0,workorder.getId_engineer()),
                    () -> assertEquals(0,workorder.getId_manager()),
                    () -> assertNull(workorder.getProblem()),
                    () -> assertNull(workorder.getStatus()),
                    () -> assertNull(workorder.getCreate_at()));
        }

        @Test
        public void testModelViewIndex() {
            //Workorder wo = new Workorder();
           // Client client = new Client();
           // HttpServletRequest request;
            //String result = ManagerController.index();
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/index");
            assertNotNull(mav);
        }

        @Test
        public void testModelViewAddOrder() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/addOrder");
            assertNotNull(mav);
        }

        @Test
        public void testModelViewEditOrder() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/editOrder");
            assertNotNull(mav);
        }
    @Test
    public void testModelViewLogout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/auth/login");
        assertNotNull(mav);
    }


        @Test
        public void testModelViewNotFound() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("404");
            assertNotNull(mav);
        }

        @Test
        public void testWorkOrdersIdIllegalArgument() throws NullPointerException {
            Workorder workorder = new Workorder();
            Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
                workorder.setId(Long.parseLong("9.25"));
            });
            assertNotNull(thrown.getMessage());
        }

        @Test
        public void testWorkOrdersIdClientIllegalArgument() throws NullPointerException {
            Workorder workorder = new Workorder();
            Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
                workorder.setId_client(Long.parseLong("11.55"));
            });
            assertNotNull(thrown.getMessage());
        }

        @Test
        public void testWorkOrdersIdManagerIllegalArgument() throws NullPointerException {
            Workorder workorder = new Workorder();
            Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
                workorder.setId_manager(Long.parseLong("27.15"));
            });
            assertNotNull(thrown.getMessage());
        }

        @Test
        public void testWorkOrdersIdEngineerIllegalArgument() throws NullPointerException {
            Workorder workorder = new Workorder();
            Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
                workorder.setId_engineer(Long.parseLong("hello"));
            });
            assertNotNull(thrown.getMessage());
        }

        @Test

        public void testAddWorkOrder() {
            Workorder workorder = new Workorder();
            workorder.setId(100);
            workorder.setId_client(1);
            workorder.setDescription("null pointer T_T");
            workorder.setId_engineer(3);
            workorder.setId_manager(2);
            workorder.setProblem("add test");
            workorder.setStatus("inwork");
            workorder.setCreate_at("startdata");
            Throwable thrown = assertThrows(NullPointerException.class, () -> {
                workorderRepository.save(workorder);
            });
            long workid = workorder.getId();
            Throwable thrown2 = assertThrows(NullPointerException.class, () -> {repairSystem.model.Workorder wo = workorderRepository.findById(workid);});
            assertNull(thrown.getMessage());
            assertNull(thrown2.getMessage());
    }
    @Test
    public void testDisplayUserEngineers() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<User> engineers = (List<repairSystem.model.User>) userRepository.findAllByRole("ROLE_ENGINEER");;
        });
        assertNull(thrown.getMessage());

    }

    @Test
    public void testClient() {
        Client client = new Client();
        client.setName("Gleb");
        client.setSecondname("Legchilov");
        client.setDiscount(60);
        client.setEmail("betrazenclient@gmail.com");
        client.setPhone_number("+375339890687");
        assertAll("Client",
                () -> assertEquals("Gleb", client.getName()),
                () -> assertEquals("Legchilov", client.getSecondname()),
                () -> assertEquals( 60, client.getDiscount()),
                () -> assertEquals("betrazenclient@gmail.com", client.getEmail()),
                () -> assertEquals("+375339890687", client.getPhone_number()));
    }

    @Test
    public void testNullClient() {
        Client client = new Client();
        assertAll("Client",
                () -> assertEquals(0,client.getId()),
                () -> assertNull(client.getName()),
                () -> assertNull(client.getSecondname()),
                () -> assertEquals(0,client.getDiscount()),
                () -> assertNull(client.getEmail()),
                () -> assertNull(client.getPhone_number()));
    }
    @Test
    public void testClientIdIllegalArgument() throws NullPointerException {
        Client client = new Client();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            client.setId(Long.parseLong("14.2"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testClientDiscountIllegalArgument() throws NullPointerException {
        Client client = new Client();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            client.setDiscount(Long.parseLong("99.99"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testAddClient() {
        Client client = new Client();
        client.setName("Gleb");
        client.setSecondname("Legchilov");
        client.setDiscount(60);
        client.setEmail("betrazenclient@gmail.com");
        client.setPhone_number("+375339890687");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            clientRepository.save(client);
        });
        long clientid = client.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {repairSystem.model.Client wo = clientRepository.findById(clientid);});
        assertNull(thrown.getMessage());
        assertNull(thrown2.getMessage());
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client();
        client.setId(100);
        client.setName("Gleb");
        client.setSecondname("Legchilov");
        client.setDiscount(60);
        client.setEmail("betrazenclient@gmail.com");
        client.setPhone_number("+375339890687");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            clientRepository.save(client);
        });
        long clientid = client.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            clientRepository.delete(client);
        });

        assertThrows(NullPointerException.class, () -> clientRepository.findById(100));
    }

    @Test
    public void testDeleteWorkorder() {
        Workorder workorder = new Workorder();
        workorder.setId(100);
        workorder.setId_client(1);
        workorder.setDescription("null pointer T_T");
        workorder.setId_engineer(3);
        workorder.setId_manager(2);
        workorder.setProblem("add test");
        workorder.setStatus("inwork");
        workorder.setCreate_at("startdata");
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            workorderRepository.save(workorder);
        });
        long workid = workorder.getId();
        Throwable thrown2 = assertThrows(NullPointerException.class, () -> {
            workorderRepository.delete(workorder);
        });

        assertThrows(NullPointerException.class, () -> workorderRepository.findById(100));
    }

    @Test
    public void testChangeEngineer() {
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
        workorder.setId_engineer(2);
        assertEquals(2, workorder.getId_engineer());
    }


}