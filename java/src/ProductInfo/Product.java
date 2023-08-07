package ProductInfo;

import java.util.Scanner;

public class Product {
//    상품의 고유한 식별자, 상품 설명, 생산자, 가격 정보
    private int id;
    private String description;
    private String maker;
    private int price;

    public Product(int id, String description, String maker, int price) {
        this.id = id;
        this.description = description;
        this.maker = maker;
        this.price = price;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMaker() {
        return maker;
    }

    public int getPrice() {
        return price;
    }

    public static Product add() {
        System.out.print("상품 종류 책<1>, 음악CD<2>, 회화책<3>> ");

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        System.out.print("상품 설명>> ");
        String description = scanner.nextLine();

        System.out.print("생산자>> ");
        String maker = scanner.nextLine();

        System.out.print("가격>> ");
        int price = scanner.nextInt();

        switch (input) {
            case 1:
                return Book.add(description, maker, price);
            case 2:
                return CompactDisc.add(description, maker, price);
            case 3:
                return ConversationBook.add(description, maker, price);
            default:
                System.out.println("잘못된 입력입니다.");
                return null;
        }

    }

    public void print() {
        System.out.println("상품 ID>> " + getId());
        System.out.println("상품 설명>> " + getDescription());
        System.out.println("생산자>> " + getMaker());
        System.out.println("가격>> " + getPrice());
    }
}
