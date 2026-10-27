/**
 * Class Setoran
 * Merepresentasikan transaksi penyetoran sampah (Relasi Asosiasi antara Nasabah dan Sampah).
 * Menjaga state transaksi agar tidak diproses lebih dari satu kali (idempotensi).
 */
public class Setoran {
    private String idSetoran;
    private Nasabah nasabah; // Relasi Asosiasi
    private Sampah sampah;   // Relasi Asosiasi
    private double beratKg;
    private double totalNilai;
    private boolean statusSelesai;

    public Setoran(String idSetoran, Nasabah nasabah, Sampah sampah, double beratKg) {
        this.idSetoran = idSetoran;
        this.nasabah = nasabah;
        this.sampah = sampah;
        this.statusSelesai = false;
        setBeratKg(beratKg);
        this.totalNilai = hitungTotal();
    }

    public String getIdSetoran() {
        return idSetoran;
    }

    public Nasabah getNasabah() {
        return nasabah;
    }

    public Sampah getSampah() {
        return sampah;
    }

    public double getBeratKg() {
        return beratKg;
    }

    public double getTotalNilai() {
        return totalNilai;
    }

    public boolean isStatusSelesai() {
        return statusSelesai;
    }

    public void setBeratKg(double beratKg) {
        if (beratKg > 0) {
            this.beratKg = beratKg;
            this.totalNilai = hitungTotal();
        } else {
            System.out.println("[Validasi Gagal] Berat setoran harus lebih dari 0 kg. Disetel 1 kg.");
            this.beratKg = 1.0;
            this.totalNilai = hitungTotal();
        }
    }

    public void prosesSetoran() {
        selesaikanSetoran();
    }

    public double hitungTotal() {
        if (sampah != null) {
            return beratKg * sampah.getHargaPerKg();
        }
        return 0;
    }

    /**
     * Method bisnis untuk memproses transaksi setoran:
     * 1. Memvalidasi bahwa transaksi belum pernah diselesaikan sebelumnya.
     * 2. Menambah saldo nasabah sesuai nilai sampah yang disetor.
     * 3. Menambah stok sampah fisik pada gudang penampungan.
     * 4. Mengubah state transaksi menjadi selesai.
     */
    public boolean selesaikanSetoran() {
        if (statusSelesai) {
            System.out.println("[Transaksi Ditolak] Setoran " + idSetoran + " sudah pernah diproses sebelumnya.");
            return false;
        }
        if (nasabah == null || sampah == null) {
            System.out.println("[Transaksi Ditolak] Data nasabah atau sampah tidak lengkap.");
            return false;
        }

        boolean saldoBertambah = nasabah.setorUang(totalNilai);
        if (saldoBertambah) {
            sampah.tambahStok(beratKg);
            this.statusSelesai = true;
            System.out.printf("[Transaksi Berhasil] Setoran %s: %.2f kg %s -> Saldo %s bertambah Rp%s.%n",
                    idSetoran, beratKg, sampah.getNama(), nasabah.getNama(), Entitas.formatRupiah(totalNilai));
            return true;
        } else {
            System.out.println("[Transaksi Gagal] Gagal menambahkan saldo ke rekening nasabah.");
            return false;
        }
    }

    public void tampilkanData() {
        System.out.println("ID Setoran   : " + idSetoran);
        System.out.println("Nasabah      : " + ((nasabah != null) ? nasabah.getNama() + " (" + nasabah.getId() + ")" : "-"));
        System.out.println("Sampah       : " + ((sampah != null) ? sampah.getNama() + " (" + sampah.getKode() + ")" : "-"));
        System.out.printf("Berat (kg)   : %.2f kg%n", beratKg);
        System.out.println("Harga/Kg     : Rp" + ((sampah != null) ? Entitas.formatRupiah(sampah.getHargaPerKg()) : "0"));
        System.out.println("Total Nilai  : Rp" + Entitas.formatRupiah(totalNilai));
        System.out.println("Status       : " + (statusSelesai ? "SELESAI (SUDAH DIBAYAR)" : "PENDING (BELUM DIPROSES)"));
    }
}
