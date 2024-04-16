import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EditDistance {

    public static Result calculateEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        String[][] operation = new String[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    operation[i][j] = "Inserir " + (j > 0 ? str2.charAt(j - 1) : "");
                } else if (j == 0) {
                    dp[i][j] = i;
                    operation[i][j] = "Remover " + str1.charAt(i - 1);
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    operation[i][j] = "Nenhuma operação";
                } else {
                    int min = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j] = 1 + min;
                    if (min == dp[i - 1][j]) {
                        operation[i][j] = "Remover " + str1.charAt(i - 1);
                    } else if (min == dp[i][j - 1]) {
                        operation[i][j] = "Inserir " + str2.charAt(j - 1);
                    } else {
                        operation[i][j] = "Substituir " + str1.charAt(i - 1) + " por " + str2.charAt(j - 1);
                    }
                }
            }
        }

        List<String> ops = new ArrayList<>();
        int i = m, j = n;
        while (i > 0 || j > 0) {
            if (operation[i][j].equals("Nenhuma operação")) {
                i--;
                j--;
            } else {
                ops.add(operation[i][j]);
                if (operation[i][j].startsWith("Substituir") || operation[i][j].startsWith("Nenhuma operação")) {
                    i--;
                    j--;
                } else if (operation[i][j].startsWith("Inserir")) {
                    j--;
                } else {
                    i--;
                }
            }
        }

        Collections.reverse(ops); 

        return new Result(dp[m][n], ops);
    }

    public static class Result {
        public int editDistance;
        public List<String> operations;

        public Result(int editDistance, List<String> operations) {
            this.editDistance = editDistance;
            this.operations = operations;
        }

        @Override
        public String toString() {
            return editDistance + " -> " + String.join(", ", operations);
        }
    }
}
