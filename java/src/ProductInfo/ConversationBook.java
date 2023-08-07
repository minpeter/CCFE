package ProductInfo;

import java.util.Scanner;

public class ConversationBook extends Book {
//    회화책에서 다루는 언어명
    private String language;

    public ConversationBook(int id, String description, String maker, int price, String isbn, String author, String title, String language) {
        super(id, description, maker, price, isbn, author, title);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

//    새로운 상품 정보를 입력받고 ConversationBook 객체를 생성하여 반환하는 메소드
    public static ConversationBook add (String description, String maker, int price) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ISBN번호>> ");
        String isbn = scanner.nextLine();

        System.out.print("저자>> ");
        String author = scanner.nextLine();

        System.out.print("책 제목>> ");
        String title = scanner.nextLine();

        System.out.print("언어>> ");
        String language = scanner.nextLine();

        return new ConversationBook(0, description, maker, price, isbn, author, title, language);
    }

    public void print() {
        super.print();
        System.out.println("언어>> " + getLanguage());
    }
}
