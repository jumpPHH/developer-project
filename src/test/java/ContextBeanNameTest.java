import com.fastcamp.programming.dmaker.DmakerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmakerApplication.class)
public class ContextBeanNameTest {

        @Autowired
        ApplicationContext context;

        @Test
        public void contextCheck() throws Exception {
            String[] beans = context.getBeanDefinitionNames();

            for(String beanName : beans) {
                System.out.println("beanName : " + beanName);
            }
        }


}
