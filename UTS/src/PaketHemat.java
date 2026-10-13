import java.util.ArrayList;
import java.util.List;

/**
 * Subclass PaketHemat (Combo Bundle)
 * Mewarisi Abstract Class Menu dan mengagregasikan beberapa item Menu.
 * Memberikan harga bundling dengan diskon otomatis serta kalkulasi waktu paket.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class PaketHemat extends Menu {
    private List<Menu> daftarItem;
    private double persentaseDiskonPaket; // Contoh: 15%

    public PaketHemat(String kodeMenu, String nama, double persentaseDiskonPaket) {
        // Harga dasar sementara 0, akan dihitung dinamis dari item penyusunnya
        super(kodeMenu, nama, 1000.0, "Paket Hemat");
        this.daftarItem = new ArrayList<>();
        setPersentaseDiskonPaket(persentaseDiskonPaket);
    }

    public List<Menu> getDaftarItem() {
        return daftarItem;
    }

    public double getPersentaseDiskonPaket() {
        return persentaseDiskonPaket;
    }

    public void setPersentaseDiskonPaket(double persentaseDiskonPaket) {
        // Validasi diskon paket
        if (persentaseDiskonPaket >= 0 && persentaseDiskonPaket <= 50) {
            this.persentaseDiskonPaket = persentaseDiskonPaket;
        } else {
            System.out.println("[Validasi Error] Diskon paket harus 0 - 50%. Diset ke default 10%.");
            this.persentaseDiskonPaket = 10.0;
        }
    }

    public void tambahItem(Menu item) {
        if (item != null) {
            daftarItem.add(item);
            // Update harga dasar paket = total harga dasar komponen dikurangi diskon paket
            double totalKomponen = 0;
            for (Menu m : daftarItem) {
                totalKomponen += m.getHargaDasar();
            }
            double hargaSetelahDiskonPaket = totalKomponen * (1.0 - (persentaseDiskonPaket / 100.0));
            super.setHargaDasar(hargaSetelahDiskonPaket);
        }
    }

    // ================= Polymorphism: Overriding Method =================

    /**
     * Polimorfisme perhitungan harga:
     * Menghitung total harga paket berdasarkan harga bundling paket dikali kuantitas.
     */
    @Override
    public double hitungTotalHarga(int jumlah) {
        return getHargaDasar() * jumlah;
    }

    /**
     * Polimorfisme estimasi waktu:
     * Dapur memasak komponen secara paralel, sehingga estimasi waktu adalah
     * waktu terlama dari komponen di dalamnya + 3 menit waktu pengemasan bundling.
     */
    @Override
    public int hitungEstimasiWaktu(int jumlah) {
        int maxWaktu = 5;
        for (Menu item : daftarItem) {
            int waktuItem = item.hitungEstimasiWaktu(1);
            if (waktuItem > maxWaktu) {
                maxWaktu = waktuItem;
            }
        }
        return maxWaktu + (jumlah * 2);
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[PAKET HEMAT] %s - %s | Harga Bundling: %s (Diskon Paket: %.0f%%)%n",
                getKodeMenu(), getNama(), formatRupiah(getHargaDasar()), persentaseDiskonPaket);
        System.out.println("   Isi Paket:");
        for (Menu item : daftarItem) {
            System.out.println("   - " + item.getNama() + " (" + formatRupiah(item.getHargaDasar()) + ")");
        }
    }
}
