public class HallTicket {

    String studentName;
    int seatNumber;

    /**
     * @param studentName name of the student
     * @param seatNumber  allotted seat number
     */
    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public static void main(String[] args) {
        HallTicket priya = new HallTicket("Priya", 0);

        // Assigning one variable to another copies the reference, not the object
        HallTicket copy = priya;
        copy.seatNumber = 45;

        // A genuinely separate object with identical field values
        HallTicket separate = new HallTicket("Priya", 45);

        System.out.println(priya.studentName + "'s seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));
    }
}