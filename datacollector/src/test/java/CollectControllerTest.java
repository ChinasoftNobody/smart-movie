import com.chinasoft.lgh.movies.datacollector.CollectorApplication;
import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = CollectorApplication.class)
@RunWith(SpringRunner.class)
public class CollectControllerTest {

    @Resource
    private CollectorService collectorService;

    @Test
    public void test(){
        try {
            Response<String> response = collectorService.collect("http://www.dytt8.net/");
            Assert.assertEquals("test pass",true,response.isSuccess());
        } catch (CollectionException e) {
            e.printStackTrace();
        }
    }
}
