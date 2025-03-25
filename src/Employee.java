
import java.util.Scanner;

public class Employee extends Staff implements ICalculator {

    private double hourAdd;

    public Employee() {
    }

    public Employee(String MaNV, String name, int age, double HeSo, String firstDate, int dayOff, String Whwork, double hourAdd) {
        super(MaNV, name, age, HeSo, firstDate, dayOff, Whwork);
        this.hourAdd = hourAdd;
    }

    public double getHourAdd() {
        return hourAdd;
    }

    public void setHourAdd(double hourAdd) {
        this.hourAdd = hourAdd;
    }

    @Override
    public void getInformation() {
        Scanner s1 = new Scanner(System.in);
        super.getInformation();
        double a;
        do {
            System.out.print("Nhập số giờ làm thêm: ");
            a = s1.nextDouble();
            if(a<0) System.out.println("Vui lòng nhập lại số giờ làm thêm.");
        } while(a<0);
        setHourAdd(a);
        super.setLuong(calculateSalary());
    }

    @Override
    public void displayInformation() {
        super.setLuong(calculateSalary());
        System.out.println(String.format("| %-16s| %-22s|   %-5d| %-9.1f|   %-13s| %-15d| %-21s| %-24.1f| %12.2f|",
                super.getMaNV(), super.getName(), super.getAge(), super.getHeSo(),
                super.getFirstDate(), super.getDayOff(), super.getWhwork(), this.hourAdd, calculateSalary()));
    }

    @Override
    public double calculateSalary() {
        double HeSo = super.getHeSo();
        return HeSo * 3000000 + hourAdd * 200000;
    }
}
