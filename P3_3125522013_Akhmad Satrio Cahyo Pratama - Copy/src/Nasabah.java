public class Nasabah {
    private String id;
    private String nama;
    private String alamat;
    private double saldo;

    public Nasabah(String id, String nama, String alamat, double saldo) {
        this.id = id;
        setNama(nama);
        setAlamat(alamat);
        setSaldo(saldo);
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isBlank()) {
            this.nama = nama;
        } else {
            System.out.println("Nama nasabah tidak valid");
        }
    }

    public void setAlamat(String alamat) {
        if (alamat != null && !alamat.isBlank()) {
            this.alamat = alamat;
        } else {
            System.out.println("Alamat nasabah tidak valid");
        }
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            System.out.println("Saldo tidak valid (tidak boleh negatif)");
        }
    }

    public void tampilkanData() {
        System.out.println("ID          : " + id);
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
        System.out.println("Saldo       : Rp" + saldo);
    }
}
