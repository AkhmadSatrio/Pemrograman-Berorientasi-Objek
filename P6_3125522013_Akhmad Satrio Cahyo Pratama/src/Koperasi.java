public class Koperasi extends Entitas {
    private String kodeKoperasi;
    private int jumlahAnggota;
    private double modal;

    public Koperasi(String kodeKoperasi, String nama, String alamat, int jumlahAnggota, double modal) {
        super(nama, alamat);
        this.kodeKoperasi = kodeKoperasi;
        setJumlahAnggota(jumlahAnggota);
        setModal(modal);
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

    @Override
    public String getPeran() {
        return "Koperasi";
    }

    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("Kode Koperasi    : " + kodeKoperasi);
        System.out.println("Jumlah Anggota   : " + jumlahAnggota);
        System.out.println("Modal per Anggota: Rp" + formatRupiah(hitungModalPerAnggota()));
    }

    @Override
    public double hitungKontribusi() {
        return modal;
    }
}
