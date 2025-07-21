package com.tang.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装id参数,使所有的post接口保持统一
 *

 */
@Data
public class IdRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}