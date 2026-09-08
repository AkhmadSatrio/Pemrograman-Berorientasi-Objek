public class Setoran {
    String idSetoran;
    String idNasabah;
    String idSampah;
    double beratKg;
    double hargaPerKg;

    public Setoran(String idSetoran, String idNasabah, String idSampah, double beratKg, double hargaPerKg) {
        this.idSetoran = idSetoran;
        this.idNasabah = idNasabah;
        this.idSampah = idSampah;
        this.beratKg = beratKg;
        this.hargaPerKg = hargaPerKg;
    }

    double hitungTotal() {
        return beratKg * hargaPerKg;
    }

    void ubahBerat(double beratBaru) {
        beratKg = beratBaru;
    }

    void tampilkanData() {
        System.out.println("ID Setoran : " + idSetoran);
        System.out.println("Nasabah    : " + idNasabah);
        System.out.println("Sampah     : " + idSampah);
        System.out.println("Berat (kg) : " + beratKg);
        System.out.println("Harga/Kg   : Rp" + hargaPerKg);
        System.out.println("Total      : Rp" + hitungTotal());
    }
}
