package guk.model;

public class UtilityFunctions {
    public static double twoDecimalPlacesRound(double number) {
        return (double) Math.round(number * 100) / 100;
    }
}
