package ProductInfo;

import java.util.Scanner;

// Product 클래스를 상속 받음
public class Book extends Product {
//    ISBN 번호, 저자, 책 제목
    private String isbn;
    private String author;
    private String title;

    public Book(int id, String description, String maker, int price, String isbn, String author, String title) {
        super(id, description, maker, price);
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

//    새로운 상품 정보를 입력받고 Book 객체를 생성하여 반환하는 메소드
    public static Book add (String description, String maker, int price) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ISBN번호>> ");
        String isbn = scanner.nextLine();

        System.out.print("저자>> ");
        String author = scanner.nextLine();

        System.out.print("책 제목>> ");
        String title = scanner.nextLine();

        return new Book(0, description, maker, price, isbn, author, title);
    }

    public void print() {
        super.print();
        System.out.println("ISBN번호>> " + getIsbn());
        System.out.println("저자>> " + getAuthor());
        System.out.println("책 제목>> " + getTitle());


    }
}