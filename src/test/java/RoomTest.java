
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomTest {
    private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");

    @Test
    public void isFree() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("12:00 PM"), formatter.parse("01:00 PM")));
        assertTrue(room.isFree(formatter.parse("11:00 AM"), formatter.parse("12:00 PM")));
    }

    @Test
    public void isFree_NotFree() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("12:00 PM"), formatter.parse("01:00 PM")));
        assertFalse(room.isFree(formatter.parse("12:15 PM"), formatter.parse("12:45 PM")));
    }

    @Test
    public void isFree_After() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("12:00 PM"), formatter.parse("01:00 PM")));
        room.addEvent(new Meeting(formatter.parse("01:00 PM"), formatter.parse("02:00 PM")));
        assertTrue(room.isFree(formatter.parse("02:00 PM"), formatter.parse("03:00 PM")));
    }

    @Test
    public void isFree_Before() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("12:00 PM"), formatter.parse("01:00 PM")));
        room.addEvent(new Meeting(formatter.parse("01:00 PM"), formatter.parse("02:00 PM")));
        assertTrue(room.isFree(formatter.parse("11:00 AM"), formatter.parse("12:00 PM")));
    }

    @Test
    public void isFree_Between() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("11:00 AM"), formatter.parse("12:00 PM")));
        room.addEvent(new Meeting(formatter.parse("1:00 PM"), formatter.parse("02:00 PM")));
        assertTrue(room.isFree(formatter.parse("12:00 PM"), formatter.parse("01:00 PM")));
    }

    @Test
    public void isNotFree_Between() throws ParseException {
        Room room = new Room();
        room.addEvent(new Meeting(formatter.parse("11:00 AM"), formatter.parse("12:00 PM")));
        room.addEvent(new Meeting(formatter.parse("1:00 PM"), formatter.parse("02:00 PM")));
        assertFalse(room.isFree(formatter.parse("11:30 AM"), formatter.parse("1:30 PM")));
    }
}