package ProductInfo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ProductInfo {


    public static void main(String[] args) {

        run();

    }

    private static int getMaxId(Set<Product> productList) {
        int maxId = 0;
        for (Product product : productList) {
            if (product.getId() > maxId) {
                maxId = product.getId();
            }
        }
        return maxId;
    }

//    모든 상품 출력
    private static void printAllProduct(Set<Product> productList) {
        for (Product product : productList) {
            product.print();
        }
    }

    private static void run() {
        Set<Product> productList = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("상품 추가<1>, 모든 상품 조회<2>, 끝내기<3> >> ");
            int input = scanner.nextInt();
            scanner.nextLine();

//            위에 선택에 따라 분기
            switch  (input) {
                case 1:

//                   상품 갯수가 10개를 넘었다면
                    if (productList.size() >= 10) {
                        System.out.println("상품은 10개까지만 등록 가능합니다.");
                        break;
                    }

                    Product pat = Product.add();
                    pat.setId(getMaxId(productList) + 1);
                    productList.add(pat);
                    break;

                case 2:
                    printAllProduct(productList);
                    break;

                case 3:
                    System.out.println("프로그램 종료");
                    System.exit(0);

                default:
                    System.out.println("잘못된 입력입니다.");
                    break;

            }


        }

    }
}

