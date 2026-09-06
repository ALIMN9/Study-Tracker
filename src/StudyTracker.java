import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudyTracker {

    private final ArrayList<Subject> subjects = new ArrayList<>();
    private final ArrayList<StudySession> studySessions = new ArrayList<>();

    public List<Subject> getSubjects(){
        return Collections.unmodifiableList(subjects);
    }
    public List<StudySession> getStudySessions(){
        return Collections.unmodifiableList(studySessions);
    }

    public void addSubject(String name, String id) {
        subjects.add(new Subject(name, id));
    }

    public boolean addStudySession(String id, int duration) {
        for (Subject subject : subjects) {
            if (subject.getId().equals(id)) {
                studySessions.add(new StudySession(subject, duration, LocalDate.now()));
                return true;
            }
        }
        return false;
    }

    public boolean searchSubject(String id) {
        for (Subject subject : subjects) {
            if (subject.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalStudyTime() {
        int totalStudyTime = 0;
        for (StudySession session : studySessions) {
            totalStudyTime += session.getDuration();
        }
        return totalStudyTime;
    }

    public boolean removeSubject(String id) {
        boolean removed = subjects.removeIf(subject -> subject.getId().equals(id));
        if (removed) {
            studySessions.removeIf(session -> session.getSubject().getId().equals(id));
        }
        return removed;
    }
}