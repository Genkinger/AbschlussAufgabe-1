package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.olympicgames.comparators.SummaryComparator;
import java.util.ArrayList;

/**
 *
 */
public class MedalTable {
    private ArrayList<CountrySummary> summaries = new ArrayList<>();

    /**
     *
     * @param code .
     * @param medals .
     */
    public void addCompetitionResults(IocCode code, Medals medals) {

        CountrySummary summary = findByCode(code);
        if (summary == null) {
            summaries.add(new CountrySummary(code, medals));
        } else {
            summary.getMedals().add(medals);
        }

        summaries.sort(new SummaryComparator());
    }


    private CountrySummary findByCode(IocCode code) {
        for (CountrySummary summary : summaries) {
            if (summary.getIocCode().equals(code)) {
                return summary;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int index = 1;
        for (CountrySummary summary : summaries) {
            builder.append("(");
            builder.append(index);
            builder.append(",");
            builder.append(summary);
            builder.append(")");
            builder.append("\n");
            index++;
        }
        return builder.toString().trim();
    }
}
