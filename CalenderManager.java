public class CalenderManager {
    //____________________________ Activities _____________________________________
    private final FitnessActivity yoga = new FitnessActivity("Yoga", 25);
    private final FitnessActivity spin = new FitnessActivity("Spin", 15);
    private final FitnessActivity zumba = new FitnessActivity("Zumba", 35);
    private final FitnessActivity aquacise = new FitnessActivity("Aquacise", 25);


    //_________________________ Calender _________________________________________________

    /* DateSlot Format: First 2 digit; Month, Second 2 digit; Week Number, Third 2 digit; Day 1 or 2(Sat or Sunday)
     * Last 2 digit; First or Second lesson of the day.
     */

    private Lesson marchWeek1SatLesson1 = new Lesson(yoga, "03010101");
    private Lesson marchWeek1SatLesson2 = new Lesson(spin, "03010102");
    private Lesson marchWeek1SunLesson1 = new Lesson(zumba, "03010201");
    private Lesson marchWeek1SunLesson2 = new Lesson(aquacise, "03010202");

    private Lesson marchWeek2SatLesson1 = new Lesson(yoga, "03020101");
    private Lesson marchWeek2SatLesson2 = new Lesson(spin, "03020102");
    private Lesson marchWeek2SunLesson1 = new Lesson(zumba, "03020201");
    private Lesson marchWeek2SunLesson2 = new Lesson(aquacise, "03020202");

    private Lesson marchWeek3SatLesson1 = new Lesson(yoga, "03030101");
    private Lesson marchWeek3SatLesson2 = new Lesson(spin, "03030102");
    private Lesson marchWeek3SunLesson1 = new Lesson(zumba, "03030201");
    private Lesson marchWeek3SunLesson2 = new Lesson(aquacise, "03030202");

    private Lesson marchWeek4SatLesson1 = new Lesson(yoga, "03040101");
    private Lesson marchWeek4SatLesson2 = new Lesson(spin, "03040102");
    private Lesson marchWeek4SunLesson1 = new Lesson(zumba, "03040201");
    private Lesson marchWeek4SunLesson2 = new Lesson(aquacise, "03040202");


    private Lesson aprilWeek1SatLesson1 = new Lesson(yoga, "04010101");
    private Lesson aprilWeek1SatLesson2 = new Lesson(spin, "04010102");
    private Lesson aprilWeek1SunLesson1 = new Lesson(zumba, "04010201");
    private Lesson aprilWeek1SunLesson2 = new Lesson(aquacise, "04010202");

    private Lesson aprilWeek2SatLesson1 = new Lesson(yoga, "04020101");
    private Lesson aprilWeek2SatLesson2 = new Lesson(spin, "04020102");
    private Lesson aprilWeek2SunLesson1 = new Lesson(zumba, "04020201");
    private Lesson aprilWeek2SunLesson2 = new Lesson(aquacise, "04020202");

    private Lesson aprilWeek3SatLesson1 = new Lesson(yoga, "04030101");
    private Lesson aprilWeek3SatLesson2 = new Lesson(spin, "04030102");
    private Lesson aprilWeek3SunLesson1 = new Lesson(zumba, "04030201");
    private Lesson aprilWeek3SunLesson2 = new Lesson(aquacise, "04030202");

    private Lesson aprilWeek4SatLesson1 = new Lesson(yoga, "04040101");
    private Lesson aprilWeek4SatLesson2 = new Lesson(spin, "04040102");
    private Lesson aprilWeek4SunLesson1 = new Lesson(zumba, "04040201");
    private Lesson aprilWeek4SunLesson2 = new Lesson(aquacise, "04040202");

    public Lesson getMarchWeek1SatLesson1() {
        return marchWeek1SatLesson1;
    }

    public Lesson getMarchWeek1SatLesson2() {
        return marchWeek1SatLesson2;
    }

    public Lesson getMarchWeek1SunLesson1() {
        return marchWeek1SunLesson1;
    }

    public Lesson getMarchWeek1SunLesson2() {
        return marchWeek1SunLesson2;
    }

    public Lesson getMarchWeek2SatLesson1() {
        return marchWeek2SatLesson1;
    }

    public Lesson getMarchWeek2SatLesson2() {
        return marchWeek2SatLesson2;
    }

    public Lesson getMarchWeek2SunLesson1() {
        return marchWeek2SunLesson1;
    }

    public Lesson getMarchWeek2SunLesson2() {
        return marchWeek2SunLesson2;
    }

    public Lesson getMarchWeek3SatLesson1() {
        return marchWeek3SatLesson1;
    }

    public Lesson getMarchWeek3SatLesson2() {
        return marchWeek3SatLesson2;
    }

    public Lesson getMarchWeek3SunLesson1() {
        return marchWeek3SunLesson1;
    }

    public Lesson getMarchWeek3SunLesson2() {
        return marchWeek3SunLesson2;
    }

    public Lesson getMarchWeek4SatLesson1() {
        return marchWeek4SatLesson1;
    }

    public Lesson getMarchWeek4SatLesson2() {
        return marchWeek4SatLesson2;
    }

    public Lesson getMarchWeek4SunLesson1() {
        return marchWeek4SunLesson1;
    }

    public Lesson getMarchWeek4SunLesson2() {
        return marchWeek4SunLesson2;
    }

    public Lesson getAprilWeek1SatLesson1() {
        return aprilWeek1SatLesson1;
    }

    public Lesson getAprilWeek1SatLesson2() {
        return aprilWeek1SatLesson2;
    }

    public Lesson getAprilWeek1SunLesson1() {
        return aprilWeek1SunLesson1;
    }

    public Lesson getAprilWeek1SunLesson2() {
        return aprilWeek1SunLesson2;
    }

    public Lesson getAprilWeek2SatLesson1() {
        return aprilWeek2SatLesson1;
    }

    public Lesson getAprilWeek2SatLesson2() {
        return aprilWeek2SatLesson2;
    }

    public Lesson getAprilWeek2SunLesson1() {
        return aprilWeek2SunLesson1;
    }

    public Lesson getAprilWeek2SunLesson2() {
        return aprilWeek2SunLesson2;
    }

    public Lesson getAprilWeek3SatLesson1() {
        return aprilWeek3SatLesson1;
    }

    public Lesson getAprilWeek3SatLesson2() {
        return aprilWeek3SatLesson2;
    }

    public Lesson getAprilWeek3SunLesson1() {
        return aprilWeek3SunLesson1;
    }

    public Lesson getAprilWeek3SunLesson2() {
        return aprilWeek3SunLesson2;
    }

    public Lesson getAprilWeek4SatLesson1() {
        return aprilWeek4SatLesson1;
    }

    public Lesson getAprilWeek4SatLesson2() {
        return aprilWeek4SatLesson2;
    }

    public Lesson getAprilWeek4SunLesson1() {
        return aprilWeek4SunLesson1;
    }

    public Lesson getAprilWeek4SunLesson2() {
        return aprilWeek4SunLesson2;
    }
}
