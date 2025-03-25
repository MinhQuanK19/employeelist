
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Staff {

    private String MaNV;
    private String name;
    private int age;
    private double HeSo;
    private String firstDate;
    private int dayOff;
    private String Whwork;
    private int a;
    private double luong;

    public Staff() {
    }

    public Staff(String MaNV, String name, int age, double HeSo, String firstDate, int dayOff, String Whwork) {
        this.MaNV = MaNV;
        this.name = name;
        this.age = age;
        this.HeSo = HeSo;
        this.firstDate = firstDate;
        this.dayOff = dayOff;
        this.Whwork = Whwork;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name=name.toLowerCase();
        String name2="";
        String[] name1 = name.trim().split(" ");
        for(String i: name1){
            i=i.toUpperCase().charAt(0)+i.substring(1);
            name2+=i+" ";
        }
        this.name = name2.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeSo() {
        return HeSo;
    }

    public void setHeSo(double HeSo) {
        this.HeSo = HeSo;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        String[] date = firstDate.split("/");
        if (date[0].length()==1) date[0]="0"+date[0];
        if (date[1].length()==1) date[1]="0"+date[1];
        this.firstDate = String.join("/", date);
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    public String getWhwork() {
        return Whwork;
    }

    public void setWhwork(String Whwork) {
        this.Whwork = Whwork;
    }

    public abstract void displayInformation();

    public void getInformation() {
        Scanner s = new Scanner(System.in);
        String nameNV;
        do{
            System.out.print("Nhập tên nhân viên: ");
            nameNV = s.nextLine();
            if (nameNV.isEmpty()) System.out.println("Vui lòng nhập lại tên nhân viên.");
        } while (nameNV.isEmpty());
        setName(nameNV);
        int ageNV=0;
        do{
            System.out.print("Nhập tuổi nhân viên: ");
            try{
                ageNV = s.nextInt();
            } catch (InputMismatchException e){
                s.nextLine();
            }
            if (ageNV<16 || ageNV>65) System.out.println("Độ tuổi không phù hợp.");
        } while(ageNV<16 || ageNV>65);
        setAge(ageNV);
        double HeSoNV=-1;
        do{
            System.out.print("Nhập hệ số lương của nhân viên: ");
            try{
                HeSoNV = s.nextDouble();
            } catch (InputMismatchException e){
                s.nextLine();
            }
            if (HeSoNV<0) System.out.println("Vui lòng nhập lại hệ số.");
        } while(HeSoNV<0);
        setHeSo(HeSoNV);
        String date; boolean checkDate=false;
        do{
            if (checkDate) System.out.println("Ngày không phù hợp.");
            checkDate=false;
            System.out.print("Nhập ngày vào làm của nhân viên: ");
            date = s.next();
            String[] day = date.trim().split("[^0-9]+");
            if (day.length!=3 || day[0].length()==0 || day[1].length()==0 
                    || day[2].length()==0) {checkDate=true; continue;}
            int ngay = Integer.parseInt(day[0]);
            int thang = Integer.parseInt(day[1]);
            int nam = Integer.parseInt(day[2]);
            if (nam<1950 || nam>2050 || thang<1||thang>12 ||ngay<1) {checkDate=true; continue;}
            if ((thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) && (ngay > 31)) {checkDate=true; continue;}
            else if ((thang == 4 || thang == 6 || thang == 9 || thang == 11) && (ngay > 30)) {checkDate=true; continue;}
            else if (thang == 2) {
                if ((ngay > 29) || (ngay == 29 && !((nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0)))) {checkDate = true; continue; }
            } 
            date=ngay+"/"+thang+"/"+nam;
            
        } while(checkDate);
        setFirstDate(date);
        int dayOffNV=-1;
        do{
            System.out.print("Nhập số ngày nghỉ phép của nhân viên: ");
            try{
                dayOffNV = s.nextInt();
            } catch (InputMismatchException e){
                s.nextLine();
            }
            if (dayOffNV<0) System.out.println("Vui lòng nhập lại số ngày nghỉ.");
        } while(dayOffNV<0);
        setDayOff(dayOffNV);
        System.out.println("1. HC - Hành chính nhân sự");
        System.out.println("2. IT - Công nghệ thông tin");
        System.out.println("3. MKT - Marketing");
        int choiceBP=0;
        do{
            System.out.print("Bạn chọn bộ phận: ");
            try{
                choiceBP = s.nextInt();
            } catch (InputMismatchException e){
                s.nextLine();
            }
            if (choiceBP<1 || choiceBP>3) System.out.println("Chọn lại!");
        } while(choiceBP<1 || choiceBP>3);
        setA(choiceBP);
        switch (choiceBP) {
            case 1:
                setWhwork("Hành chính nhân sự");
                break;
            case 2:
                setWhwork("Công nghệ thông tin");
                break;
            case 3:
                setWhwork("Marketing");
                break;
        }
    }
}
