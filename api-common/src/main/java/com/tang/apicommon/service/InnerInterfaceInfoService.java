package com.tang.apicommon.service;

import com.tang.apicommon.model.entity.InterfaceInfo;


/**
 * @author tang
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2025-04-01 02:36:43
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询接口是否存在（请求路径，请求方法，请求参数）
     */
    InterfaceInfo getInterfaceInfo(String path, String method);


}
