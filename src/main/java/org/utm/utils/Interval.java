package org.utm.utils;

/**
 * Класс для хранения интервала [alfa, beta].
 */
public record Interval(double alfa, double beta) {
    @Override
    public String toString() {
        return String.format("[%.2f, %.2f]", alfa, beta);
    }
}
