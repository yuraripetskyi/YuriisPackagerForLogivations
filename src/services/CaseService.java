package services;

import models.Case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CaseService {
    private static CaseService caseService;


    public Set<Case> getCases() {
        Set<Case> cases = new TreeSet<>(Comparator.comparingInt(o -> (o.getSizeX() * o.getSizeY() * o.getSizeZ())));
        try {
            Connection connection = SConnection.getConnection();

            PreparedStatement query = connection.prepareStatement("SELECT * FROM cases");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                cases.add(new Case(
                                resultSet.getInt("id"),
                                resultSet.getInt("sizeX"),
                                resultSet.getInt("sizeY"),
                                resultSet.getInt("sizeZ")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
        if (cases.isEmpty())
            System.out.println("Some error, no cases loaded ");
        else
            System.out.println("Cases loaded");
        return cases;
    }


    public static CaseService getCaseService() {
        if (caseService == null)
            caseService = new CaseService();

        return caseService;
    }
}
