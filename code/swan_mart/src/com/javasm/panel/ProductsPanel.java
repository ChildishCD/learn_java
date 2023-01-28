package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.bean.ProductsModel;
import com.javasm.service.ProductsService;

public class ProductsPanel extends BasePanel<ProductsModel> {
    public ProductsPanel(BasePanel prePanel) {
        super(prePanel, new ProductsService());
    }


    public void selectProduct() {
        listPanel("查询商品", this, new String[][]{
                {"id查询", "idSelect"},
                {"商品名查询", "nameSelect"},
                {"查询全部商品", "totalSelect"}
        });
    }

    public void idSelect() {
        sqlPanel("id名查询", "selectProductById", new String[]{
                "商品id"
        });
    }

    public void nameSelect() {
        sqlPanel("商品名查询", "selectProductByName", new String[]{
                "商品名称"
        });
    }

    public void totalSelect() {
        sqlPanel("查询全部商品", "selectAllProducts", false, true, new String[]{

        });
    }

    public void insertProduct() {
        sqlPanel("增加商品", "insertProduct", new String[]{
                "商品名", "商品所属类型", "库存数量", "单价", "商品状态（ 1-正常，2-下架，3-删除）", "商品规格"
        });
    }

    public void updateProduct() {
        sqlPanel("修改商品", "updateProduct", true, false, new String[]{
                "商品名", "商品所属类型", "库存数量", "单价", "商品状态（ 1-正常，2-下架，3-删除）", "商品规格"
        });
    }

    public void deleteProduct() {
        deletePanel();
    }
}
