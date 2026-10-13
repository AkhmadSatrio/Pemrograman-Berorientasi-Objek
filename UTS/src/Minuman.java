/**
 * Subclass Minuman
 * Mewarisi Abstract Class Menu.
 * Mengimplementasikan polymorphism pada perhitungan harga berdasarkan ukuran dan waktu racik.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class Minuman extends Menu {
    private String ukuran; // "REGULER" atau "LARGE"
    private String suhu;   // "DINGIN" atau "HANGAT"

    public Minuman(String kodeMenu, String nama, double hargaDasar, String ukuran, String suhu) {
        super(kodeMenu, nama, hargaDasar, "Minuman");
        setUkuran(ukuran);
        setSuhu(suhu);
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        // Validasi ukuran minuman
        if (ukuran != null && (ukuran.equalsIgnoreCase("LARGE") || ukuran.equalsIgnoreCase("REGULER"))) {
            this.ukuran = ukuran.toUpperCase();
        } else {
            System.out.println("[Validasi Error] Ukuran minuman harus REGULER atau LARGE. Diberikan: " + ukuran + ". Diset ke REGULER.");
            this.ukuran = "REGULER";
        }
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        if (suhu != null && (suhu.equalsIgnoreCase("DINGIN") || suhu.equalsIgnoreCase("HANGAT"))) {
            this.suhu = suhu.toUpperCase();
        } else {
            this.suhu = "DINGIN";
        }
    }

    // ================= Polymorphism: Overriding Method =================

    /**
     * Polimorfisme perhitungan harga:
     * Ukuran LARGE mendapatkan tambahan biaya upgrade ukuran Rp3.000 per cup.
     */
    @Override
    public double hitungTotalHarga(int jumlah) {
        double biayaUkuran = ukuran.equalsIgnoreCase("LARGE") ? 3000.0 : 0.0;
        return (getHargaDasar() + biayaUkuran) * jumlah;
    }

    /**
     * Polimorfisme estimasi waktu:
     * Minuman diracik jauh lebih cepat dari makanan:
     * 3 menit waktu racik dasar + (jumlah * 1 menit).
     */
    @Override
    public int hitungEstimasiWaktu(int jumlah) {
        return 3 + (jumlah * 1);
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[MINUMAN] %s - %s | Harga: %s | Ukuran: %s | Suhu: %s%n",
                getKodeMenu(), getNama(), formatRupiah(getHargaDasar()), ukuran, suhu);
    }
}
