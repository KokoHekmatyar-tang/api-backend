package com.tang.apicommon.service;


import com.tang.apicommon.model.entity.UserInterfaceInfo;

/**
 * @author tang
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2025-05-23 16:37:19
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口次数
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
