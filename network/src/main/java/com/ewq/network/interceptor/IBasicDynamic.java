package com.ewq.network.interceptor;

import java.util.Map;

/**
 * Description: 添加动态参数接口
 */
public interface IBasicDynamic {
    String signParams(String postBodyString);

    Map signParams(Map map);

    Map signHeadParams(Map headMap);
}
