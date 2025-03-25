
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager extends Staff implements ICalculator {

    private String work;

    public Manager() {
    }

    public Manager(String MaNV, String name, int age, double HeSo, String firstDate, int dayOff, String Whwork, String work) {
        super(MaNV, name, age, HeSo, firstDate, dayOff, Whwork);
        this.work = work;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public void getInformation() {
        Scanner s1 = new Scanner(System.in);
        super.getInformation();
        System.out.println("Chức danh:");
        System.out.println("1. Business Leader");
        System.out.println("2. Project Leader");
        System.out.println("3. Technical Leader");
        int so=0;
        do{
            System.out.print("Nhập chức danh: ");
            try{
                so = s1.nextInt();
            } catch (InputMismatchException e){
                s1.nextLine();
            }
            if(so<1 || so>3) System.out.println("Chọn lại!");
        } while(so<1 || so>3);
        switch (so) {
            case 1:
                setWork("Business Leader");
                break;
            case 2:
                setWork("Project Leader");
                break;
            case 3:
                setWork("Technical Leader");
                break;
        }
        super.setLuong(calculateSalary());
    }

    @Override
    public void displayInformation() {
        super.setLuong(calculateSalary());
        System.out.println(String.format("| %-16s| %-22s|   %-5d| %-9.1f|   %-13s| %-15d| %-21s| %-24s| %12.2f|",
                super.getMaNV(), super.getName(), super.getAge(), super.getHeSo(),
                super.getFirstDate(), super.getDayOff(), super.getWhwork(), this.work, calculateSalary()));
    }

    @Override
    public double calculateSalary() {
        int a = 0;
        switch (work) {
            case "Business Leader":
                a = 8000000;
                break;
            case "Project Leader":
                a = 5000000;
                break;
            case "Technical Leader":
                a = 6000000;
                break;
            default:
                break;
        }
        double HeSo = super.getHeSo();
        return HeSo * 5000000 + a;
    }
}
