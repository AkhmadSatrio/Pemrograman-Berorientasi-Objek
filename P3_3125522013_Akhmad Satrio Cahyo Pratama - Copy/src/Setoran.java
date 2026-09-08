public class Setoran {
    private String idSetoran;
    private String idNasabah;
    private String idSampah;
    private double beratKg;
    private double hargaPerKg;

    public Setoran(String idSetoran, String idNasabah, String idSampah, double beratKg, double hargaPerKg) {
        this.idSetoran = idSetoran;
        this.idNasabah = idNasabah;
        this.idSampah = idSampah;
        setBeratKg(beratKg);
        setHargaPerKg(hargaPerKg);
    }

    public String getIdSetoran() {
        return idSetoran;
    }

    public String getIdNasabah() {
        return idNasabah;
    }

    public String getIdSampah() {
        return idSampah;
    }

    public double getBeratKg() {
        return beratKg;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public void setBeratKg(double beratKg) {
        if (beratKg > 0) {
            this.beratKg = beratKg;
        } else {
            System.out.println("Berat tidak valid (harus lebih dari 0)");
        }
    }

    public void setHargaPerKg(double hargaPerKg) {
        if (hargaPerKg > 0) {
            this.hargaPerKg = hargaPerKg;
        } else {
            System.out.println("Harga/Kg tidak valid (harus lebih dari 0)");
        }
    }

    public double hitungTotal() {
        return beratKg * hargaPerKg;
    }

    public void tampilkanData() {
        System.out.println("ID Setoran : " + idSetoran);
        System.out.println("Nasabah    : " + idNasabah);
        System.out.println("Sampah     : " + idSampah);
        System.out.println("Berat (kg) : " + beratKg);
        System.out.println("Harga/Kg   : Rp" + hargaPerKg);
        System.out.println("Total      : Rp" + hitungTotal());
    }
}
