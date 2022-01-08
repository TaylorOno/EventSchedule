import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private List<Meeting> events = new ArrayList<>();

    public boolean isFree(Date start, Date end) {
        return !events.stream()
                .filter(e->e.getEnd().after(start)) // all existing events that end after current event starts
                .filter(e->e.getStart().before(end)) // all existing events that start before current event ends
                .findAny().isPresent(); //if anything is present room is busy.
    }

    public void addEvent(Meeting meeting) {
        if (isFree(meeting.getStart(), meeting.getEnd())) {
            events.add(meeting);
        }
    }
}


class Meeting {
    private Date start;
    private Date end;

    public Meeting(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}