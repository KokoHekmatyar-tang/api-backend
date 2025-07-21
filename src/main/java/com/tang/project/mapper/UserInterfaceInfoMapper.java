package com.tang.project.mapper;

import com.tang.apicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author tang
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2025-05-23 16:37:19
* @Entity com.tang.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




