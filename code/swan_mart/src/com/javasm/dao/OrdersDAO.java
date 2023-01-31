package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.ProductsTypesModel;
import com.javasm.utils.DBHelper;
import com.javasm.vo.RankVO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrdersDAO extends BaseDAO<MemberOrdersModel> {
    public int insertOrder(MemberOrdersModel order){
        super.saveOrUpdate(order);
        int oId;
        String sql = "SELECT * FROM " + getTableName() +
                " WHERE member_id = ?" +
                " ORDER BY create_time DESC LIMIT 1";
        return super.selectObject(sql,order.getMemberId()).getId();
    }

    public MemberOrdersModel selectOrderById(Integer id) {
        return super.selectObjectById(id);
    }

    public List<MemberOrdersModel> selectOrderByPId(Integer pId) {
        String sql = "SELECT o.* FROM member_orders o,member_orders_products op WHERE o.id = op.order_id AND product_id = ?";
        return super.selectList(sql,pId);
    }
    public List<MemberOrdersModel> selectOrderByMId(Integer mId) {
        String sql = "SELECT * FROM " + getTableName() +
                " WHERE member_id = ?";
        return super.selectList(sql,mId);
    }

    public List<RankVO> selectRank(){
        String sql = "SELECT p.id,p.name,t.name type,s.num,RANK() over (ORDER BY s.num DESC) itsRank FROM products_types t,products p,(SELECT product_id pid,SUM(count) num FROM member_orders_products GROUP BY product_id) s WHERE p.type_id=t.id AND p.id=s.pid LIMIT 10";
        BeanListHandler<RankVO> beanListHandler = new BeanListHandler<>(RankVO.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return DBHelper.getQueryRunner().query(sql, beanListHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RankVO> selectRankByMonth(Integer month){
        String sql = "SELECT p.id,p.NAME,t.NAME type,s.num,RANK() over (ORDER BY s.num DESC) itsRank FROM products_types t,products p,(SELECT op.product_id pid,SUM(count) num FROM member_orders_products op,member_orders o WHERE op.order_id=o.id AND YEAR (NOW())=YEAR (create_time) AND MONTH (NOW())=? GROUP BY op.product_id) s WHERE p.type_id=t.id AND p.id=s.pid LIMIT 10";
        BeanListHandler<RankVO> beanListHandler = new BeanListHandler<>(RankVO.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return DBHelper.getQueryRunner().query(sql, beanListHandler,month);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RankVO> selectRankByType(Integer tId){
        String sql = "SELECT p.id,p.NAME,t.NAME type,s.num,RANK() over (ORDER BY s.num DESC) itsRank FROM products_types t,products p,(SELECT op.product_id pid,SUM(count) num FROM member_orders_products op,products p WHERE op.product_id=p.id AND p.type_id=? GROUP BY op.product_id) s WHERE p.type_id=t.id AND p.id=s.pid LIMIT 10";
        BeanListHandler<RankVO> beanListHandler = new BeanListHandler<>(RankVO.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return DBHelper.getQueryRunner().query(sql, beanListHandler,tId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
