/**
 * Subclass Makanan
 * Mewarisi Abstract Class Menu.
 * Mengimplementasikan polymorphism pada perhitungan harga dan waktu penyajian.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class Makanan extends Menu {
    private int levelPedas; // Skala 0 - 5
    private boolean bisaBungkus;

    public Makanan(String kodeMenu, String nama, double hargaDasar, int levelPedas, boolean bisaBungkus) {
        super(kodeMenu, nama, hargaDasar, "Makanan");
        setLevelPedas(levelPedas);
        this.bisaBungkus = bisaBungkus;
    }

    public int getLevelPedas() {
        return levelPedas;
    }

    public void setLevelPedas(int levelPedas) {
        // Validasi: Level pedas dibatasi antara 0 sampai 5
        if (levelPedas >= 0 && levelPedas <= 5) {
            this.levelPedas = levelPedas;
        } else {
            System.out.println("[Validasi Error] Level pedas harus antara 0 - 5. Diberikan: " + levelPedas + ". Diset ke 0.");
            this.levelPedas = 0;
        }
    }

    public boolean isBisaBungkus() {
        return bisaBungkus;
    }

    public void setBisaBungkus(boolean bisaBungkus) {
        this.bisaBungkus = bisaBungkus;
    }

    // ================= Polymorphism: Overriding Method =================

    /**
     * Polimorfisme perhitungan harga:
     * Untuk makanan dengan level pedas ekstrim (> 3), dikenakan biaya ekstra racikan cabai Rp2.000 per porsi.
     */
    @Override
    public double hitungTotalHarga(int jumlah) {
        double biayaEkstra = 0;
        if (levelPedas > 3) {
            biayaEkstra = 2000.0 * (levelPedas - 3);
        }
        return (getHargaDasar() + biayaEkstra) * jumlah;
    }

    /**
     * Polimorfisme estimasi waktu:
     * Makanan butuh proses memasak di wajan/kompor:
     * 10 menit waktu persiapan + (jumlah * 2 menit) + (levelPedas * 1 menit perataan rasa pedas).
     */
    @Override
    public int hitungEstimasiWaktu(int jumlah) {
        return 10 + (jumlah * 2) + (levelPedas * 1);
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[MAKANAN] %s - %s | Harga: %s | Level Pedas: %d/5 | Takeaway: %s%n",
                getKodeMenu(), getNama(), formatRupiah(getHargaDasar()), levelPedas, (bisaBungkus ? "Bisa" : "Dine-in Only"));
    }
}
