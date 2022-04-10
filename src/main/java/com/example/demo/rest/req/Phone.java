package com.example.demo.rest.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: luozijian
 * @date: 5/4/21 20:55:03
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private int price;
}