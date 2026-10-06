/**
 * Subclass Nasabah
 * Mewarisi Abstract Class Entitas dan mengimplementasikan abstract method getPeran() dan hitungKontribusi().
 * Memiliki relasi Composition dengan Rekening.
 */
public class Nasabah extends Entitas {
    private String id;
    private Rekening rekening;

    public Nasabah(String id, String nama, String alamat, double saldoAwal) {
        super(nama, alamat);
        this.id = id;
        this.rekening = new Rekening("R-" + id, saldoAwal);
    }

    public String getId() {
        return id;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public double getSaldo() {
        return rekening.getSaldo();
    }

    public void setSaldo(double saldo) {
        rekening.setSaldo(saldo);
    }

    public void tambahSaldo(double nominal) {
        rekening.tambahSaldo(nominal);
    }

    public void tampilkanData() {
        System.out.println("ID          : " + id);
        System.out.println("Nama        : " + getNama());
        System.out.println("Alamat      : " + getAlamat());
        System.out.println("No Rekening : " + rekening.getNomorRekening());
        System.out.println("Saldo       : Rp" + formatRupiah(rekening.getSaldo()));
    }

    // Implementasi Abstract Method getPeran() dari Entitas
    @Override
    public String getPeran() {
        return "Nasabah";
    }

    // Implementasi Abstract Method hitungKontribusi() dari Entitas
    @Override
    public double hitungKontribusi() {
        return rekening.getSaldo();
    }

    // Overriding tampilkanPeran()
    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("ID Nasabah       : " + id);
        System.out.println("No Rekening      : " + rekening.getNomorRekening());
    }
}
