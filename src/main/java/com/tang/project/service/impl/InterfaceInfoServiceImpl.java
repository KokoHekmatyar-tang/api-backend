package com.tang.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.apicommon.model.entity.InterfaceInfo;
import com.tang.project.common.ErrorCode;
import com.tang.project.constant.CommonConstant;
import com.tang.project.exception.BusinessException;
import com.tang.project.mapper.InterfaceInfoMapper;
import com.tang.project.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.tang.project.model.vo.InterfaceInfoVO;
import com.tang.project.service.InterfaceInfoService;
import com.tang.project.service.UserService;
import com.tang.project.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author tang
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2025-04-01 02:36:43
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Resource
    private UserService userService;
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }
//    @Override
//    public QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
//     Long id = interfaceInfoQueryRequest.getId();
//     String name = interfaceInfoQueryRequest.getName();
//     String description = interfaceInfoQueryRequest.getDescription();
//     String url = interfaceInfoQueryRequest.getUrl();
//     String requestHeader = interfaceInfoQueryRequest.getRequestHeader();
//     String responseHeader = interfaceInfoQueryRequest.getResponseHeader();
//     Integer status = interfaceInfoQueryRequest.getStatus();
//     String method = interfaceInfoQueryRequest.getMethod();
//     Long userId = interfaceInfoQueryRequest.getUserId();
//     Date createTime = interfaceInfoQueryRequest.getCreateTime();
//     Date updateTime = interfaceInfoQueryRequest.getUpdateTime();
//     Integer isDelete = interfaceInfoQueryRequest.getIsDelete();
//     int current = interfaceInfoQueryRequest.getCurrent();
//     int pageSize = interfaceInfoQueryRequest.getPageSize();
//     String sortField = interfaceInfoQueryRequest.getSortField();
//     String sortOrder = interfaceInfoQueryRequest.getSortOrder();
//
//        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
//        if (interfaceInfoQueryRequest == null) {
//            return queryWrapper;
//        }
//
//        // 拼接查询条件
////        if (StringUtils.isNotBlank(searchText)) {
////            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
////        }
////        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
////        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
////        if (CollUtil.isNotEmpty(tagList)) {
////            for (String tag : tagList) {
////                queryWrapper.like("tags", "\"" + tag + "\"");
////            }
////        }
////        queryWrapper.new(ObjectUtils.isNotEmpty(notId), "id", notId);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
//        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
//                sortField);
//        return queryWrapper;
//    }
//
//    @Override
//    public InterfaceInfoVO getInterfaceInfoVO(InterfaceInfo interfaceInfo, HttpServletRequest request) {
////        InterfaceInfoVO interfaceInfoVO = InterfaceInfoVO.objToVo(interfaceInfo);
////        long interfaceInfoId = interfaceInfo.getId();
////        // 1. 关联查询用户信息
////        Long userId = interfaceInfo.getUserId();
////        User user = null;
////        if (userId != null && userId > 0) {
////            user = userService.getById(userId);
////        }
////        UserVO userVO = userService.getUserVO(user);
////        interfaceInfoVO.setUser(userVO);
////        // 2. 已登录，获取用户点赞、收藏状态
////        User loginUser = userService.getLoginUserPermitNull(request);
////        if (loginUser != null) {
////            // 获取点赞
////            QueryWrapper<PostThumb> interfaceInfoThumbQueryWrapper = new QueryWrapper<>();
////            interfaceInfoThumbQueryWrapper.in("interfaceInfoId", interfaceInfoId);
////            interfaceInfoThumbQueryWrapper.eq("userId", loginUser.getId());
////            PostThumb interfaceInfoThumb = interfaceInfoThumbMapper.selectOne(interfaceInfoThumbQueryWrapper);
////            interfaceInfoVO.setHasThumb(interfaceInfoThumb != null);
////            // 获取收藏
////            QueryWrapper<PostFavour> interfaceInfoFavourQueryWrapper = new QueryWrapper<>();
////            interfaceInfoFavourQueryWrapper.in("interfaceInfoId", interfaceInfoId);
////            interfaceInfoFavourQueryWrapper.eq("userId", loginUser.getId());
////            PostFavour interfaceInfoFavour = interfaceInfoFavourMapper.selectOne(interfaceInfoFavourQueryWrapper);
////            interfaceInfoVO.setHasFavour(interfaceInfoFavour != null);
////        }
////        return interfaceInfoVO;
//        return null;
//    }

}




