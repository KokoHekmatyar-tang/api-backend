package com.tang.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.project.annotation.AuthCheck;
import com.tang.project.common.BaseResponse;
import com.tang.project.common.DeleteRequest;
import com.tang.project.common.ErrorCode;
import com.tang.project.common.ResultUtils;
import com.tang.project.constant.CommonConstant;
import com.tang.project.constant.UserConstant;
import com.tang.project.exception.BusinessException;
import com.tang.project.exception.ThrowUtils;
import com.tang.project.model.dto.userInterfaceInfo.UserInterfaceInfoAddRequest;
import com.tang.project.model.dto.userInterfaceInfo.UserInterfaceInfoQueryRequest;
import com.tang.project.model.dto.userInterfaceInfo.UserInterfaceInfoUpdateRequest;
import com.tang.apicommon.model.entity.User;
import com.tang.apicommon.model.entity.UserInterfaceInfo;
import com.tang.project.service.UserInterfaceInfoService;
import com.tang.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口管理
 */
@RestController
@RequestMapping("/userInterfaceInfo")
@Slf4j
public class UserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private UserService userService;


    //非管理员不能对调用次数进行更改

    /**
     * 创建
     *
     * @param userInterfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddRequest userInterfaceInfoAddRequest, HttpServletRequest request) {
        if (userInterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoAddRequest, userInterfaceInfo);
        //校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        userInterfaceInfo.setUserId(loginUser.getId());
        boolean result = userInterfaceInfoService.save(userInterfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = userInterfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldInterfaceInfo = userInterfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = userInterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param userInterfaceInfoUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateRequest userInterfaceInfoUpdateRequest) {
        if (userInterfaceInfoUpdateRequest == null || userInterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoUpdateRequest, userInterfaceInfo);
        // 参数校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, false);
        long id = userInterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldInterfaceInfo = userInterfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = userInterfaceInfoService.updateById(userInterfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<UserInterfaceInfo> getUserInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getById(id);
        return ResultUtils.success(userInterfaceInfo);
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param userInterfaceInfoQueryRequest
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<UserInterfaceInfo>> listUserInterfaceInfoByPage(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest, HttpServletRequest request) {
        if (userInterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfoQuery = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoQueryRequest, userInterfaceInfoQuery);
        long current = userInterfaceInfoQueryRequest.getCurrent();
        long size = userInterfaceInfoQueryRequest.getPageSize();
        String sortField = userInterfaceInfoQueryRequest.getSortField();
        String sortOrder = userInterfaceInfoQueryRequest.getSortOrder();

        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userInterfaceInfoQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<UserInterfaceInfo> userInterfaceInfoPage = userInterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(userInterfaceInfoPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param userInterfaceInfoQueryRequest
     * @param request
     * @return
     */
//    @PostMapping("/list/page/vo")
//    public BaseResponse<Page<UserInterfaceInfoService>> listInterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest userInterfaceInfoQueryRequest,
//                                                                              HttpServletRequest request) {
//        long current = userInterfaceInfoQueryRequest.getCurrent();
//        long size = userInterfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<UserInterfaceInfo> userInterfaceInfoPage = userInterfaceInfoService.page(new Page<>(current, size),
//                userInterfaceInfoService.getQueryWrapper(userInterfaceInfoQueryRequest));
//        return ResultUtils.success(userInterfaceInfoService.getInterfaceInfoVOPage(userInterfaceInfoPage, request));
//    }
//
//    /**
//     * 分页获取当前用户创建的资源列表
//     *
//     * @param userInterfaceInfoQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/my/list/page/vo")
//    public BaseResponse<Page<UserInterfaceInfoService>> listMyInterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest userInterfaceInfoQueryRequest,
//                                                                                HttpServletRequest request) {
//        if (userInterfaceInfoQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        User loginUser = userService.getLoginUser(request);
//        userInterfaceInfoQueryRequest.setUserId(loginUser.getId());
//        long current = userInterfaceInfoQueryRequest.getCurrent();
//        long size = userInterfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<UserInterfaceInfo> userInterfaceInfoPage = userInterfaceInfoService.page(new Page<>(current, size),
//                userInterfaceInfoService.getQueryWrapper(userInterfaceInfoQueryRequest));
//        return ResultUtils.success(userInterfaceInfoService.getInterfaceInfoVOPage(userInterfaceInfoPage, request));
//    }
//
//    // endregion
//
//    /**
//     * 分页搜索（从 ES 查询，封装类）
//     *
//     * @param userInterfaceInfoQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/search/page/vo")
//    public BaseResponse<Page<UserInterfaceInfoService>> searchInterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest userInterfaceInfoQueryRequest,
//                                                                                HttpServletRequest request) {
//        long size = userInterfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<UserInterfaceInfo> userInterfaceInfoPage = userInterfaceInfoService.searchFromEs(userInterfaceInfoQueryRequest);
//        return ResultUtils.success(userInterfaceInfoService.getInterfaceInfoVOPage(userInterfaceInfoPage, request));
//    }
//
//    /**
//     * 编辑（用户）
//     *
//     * @param userInterfaceInfoEditRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/edit")
//    public BaseResponse<Boolean> editInterfaceInfo(@RequestBody InterfaceInfoEditRequest userInterfaceInfoEditRequest, HttpServletRequest request) {
//        if (userInterfaceInfoEditRequest == null || userInterfaceInfoEditRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
//        BeanUtils.copyProperties(userInterfaceInfoEditRequest, userInterfaceInfo);
//        // 参数校验
//        userInterfaceInfoService.validInterfaceInfo(userInterfaceInfo, false);
//        User loginUser = userService.getLoginUser(request);
//        long id = userInterfaceInfoEditRequest.getId();
//        // 判断是否存在
//        UserInterfaceInfo oldInterfaceInfo = userInterfaceInfoService.getById(id);
//        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可编辑
//        if (!oldInterfaceInfo.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean result = userInterfaceInfoService.updateById(userInterfaceInfo);
//        return ResultUtils.success(result);
//    }



}
