package com.tang.project.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子视图
 *

 */
@Data
public class InterfaceInfoVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 创建人信息
     */
    private UserVO user;

    /**
     * 是否已点赞
     */
    private Boolean hasThumb;

    /**
     * 是否已收藏
     */
    private Boolean hasFavour;

    /**
     * 包装类转对象
     *
     * @param interfaceInfoVO
     * @return
     */
//    public static InterfaceInfoVO voToObj(InterfaceInfoVO interfaceInfoVO) {
//        if (interfaceInfoVO == null) {
//            return null;
//        }
//        InterfaceInfo interfaceInfo = new InterfaceInfo();
//        BeanUtils.copyProperties(interfaceInfoVO, interfaceInfo);
//        List<String> tagList = interfaceInfoVO.getTagList();
//        interfaceInfo.setTags(JSONUtil.toJsonStr(tagList));
//        return interfaceInfoVO;
//    }

    /**
     * 对象转包装类
     *
     * @param interfaceInfoVO
     * @return
     */
//    public static InterfaceInfoVO objToVo(InterfaceInfoVO interfaceInfoVO) {
//        if (interfaceInfoVO == null) {
//            return null;
//        }
//        InterfaceInfoVO interfaceInfoVOVO = new InterfaceInfoVO();
//        BeanUtils.copyProperties(interfaceInfoVO, interfaceInfoVOVO);
//        interfaceInfoVOVO.setTagList(JSONUtil.toList(interfaceInfoVO.getTags(), String.class));
//        return interfaceInfoVOVO;
//    }
}
