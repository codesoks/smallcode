package BookMessage;

public class User {
    public String name;
    public String card;

    public String Username;
    public String sex;
    public int age;
    public int bookCount;

    public String message;

    public int root=0;

    public User(String name, String card, String username, String sex, int age, int bookCount, int root) {
        this.name = name;
        this.card = card;
        Username = username;
        this.sex = sex;
        this.age = age;
        this.bookCount = bookCount;
        this.root = root;
    }


    public User() {
    }
    public User(String name, String card) {
        this.name = name;
        this.card = card;
    }
    public User(String username, String sex, int age, int bookCount) {
        this.Username = username;
        this.sex = sex;
        this.age = age;
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", Username='" + Username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", bookCount=" + bookCount +
                ", message='" + message + '\'' +
                '}';
    }
}
