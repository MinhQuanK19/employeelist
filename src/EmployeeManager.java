import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class EmployeeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Staff> list = new ArrayList();
        ArrayList<Department> d = new ArrayList();
        int choice=-1, ds = 0;
        VD(list,d);
        do {
            menu();
            System.out.print("Lựa chọn của bạn: ");
            try{
                choice = sc.nextInt();
            } catch(InputMismatchException e){
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1: hienThiThongTin(list); break;
                case 2: hienThiTTBoPhan(d); break;
                case 3: thongTinTungBoPhan(list); break;
                case 4: themNhanVien(list, d, ds); break;
                case 5: timKiemNV(list); break;
                case 6: luongGiam(list); break;
                case 7: luongTang(list); break;
                case 0: System.out.println("Kết thúc!"); break;
                default: System.out.println("Vui lòng nhập lại!"); break;
            }
        } while (choice != 0);
    }
    static void VD(ArrayList<Staff> t, ArrayList<Department> k){
        t.add(new Employee("HE01","Tran Minh Quan",20,5,"08/05/2005",30,"Công nghệ thông tin",8));
        t.add(new Manager("HE02","Ho Phi Hung",23,1,"14/02/2003",40,"Công nghệ thông tin","Business Leader"));
        t.add(new Employee("HE03","Tran Van Quan",19,3,"05/05/2004",10,"Hành chính nhân sự",10));
        k.add(new Department("HC", "Hành chính nhân sự", 1));
        k.add(new Department("IT", "Công nghệ thông tin", 2));
        k.add(new Department("MKT", "Marketing", 0));
        t.get(0).setA(2);
        t.get(1).setA(2);
        t.get(2).setA(1);
    }
    static void menu() {
        System.out.println("\n1. Hiển thị danh sách nhân viên hiện có trong công ty.");
        System.out.println("2. Hiển thị các bộ phận trong công ty.");
        System.out.println("3. Hiển thị các nhân viên theo từng bộ phận.");
        System.out.println("4. Thêm nhân viên mới vào công ty.");
        System.out.println("5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
        System.out.println("6. Hiển thị bảng lương của nhân viên theo thứ tự giảm dần");
        System.out.println("7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần.");
        System.out.println("0. Thoát chương trình.");
    }
    static void formatTable() {
        System.out.println("________________________________________________________________________________________________________________________________________________________________");
        System.out.println("|  Mã nhân viên   |    Tên nhân viên      |  Tuổi  | HS Lương |  Ngày vào làm  | Ngày nghỉ phép |       Bộ phận        | Số giờ làm thêm/Chức vụ |    Lương    |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    static void endFormat() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
    static boolean checkName(String a, String b) {
        String[] c = a.trim().split(" ");
        String[] d = b.trim().split(" ");
        int firstName = c[c.length - 1].compareTo(d[d.length - 1]);
        int lastName = a.compareTo(b);
        if (firstName > 0) {
            return true;
        } else if (firstName == 0) {
            return lastName > 0;
        }
        return false;
    }
    static void hienThiThongTin(ArrayList<Staff> t) {
        if(t.isEmpty()){
            System.out.println("Không có nhân viên trong danh sách.");
            return;
        }
        formatTable();
        for (Staff i : t) {
            i.displayInformation();
            endFormat();
        }
        
    }
    static void hienThiTTBoPhan(ArrayList<Department> t) {
        System.out.println("________________________________________________________________________");
        System.out.println("| Mã bộ phận | Tên bộ phận               | Số lượng nhân viên hiện tại |");
        System.out.println("------------------------------------------------------------------------");
        
        for (Department i : t) {
            System.out.println(i.toString());
        }
    }
    static void thongTinTungBoPhan(ArrayList<Staff> t) {
        String[] departments = {"Hành chính nhân sự", "Công nghệ thông tin", "Marketing"};
        for (int i = 0; i < departments.length; i++) {
            System.out.println(departments[i]);
            System.out.println("--------------------");
            int STT = i + 1;
            formatTable();
            for (Staff j : t) {
                if (j.getA() == STT) {
                    j.displayInformation();
                        endFormat();
                }
            }
            System.out.println();
        }
    }
    static String checkID(ArrayList<Staff> t){
        String ID=null;
        Scanner sc4 = new Scanner(System.in);
        boolean T=true;
            while(T){
                T=false;
                System.out.print("Nhập mã nhân viên: ");
                ID = sc4.next();
                if (t.isEmpty()) break;
                for(int i=0;i<=t.size()-1;i++){
                    T = ID.equals(t.get(i).getMaNV());
                    if (T) { System.out.println("Mã nhân viên đã tồn tại."); break;}
                }
            }
        return ID;
    }
    static void themNhanVien(ArrayList<Staff> t, ArrayList<Department> s, int ds) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("1. Thêm nhân viên thông thường");
        System.out.println("2. Thêm nhân viên là cấp quản lý (Có thêm chức vụ)");
        System.out.println("0. Thoát");
        int chon=-1;
        do{
            System.out.print("Bạn chọn: ");
            try{
                chon = sc1.nextInt();
            } catch (InputMismatchException e){
                sc1.nextLine();
            }
            if (chon<0 || chon>2) System.out.println("Chọn lại!");
        } while(chon<0 || chon>2);
        Employee e = new Employee();
        Manager m = new Manager();
        switch (chon) {
            case 1:
                e.setMaNV(checkID(t));
                e.getInformation();
                t.add(e);
                ds = e.getA();
                break;
            case 2:
                m.setMaNV(checkID(t));
                m.getInformation();
                t.add(m);
                ds = m.getA();
                break;
            default: return;
        }
        s.get(ds-1).setSoLuong(s.get(ds-1).getSoLuong() + 1);
    }
    static void timKiemNV(ArrayList<Staff> t) {
        if(t.isEmpty()){
            System.out.println("Không thể tìm kiếm!");
            return;
        }
        Scanner sc2 = new Scanner(System.in);
        System.out.println("1. Tìm nhân viên bằng tên");
        System.out.println("2. Tìm nhân viên bằng mã nhân viên");
        System.out.println("0. Thoát");
        int choice1=-1;
        do{
            System.out.print("Bạn chọn: ");
            try{
                choice1 = sc2.nextInt();
            } catch (InputMismatchException e){
                sc2.nextLine();
            }
            if (choice1<0 || choice1>2) System.out.println("Chọn lại!");
        } while(choice1<0 || choice1>2);
        int check=0;
        switch (choice1) {
            case 1:
                String ten;
                sc2.nextLine();
                do{
                    System.out.print("Nhập tên nhân viên cần tìm: ");
                    ten = sc2.nextLine();
                    if (ten.isEmpty()) {
                        System.out.println("Nhâp lại!");
                    }
                } while(ten.isEmpty());
                ten=ten.toLowerCase().trim();
                int dTen=ten.length();
                for (Staff i : t) {
                    String iName=i.getName().toLowerCase().substring(i.getName().length()-dTen);
                    if (iName.equals(ten)) {formatTable(); i.displayInformation(); endFormat(); check = 1; break; }
                }
                break;
            case 2:
                System.out.print("Nhập mã nhân viên cần tìm: ");
                sc2.nextLine();
                String ma = sc2.next();
                for (Staff i : t) {
                    if (i.getMaNV().equals(ma)) { formatTable(); i.displayInformation(); endFormat(); check = 1; break; }
                }
                break;
            default: return;
        }
        if (check == 0) System.out.println("Không tìm thấy nhân viên!");
    }
    static void luongGiam(ArrayList<Staff> t) {
        if(t.isEmpty()){
            System.out.println("Không thể sắp xếp!");
            return;
        }
        for (int i = 0; i < t.size() - 1; i++) {
            for (int j = 0; j < t.size() - i - 1; j++) {
                double luong1 = t.get(j).getLuong();
                double luong2 = t.get(j + 1).getLuong();
                boolean CN = checkName(t.get(j).getName(), t.get(j + 1).getName());
                if (luong1 < luong2 || (luong1 == luong2 && CN)) {
                    Staff temp1 = t.get(j);
                    t.set(j, t.get(j + 1));
                    t.set(j + 1, temp1);
                }
            }
        }
        hienThiThongTin(t);
    }
    static void luongTang(ArrayList<Staff> t) {
        if(t.isEmpty()){
            System.out.println("Không thể sắp xếp!");
            return;
        }
        for (int i = 0; i < t.size() - 1; i++) {
            for (int j = 0; j < t.size() - i - 1; j++) {
                double luong1 = t.get(j).getLuong();
                double luong2 = t.get(j + 1).getLuong();
                boolean CN = checkName(t.get(j).getName(), t.get(j + 1).getName());
                if (luong1 > luong2 || (luong1 == luong2 && CN)) {
                    Staff temp1 = t.get(j);
                    t.set(j, t.get(j + 1));
                    t.set(j + 1, temp1);
                }
            }
        }
        hienThiThongTin(t);
    }
}