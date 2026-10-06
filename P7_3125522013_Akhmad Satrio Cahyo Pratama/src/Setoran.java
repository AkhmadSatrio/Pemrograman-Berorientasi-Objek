/**
 * Class Setoran
 * Relasi Asosiasi antara Nasabah dan Sampah.
 */
public class Setoran {
    private String idSetoran;
    private Nasabah nasabah;
    private Sampah sampah;
    private double beratKg;

    public Setoran(String idSetoran, Nasabah nasabah, Sampah sampah, double beratKg) {
        this.idSetoran = idSetoran;
        this.nasabah = nasabah;
        this.sampah = sampah;
        setBeratKg(beratKg);
    }

    public String getIdSetoran() {
        return idSetoran;
    }

    public Nasabah getNasabah() {
        return nasabah;
    }

    public Sampah getSampah() {
        return sampah;
    }

    public double getBeratKg() {
        return beratKg;
    }

    public void setBeratKg(double beratKg) {
        if (beratKg > 0) {
            this.beratKg = beratKg;
        } else {
            System.out.println("Berat tidak valid (harus lebih dari 0)");
        }
    }

    public double hitungTotal() {
        return beratKg * sampah.getHargaPerKg();
    }

    public void prosesSetoran() {
        nasabah.tambahSaldo(hitungTotal());
        System.out.println("Setoran " + idSetoran + " diproses. Saldo " + nasabah.getNama()
                + " bertambah Rp" + Entitas.formatRupiah(hitungTotal()));
    }

    public void tampilkanData() {
        System.out.println("ID Setoran : " + idSetoran);
        System.out.println("Nasabah    : " + nasabah.getNama() + " (" + nasabah.getId() + ")");
        System.out.println("Sampah     : " + sampah.getNama() + " (" + sampah.getKode() + ")");
        System.out.println("Berat (kg) : " + beratKg);
        System.out.println("Harga/Kg   : Rp" + Entitas.formatRupiah(sampah.getHargaPerKg()));
        System.out.println("Total      : Rp" + Entitas.formatRupiah(hitungTotal()));
    }
}
