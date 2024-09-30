package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetBottomGradeUseCase class.
 */
public final class GetBottomGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetBottomGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the worst Grade for a course across your team.
     * @param course The course.
     * @return The bottom grade, or null if there are no grades in the course.
     */
    public Grade getBottomGrade(String course) {
        // Call the API to get the usernames of all your team members
        float min = Float.POSITIVE_INFINITY;
        Grade worstGrade = null;
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        for (String username : team.getMembers()) {
            // Call the API to get the grade for the course for the username
            final Grade[] grades = gradeDataBase.getGrades(username);
            for (Grade grade : grades) {

                if (grade.getCourse().equals(course)) {
                    // Sum all the grades
                    if (grade.getGrade() < min) {
                        min = grade.getGrade();
                        worstGrade = grade;
                    }
                }
            }
        }
        return worstGrade;
    }
}
