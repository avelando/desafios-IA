import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Digite uma palavra: ");
        @SuppressWarnings("resource")
        String str1 = new Scanner(System.in).nextLine();
        System.out.println("Digite outra palavra: ");
        @SuppressWarnings("resource")
        String str2 = new Scanner(System.in).nextLine();

        EditDistance.Result result = EditDistance.calculateEditDistance(str1, str2);
        System.out.println("Edições mínimas necessárias: " + result.editDistance);
        System.out.println(result);
    }
}
