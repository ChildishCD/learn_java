package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.Shopping;

import java.util.HashMap;
import java.util.Map;

public class DealsService extends BaseService<MemberOrdersModel> {
    public Map<Integer, Shopping> shoppingCar = new HashMap<>();


}
