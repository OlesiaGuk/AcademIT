package guk.csv;

import java.io.*;

public class CSV {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов программы!");
            System.out.println("Программе необходимо задать 2 аргумента: путь к исходному CSV-файлу и путь к итоговому HTML-файлу");
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(args[0]));
             PrintWriter out = new PrintWriter(args[1])) {
            out.println("<!DOCTYPE html>");
            out.println("<head><meta charset=\"utf-8\"></head>");
            out.println("<body>");
            out.println("<table border=\"1\">");

            String line;
            boolean hasQuotes = false;

            while ((line = in.readLine()) != null) {
                if (!hasQuotes) {
                    out.print("<tr><td>");
                }

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '"') {
                        if ((i + 1) == line.length() || line.charAt(i + 1) != '"') {
                            hasQuotes = !hasQuotes;
                        } else if (line.charAt(i + 1) == '"' && line.charAt(i + 2) == '"') {
                            out.print(line.charAt(i));
                            i += 3;
                        } else {
                            out.print(line.charAt(i));
                            i++;
                        }

                    } else if (line.charAt(i) == ',' && !hasQuotes) {
                        out.println("</td>");
                        out.print("<td>");
                    } else if (line.charAt(i) == '<') {
                        out.print("&lt;");
                    } else if (line.charAt(i) == '>') {
                        out.print("&gt;");
                    } else if (line.charAt(i) == '&') {
                        out.print("&amp;");
                    } else {
                        out.print(line.charAt(i));
                    }
                }

                if (hasQuotes) {
                    out.print("<br/>");
                } else {
                    out.println("</td></tr>");
                }
            }

            out.println("</table>");
            out.println("</body>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при работе с файлом! " + e.getClass() + ": " + e.getMessage());
        }
    }
}