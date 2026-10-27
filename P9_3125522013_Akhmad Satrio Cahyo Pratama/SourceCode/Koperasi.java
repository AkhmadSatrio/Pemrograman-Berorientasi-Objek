/**
 * Subclass Koperasi
 * Mewarisi Abstract Class Entitas dan mengimplementasikan Interface DapatDilacak.
 * Bertindak sebagai mitra pendanaan dan pemberdayaan ekonomi sirkular bank sampah.
 */
public class Koperasi extends Entitas implements DapatDilacak {
    private String kodeKoperasi;
    private int jumlahAnggota;
    private double modal;
    private String lokasiKantor;
    private String statusOperasional;

    public Koperasi(String kodeKoperasi, String nama, String alamat, int jumlahAnggota, double modal) {
        this(kodeKoperasi, nama, alamat, jumlahAnggota, modal, alamat + " [Kantor Pusat Koperasi]");
    }

    public Koperasi(String kodeKoperasi, String nama, String alamat, int jumlahAnggota, double modal, String lokasiKantor) {
        super(nama, alamat);
        this.kodeKoperasi = kodeKoperasi;
        setJumlahAnggota(jumlahAnggota);
        setModal(modal);
        this.lokasiKantor = lokasiKantor;
        this.statusOperasional = "BEROPERASI RESMI";
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
            System.out.println("[Validasi Gagal] Jumlah anggota koperasi harus lebih dari 0.");
        }
    }

    public void setModal(double modal) {
        if (modal >= 0) {
            this.modal = modal;
        } else {
            System.out.println("[Validasi Gagal] Modal koperasi tidak boleh bernilai negatif.");
        }
    }

    public void tambahAnggota() {
        this.jumlahAnggota++;
    }

    public void tambahAnggota(int penambahan) {
        if (penambahan > 0) {
            this.jumlahAnggota += penambahan;
        } else {
            System.out.println("[Validasi Gagal] Penambahan anggota harus lebih dari 0.");
        }
    }

    public double hitungModalPerAnggota() {
        if (jumlahAnggota == 0) return 0;
        return modal / jumlahAnggota;
    }

    /**
     * Penyaluran bantuan modal usaha kepada Bank Sampah.
     */
    public boolean salurkanBantuanModal(BankSampah bank, double nominal) {
        if (nominal <= 0) {
            System.out.println("[Validasi Gagal] Nominal bantuan harus lebih dari 0.");
            return false;
        }
        if (this.modal >= nominal) {
            this.modal -= nominal;
            System.out.printf("[Bantuan Modal] Berhasil menyalurkan dana Rp%s ke %s.%n",
                    formatRupiah(nominal), bank.getNama());
            return true;
        } else {
            System.out.println("[Bantuan Modal Ditolak] Modal koperasi tidak mencukupi untuk penyaluran bantuan.");
            return false;
        }
    }

    public void tampilkanData() {
        System.out.println("Kode Koperasi  : " + kodeKoperasi);
        System.out.println("Nama Koperasi  : " + getNama());
        System.out.println("Alamat Kantor  : " + getAlamat());
        System.out.println("Jumlah Anggota : " + jumlahAnggota + " orang");
        System.out.println("Modal Kerja    : Rp" + formatRupiah(modal));
    }

    @Override
    public String getPeran() {
        return "Mitra Finansial & Pemodal Kemitraan";
    }

    @Override
    public double hitungKontribusi() {
        return modal;
    }

    @Override
    public String getLokasi() {
        return lokasiKantor;
    }

    @Override
    public String getStatusOperasional() {
        return statusOperasional;
    }

    @Override
    public void perbaruiLokasi(String lokasiBaru) {
        if (lokasiBaru != null && !lokasiBaru.isBlank()) {
            this.lokasiKantor = lokasiBaru;
        }
    }
}
