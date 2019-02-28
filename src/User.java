
import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private static final long serialVersionUID = 7753744496916409672L;
    private String name;
    private String passWord;
    // 定义已经借了的书的集合
    private HashMap<Integer, String> borrowBook = null;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String name, String passWord) {
        super();
        this.name = name;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public HashMap<Integer, String> getBorrowBook() {
        if (borrowBook == null) {
            borrowBook = new HashMap<Integer, String>();
            
        }
        return borrowBook;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + passWord + "]";
    }

}