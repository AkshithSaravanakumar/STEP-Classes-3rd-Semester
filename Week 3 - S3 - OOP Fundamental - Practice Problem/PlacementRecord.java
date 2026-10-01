import java.util.Scanner;

public class PlacementRecord {

    private String studentName;
    private String company;
    private double packageLpa;

    /**
     * Constructor that sets all three fields, replacing the need for
     * parallel arrays for names, companies and packages.
     *
     * @param studentName name of the placed student
     * @param company     company the student was placed in
     * @param packageLpa  package offered in LPA
     */
    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCompany() {
        return company;
    }

    public double getPackageLpa() {
        return packageLpa;
    }

    /**
     * Instance method that prints one formatted placement line.
     */
    public void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of placement records: ");
        int count = Integer.parseInt(scanner.nextLine().trim());

        PlacementRecord[] records = new PlacementRecord[count];

        for (int i = 0; i < count; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter company: ");
            String company = scanner.nextLine().trim();
            System.out.print("Enter package in LPA: ");
            double packageLpa = Double.parseDouble(scanner.nextLine().trim());

            records[i] = new PlacementRecord(name, company, packageLpa);
        }

        for (PlacementRecord record : records) {
            record.printRecord();
        }

        scanner.close();
    }
}