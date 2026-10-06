/**
 * Subclass Koperasi
 * Mewarisi Abstract Class Entitas dan mengimplementasikan Interface DapatDilacak.
 */
public class Koperasi extends Entitas implements DapatDilacak {
    private String kodeKoperasi;
    private int jumlahAnggota;
    private double modal;
    private String lokasiKantor;

    public Koperasi(String kodeKoperasi, String nama, String alamat, int jumlahAnggota, double modal) {
        super(nama, alamat);
        this.kodeKoperasi = kodeKoperasi;
        setJumlahAnggota(jumlahAnggota);
        setModal(modal);
        this.lokasiKantor = alamat + " [Gedung Pusat Koperasi]";
    }

    public Koperasi(String kodeKoperasi, String nama, String alamat, int jumlahAnggota, double modal, String lokasiKantor) {
        super(nama, alamat);
        this.kodeKoperasi = kodeKoperasi;
        setJumlahAnggota(jumlahAnggota);
        setModal(modal);
        this.lokasiKantor = lokasiKantor;
    }

    public String getKodeKoperasi() {
        return kodeKoperasi;
    }

    public int getJumlahAnggota() {
        return jumlahAnggota;
    }

    public double getModal() {
        return modal;
    }

    public void setJumlahAnggota(int jumlahAnggota) {
        if (jumlahAnggota > 0) {
            this.jumlahAnggota = jumlahAnggota;
        } else {
            System.out.println("Jumlah anggota tidak valid (harus lebih dari 0)");
        }
    }

    public void setModal(double modal) {
        if (modal >= 0) {
            this.modal = modal;
        } else {
            System.out.println("Modal tidak valid (tidak boleh negatif)");
        }
    }

    // Method Overloading tambahAnggota (dari P6)
    public void tambahAnggota() {
        jumlahAnggota++;
    }

    public void tambahAnggota(int jumlah) {
        if (jumlah > 0) {
            jumlahAnggota += jumlah;
        } else {
            System.out.println("Jumlah anggota tidak valid (harus lebih dari 0)");
        }
    }

    public double hitungModalPerAnggota() {
        if (jumlahAnggota == 0) {
            return 0;
        }
        return modal / jumlahAnggota;
    }

    public void tampilkanData() {
        System.out.println("Kode Koperasi : " + kodeKoperasi);
        System.out.println("Nama          : " + getNama());
        System.out.println("Alamat        : " + getAlamat());
        System.out.println("Jumlah Anggota: " + jumlahAnggota);
        System.out.println("Modal         : Rp" + formatRupiah(modal));
    }

    // Implementasi Abstract Method getPeran() dari Entitas
    @Override
    public String getPeran() {
        return "Koperasi";
    }

    // Implementasi Abstract Method hitungKontribusi() dari Entitas
    @Override
    public double hitungKontribusi() {
        return modal;
    }

    // Overriding tampilkanPeran()
    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("Kode Koperasi    : " + kodeKoperasi);
        System.out.println("Jumlah Anggota   : " + jumlahAnggota);
        System.out.println("Modal per Anggota: Rp" + formatRupiah(hitungModalPerAnggota()));
    }

    // Implementasi Method Interface DapatDilacak
    @Override
    public String getLokasi() {
        return lokasiKantor != null ? lokasiKantor : getAlamat();
    }

    @Override
    public String getStatusOperasional() {
        return "Aktif / Melayani Permodalan Daur Ulang (" + jumlahAnggota + " Anggota)";
    }
}
