package com.Emudiaga.test;

import com.Emudiaga.weekendFitnessClub.CalendarManager;
import com.Emudiaga.weekendFitnessClub.FitnessActivity;
import com.Emudiaga.weekendFitnessClub.Lesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarManagerTest {
    private CalendarManager calendarManager;

    @BeforeEach
    public void setUp() {
        calendarManager = new CalendarManager();
    }

    @Test
    public void testGetLessonsByMonth() {
        HashMap<Integer, ArrayList<Lesson>> lessonsByMonth = calendarManager.getLessonsByMonth();
        assertNotNull(lessonsByMonth);
        assertEquals(2, lessonsByMonth.size());
        assertTrue(lessonsByMonth.containsKey(3));
        assertTrue(lessonsByMonth.containsKey(4));
    }

    @Test
    public void testGetSpecificActivity() {
        FitnessActivity yoga = calendarManager.getSpecificActivity("Yoga");
        assertNotNull(yoga);
        assertEquals("Yoga", yoga.getActivityName());
        assertEquals(25, yoga.getPrice());
    }

    @Test
    public void testCreateNewLesson() {
        HashMap<Integer, ArrayList<Lesson>> lessonsByMonth = calendarManager.getLessonsByMonth();
        assertEquals(16, lessonsByMonth.get(3).size());
        calendarManager.createNewLesson(new FitnessActivity("Test Activity", 20), 3, 1, 1, 3);
        assertEquals(17, lessonsByMonth.get(3).size());
    }

    @Test
    public void testGetAvailableLessonsByDay() {
        ArrayList<Lesson> availableLessons = calendarManager.getAvailableLessons("Saturday");
        assertNotNull(availableLessons);
        assertEquals(16, availableLessons.size());
    }

    @Test
    public void testGetAvailableLessonsByActivity() {
        FitnessActivity yoga = calendarManager.getSpecificActivity("Yoga");
        ArrayList<Lesson> availableLessons = calendarManager.getAvailableLessons(yoga);
        assertNotNull(availableLessons);
        assertEquals(8, availableLessons.size());
    }
}
