package BookMessage;

public class Book {
    String name;
    int Nowcount;
    int Originallycout;
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", Nowcount=" + Nowcount +
                ", Originallycout=" + Originallycout +
                '}';
    }
}
