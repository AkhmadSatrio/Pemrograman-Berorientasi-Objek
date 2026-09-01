public class Sampah {
    String kode;
    String jenis;
    String nama;
    double hargaPerKg;

    void tampilkanData() {
        System.out.println("Kode        : " + kode);
        System.out.println("Jenis       : " + jenis);
        System.out.println("Nama Sampah : " + nama);
        System.out.println("Harga/Kg    : Rp" + hargaPerKg);
    }
}
