import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Администратор on 21.05.2017.
 */
public class Retry implements TestRule{

    private int rc;

    public Retry(int rc){
        this.rc = rc;
    }
    @Override
    public Statement apply(Statement base,Description description){
        return null;
    }

    private Statement statement(final Statement base, final Description description){
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable throwable = null;
                for(int i = 0; i < rc; i++) {
                    try {
                    } catch (Throwable e) {
                        throwable = e;
                    }
                }
            }
        };
    }
}
