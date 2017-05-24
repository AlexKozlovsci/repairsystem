import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
            u.setName("manager");
            assertEquals("manager", u.getName());
        }

        @Test
        void TestException() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("a message");
            });
            assertEquals("a message", exception.getMessage());
        }

        @Test
        void TestUserLogin() {
            repairSystem.model.User u = new repairSystem.model.User();
            u.setLogin("Vlad");
            assertEquals("Vlad", u.getLogin());
        }

        @Test
        public void TestWorkOrder() {
            Workorder workorder = new Workorder();
            workorder.setId_client(1);
            workorder.setComplete_at("");
            workorder.setDescription("");
            workorder.setId_engineer(1);
            workorder.setId_manager(1);
            workorder.setProblem("");
            workorder.setStatus("");
            workorder.setCreate_at("");
            assertAll("workorder",
                    () -> assertEquals(1, workorder.getId_client()),
                    () -> assertEquals("", workorder.getComplete_at()),
                    () -> assertEquals("", workorder.getDescription()),
                    () -> assertEquals(1, workorder.getId_engineer()),
                    () -> assertEquals(1, workorder.getId_manager()),
                    () -> assertEquals("", workorder.getProblem()),
                    () -> assertEquals("", workorder.getStatus()),
                    () -> assertEquals("", workorder.getCreate_at()));
        }

        @Test
        public void TestNullOrder() {
            Workorder workorder = new Workorder();
            assertAll("workorder",
                    () -> assertNotNull(workorder.getId()),
                    () -> assertNull(workorder.getComplete_at()),
                    () -> assertNull(workorder.getDescription()),
                    () -> assertNotNull(workorder.getId_engineer()),
                    () -> assertNotNull(workorder.getId_manager()),
                    () -> assertNull(workorder.getProblem()),
                    () -> assertNull(workorder.getStatus()),
                    () -> assertNull(workorder.getCreate_at()));
        }

        @Test
        public void TestModelViewIndex() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/index");
            assertNotNull(mav);
        }

        @Test
        public void TestModelViewAddOrder() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/addOrder");
            assertNotNull(mav);
        }

        @Test
        public void TestModelViewEditOrder() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("manager/editOrder");
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

        public void TestAddWorkOrder() {
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
    public void TestDisplayUserEngineers() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            List<User> engineers = (List<repairSystem.model.User>) userRepository.findAllByRole("ROLE_ENGINEER");;
        });
        assertNull(thrown.getMessage());

    }

    @Test
    public void TestClient() {
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
    public void TestNullClient() {
        Client client = new Client();
        assertAll("Client",
                () -> assertNotNull(client.getId()),
                () -> assertNull(client.getName()),
                () -> assertNull(client.getSecondname()),
                () -> assertNotNull(client.getDiscount()),
                () -> assertNull(client.getEmail()),
                () -> assertNull(client.getPhone_number()));
    }
    @Test
    public void TestClientIdIllegalArgument() throws NullPointerException {
        Client client = new Client();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            client.setId(Long.parseLong("14.2"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestClientDiscountIllegalArgument() throws NullPointerException {
        Client client = new Client();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            client.setDiscount(Long.parseLong("99.99"));
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestAddClient() {
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
    public void TestDeleteClient() {
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
    public void TestDeleteWorkorder() {
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
    public void TestChangeEngineer() {
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