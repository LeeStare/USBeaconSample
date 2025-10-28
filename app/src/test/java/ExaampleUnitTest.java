import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExaampleUnitTest {
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("testBeforeClass");
    }

    @Before
    public void testBefore() {
        System.out.println("testBefore");
    }

    @Test
    public void testCase() {
        System.out.println("--------");
        System.out.println("testCase");
        System.out.println("--------");
    }

    @After
    public void testAfter() {
        System.out.println("testAfter");
    }

    @AfterClass
    public static void testAfterClass() {
        System.out.println("testAfterClass");
    }
}

