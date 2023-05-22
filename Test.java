package BookMessage;
import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Test {
    static List<Book> bookGit=new ArrayList<>();
    static List<User> usersList=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        Book book =new Book();
        book.name="张三历险记";
        book.Nowcount=3;
        book.Originallycout=3;
        bookGit.add(book);
        User a1=new User("S","12","超级管理员","保密",0,100,2);
        User a2=new User("ss","1","超级管理员","保密",0,100,0);
        usersList.add(a1);
        usersList.add(a2);
        while(true)
        {
            menu();
            int Input=sc.nextInt();
            if(Input==2) {
                String name=null;
                while(true){
                    System.out.println("请输入用户名:");
                    name=sc.next();
                    if(judgeUsername(name)) break;
                    else System.out.println("输入格式错误！请重新输入");
                }
                int n=3;
                String card=null;
                while(n!=0){
                    System.out.println("请输入用户密码:");
                    card=sc.next();
                    if(judgeUserCard(card)){
                        n=0;
                        int c=judgeUserCardSafe(card);
                        switch (c){
                            case 1:
                                System.out.println("很弱!--小心被暴力破解");
                                break;
                            case 2:
                                System.out.println("较弱!--有待加强");
                                break;
                            case 3:
                                System.out.println("较强！--防范意识较强！");
                                break;
                            case 4:
                                System.out.println("很强!--防范意识超强！！");
                                break;
                        }
                    }
                    else {
                        System.out.println("密码格式错误！");
                        n--;
                    }
                }
                User d=new User();
                d.name=name;
                d.card=card;
                System.out.println("输入昵称：");
                String c=sc.next();
                d.Username=c;
                System.out.println("输入性别：");
                c=sc.next();
                d.sex=c;
                System.out.println("输入年龄：");
                int v=sc.nextInt();
                d.age=v;
                System.out.println("输入可借书籍数：");
                v=sc.nextInt();
                d.bookCount=v;
                usersList.add(d);//用户集合
            }
            else if(Input==1){
                //System.out.println(usersList);
                System.out.println("请输入用户名:");
                String Username;
                Username=sc.next();
                System.out.println("请输入用户密码:");
                String Usercard;
                Usercard=sc.next();
                int y=0;
                for (int i=0;i<usersList.size();i++) {
                    if (usersList.get(i).name != null && usersList.get(i).name.equals(Username)) {
                        if (usersList.get(i).card != null && usersList.get(i).card.equals(Usercard)) {
                            y = 1;
                            System.out.println("登陆成功！");
                            if(usersList.get(i).root==0) memberSelctMenu(usersList.get(i));
                            else if(usersList.get(i).root==1) mengerSelctMenu(usersList.get(i));
                            else superMengerMenu(usersList.get(i));
                        } else {
                            System.out.println("密码错误！");
                            y=-1;
                        }
                    }
                }
                if (y == 0) System.out.println("查无此人！！");
            }
        }
    }

    private static void superMengerMenu(User a) {
        System.out.println("进入图书管理系统！！！");
        System.out.println("您的身份是：超级管理员！！！");
        System.out.println("-----------------------");
        System.out.println(usersList);
        System.out.println("输入你想要提升权限的用户名:");
        String name=sc.next();
        for (int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).name.equals(name)){
                usersList.get(i).root=1;
                System.out.println("修改成功！");
            }
        }
        System.out.println("无此人!");
    }
    private static void mengerSelctMenu(User a) {
        System.out.println("进入图书管理系统！！！");
        System.out.println("您的身份是：管理员！！");
        while(true){
            menuSo();
            int n=sc.nextInt();
            switch (n){
                case 1:
                    FoundBookGit();
                    break;
                case 2:
                    int c=borrowBook();
                    if(c==-1) System.out.println("无此书！！");
                    else a.bookCount-=c;
                    break;
                case 3:
                    ViewBook();
                    break;
                case 4:
                    Storage();
                    break;
                case 5:
                    PrintUserMessage();
                    break;
                case 0:
                    System.out.println("谢谢使用");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }

    private static void PrintUserMessage() {
        System.out.println(usersList);
    }

    private static void Storage() {
        Book s=new Book();
        System.out.println("输入书籍名字:");
        String name=sc.next();
        s.name=name;
        System.out.println("输入书籍个数:");
        int n=sc.nextInt();
        if(n<0||n>10000){
            System.out.println("书籍数目有误！");
            return;
        }
        for (int i = 0; i < bookGit.size(); i++) {
            if(bookGit.get(i).name.equals(name)){
                bookGit.get(i).Nowcount+=n;
                bookGit.get(i).Originallycout+=n;
                return;
            }
        }
        s.Originallycout+=n;
        s.Nowcount+=n;
        bookGit.add(s);
    }

    private static void memberSelctMenu(User a) {
        Scanner s=new Scanner(System.in);
        System.out.println("进入图书管理系统！！！");
        System.out.println("您的身份是：会员！");
        System.out.println("-------------------------------------");
        menuSo();
        while(true){
            System.out.println("请输入你想要的功能:");
            int Input=s.nextInt();
            switch (Input){
                case 1:
                    FoundBookGit();
                    break;
                case 2:
                    int c=borrowBook();
                    a.bookCount-=c;
                    break;
                case 3:
                    ViewBook();
                    break;
                case 4:
                    System.out.println("权限不够，无权访问！！");
                    break;
                case 0:
                    System.out.println("谢谢使用！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误！！");
                    break;
            }
        }
    }
    private static void FoundBookGit() {
        for (Book e:bookGit) {
            System.out.println(e);
        }
    }
    private static int borrowBook() {
        System.out.println("请输入想要借走的书籍名称:");
        int returnBookCnt=0;
        String name=sc.next();
        for (int i = 0; i < bookGit.size(); i++) {
            if(bookGit.get(i).name.equals(name)){
                System.out.println("请输入你想要借走的书籍个数:");
                int bookCnt=sc.nextInt();
                if(bookGit.get(i).Nowcount<bookCnt&&bookGit.get(i).Nowcount!=0) {
                    System.out.println("抱歉书籍库存不够");
                    System.out.println("现有书籍库存为"+bookGit.get(i).Nowcount);
                }
                else if(bookGit.get(i).Nowcount==0) {
                    System.out.println("书籍数量为0");
                    return -1;
                }
                else {
                    bookGit.get(i).Nowcount-=bookCnt;
                    return bookCnt;
                }
                System.out.println("是否需要全部借走书籍(1/0)");
                int n=sc.nextInt();
                if(n==1){
                    returnBookCnt=bookGit.get(i).Nowcount;
                    bookGit.get(i).Nowcount=0;
                    return returnBookCnt;
                }
                else return 0;
            }
        }
        return -1;
    }

    private static void ViewBook() {
        System.out.println("请输入书籍名称:");
        String foundBookName=sc.next();
        for (Book e:bookGit) {
            if(e.name.equals(foundBookName)){
                System.out.println(e);
                return;
            }
        }
        System.out.println("无该书籍");
    }

    private static void menuSo() {
        System.out.println("--------------------------------------");
        System.out.println("--------1.查询图书库   2.借阅书籍---------");
        System.out.println("--------3.查看书籍信息  4.入库--------------");
        System.out.println("-----------------0.exit----------------");
    }
    public static int judgeUserCardSafe(String card)
    {
        int len = card.length();
        int cnt=0,cntChar=0,cntNumber=0,result=0;
        for (int i = 0; i < len; i++) {
            char l=card.charAt(i);
            if((l>='A'&&l<='Z')||(l>='a'&&l<='z')){
                cntChar++;
            }
            else if(l=='&'||l=='@'){
                cnt++;
            }
        }
        cntChar=cntChar>=3?1:0;

        cnt=cnt>0?1:0;

        cntNumber=cntNumber>=8&&cntNumber<=12?2:1;
        result=cnt+cntChar+cntNumber;

        return result;
    }
    public static boolean judgeUserCard(String card)
    {
        int len=card.length();
        if(len>=6&&len<=18){
            for (int i = 0; i < len; i++) {
                char c=card.charAt(i);
                if((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9')||c=='&'&&c=='@'){
                    return true;
                }
                else return false;
            }
        }
        return false;
    }
    public static boolean judgeUsername(String name)
    {
        int len=name.length();
        if(len<=20&&len>=3) {
            for (int i = 0; i < len; i++) {
                char c=name.charAt(i);
                if((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9')){
                    return true;
                }
                else return false;
            }
        }
        return false;
    }
    public static void menu()
    {
        System.out.println("-----------------------");
        System.out.println("------1.登录 2.注册------");
        System.out.println("-----------------------");
    }
}
