package com.javasm.order.dao;

import com.javasm.base.BaseDAO;
import com.javasm.order.model.SmOrderModel;
import com.javasm.order.vo.RankVO;
import com.javasm.utils.DBHelper;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderDAO extends BaseDAO<SmOrderModel> {


    public Integer insertOrderGenKey(SmOrderModel orderModel){
        String sql = "INSERT INTO sm_order (member_id,rental,create_time,pay_type) VALUES(?,?,NOW(),?)";
        int id = -1;
        try {
            BigInteger key = DBHelper.getQueryRunner().
                    insert(sql,new ScalarHandler<BigInteger>(), orderModel.getMemberId(), orderModel.getRental(),orderModel.getPayType());
            if (key!=null){
                id = key.intValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<SmOrderModel> selectOrderList(List<Integer> ids){
        String str = ids.stream().map(Objects::toString).collect(Collectors.joining(","));
        String sql = "SELECT * FROM sm_order WHERE id IN ( "+str+" )";
        return selectList(sql);
    }

    public List<RankVO> selectGoodsRank(Integer year,Integer month,Integer type){
        List<Integer> parameters = new ArrayList<>();
        parameters.add(year);
        parameters.add(month);
        String sql= "SELECT info.goods_id id,goods.goods_name name,type.type_name type,SUM(info.goods_num) num " +
                "FROM sm_order_info info,sm_order o,sm_goods goods,sm_goods_type type " +
                "WHERE info.order_id=o.id AND info.goods_id=goods.id " +
                "AND goods.goods_type=type.id " +
                "AND YEAR (o.create_time)=? AND MONTH (o.create_time)=? ";
            if (type != -1){//-1查询全部商品类型
                sql +="AND goods.goods_type=? ";
                parameters.add(type);
            }
                sql +="GROUP BY info.goods_id " +
                "ORDER BY num DESC " +
                "LIMIT 10";
        BeanListHandler<RankVO> beanListHandler = new BeanListHandler<>(RankVO.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return DBHelper.getQueryRunner().query(sql, beanListHandler, parameters.toArray(new Object[0]));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
