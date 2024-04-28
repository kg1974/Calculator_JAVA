package Calculator;

import java.util.Scanner;

public class Main {
    
    enum RomanNumeral {
        I(1), II(2), III(3), IV(4), V(5), VI(6), 
        VII(7), VIII(8), IX(9), X(10);
        private final int value;
        RomanNumeral(int value){
            this.value=value;            
        }
        public int getValue() {
            return value;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение: ");
        System.out.println("Введите exit для выхода.");

        while (true) {
            System.out.println("Введите арифметическое выражение: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершается");
                break;
            }

            char[] tokens = input.toCharArray();
            if (tokens.length != 3) {
                System.out.println("Неккоректно введено: введите в одну строку!");
                continue;
            }

            try {
                int num1 = parseNumber(tokens[0]);
                char operator = tokens[1];
                int num2 = parseNumber(tokens[2]);

                int result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("Error: Invalid operator.");
                }

                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid numbers entered.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    static int parseNumber(char c) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else {
            throw new IllegalArgumentException("Error: Invalid number.");
        }
    }
}
