package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
            .append(System.lineSeparator());

        for (String name : names) {
            int total = 0;
            for (String record : data) {
                String[] parts = record.split("\\s+");
                if (parts.length < 4) {
                    continue;
                }
                LocalDate workDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                String recordName = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);

                if (recordName.equals(name) && !workDate.isBefore(from) && !workDate.isAfter(to)) {
                    total += hours * rate;
                }
            }
            report.append(name).append(" - ").append(total)
            .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
