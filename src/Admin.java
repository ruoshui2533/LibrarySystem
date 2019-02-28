
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Admin {

    @SuppressWarnings({ "resource" })
    public static void main(String[] args) throws IOException, IOException, ClassNotFoundException {

        // 获取文件目录
        File f = Library_Tool.getFileAddress();
        // 获取图书集合
        HashMap<Integer, String> hm = Library_Tool.getBookList();
        // 获取用户集合
        ArrayList<User> al = Library_Tool.getUserList();
        while (true) {
            System.out.println("请输入要执行的操作");
            System.out.println("1.输出图书目录");
            System.out.println("2.输出所有用户");
            System.out.println("3.增加图书");
            System.out.println("4.删除图书");
            System.out.println("5.增加用户");
            System.out.println("6.删除用户");
            System.out.println("7.保存退出");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i == 1) {
                getBookList(f, hm);
            } else if (i == 2) {
                getUserList(f, al);
            } else if (i == 3) {
                addBook(f, hm);
            } else if (i == 4) {
                delBook(f, hm);
            } else if (i == 5) {
                adduser(f);
            } else if (i == 6) {
                deluser(f, al);
            } else if (i == 7) {
                Library_Tool.saveFile();
                System.out.println("保存完成");
                if (Library_Tool.getInt("输入0退出 输入其他继续管理") == 0) {
                    break;
                }
            }
        }
    }

    // 输出图书
    private static void getBookList(File f, HashMap<Integer, String> hm) {
        Set<Map.Entry<Integer, String>> bookListSet = hm.entrySet();
        System.out.println("输出图书:");
        for (Entry<Integer, String> entry : bookListSet) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    // 输出用户
    private static void getUserList(File f, ArrayList<User> al) throws ClassNotFoundException, IOException {
        System.out.println("输出用户:");
        for (int i = 0; i < al.size(); i++) {
            System.out.println(i + "--" + al.get(i).getName() + "--" + al.get(i).getPassWord());
        }
    }

    // 添加图书
    private static void addBook(File f, HashMap<Integer, String> hm) {
        int i = Library_Tool.getInt("输入图书编号");
        String s = Library_Tool.getString("请输入书名");
        hm.put(i, s);

    }

    // 删除图书
    private static void delBook(File f, HashMap<Integer, String> hm) {
        getBookList(f, hm);
        int i = Library_Tool.getInt("输入图书编号");
        hm.remove(i);

    }

    // 添加用户
    private static void adduser(File f) throws ClassNotFoundException, IOException {
        Library l = new Library();
        l.register();

    }

    // 删除用户
    private static void deluser(File f, ArrayList<User> al) throws ClassNotFoundException, IOException {
        getUserList(f, al);
        int i = Library_Tool.getInt("输入要删除的用户编号");
        try {
            al.remove(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("请输入正确的编号");
        }

    }

}