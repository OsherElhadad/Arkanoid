// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a Counter that has a value.
 */

package listeners.lhelpers;

/**
 * This Counter class has an integer value and can increase/decrease this value.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class Counter {

    // The integer value of the Counter.
    private int value;

    /**
     * Counter is the constructor method.
     * it defines the integer value of the Counter.
     *
     * @param value is the integer value of the Counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * getValue is a method that returns the integer value of the Counter.
     *
     * @return the integer value of the Counter.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * increase is a method that add number to current count.
     *
     * @param number is the number we add to the current count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * decrease is a method that subtract number from current count.
     *
     * @param number is the number we subtract from the current count.
     */
    public void decrease(int number) {
        this.value -= number;
    }
}
