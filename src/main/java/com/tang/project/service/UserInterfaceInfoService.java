package com.tang.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.apicommon.model.entity.UserInterfaceInfo;

/**
 * ClassName:UserInterfaceInfoService
 *
 * @Author tdc
 * Create 2025/7/11 10:57
 * Content
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);
    /**
     * 调用接口次数
     *
     * @param interfaceInfoId
     * @param userId
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
