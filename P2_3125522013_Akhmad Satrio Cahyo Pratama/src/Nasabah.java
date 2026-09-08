public class Nasabah {
    String id;
    String nama;
    String alamat;
    double saldo;

    public Nasabah(String id, String nama, String alamat, double saldo) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.saldo = saldo;
    }

    void tampilkanData() {
        System.out.println("ID          : " + id);
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
        System.out.println("Saldo       : Rp" + saldo);
    }

    void ubahAlamat(String alamatBaru) {
        alamat = alamatBaru;
    }

    String getNama() {
        return nama;
    }
}
