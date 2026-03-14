import java.util.Scanner;

public class TicTacToe {
  private char[][] board = new char[3][3];
  private char currentPlayer = '○';

  public TicTacToe() {
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) board[i][j] = ' ';
  }

  public void printBoard() {
    System.out.println("  1   2   3");
    for (int i = 0; i < 3; i++) {
      System.out.print((i + 1) + " ");
      for (int j = 0; j < 3; j++) {
        System.out.print(board[i][j]);
        if (j < 2) System.out.print(" | ");
      }
      System.out.println();
      if (i < 2) System.out.println("  ---------");
    }
  }

  public boolean makeMove(int row, int col) {
    if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
      System.out.println("無効な手です。もう一度入力してください。");
      return false;
    }
    board[row][col] = currentPlayer;
    return true;
  }

  public boolean checkWin() {
    // 横・縦チェック
    for (int i = 0; i < 3; i++) {
      if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
        return true;
      if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
        return true;
    }
    // 斜めチェック
    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
    if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
    return false;
  }

  public boolean isBoardFull() {
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (board[i][j] == ' ') return false;
    return true;
  }

  public void switchPlayer() {
    currentPlayer = (currentPlayer == '○') ? '×' : '○';
  }

  public void play() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== ○×ゲーム ===");
    System.out.println("行と列を入力してください（例: 1 2）\n");

    while (true) {
      printBoard();
      System.out.print("\n" + currentPlayer + " の番 > ");
      int row = scanner.nextInt() - 1;
      int col = scanner.nextInt() - 1;

      if (!makeMove(row, col)) continue;

      if (checkWin()) {
        printBoard();
        System.out.println("\n" + currentPlayer + " の勝ち！おめでとう！");
        break;
      }
      if (isBoardFull()) {
        printBoard();
        System.out.println("\n引き分けです！");
        break;
      }
      switchPlayer();
    }
    scanner.close();
  }

  public static void main(String[] args) {
    new TicTacToe().play();
  }
}
