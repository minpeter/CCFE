import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void increaseScore() {
        this.score++;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }
}

public class WordChainGame {


    public static void main(String[] args) {

        run();

    }

    private static void run() {
        Set<String> usedWords = new HashSet<>();
        Scanner scanner = new Scanner(System.in);


        System.out.print("참가자 수를 입력하세요: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        Player[] players = setPlayers(n);


        String prevWord = "아버지"; // 첫 단어는 "아버지"로 시작
        int currentPlayer = 0;

        while (true) {
            System.out.println(players[currentPlayer].getName() + " 참가자의 차례입니다.");
            System.out.println("이전 단어: " + prevWord);
            System.out.print("단어를 입력하세요: ");
            String word = scanner.nextLine();

            if (!isWordValid(word)) {
                System.out.println("유효하지 않은 단어를 입력했습니다. " + players[currentPlayer].getName() + " 참가자가 패배했습니다.");
                printWinner(players);
                break;
            }

            if (!isWordChainValid(prevWord, word) || usedWords.contains(word)) {
                System.out.println("잘못된 단어를 입력했습니다. " + players[currentPlayer].getName() + " 참가자가 패배했습니다.");
                printWinner(players);
                break;
            }

            players[currentPlayer].increaseScore();
            usedWords.add(word);
            prevWord = word;

            currentPlayer = (currentPlayer + 1) % n;
        }

    }

    private static Player[] setPlayers(int n) {

        Scanner scanner = new Scanner(System.in);
//     참가자 이름 입력 받기
        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "번째 참가자의 이름을 입력하세요: ");
            String name = scanner.nextLine();
            players[i] = new Player(name);
        }

        return players;
    }

    // 단어가 유효한지(빈 문자열이 아닌지) 검사하는 함수
    private static boolean isWordValid(String word) {
        return !word.trim().isEmpty();
    }

    // 끝말잇기 규칙에 맞는지 검사하는 함수
    private static boolean isWordChainValid(String prevWord, String currentWord) {
        return prevWord.charAt(prevWord.length() - 1) == currentWord.charAt(0);
    }

    private static void printWinner(Player[] players) {

        int maxScore = 0;
        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
            }
        }

        System.out.println("최종 스코어");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }

        System.out.print("최종 승자: ");
        for (Player player : players) {
            if (player.getScore() == maxScore) {
                System.out.print(player.getName() + " ");
            }
        }
        System.out.println();
    }
}
