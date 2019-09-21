package guk.model;

class UtilityFunctions {
    static double twoDecimalPlacesRound(double number) {
        return (double) Math.round(number * 100) / 100;
    }
}
