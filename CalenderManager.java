import java.io.Serializable;

public class CalenderManager implements Serializable {
    //____________________________ Activities _____________________________________
    private final FitnessActivity yoga = new FitnessActivity("Yoga", 25);
    private final FitnessActivity spin = new FitnessActivity("Spin", 15);
    private final FitnessActivity zumba = new FitnessActivity("Zumba", 35);
    private final FitnessActivity aquacise = new FitnessActivity("Aquacise", 25);


    //_________________________ Calender _________________________________________________

    /* DateSlot Format: First 2 digit; Month, Second 2 digit; Week Number, Third 2 digit; Day 1 or 2(Sat or Sunday)
     * Last 2 digit; First or Second lesson of the day.
     */

    public Lesson marchWeek1SatLesson1 = new Lesson(yoga, "03010101");
    public Lesson marchWeek1SatLesson2 = new Lesson(spin, "03010102");
    public Lesson marchWeek1SunLesson1 = new Lesson(zumba, "03010201");
    public Lesson marchWeek1SunLesson2 = new Lesson(aquacise, "03010202");

    public Lesson marchWeek2SatLesson1 = new Lesson(yoga, "03020101");
    public Lesson marchWeek2SatLesson2 = new Lesson(spin, "03020102");
    public Lesson marchWeek2SunLesson1 = new Lesson(zumba, "03020201");
    public Lesson marchWeek2SunLesson2 = new Lesson(aquacise, "03020202");

    public Lesson marchWeek3SatLesson1 = new Lesson(yoga, "03030101");
    public Lesson marchWeek3SatLesson2 = new Lesson(spin, "03030102");
    public Lesson marchWeek3SunLesson1 = new Lesson(zumba, "03030201");
    public Lesson marchWeek3SunLesson2 = new Lesson(aquacise, "03030202");

    public Lesson marchWeek4SatLesson1 = new Lesson(yoga, "03040101");
    public Lesson marchWeek4SatLesson2 = new Lesson(spin, "03040102");
    public Lesson marchWeek4SunLesson1 = new Lesson(zumba, "03040201");
    public Lesson marchWeek4SunLesson2 = new Lesson(aquacise, "03040202");


    public Lesson aprilWeek1SatLesson1 = new Lesson(yoga, "04010101");
    public Lesson aprilWeek1SatLesson2 = new Lesson(spin, "04010102");
    public Lesson aprilWeek1SunLesson1 = new Lesson(zumba, "04010201");
    public Lesson aprilWeek1SunLesson2 = new Lesson(aquacise, "04010202");

    public Lesson aprilWeek2SatLesson1 = new Lesson(yoga, "04020101");
    public Lesson aprilWeek2SatLesson2 = new Lesson(spin, "04020102");
    public Lesson aprilWeek2SunLesson1 = new Lesson(zumba, "04020201");
    public Lesson aprilWeek2SunLesson2 = new Lesson(aquacise, "04020202");

    public Lesson aprilWeek3SatLesson1 = new Lesson(yoga, "04030101");
    public Lesson aprilWeek3SatLesson2 = new Lesson(spin, "04030102");
    public Lesson aprilWeek3SunLesson1 = new Lesson(zumba, "04030201");
    public Lesson aprilWeek3SunLesson2 = new Lesson(aquacise, "04030202");

    public Lesson aprilWeek4SatLesson1 = new Lesson(yoga, "04040101");
    public Lesson aprilWeek4SatLesson2 = new Lesson(spin, "04040102");
    public Lesson aprilWeek4SunLesson1 = new Lesson(zumba, "04040201");
    public Lesson aprilWeek4SunLesson2 = new Lesson(aquacise, "04040202");

}
