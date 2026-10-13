import java.text.NumberFormat;
import java.util.Locale;

/**
 * Abstract Class Menu
 * Mewakili blueprint umum untuk semua jenis item kuliner pada sistem restoran.
 * Mengimplementasikan interface DapatDiberiDiskon.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public abstract class Menu implements DapatDiberiDiskon {
    // Encapsulation: Seluruh atribut utama bersifat private
    private String kodeMenu;
    private String nama;
    private double hargaDasar;
    private String kategori;

    public Menu(String kodeMenu, String nama, double hargaDasar, String kategori) {
        setKodeMenu(kodeMenu);
        setNama(nama);
        setHargaDasar(hargaDasar);
        setKategori(kategori);
    }

    // ================= Getter dan Setter dengan Validasi =================

    public String getKodeMenu() {
        return kodeMenu;
    }

    public void setKodeMenu(String kodeMenu) {
        // Validasi 1: Kode menu tidak boleh null atau kosong
        if (kodeMenu != null && !kodeMenu.trim().isEmpty()) {
            this.kodeMenu = kodeMenu.trim().toUpperCase();
        } else {
            System.out.println("[Validasi Error] Kode menu tidak boleh kosong! Menggunakan default 'MNU-000'.");
            this.kodeMenu = "MNU-000";
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        // Validasi 2: Nama tidak boleh null atau kosong
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        } else {
            System.out.println("[Validasi Error] Nama menu tidak boleh kosong! Menggunakan default 'Tanpa Nama'.");
            this.nama = "Tanpa Nama";
        }
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public void setHargaDasar(double hargaDasar) {
        // Validasi 3: Harga harus lebih dari 0
        if (hargaDasar > 0) {
            this.hargaDasar = hargaDasar;
        } else {
            System.out.println("[Validasi Error] Harga dasar harus > 0 (Diberikan: " + hargaDasar + "). Diset ke minimum Rp1.000.");
            this.hargaDasar = 1000.0;
        }
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        if (kategori != null && !kategori.trim().isEmpty()) {
            this.kategori = kategori.trim();
        } else {
            this.kategori = "Umum";
        }
    }

    // ================= Abstract Methods =================

    /**
     * Menghitung total harga pemesanan menu berdasarkan jumlah porsi.
     * Akan dioverride oleh subclass (Polymorphism).
     * @param jumlah kuantitas pesanan
     * @return total harga setelah perhitungan aturan spesifik
     */
    public abstract double hitungTotalHarga(int jumlah);

    /**
     * Menghitung estimasi waktu persiapan/memasak dalam satuan menit.
     * Logika berbeda pada makanan, minuman, dan paket hemat.
     * @param jumlah kuantitas pesanan
     * @return estimasi waktu dalam menit
     */
    public abstract int hitungEstimasiWaktu(int jumlah);

    /**
     * Menampilkan informasi rincian menu secara spesifik.
     */
    public abstract void tampilkanDetail();

    // ================= Implementasi Interface DapatDiberiDiskon =================

    @Override
    public double hitungDiskon(double persentaseDiskon) {
        if (persentaseDiskon < 0) {
            persentaseDiskon = 0;
        } else if (persentaseDiskon > 100) {
            persentaseDiskon = 100;
        }
        return this.hargaDasar * (persentaseDiskon / 100.0);
    }

    @Override
    public double getHargaSetelahDiskon(double persentaseDiskon) {
        return this.hargaDasar - hitungDiskon(persentaseDiskon);
    }

    // ================= Helper Utility =================

    public static String formatRupiah(double nilai) {
        Locale indonesia = Locale.of("id", "ID");
        NumberFormat formater = NumberFormat.getCurrencyInstance(indonesia);
        return formater.format(nilai);
    }
}
