import org.junit.jupiter.api.Test;
import repairSystem.controller.ManagerController;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertTimeout;


/**
 * Created by Администратор on 20.05.2017.
 */
class ManagerControllerTest {

    @Test
    public void Test(){
        System.out.println("test");
    }

    @Test
    public void  TestTimeoutMinutes(){
        assertTimeout(ofMinutes(2),()->{});
    }

    @Test
    public void  TestTimeoutMillis(){
        assertTimeout(ofMillis(2),()->{});
    }

    @Test
    public void TestNullException() throws Exception{
        ManagerController manager = new ManagerController();
        manager.index();
    }
    /*@Test
    public void testUsingTempFolder() throws IOException {
        File createdFolder = folder.newFolder("newfolder");
        File createdFile = folder.newFile("myfilefile.txt");
        assertTrue(createdFile.exists());*/
    /*    @Test
    public void testMultiplyException() {
        MyClass tester = new MyClass();
        assertEquals("Result", m1 * m2, tester.multiply(m1, m2));
    }*/

    /* @Test
   public void testPrintMessage() {
      System.out.println("Inside testPrintMessage()");
      assertEquals(message, messageUtil.printMessage());
   }*/

    /*@Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }*/

}