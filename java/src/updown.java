import java.util.Scanner;
import java.util.Random;

public class updown {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 사이의 랜덤 숫자 생성

        System.out.println("0부터 99까지의 숫자를 맞춰보세요!");

        int guess;
        int count = 0;
        do {
            System.out.print("입력: ");
            count++;
            guess = scanner.nextInt();

            if (guess < randomNumber) {
                System.out.println("입력한 숫자보다 큽니다.");
            } else if (guess > randomNumber) {
                System.out.println("입력한 숫자보다 작습니다.");
            } else {
                System.out.printf("정답입니다! 축하합니다. %d번 만에 맞추셨습니다.\n", count);
            }
        } while (guess != randomNumber);

        scanner.close();
    }
}
