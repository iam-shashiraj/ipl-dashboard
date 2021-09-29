package iam.shashiraj.ipldashboard;

import iam.shashiraj.ipldashboard.data.MatchInput;
import iam.shashiraj.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setMatchWinner(matchInput.getWinner());
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setCity(matchInput.getCity());
        match.setResultMargin(matchInput.getResult_margin());
        // Set Team 1 and Team 2 depending on the innings order
        String firstInningsTeam, secondInningsTeam;

        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2() : matchInput.getTeam1();

        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
        match.setTossDecision(matchInput.getToss_decision());
        match.setTossWinner(matchInput.getToss_winner());
        match.setUmpire2(matchInput.getUmpire2());
        match.setUmpire1(matchInput.getUmpire1());
        match.setVenue(matchInput.getVenue());

        return match;
    }

}
