import java.util.Scanner;

public class alphabetCount {
    public static void main(String[] args) {

        System.out.println("종료하기 위해 `exit`를 입력하세요.");

        Scanner scanner = new Scanner(System.in);
        int[] alphabet = new int[26];
        String input;

        while (true) {
            System.out.print(">> ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    alphabet[ch - 'a']++;
                } else if (ch >= 'A' && ch <= 'Z') {
                    alphabet[ch - 'A']++;
                }
            }
        }

        for (int i = 0; i < alphabet.length; i++) {
            System.out.print((char)('a' + i) + ": ");
            for (int j = 0; j < alphabet[i]; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
