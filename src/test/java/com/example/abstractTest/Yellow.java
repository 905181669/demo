package com.example.abstractTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.tags.Param;

/**
 * @author: luozijian
 * @date: 10/12/21 13:57:08
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Yellow extends Parent {


    private String country;

}