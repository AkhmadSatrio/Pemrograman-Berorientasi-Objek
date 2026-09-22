public class Nasabah {
    private String id;
    private String nama;
    private String alamat;
    private Rekening rekening;

    public Nasabah(String id, String nama, String alamat, double saldoAwal) {
        this.id = id;
        setNama(nama);
        setAlamat(alamat);
        this.rekening = new Rekening("R-" + id, saldoAwal);
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

    public Rekening getRekening() {
        return rekening;
    }

    public double getSaldo() {
        return rekening.getSaldo();
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
        rekening.setSaldo(saldo);
    }

    public void tambahSaldo(double nominal) {
        rekening.tambahSaldo(nominal);
    }

    public void tampilkanData() {
        System.out.println("ID          : " + id);
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
        System.out.println("No Rekening : " + rekening.getNomorRekening());
        System.out.println("Saldo       : Rp" + rekening.getSaldo());
    }
}