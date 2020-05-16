import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final BigDecimal TWO = new BigDecimal("2");

    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int numberOfAttempts = in.nextInt();
        for (int i = 0; i < numberOfAttempts; i++) {
            BigDecimal fromNumber = in.nextBigDecimal();
            findBalancedNumber(fromNumber);
        }
    }

    private static boolean isEven(BigDecimal number){
        if(number.remainder(new BigDecimal("2")).compareTo(BigDecimal.ZERO) != 0){
            return false;
        }
        return true;
    }

    private static void findBalancedNumber(BigDecimal fromNumber) {
        BigDecimal potentialBalancedNumber = fromNumber.add(BigDecimal.ONE);
        while (true) {
            int evenDivider = 0;
            int oddDivider = 1; //to not start from 1 as divisor, it's always odd and divide potentialBalancedNumber so can start checking divisors from 2
            if (isEven(potentialBalancedNumber)) {
                evenDivider = 1;
            } else {
                oddDivider++;
            }
            for (BigDecimal divider = TWO; (divider.compareTo(potentialBalancedNumber.divide(TWO)) == -1 || divider.compareTo(potentialBalancedNumber.divide(TWO)) == 0); divider = divider.add(BigDecimal.ONE)) {
                boolean isDivisor = potentialBalancedNumber.remainder(divider).compareTo(BigDecimal.ZERO) == 0;
                if(isDivisor){
                    boolean isEven = divider.remainder(new BigDecimal("2")).compareTo(BigDecimal.ZERO) == 0;
                    boolean isOdd = divider.remainder(new BigDecimal("2")).compareTo(BigDecimal.ZERO) != 0;
                    if (isDivisor && isEven) {
                        evenDivider++;
                    } else if (isDivisor && isOdd) {
                        oddDivider++;
                    }
                }
            }
            if (oddDivider == evenDivider) { //found balanced number
                System.out.println(potentialBalancedNumber);
                break;
            }
            potentialBalancedNumber = potentialBalancedNumber.add(BigDecimal.ONE);
        }
    }
}
