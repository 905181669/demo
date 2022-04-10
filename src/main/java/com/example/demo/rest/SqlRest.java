package com.example.demo.rest;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 5/6/21 11:50:42
 * @description:
 */
//@RestController
public class SqlRest {


    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @GetMapping("/sql/search")
    public void search() throws Exception{



        while(true){

            try{
//                List list = jdbcTemplate.queryForList("select * from man");

//                List list = jdbcTemplate.queryForList("select top 1 * from CFG_ACE");

//                System.out.println(list);
                Thread.sleep(1000);
                break;

            }catch (Exception e){
                e.printStackTrace();
            }

        }

//        return JSONUtils.toJSONString(list);


    }

}