package log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
    public static final Logger logger = LoggerFactory.getLogger(TestLog4j.class);

    @Test
    public void testLog4j(){
        logger.debug("测试");
    }
}
