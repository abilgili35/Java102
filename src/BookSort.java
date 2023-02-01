import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;

public class BookSort {

    

    static class Book implements Comparable<Book>{

        private String name;
        private int pageCount;
        private String author;
        private Date publishDate;
        

        public Book(String name, String author, int pageCount, Date pDate){
            setName(name);
            setAuthor(author);
            setPageCount(pageCount);
            setPublishDate(pDate);
        }

        
        public Date getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(Date publishDate) {
            this.publishDate = publishDate;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
              return false;
            // type check and cast
            if (getClass() != o.getClass())
              return false;
            Book book = (Book) o;
            // field comparison
            if(this.getAuthor().equals(book.getAuthor()) && (this.getName().equals(book.getName()))){
                return true;
            }else{
                return false;
            }
}



        @Override
        public int compareTo(BookSort.Book o) {
            return this.getName().compareTo(o.getName());
        }
        
    }

    public static void printSet(TreeSet<Book> set){
        Iterator<Book> itr = set.iterator();

        while(itr.hasNext()){
            Book b = itr.next();
            String formatted = String.format("%-24s%-24s%-6d%-24s", b.getName(), b.getAuthor(), b.getPageCount(), b.getPublishDate().toString() );
            System.out.println( formatted);
        }
    }

    static class OrderByPageComparator implements Comparator<Book>{

        @Override
        public int compare(BookSort.Book o1, BookSort.Book o2) {
            if(o1.getPageCount() > o2.getPageCount()){
                return 1;
            }else if(o1.getPageCount() < o2.getPageCount()){
                return -1;
            }
            else{
                return 0;
            }
        }

    }

    public static void main(String[] args) {

        TreeSet<Book> tSet = new TreeSet<>();
        tSet.add(new Book("Prag Mezarligi", "Umberto Eco", 510, new Date()));
        tSet.add(new Book("Nutuk", "Mustafa Kemal Ataturk", 950, new Date()));
        tSet.add(new Book("Kumarbaz", "Dostoyevski", 390, new Date()));
        tSet.add(new Book("Savas Ve Baris", "Tolstoy", 600, new Date()));
        tSet.add(new Book("Benim Adim Kirmizi", "Orhan Pamuk", 500, new Date()));
        
        printSet(tSet);

        System.out.println();
        System.out.println();

        TreeSet<Book> tSet2 = new TreeSet<>(new OrderByPageComparator());
        tSet2.addAll(tSet);

        printSet(tSet2);
        
    }
    
}
