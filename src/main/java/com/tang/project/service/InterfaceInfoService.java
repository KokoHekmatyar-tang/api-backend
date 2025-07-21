package com.tang.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.apicommon.model.entity.InterfaceInfo;

/**
 * @author tang
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2025-04-01 02:36:43
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
