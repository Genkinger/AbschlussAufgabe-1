package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.olympicgames.comparators.SummaryComparator;

import java.util.ArrayList;

/**
 * This class represents the {@link MedalTable}.
 * This is a summary of all medals won by a specific country in all olympic games.
 */
public class MedalTable {
    private ArrayList<CountrySummary> summaries = new ArrayList<>();

    /**
     * Adds results of a competition to the specified country represented by the {@link IocCode}.
     *
     * @param code   the countries {@link IocCode}.
     * @param medals the {@link Medals} won in the competition.
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
