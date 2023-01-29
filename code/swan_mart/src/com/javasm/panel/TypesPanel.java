package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.bean.ProductsTypesModel;
import com.javasm.service.TypesService;

public class TypesPanel extends BasePanel<ProductsTypesModel> {
    public TypesPanel(BasePanel prePanel) {
        super(prePanel, new TypesService());
    }
    //TODO：show()重载实现树级显示

    public void selectType() {
        listPanel("查询商品", this, new String[][]{
                {"id查询", "idSelect"},
                {"商品类型名查询", "nameSelect"},
                {"查询全部商品类型", "totalSelect"}
        });
    }

    public void idSelect() {
        sqlPanel("id名查询", "selectTypeById", new String[]{
                "商品类型id"
        });
    }

    public void nameSelect() {
        sqlPanel("商品名查询", "selectTypeByName", new String[]{
                "商品类型名称"
        });
    }

    public void totalSelect() {
        sqlPanel("查询全部商品类型", "selectAllTypes", false, true, new String[]{

        });
    }

    public void insertType() {
        sqlPanel("增加商品类型", "insertType", new String[]{
                "商品类型ID", "父级商品类型ID", "是否为父类型（1为是，0为否）", "商品类型名称"
        });
    }

    public void updateType() {
        sqlPanel("修改商品类型", "updateType", true, false, new String[]{
                "商品类型ID", "父级商品类型ID", "是否为父类型（1为是，0为否）", "商品类型名称"
        });
    }

    public void deleteType() {
        deletePanel();
    }
}
