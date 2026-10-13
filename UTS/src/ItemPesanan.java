/**
 * Class ItemPesanan
 * Mewakili item satuan dalam suatu transaksi pesanan.
 * Mengimplementasikan relasi asosiasi ke Menu dan relasi komposisi dalam Pesanan.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class ItemPesanan {
    private Menu menu;
    private int jumlah;
    private String catatanKhusus;

    public ItemPesanan(Menu menu, int jumlah, String catatanKhusus) {
        this.menu = menu;
        setJumlah(jumlah);
        this.catatanKhusus = (catatanKhusus != null && !catatanKhusus.trim().isEmpty()) ? catatanKhusus.trim() : "-";
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        if (menu != null) {
            this.menu = menu;
        } else {
            System.out.println("[Validasi Error] Objek Menu tidak boleh null.");
        }
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        // Validasi: Jumlah pesanan harus minimal 1
        if (jumlah > 0) {
            this.jumlah = jumlah;
        } else {
            System.out.println("[Validasi Error] Jumlah porsi harus > 0. Diberikan: " + jumlah + ". Diset ke 1.");
            this.jumlah = 1;
        }
    }

    public String getCatatanKhusus() {
        return catatanKhusus;
    }

    public void setCatatanKhusus(String catatanKhusus) {
        this.catatanKhusus = catatanKhusus;
    }

    // ================= Pemanfaatan Polymorphism =================

    /**
     * Memanggil method hitungTotalHarga() dari polymorphic reference Menu.
     * Perhitungan otomatis menyesuaikan apakah objek konkret berupa Makanan, Minuman, atau PaketHemat.
     */
    public double hitungSubtotal() {
        if (menu != null) {
            return menu.hitungTotalHarga(jumlah);
        }
        return 0.0;
    }

    /**
     * Memanggil method hitungEstimasiWaktu() dari polymorphic reference Menu.
     */
    public int getEstimasiWaktuItem() {
        if (menu != null) {
            return menu.hitungEstimasiWaktu(jumlah);
        }
        return 0;
    }

    public void tampilkanItem() {
        System.out.printf("  - %-24s x%d  %15s (Catatan: %s)%n",
                menu.getNama(), jumlah, Menu.formatRupiah(hitungSubtotal()), catatanKhusus);
    }
}
