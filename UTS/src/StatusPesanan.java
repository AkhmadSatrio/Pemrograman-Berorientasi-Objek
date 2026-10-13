/**
 * Enum StatusPesanan
 * Mewakili tahapan siklus hidup pesanan dalam sistem pemesanan makanan.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public enum StatusPesanan {
    DITERIMA("Pesanan Diterima di Kasir"),
    DIPROSES("Sedang Dimasak di Dapur"),
    SIAP_DISAJIKAN("Makanan Siap Disajikan / Diambil"),
    SELESAI("Transaksi Selesai & Pembayaran Berhasil"),
    DIBATALKAN("Pesanan Dibatalkan");

    private final String deskripsi;

    StatusPesanan(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
