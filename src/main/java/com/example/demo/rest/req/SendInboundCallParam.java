package com.example.demo.rest.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: luozijian
 * @date: 4/20/21 14:05:56
 * @description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendInboundCallParam {

    /**
     * 来电号码
     */
    private String callerNum;

    /**
     * id
     */
    private String serialNo;

    private String agentEmail;

    /**
     * yyyyMMddHHmmss
     */
    private String callTime;
}