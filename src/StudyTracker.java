import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudyTracker {

    private final List<Subject> subjects = new ArrayList<>();
    private final List<StudySession> studySessions = new ArrayList<>();

    public List<Subject> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }
    public List<StudySession> getStudySessions() {
        return Collections.unmodifiableList(studySessions);
    }

    public AddSubjectResult addSubject(String name, String id) {
        if(name.isBlank() || id.isBlank()){
            return AddSubjectResult.INVALID;
        }
        if(searchSubject(id)==null) {
            subjects.add(new Subject(name, id));
            return AddSubjectResult.ADDED;
        }
        return AddSubjectResult.DUPLICATE;
    }

    public AddStudySessionResult addStudySession(String id, int duration) {
        Subject subject = searchSubject(id);
            if (subject != null && duration>=5){
                studySessions.add(new StudySession(subject, duration, LocalDate.now()));
                return AddStudySessionResult.SESSION_ADDED;
            }else if(subject==null){
                return AddStudySessionResult.SUBJECT_NOT_FOUND;
            } else {
                return AddStudySessionResult.INVALID_DURATION;
            }
    }

    public Subject searchSubject(String id) {
        for (Subject subject : subjects) {
            if (subject.getId().equals(id)) {
                return subject;
            }
        }
        return null;
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