package com.javasm.base;

import com.javasm.utils.ParameterUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class BaseService<T> extends Base<T> {
    public List<T> results = new ArrayList<>();
    public List<BaseVO> voResults = new ArrayList<>();
    protected BaseDAO dao;

    public BaseService(BaseDAO dao) {
        this.dao = dao;
    }

    public void initService(){
        results.clear();
        voResults.clear();
    }

    public T selectById(Integer id){
        return (T) dao.selectObjectById(id);
    }

    public void deleteById(Integer id){
        dao.deleteById(id);
    }

    public List<T> getResults() {
        return results;
    }

    public boolean checkInputs(List<String> inputs) {
        if (!ParameterUtil.checkParameter(inputs)) {
            return false;
        }
        for (String s : inputs) {
            if (!ParameterUtil.checkParameter(s)) {
                return false;
            }
        }
        return true;
    }



    public boolean checkResults(){
        return ParameterUtil.checkList((List<Object>) results);
    }
    public boolean checkVOResults(){
        return ParameterUtil.checkList(Collections.singletonList(voResults));
    }

    public void showResults(){
        results.forEach(System.out::println);
    }

    public void showVOs(){
        voResults.forEach(System.out::println);
    }
}
