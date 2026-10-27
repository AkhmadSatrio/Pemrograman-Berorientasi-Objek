/**
 * Subclass Nasabah
 * Mewarisi Abstract Class Entitas dan memiliki relasi Komposisi dengan Rekening.
 * Bertanggung jawab mengelola profil nasabah dan interaksi perbankan sampah.
 */
public class Nasabah extends Entitas {
    private String id;
    private Rekening rekening; // Relasi Komposisi (1 to 1)
    private boolean aktif;     // State validasi keaktifan akun

    public Nasabah(String id, String nama, String alamat, double saldoAwal) {
        super(nama, alamat);
        this.id = id;
        this.rekening = new Rekening("REK-" + id, saldoAwal);
        this.aktif = true;
    }

    public String getId() {
        return id;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setStatusAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public double getSaldo() {
        return rekening.getSaldo();
    }

    public void setSaldo(double saldo) {
        rekening.setSaldo(saldo);
    }

    public void tambahSaldo(double nominal) {
        setorUang(nominal);
    }

    /**
     * Method bisnis untuk menambah saldo melalui relasi komposisi.
     */
    public boolean setorUang(double nominal) {
        if (!aktif) {
            System.out.println("[Transaksi Ditolak] Akun nasabah " + getNama() + " non-aktif.");
            return false;
        }
        return rekening.setor(nominal);
    }

    /**
     * Method bisnis untuk penarikan saldo nasabah dengan verifikasi keaktifan.
     */
    public boolean tarikUang(double nominal) {
        if (!aktif) {
            System.out.println("[Transaksi Ditolak] Akun nasabah " + getNama() + " non-aktif.");
            return false;
        }
        return rekening.tarik(nominal);
    }

    public void tampilkanData() {
        System.out.println("ID Nasabah  : " + id);
        System.out.println("Nama        : " + getNama());
        System.out.println("Alamat      : " + getAlamat());
        System.out.println("Status Akun : " + (aktif ? "AKTIF" : "NON-AKTIF"));
        System.out.println("No Rekening : " + rekening.getNomorRekening());
        System.out.println("Saldo       : Rp" + formatRupiah(rekening.getSaldo()));
    }

    @Override
    public String getPeran() {
        return "Nasabah Bank Sampah";
    }

    @Override
    public double hitungKontribusi() {
        return rekening.getSaldo();
    }

    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("ID Nasabah       : " + id);
        System.out.println("No Rekening      : " + rekening.getNomorRekening());
    }
}
