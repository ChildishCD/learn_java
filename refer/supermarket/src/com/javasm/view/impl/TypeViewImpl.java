package com.javasm.view.impl;

import com.javasm.bean.Type;
import com.javasm.service.TypeService;
import com.javasm.service.impl.TypeServiceImpl;
import com.javasm.util.InputUtil;
import com.javasm.view.TypeView;

import java.util.List;

/**
 * @author: Lisa
 * @className: TypeViewImpl
 * @description:
 * @date: 2021/6/15 10:12
 * @version: 0.1
 * @since: 1.8
 */
public class TypeViewImpl implements TypeView {

    private static TypeService typeService;

    private static List<Type> typeList;

    static {
        typeService = new TypeServiceImpl();
        typeList = typeService.selectAllType();
    }

    @Override
    public void menu() {
        do {
            System.out.println("1.添加类型");
            System.out.println("2.修改类型");
            System.out.println("3.删除类型");
            System.out.println("4.查询类型");
            System.out.println("5.查询子级类型");
            System.out.println("6.退出");

            int choice = InputUtil.inputInt("^[1-6]{1}$", "请录入1-6的数字:");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    select();
                    break;
                case 5:
                    selectChild();
                    break;
                case 6:
                    return;
            }
        } while (true);

    }

    @Override
    public void add() {
        System.out.println("录入类型的名称:");
        String name = InputUtil.input.next();

        selectChild();

        System.out.println("录入类型父级类型:");
        int parentId = InputUtil.inputInt();

        System.out.println("当前类型 是否标记为父类型:  1  0");
        String is_pid = InputUtil.input.next();
        Type type = Type.builder().typeName(name).fid(parentId).isParent(is_pid).build();
        System.out.println(typeService.addType(type));
    }

    @Override
    public void delete() {

        selectChild();
        System.out.println("请录入要删除的类型的id:");
        int typeId = InputUtil.inputInt();
        System.out.println(typeService.deleteTypeById(typeId));
    }

    @Override
    public void update() {

    }

    @Override
    public void select() {

    }

    @Override
    public void selectChild() {
        String str = "|-";
        typeList.stream()
                .filter(type -> type.getFid().equals(0))
                .peek(parentType -> {
                    System.out.println(str + parentType.getId() + ":" + parentType.getTypeName());
                    selectParentChildList(parentType, "| " + str, typeList);
                }).count();

    }

    private void selectParentChildList(Type parentType, String str, List<Type> typeList) {
        typeList.stream().filter(type -> type.getFid().equals(parentType.getId())).peek(childType -> {
            System.out.println("| " + str + childType.getId() + ":" + childType.getTypeName());
            selectParentChildList(childType, "| " + str, typeList);
        }).count();
    }
}
