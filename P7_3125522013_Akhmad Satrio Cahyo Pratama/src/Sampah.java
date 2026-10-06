/**
 * Class Sampah
 * Agregasi pada BankSampah dan Asosiasi pada Setoran.
 */
public class Sampah {
    private String kode;
    private String jenis;
    private String nama;
    private double hargaPerKg;

    public Sampah(String kode, String jenis, String nama, double hargaPerKg) {
        this.kode = kode;
        setJenis(jenis);
        setNama(nama);
        setHargaPerKg(hargaPerKg);
    }

    public String getKode() {
        return kode;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public void setJenis(String jenis) {
        if (jenis != null && !jenis.isBlank()) {
            this.jenis = jenis;
        } else {
            System.out.println("Jenis sampah tidak valid");
        }
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isBlank()) {
            this.nama = nama;
        } else {
            System.out.println("Nama sampah tidak valid");
        }
    }

    public void setHargaPerKg(double hargaPerKg) {
        if (hargaPerKg > 0) {
            this.hargaPerKg = hargaPerKg;
        } else {
            System.out.println("Harga/Kg tidak valid (harus lebih dari 0)");
        }
    }

    public void tampilkanData() {
        System.out.println("Kode        : " + kode);
        System.out.println("Jenis       : " + jenis);
        System.out.println("Nama Sampah : " + nama);
        System.out.println("Harga/Kg    : Rp" + Entitas.formatRupiah(hargaPerKg));
    }
}
