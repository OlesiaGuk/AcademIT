package guk.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (from >= range.to || to <= range.from) {
            return null;
        }
        double max = Math.max(from, range.from);
        double min = Math.min(to, range.to);
        return new Range(max, min);
    }

    public Range[] getUnion(Range range) {
        if (from > range.to || to < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
        double min = Math.min(from, range.from);
        double max = Math.max(to, range.to);
        return new Range[]{new Range(min, max)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.to || to <= range.from) {
            return new Range[]{new Range(from, to)};
        }
        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }
        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }
        if (from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        }
        return new Range[]{new Range(range.to, to)};
    }

    @Override
    public String toString() {
        return ("(" + from + ", " + to + ")");
    }
}