package com.hit.edu;


import io.vavr.Tuple;
import io.vavr.Tuple2;
import one.util.streamex.StreamEx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyApplicationTests {


    @Test
    public void contextLoads() {

    }

    @Test
    public void createTuple(){
        Tuple2<String, Integer> tuple = Tuple.of("orgCode", 123456);
        System.out.println(tuple);
    }

    @Test
    public void streamExDemo(){


        String result = StreamEx.of(1, 2, 3).joining(";");
        System.out.println(result);
    }
}


