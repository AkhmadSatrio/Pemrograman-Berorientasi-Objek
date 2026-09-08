public class Sampah {
    String kode;
    String jenis;
    String nama;
    double hargaPerKg;

    public Sampah(String kode, String jenis, String nama, double hargaPerKg) {
        this.kode = kode;
        this.jenis = jenis;
        this.nama = nama;
        this.hargaPerKg = hargaPerKg;
    }

    void tampilkanData() {
        System.out.println("Kode        : " + kode);
        System.out.println("Jenis       : " + jenis);
        System.out.println("Nama Sampah : " + nama);
        System.out.println("Harga/Kg    : Rp" + hargaPerKg);
    }

    void ubahHarga(double hargaBaru) {
        hargaPerKg = hargaBaru;
    }

    String getNama() {
        return nama;
    }
}
