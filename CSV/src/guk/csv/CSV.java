package guk.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public static void main(String[] args) throws IOException {
        String fileName = "CSV/src/guk/csv/input.csv";
        List<Character> charsList = readToList(fileName);

        List<List<String>> lines = splitText(charsList);

        printToHtml(lines);
    }

    private static List<Character> readToList(String fileName) throws IOException {
        List<Character> charsList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            int ch;
            while ((ch = in.read()) != -1) {
                charsList.add((char) ch);
            }
        }
        return charsList;
    }

    private static List<List<String>> splitText(List<Character> charsList) {
        List<List<String>> lines = new ArrayList<>();
        boolean hasQuotes = false;
        StringBuilder s = new StringBuilder();

        s.append("<tr>").append("<td>");
        for (int k = 0; k < charsList.size(); k++) {
            if (charsList.get(k) == '"') {
                if (charsList.get(k + 1) != '"') {
                    hasQuotes = !hasQuotes;
                    continue;
                } else if (charsList.get(k + 1) == '"' && charsList.get(k + 2) == '"') {
                    s.append(charsList.get(k));
                    k += 3;
                    continue;
                } else {
                    s.append(charsList.get(k));
                    k++;
                    continue;
                }
            }

            if (charsList.get(k) == '\r') {
                if (!hasQuotes) {
                    List<String> cell = new ArrayList<>();
                    cell.add(s.append("</td>").append("</tr>").toString());
                    lines.add(cell);
                    k++;
                    s.setLength(0);
                    s.append("<tr>").append("<td>");
                    continue;
                } else {
                    s.append("<br/>");
                    k++;
                    continue;
                }
            }

            if (charsList.get(k) == ',' && !hasQuotes) {
                List<String> cell = new ArrayList<>();
                cell.add(s.append("</td>").toString());
                lines.add(cell);
                s.setLength(0);
                s.append("<td>");
                continue;
            }
            s.append(charsList.get(k));
        }
        return lines;
    }

    private static void printToHtml(List<List<String>> lines) throws IOException {
        try (PrintWriter out = new PrintWriter("CSV/src/guk/csv/output.html")) {
            out.println("<head><meta charset=\"utf-8\"></head>");
            out.println("<table border=\"1\">");

            for (List<String> line : lines) {
                line.forEach(out::println);
            }

            out.print("</table>");
        }
    }
}