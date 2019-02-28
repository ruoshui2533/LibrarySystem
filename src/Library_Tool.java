
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library_Tool {

    //定义数据文件保存路径
    private static File fileAddress = new File("C:\\test\\library");

    //获取地址
    public static File getFileAddress() {
        if (!fileAddress.isDirectory()) {
            fileAddress.mkdirs();
        }
        return fileAddress;
    }

    // 定义用户库
    private static ArrayList<User> userList = null;
    // 定义书库
    private static HashMap<Integer, String> bookList = null;

    // 获取用户库
    @SuppressWarnings("unchecked")
    public static ArrayList<User> getUserList() throws IOException, IOException, ClassNotFoundException {
        if (userList == null) {
            // 创建反序列化流
            try {
                ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(getFileAddress(), "User.library")));
                Object o = oi.readObject();
                userList = (ArrayList<User>) o;
                oi.close();
            } catch (Exception e) {
                userList = new ArrayList<User>();
                //创建测试用户
                userList.add(new User("mm", "mm"));
            }
        }
        return userList;
    }

    // 获取书库
    @SuppressWarnings("unchecked")
    public static HashMap<Integer, String> getBookList() throws IOException, IOException, ClassNotFoundException {
        if (bookList == null) {
            try {
                // 创建反序列化流
                ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(getFileAddress(), "Book.library")));
                Object o = oi.readObject();
                bookList = (HashMap<Integer, String>) o;
                oi.close();
            } catch (Exception e) {
                bookList = new HashMap<Integer, String>();
                //创建测试图书
                bookList.put(1, "图书1号");
                bookList.put(2, "十万个为什么");
                bookList.put(3, "嫚嫚为啥那么美");
                
            }
        }
        return bookList;
    }
    
    // 保存文件
    public static void saveFile() throws IOException, IOException, ClassNotFoundException {
        // 创建序列化流
        ObjectOutputStream ooUser = new ObjectOutputStream(new FileOutputStream(new File(fileAddress, "User.library")));
        ObjectOutputStream ooBook = new ObjectOutputStream(new FileOutputStream(new File(fileAddress, "Book.library")));
        
        ooUser.writeObject(getUserList());// 写
        ooBook.writeObject(getBookList());// 写

        ooUser.flush();// 刷新
        ooBook.flush();// 刷新

        ooUser.close();// 关闭
        ooBook.close();// 关闭
    }
    
    // 获取文本数据
    public static String getString(String i) {
        // 创建键盘对象
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        // 输入提示
        System.out.println(i);
        // 赋值数据
        String x = sc.nextLine();
        return x;
    }

    // 获取整数数据
    public static int getInt(String i) {
        // 创建键盘对象
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        // 输入提示
        System.out.println(i);
        while (true) {
            if (sc.hasNextInt()) {
                boolean intFlag = true;
                while (intFlag) {
                    int x = sc.nextInt();
                    if (x < 0) {
                        intFlag = false;
                        System.out.println("请输入正数");
                    } else {
                        return x;
                    }
                }
            } else {
                System.out.println("请输入整数数字");
                sc = new Scanner(System.in);
            }
        }
    }
}