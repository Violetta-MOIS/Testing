import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    void addTest(){
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }
    @Test
    void subtractTest() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    void multiplyTest() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void divideTest() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void divideByZeroTest() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    public static void main(String[] args) {
        System.out.println("Тесты сделаны");
    }

}

