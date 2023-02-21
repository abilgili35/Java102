package first;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BookList{
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        List<Book> filteredBookList = new ArrayList<>();
        Map<String, String> bookMap = new HashMap<>();

        Book b1 = new Book("Kar", 450, "Orhan Pamuk", new Date(2001,1,1));
        Book b2 = new Book("Suc ve Ceza", 80, "Dostoyevski", new Date(1970,1,1));
        Book b3 = new Book("Totem ve Tabu", 90, "Sigmund Freud", new Date(1960,1,1));
        Book b4 = new Book("Sefiller", 90, "Victor Hugo", new Date(1970,1,1));
        Book b5 = new Book("Milenaya Mektuplar", 110, "Franz Kafka", new Date(1950,1,1));
        Book b6 = new Book("Tutunamayanlar", 450, "Oguz Atay", new Date(1961,1,1));
        Book b7 = new Book("Simyaci", 60, "Paulo Coelho", new Date(1970,1,1));
        Book b8 = new Book("Kurk Mantolu Madonna", 150, "Sabahattin Ali", new Date(1965,1,1));
        Book b9 = new Book("Hayvan Ciftligi", 500, "George Orwell", new Date(1990,1,1));
        Book b10 = new Book("Fareler ve Insanlar", 110, "John Steinbeck", new Date(2001,1,1));
        
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);
        bookList.add(b7);
        bookList.add(b8);
        bookList.add(b9);
        bookList.add(b10);


        bookMap = bookList.stream().collect(Collectors.toMap(Book::getName, Book::getAuthorName));

        System.out.println("Kitap Listesi");
        
        for(int i=0; i<50; i++){
            System.out.print("-");
        }

        System.out.println("\n");

        for(Book b : bookList){
            System.out.println(b.toString());
        }

        System.out.println("\n\n");

        System.out.println("Kitap ismi ve Yazarlar Map<String, String> icerisinde");
        
        for(int i=0; i<50; i++){
            System.out.print("-");
        }

        System.out.println("\n");

        for(Map.Entry<String, String> entry : bookMap.entrySet()){
            System.out.format("%-30s %-20s\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n\nFiltrelenmis Kitap Listesi(sayfa sayisi > 100)");
        
        for(int i=0; i<50; i++){
            System.out.print("-");
        }
        System.out.println("\n");

        filteredBookList = bookList.stream().filter(b -> b.getPageCount() > 100).collect(Collectors.toList());

        for(Book b : filteredBookList){
            System.out.println(b.toString());
        }
    }
}

class Book {
    
    private String name;
    private int pageCount;
    private String authorName;
    private Date publishDate;

    public Book(String name, int pageCount, String authorName, Date publishDate){
        setName(name);
        setPageCount(pageCount);
        setAuthorName(authorName);
        setPublishDate(publishDate);
    }
    
    

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        String dateString = String.valueOf(getPublishDate().getYear()) + "/" + String.valueOf(getPublishDate().getMonth()) + "/" + String.valueOf(getPublishDate().getDate());
        return String.format("%-30s %-5s %-20s %-10s", getName(), String.valueOf(getPageCount()), getAuthorName(), dateString);
    }
}
