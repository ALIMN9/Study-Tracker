import java.time.LocalDate;

public class StudySession{
    private final Subject subjectInfo;
    private int duration;
    private LocalDate date;

    public StudySession(Subject subjectInfo,int duration,LocalDate date){
        if (subjectInfo == null) {
            throw new IllegalArgumentException("Subject cannot be null!");
        }
        this.subjectInfo = subjectInfo;
        if(duration<5){
            throw new IllegalArgumentException("session duration can't be less than 5min");
        }
            this.duration = duration;
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null!");
        }
        if(date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Invalid Date");
        }
            this.date= date;
    }
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null!");
        }
        if(date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Invalid Date");
        }
            this.date=date;
    }

    public int getDuration(){return duration;}
    public void setDuration(int duration){
        if(duration<5){
            throw new IllegalArgumentException("session duration can't be less than 5min");
        }
        this.duration = duration;
    }

    public Subject getSubject(){return subjectInfo;}

}
