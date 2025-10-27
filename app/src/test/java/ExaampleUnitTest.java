
import android.util.Log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExaampleUnitTest {
    @BeforeClass
    public static void testBeforeClass() throws Exception {
        System.out.println("testBeforeClass");
    }

    @Before
    public void testBefore() throws Exception {
        System.out.println("testBefore");
    }

    @Test
    public void testCase() {
        System.out.println("--------");
        System.out.println("testCase");
        System.out.println("--------");
    }

    @After
    public void testAfter() throws Exception {
        System.out.println("testAfter");
    }

    @AfterClass
    public static void testAfterClass() throws Exception {
        System.out.println("testAfterClass");
    }
}

