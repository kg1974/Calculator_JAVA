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
    
    static int findOperatorIndex(String input) throws IllegalArgumentException {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                return i;
            }
        }
        throw new IllegalArgumentException("Ошибка: Некорректное выражение.");
    }
    
    static int parseNumber(String str) throws IllegalArgumentException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Попробуем распознать римское число
            try {
                return RomanNumeral.valueOf(str).getValue();
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Ошибка: Неверное число.");
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа Калькулятор ");
        System.out.println("Введите exit для выхода.");
        
        while (true) {
            System.out.println("Введите арифметическое выражение: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершается");
                break;
            }
            
            try {
                // Находим индекс первого символа оператора
                int operatorIndex = findOperatorIndex(input);
                if (operatorIndex == -1 || operatorIndex == 0 || operatorIndex == input.length() - 1) {
                    throw new IllegalArgumentException("Ошибка: Некорректное выражение.");
                }
                
                String leftOperand = input.substring(0, operatorIndex).trim();
                String operator = input.substring(operatorIndex, operatorIndex + 1);
                String rightOperand = input.substring(operatorIndex + 1).trim();
                
                // Преобразуем операнды в числа
                int num1 = parseNumber(leftOperand);
                int num2 = parseNumber(rightOperand);
                
                // Выполняем операцию и выводим результат
                int result;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Деление на ноль!");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("Ошибка: Неверная операция.");
                }
                
                System.out.println("Результат: " + result);
                
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введены некорректные числа.");
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
