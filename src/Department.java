
public class Department {
    private String MaBP;
    private String TenBP;
    private int SoLuong=0;

    public Department() {
    }

    public Department(String MaBP, String TenBP, int SoLuong) {
        this.MaBP = MaBP;
        this.TenBP = TenBP;
        this.SoLuong = SoLuong;
    }

    public String getMaBP() {
        return MaBP;
    }

    public void setMaBP(String MaBP) {
        
        this.MaBP = MaBP;
    }

    public String getTenBP() {
        return TenBP;
    }

    public void setTenBP(String TenBP) {
        
        this.TenBP = TenBP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    @Override
    public String toString() {
        return String.format("| %-11s| %-26s| %-28d|", MaBP,TenBP,SoLuong);
    }
    
}
