package jug.ua.meetup;

/**
 * Created by Olena_Syrota on 4/19/2015.
 */
public class POJO {

    public static class Book {
        private String title;
        private String author;

        public Book () {}

        public Book (String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Book book = (Book) o;

            if (!author.equals(book.author)) return false;
            if (!title.equals(book.title)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + author.hashCode();
            return result;
        }
    }

}
