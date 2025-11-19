//package com.ecom.server;
//
//import com.ecom.service.ReturnSearchService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.security.NoSuchAlgorithmException;
//
//
//@SpringBootTest
//class ServerApplicationTests {
//    @Autowired
//    private ReturnSearchService returnSearchService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Test
//    public void redisTest() {
//        redisTemplate.opsForValue().set("key1","value1");
//        var val = redisTemplate.opsForValue().get("key1");
//        assert val.equals("value1");
//    }
//
//    @Test
//    public void getSummaryTest() {
//        try {
//            var result = returnSearchService.getAgedReturnDashboard(true);
//            var test = returnSearchService.getAgedReturnSummary(result);
//            assert (test.size() > 1);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//
//
//}
