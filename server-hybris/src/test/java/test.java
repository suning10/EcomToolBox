import com.ecom.ServerApplication;
import com.ecom.service.ReturnService;
import com.ecom.service.impl.ReturnServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ServerApplication.class)
public class test {

    @Autowired
    ReturnService returnService;
    @Test
    void testFind(){
        List<String> l = new ArrayList<>();
        l.add("us260225-42300681");
        l.add("us260520-67430329");
        var res = returnService.getByPo(l);
        System.out.println(res);
    }
}
