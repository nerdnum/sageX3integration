package na.com.meatco.sageX3integration.sageX3integration.routes;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class BasicFileRouteTest{

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    BasicFileRoute basicFileRoute;

    @BeforeClass
    public static void doCleanUp() throws IOException {

        FileUtils.deleteDirectory(new File("data/output"));

    }

    @Test
    public void testBasicFileMove() throws InterruptedException {

        Thread.sleep(5000);
        File outFile = new File("data/output");
        assertTrue(outFile.exists());

    }

}
