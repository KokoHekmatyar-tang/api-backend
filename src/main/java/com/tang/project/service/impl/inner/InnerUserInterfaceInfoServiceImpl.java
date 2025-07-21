package com.tang.project.service.impl.inner;

import com.tang.apicommon.service.InnerUserInterfaceInfoService;
import com.tang.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * ClassName:InnerUserInterfaceInfoServiceImpl
 *
 * @Author tdc
 * Create 2025/7/11 21:28
 * Content
 */
@DubboService(timeout = 3000)
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
