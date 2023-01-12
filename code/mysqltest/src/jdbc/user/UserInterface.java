package jdbc.user;

import jdbc.user.model.SmMenu;
import jdbc.user.model.SmUser;
import jdbc.user.service.SmRoleService;
import jdbc.user.service.SmUserService;

import java.util.*;

public class UserInterface {
    public static void main(String[] args) {
        loginUI();
//        searchByNameLikelyUI();
//        searchByPageUI();
//        changeRoleMenuUI();
    }

    private static void changeRoleMenuUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input roleName:");
        String roleName = scanner.next();
        System.out.println("Please input menu id: (divide by ',' )");
        String mids = scanner.next();
        SmRoleService smRoleService = new SmRoleService();
        if (smRoleService.changeRoleMenu(roleName, mids) != null) {
            System.out.println("Change successfully!");
        } else {
            System.out.println("Input invalid or change failed!");
        }
    }


    private static void searchByPageUI() {
        //TODO:分页查询
        //跟模糊查询一样，仅需在sql中添加limit返回list即可
        //pageUtil,parameterUtil
        //UI,service,DAO
    }

    private static void searchByNameLikelyUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the likely name word:");
        String likelyName = scanner.next();
        //service判断并调用DAO返回姓名类似的user list
        SmUserService smUserService = new SmUserService();
        List<SmUser> smUsers = smUserService.searchByName(likelyName);
        if (smUsers == null) {
            System.out.println("Please input valid name!");
        } else if (smUsers.isEmpty()) {
            System.out.println("No match user!");
        } else {
            smUsers.forEach(System.out::println);
        }
    }

    private static void loginUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        System.out.println("请输入密码:");
        String password = scanner.next();
        //TODO:MD5加密密码
        //新建service类
        SmUserService smUserService = new SmUserService();
        SmUser smUser = smUserService.Login(username, password);
        SmRoleService smRoleService = new SmRoleService();
        smRoleService.setSmRoleForSmUser(smUser);
        Map<Integer, List<SmMenu>> menuListMap = smRoleService.getUserRoleMenus(smUser);
        //输出登录信息
        if (smUser != null) {
            System.out.println("欢迎" + smUser.getSmUserInfo().getNickname() + "登录，您是角色" + smUser.getSmRole().getRoleName() + "，可操作菜单：");
            for(List<SmMenu> smMenus : menuListMap.values()){
                for (SmMenu menu : smMenus){
                    if(menu.getPid() == 0){
                        if(menu.getShow() != 1) break;
                    }else {
                        if(menu.getShow()!=1) continue;
                        System.out.print("\t");
                    }
                    System.out.println("-- "+menu.getMid()+"|"+menu.getName());
                }
            }
        } else {
            System.out.println("Login failed!");
        }
    }
}
