package com.javasm.service;

import com.javasm.bean.Type;
import com.javasm.common.ServerResponse;

import java.util.List;

/**
 * @author: Lisa
 * @className: TypeService
 * @description:
 * @date: 2021/6/15 10:25
 * @version: 0.1
 * @since: 1.8
 */
public interface TypeService {


    List<Type> selectAllType();

    ServerResponse addType(Type type);

    ServerResponse deleteTypeById(int typeId);
}
