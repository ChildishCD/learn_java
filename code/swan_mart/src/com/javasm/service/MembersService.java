package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.bean.MembersModel;
import com.javasm.dao.MembersDAO;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

public class MembersService extends BaseService<MembersModel> {

    //TODO:密码加密
    public MembersService() {
        super(new MembersDAO());
    }

    public void selectMemberById(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectMemberById", Integer.class);
            results.add((MembersModel) method.invoke(dao, Integer.valueOf(inputs.get(0))));
        }
    }

    public void selectMemberByName(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectMemberByName", String.class);
            results = (List<MembersModel>)method.invoke(dao, inputs.get(0));
        }
    }

    public void selectAllMembers(List<String> inputs) throws Exception {
        Method method = dao.getClass().getMethod("selectAllMembers", Integer.class);
        results = (List<MembersModel>) method.invoke(dao, Integer.valueOf(inputs.get(0)));
    }

    private void insertOne(List<String> inputs, MembersModel member) throws Exception {
        Method method = dao.getClass().getMethod("insertMember", MembersModel.class);
        member.setUpdateTime(LocalDateTime.now());
        method.invoke(dao, member);
        if (!checkResults()) {
            results.add(member);
        }
    }

    public void insertMember(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            MembersModel member = new MembersModel();
            member.setName(inputs.get(0));
            member.setPassword(inputs.get(1));
            member.setContact(inputs.get(2));
            member.setCreateTime(LocalDateTime.now());
            insertOne(inputs, member);
        }
    }

    public void updateMember(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            results.get(0).setName(inputs.get(0));
            results.get(0).setPassword(inputs.get(1));
            results.get(0).setContact(inputs.get(2));
            insertOne(inputs, results.get(0));
        }
    }

    public void addBalance(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Double origin = results.get(0).getBalance();
            results.get(0).setBalance(origin + Double.parseDouble(inputs.get(0)));
            insertOne(inputs, results.get(0));
        }
    }

    public void addPoints(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Double origin = results.get(0).getPoints();
            results.get(0).setPoints(origin + Double.parseDouble(inputs.get(0)));
            insertOne(inputs, results.get(0));
        }
    }
}
