package iam.shashiraj.ipldashboard.repository;

import iam.shashiraj.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
