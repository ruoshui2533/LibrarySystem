
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Library implements Serializable {

    private static final long serialVersionUID = 5592434503037466042L;

    // 起始方法
    public void begin() throws ClassNotFoundException, IOException {
        System.out.println("====XXX图书馆欢迎您====");
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("|请选择您要执行的操作（输入数字）|");
            System.out.println("|1.登录                                            |");
            System.out.println("|2.注册                                            |");
            System.out.println("|0.退出                                            |");
            System.out.println("--------------------------------");
            int operation = Library_Tool.getInt("--------------------------------");
            if (operation == 1) {
                System.out.println("======请登录======");
                this.login();
            } else if (operation == 2) {
                this.register();
            } else if (operation == 0) {
                Library_Tool.saveFile();
                break;
            } else {
                System.out.println("请输入指定的数字");
            }
        }
        System.out.println("---------------");
        System.out.println("已退出");
    }

    // 登录方法
    public void login() throws ClassNotFoundException, IOException {
        // 登录循环
        System.out.println("====登录====");
        while (true) {
            // String id = DiyToolsaaa.DiyOperation.getString(i);
            String id = Library_Tool.getString("请输入用户名：");
            String passWord = Library_Tool.getString("请输入密码：");
            // 登录成功标记
            boolean access = false;
            for (User user : Library_Tool.getUserList()) {
                if (id.equals(user.getName()) && passWord.equals(user.getPassWord())) {
                    access = true;
                    loginyes(user);
                    break;
                }
            }
            if (!access) {
                // 登录密码错误执行
                int loginerrorreturn = this.loginError();
                if (loginerrorreturn == 1) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    // 注册方法
    public void register() throws ClassNotFoundException, IOException {
        System.out.println("====注册====");
        // 设置用户名
        String registerName;
        while (true) {
            registerName = Library_Tool.getString("请设置用户名");
            // 检查用户名是否存在
            boolean userExist = false;//假设不重复
            for (User user : Library_Tool.getUserList()) {
                if (user.getName().equals(registerName)) {
                    userExist = true;//标记该位真 表示有重复的用户名
                    break;
                }
            }
            
            // 判断标记如果为真 则表示用户名已存在并给出提示。
            if (userExist) {
                System.out.println("用户名已存在，请重试");
            } else {
                break;
            }
        }
        
    

        // 设置密码
        String registerPassword = Library_Tool.getString("请设置密码");
        // 添加用户对象
        Library_Tool.getUserList().add(new User(registerName, registerPassword));
        Library_Tool.saveFile();
        System.out.println("注册成功");
    }

    // 登录成功执行
    public void loginyes(User c) throws IOException, ClassNotFoundException {
        System.out.println("======登录成功======");
        while (true) {
            System.out.println("|-----------------------------|");
            System.out.println("|请选择您要执行的操作(输入数字):  |");
            System.out.println("|1.查看图书目录                                       |");
            System.out.println("|2.查看已借图书                                       |");
            System.out.println("|3.借书                                                       |");
            System.out.println("|4.还书                                                       |");
            System.out.println("|5.销户                                                       |");
            System.out.println("|0.退出                                                       |");
            // System.out.println("|-----------------------------|");

            // 让用户输入要操作的数字
            int operationnum = Library_Tool.getInt("|-----------------------------|");
            // 判断是否退出
            if (operationnum == 0) {
                break;
            }else if (operationnum == 5) {
                accountCancellation(c);
                break;
            }
            // 执行操作数
            this.operationnum(c, operationnum);
        }
    }

    // 登录密码错误执行
    public int loginError() {
        System.out.println("====用户名或密码错误====");
        int x = 0;
        System.out.println("|---------------------------|");
        System.out.println("|请选择您要执行的操作(输入数字):|");
        System.out
                .println("|1.重试                                                   |");
        System.out
                .println("|2.退出                                                   |");
        // System.out.println("|------------------------------------------|");
        int operationerror = Library_Tool.getInt("|---------------------------|");
        switch (operationerror) {
        case 1:
            break;
        case 2:
            x = 1;
            break;
        default:
            System.out.println("请输入指定的数字");
            break;
        }
        return x;
    }

    // 具体操作方法
    public void operationnum(User c, int operationnum) throws IOException, ClassNotFoundException {
        // 具体操作执行
        switch (operationnum) {
        case 1:
            // 查看图书
            Set<Map.Entry<Integer, String>> bookListSet = Library_Tool.getBookList().entrySet();
            if (bookListSet.size() != 0) {
                System.out.println("==========");
                for (Entry<Integer, String> entry : bookListSet) {
                    System.out.println(entry.getKey() + "-" + entry.getValue());
                }
                System.out.println("==========");
            } else {
                System.out.println("书库暂时没有书");
            }
            break;
        case 2:
            // 看已借图书
            Set<Map.Entry<Integer, String>> borrowBookSet = c.getBorrowBook().entrySet();
            if (borrowBookSet.size() != 0) {
                System.out.println("==========");
                for (Entry<Integer, String> entry : borrowBookSet) {
                    System.out.println(entry.getKey() + "-" + entry.getValue());
                }
                System.out.println("==========");
            } else {
                System.out.println("您还没有借过书喔");
            }

            break;
        case 3:
            // 借书
            borrowBook(c);
            break;
        case 4:
            // 还书
            giveBack(c);
            break;
        default:
            System.out.println("请输入指定的数字");
            break;
        }
    }

    // 借书
    public void borrowBook(User c) throws IOException, ClassNotFoundException {
        int booki = Library_Tool.getInt("请输入要借的图书编号");
        // 向用户对象添加借的书名
        HashMap<Integer, String> bookList = Library_Tool.getBookList();
        if (bookList.get(booki) != null) {
            c.getBorrowBook().put(booki, bookList.get(booki));
            // 从图书馆目录删除书名
            bookList.remove(booki);
            Library_Tool.saveFile();
            System.out.println("操作完成");
        } else {
            System.out.println("请输入正确的编号");
        }
    }

    // 还书
    public void giveBack(User c) throws IOException, ClassNotFoundException {
        int booki = Library_Tool.getInt("请输入要还的图书编号");
        if (c.getBorrowBook().get(booki) != null) {
            // 从图书馆目录添加书名
            Library_Tool.getBookList().put(booki, c.getBorrowBook().get(booki));
            // 从用户对象删除还的书名
            c.getBorrowBook().remove(booki);
            Library_Tool.saveFile();
            System.out.println("操作完成");
        } else {
            System.out.println("请输入正确的编号");
        }

    }

    // 销户方法
    public void accountCancellation(User c) throws ClassNotFoundException, IOException {
        System.out.println("====销户====");
        // 登录循环
        System.out.println("销户后将无法再次登录本银行系统，账户余额清零，确认执行销户操作吗？");
        System.out.println("1、确认");
        System.out.println("0、返回");
        String operation = Library_Tool.getString("请选择：");
        if (operation.equals("1")) {
            for (User user : Library_Tool.getUserList()) {
                if (c.getName().equals(user.getName())) {
                    Library_Tool.getUserList().remove(user);
                    Library_Tool.saveFile();
                    System.out.println("销户成功");
                    break;
                }
            }
        }
    }
}