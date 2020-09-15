package eban;


import java.util.Scanner;
import java.util.ArrayList;
/**
 * 2.Вывести числа в порядке возрастания (убывания) значений их длины.
 *
 * 3.Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.
 *
 * 4.Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.
 *
 */
public class App
{
    public static void main(String[] args)
    {
        try {

            System.out.print("Введите кол-во чисел: ");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            System.out.println("\n");

            System.out.print("Введите числа: ");
            int[] argsInt = new int[n];
            for (int i = 0; i < n; i++)
                argsInt[i] = scanner.nextInt();
            System.out.println("\n");

            /* №2 */
            sortByAscendingByLength(argsInt);
            System.out.print("sortByAscendingByLength : ");
            for (int j : argsInt) System.out.print(j + " ");
            System.out.println("\n");

            sortDescendingByLength(argsInt);
            System.out.print("sortDescendingByLength : ");
            for (int j : argsInt) System.out.print(j + " ");
            System.out.println("\n");

            /* №3 */

            int averageLen = averageLength(argsInt);

            System.out.println("Средняя длина: " + averageLen);

            System.out.print("lessAverageLength: ");
            lessAverageLength(argsInt, averageLen);

            System.out.print("moreAverageLength: ");
            moreAverageLength(argsInt, averageLen);

            /* №4 */

            int min = countDifferentNum(argsInt[0]);
            int minId = 0;
            for(int i = 1; i < argsInt.length; i++) {
                int countDiffNum = countDifferentNum(argsInt[i]);
                if ( countDiffNum < min) {
                    min = countDiffNum;
                    minId = i;
                }
            }
            System.out.println("Минимальное кол-во разных цифр в числе: " + argsInt[minId]
                    + " Разных цифр: " + min);

        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
    }

    public static int lengthNum(int num){
        int length = 0;

        while(num % 10 != 0) {
            num /= 10;
            length++;
        }

        return length;
    }

    public static void sortByAscendingByLength(int[] args) {
        for (int i = 0; i < args.length; i++) {
            int minLength = lengthNum(args[i]);
            int min = args[i];
            int minId = i;
            for (int j = i+1; j < args.length; j++) {
                if (lengthNum(args[j]) < minLength) {
                    min = args[j];
                    minId = j;
                }
            }

            int temp = args[i];
            args[i] = min;
            args[minId] = temp;
        }
    }

    public static void sortDescendingByLength(int[] args){

        for (int i = 0; i < args.length; i++) {
            int maxLength = lengthNum(args[i]);
            int max = args[i];
            int maxId = i;
            for (int j = i+1; j < args.length; j++) {
                if (lengthNum(args[j]) > maxLength) {
                    max = args[j];
                    maxId = j;
                }
            }

            int temp = args[i];
            args[i] = max;
            args[maxId] = temp;
        }
    }

    public static int averageLength(int[] args){
        int sum = 0;
        for(int j : args) sum += lengthNum(j);

        return sum/args.length;
    }

    public static void lessAverageLength(int[] args, int averageLength){

        for(int j: args) {
            if (lengthNum(j) < averageLength)
                System.out.print(j + " ");
        }
        System.out.println("\n");

    }

    public static void moreAverageLength(int[] args, int averageLength){
        for(int j: args) {
            if (lengthNum(j) > averageLength)
                System.out.print(j + " ");
        }
        System.out.println("\n");

    }

    private static int countInArray(ArrayList<Integer> args, int elem)
    {
        int count = 0;
        for(int j : args)
            if(j == elem) count++;
        return  count;
    }

    private static int countDifferentNum(int num){
        ArrayList<Integer> numeral = new ArrayList<>();

        while (num % 10 != 0){
            numeral.add(num%10);
            num /= 10;
        }

        ArrayList<Integer> diffNum = new ArrayList<>();
        for (int j : numeral)
            if(countInArray(diffNum,j) == 0) diffNum.add(j);

        return diffNum.size();
    }
}
