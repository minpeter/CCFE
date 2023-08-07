package ProductInfo;

import java.util.Scanner;

public class CompactDisc extends Product {
//    앨범 제목, 가수 이름
    private String albumTitle;
    private String singer;

    public CompactDisc(int id, String description, String maker, int price, String albumTitle, String singer) {
        super(id, description, maker, price);
        this.albumTitle = albumTitle;
        this.singer = singer;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getSinger() {
        return singer;
    }

//    새로운 상품 정보를 입력받고 CompactDisc 객체를 생성하여 반환하는 메소드
    public static CompactDisc add (String description, String maker, int price) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("앨범 제목>> ");
        String albumTitle = scanner.nextLine();

        System.out.print("가수>> ");
        String singer = scanner.nextLine();

        return new CompactDisc(0, description, maker, price, albumTitle, singer);
    }

    public void print() {
        super.print();
        System.out.println("앨범 제목>> " + getAlbumTitle());
        System.out.println("가수>> " + getSinger());
    }
}
