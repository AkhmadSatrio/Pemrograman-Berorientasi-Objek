import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Pesanan
 * Mengelola transaksi pemesanan makanan.
 * Menerapkan relasi:
 * - Agregasi: Pesanan memiliki referensi Pelanggan (Pelanggan tetap eksis tanpa Pesanan).
 * - Komposisi: Pesanan memiliki daftar ItemPesanan (dikelola langsung oleh Pesanan).
 * 
 * Fitur Kreatif:
 * - Estimasi waktu memasak dapur berdasarkan seluruh menu yang dipesan.
 * - Diskon otomatis berdasarkan tier keanggotaan pelanggan.
 * - Penebusan poin loyalitas sebagai potongan tunai.
 * - Perhitungan Pajak Restoran (PB1 10%).
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class Pesanan {
    private String idPesanan;
    private Pelanggan pelanggan;             // Relationship: Aggregation
    private List<ItemPesanan> daftarItem;     // Relationship: Composition
    private StatusPesanan status;
    private String metodePembayaran;
    private LocalDateTime waktuTransaksi;
    private int poinDitukarkan;

    public static final double TARIF_PAJAK_PB1 = 0.10; // Pajak Restoran 10%

    public Pesanan(String idPesanan, Pelanggan pelanggan, String metodePembayaran) {
        this.idPesanan = idPesanan;
        this.pelanggan = pelanggan;
        this.daftarItem = new ArrayList<>();
        this.status = StatusPesanan.DITERIMA;
        setMetodePembayaran(metodePembayaran);
        this.waktuTransaksi = LocalDateTime.now();
        this.poinDitukarkan = 0;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public List<ItemPesanan> getDaftarItem() {
        return daftarItem;
    }

    public StatusPesanan getStatus() {
        return status;
    }

    public void setStatus(StatusPesanan status) {
        if (status != null) {
            this.status = status;
            System.out.println("[Status Update] Pesanan #" + idPesanan + " berubah status menjadi: " + status.getDeskripsi());
        }
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        if (metodePembayaran != null && !metodePembayaran.trim().isEmpty()) {
            this.metodePembayaran = metodePembayaran.trim();
        } else {
            this.metodePembayaran = "TUNAI";
        }
    }

    // ================= Manajemen Item Pesanan (Komposisi) =================

    public void tambahItem(Menu menu, int jumlah, String catatan) {
        if (menu == null) {
            System.out.println("[Error] Menu tidak valid!");
            return;
        }
        if (jumlah <= 0) {
            System.out.println("[Validasi Error] Gagal menambahkan " + menu.getNama() + ": Jumlah harus lebih dari 0!");
            return;
        }
        ItemPesanan item = new ItemPesanan(menu, jumlah, catatan);
        daftarItem.add(item);
    }

    // ================= Logika Bisnis & Fitur Kreatif =================

    /**
     * Menghitung total kotor (subtotal) dari seluruh item yang dipesan.
     */
    public double hitungSubtotal() {
        double subtotal = 0;
        for (ItemPesanan item : daftarItem) {
            subtotal += item.hitungSubtotal();
        }
        return subtotal;
    }

    /**
     * Menghitung diskon member dari subtotal.
     */
    public double hitungNominalDiskonMember() {
        if (pelanggan != null) {
            double persenDiskon = pelanggan.getPersenDiskonMember();
            return hitungSubtotal() * (persenDiskon / 100.0);
        }
        return 0.0;
    }

    /**
     * Menggunakan poin loyalti pelanggan untuk diskon belanja (1 poin = Rp1.000).
     */
    public boolean gunakanPoinLoyalti(int poin) {
        if (pelanggan == null) return false;
        if (poin <= 0) return false;

        double subtotalSetelahDiskon = hitungSubtotal() - hitungNominalDiskonMember();
        int maxPoinBisaPakai = (int) (subtotalSetelahDiskon / 1000.0);

        int poinDipakai = Math.min(poin, maxPoinBisaPakai);
        if (pelanggan.tukarPoin(poinDipakai)) {
            this.poinDitukarkan = poinDipakai;
            return true;
        }
        return false;
    }

    public double hitungNominalPotonganPoin() {
        return this.poinDitukarkan * 1000.0;
    }

    /**
     * Menghitung Dasar Pengenaan Pajak (DPP) setelah dikurangi diskon member & poin.
     */
    public double hitungDPP() {
        double dpp = hitungSubtotal() - hitungNominalDiskonMember() - hitungNominalPotonganPoin();
        return Math.max(0, dpp);
    }

    /**
     * Menghitung Pajak Restoran (PB1 10%).
     */
    public double hitungPajak() {
        return hitungDPP() * TARIF_PAJAK_PB1;
    }

    /**
     * Menghitung total akhir yang harus dibayar.
     */
    public double hitungTotalBayar() {
        return hitungDPP() + hitungPajak();
    }

    /**
     * Menghitung estimasi total waktu penyajian di dapur secara dinamis.
     * Menggunakan estimasi terlama dari item masakan + overhead per item.
     */
    public int hitungEstimasiWaktuTotal() {
        int maxWaktu = 0;
        for (ItemPesanan item : daftarItem) {
            int waktuItem = item.getEstimasiWaktuItem();
            if (waktuItem > maxWaktu) {
                maxWaktu = waktuItem;
            }
        }
        // Ditambah waktu plating/tray delivery 2 menit
        return maxWaktu + (daftarItem.isEmpty() ? 0 : 2);
    }

    /**
     * Menyelesaikan transaksi: update status ke SELESAI dan berikan poin cashback jika ada.
     */
    public void selesaikanTransaksi() {
        this.status = StatusPesanan.SELESAI;
        // Reward poin: Tiap kelipatan Rp50.000 dari total bayar mendapat 1 poin
        if (pelanggan != null) {
            int poinReward = (int) (hitungTotalBayar() / 50000.0);
            if (poinReward > 0) {
                pelanggan.tambahPoin(poinReward);
            }
        }
    }

    // ================= Cetak Struk Transaksi =================

    public void cetakStruk() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("=============================================================");
        System.out.println("                 STRUK RESTORAN NUSANTARA ENJOY              ");
        System.out.println("               Jl. Lenteng KM 2, Sumenep, Madura             ");
        System.out.println("=============================================================");
        System.out.printf("No. Pesanan     : %-20s Waktu: %s%n", idPesanan, waktuTransaksi.format(dtf));
        System.out.printf("Pelanggan       : %-20s Member: %s%n",
                (pelanggan != null ? pelanggan.getNama() : "Tamu"),
                (pelanggan != null ? pelanggan.getTipeMember() : "REGULER"));
        System.out.printf("Metode Bayar    : %-20s Status: %s%n", metodePembayaran, status.name());
        System.out.printf("Estimasi Dapur  : +/- %d Menit Siap Saji%n", hitungEstimasiWaktuTotal());
        System.out.println("-------------------------------------------------------------");
        System.out.println("Rincian Pesanan:");

        for (ItemPesanan item : daftarItem) {
            item.tampilkanItem();
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Subtotal Menu                   : %25s%n", Menu.formatRupiah(hitungSubtotal()));
        if (hitungNominalDiskonMember() > 0) {
            System.out.printf("Diskon Member (%s %.0f%%)       : -%24s%n",
                    pelanggan.getTipeMember(), pelanggan.getPersenDiskonMember(), Menu.formatRupiah(hitungNominalDiskonMember()));
        }
        if (hitungNominalPotonganPoin() > 0) {
            System.out.printf("Potongan Poin (%d Poin)         : -%24s%n",
                    poinDitukarkan, Menu.formatRupiah(hitungNominalPotonganPoin()));
        }
        System.out.printf("Dasar Pengenaan Pajak (DPP)     : %25s%n", Menu.formatRupiah(hitungDPP()));
        System.out.printf("Pajak Restoran (PB1 10%%)        : %25s%n", Menu.formatRupiah(hitungPajak()));
        System.out.println("=============================================================");
        System.out.printf("TOTAL BAYAR                     : %25s%n", Menu.formatRupiah(hitungTotalBayar()));
        System.out.println("=============================================================");
        System.out.println("          Terima Kasih Atas Kunjungan Anda! Selamat Menikmati        ");
        System.out.println("=============================================================\n");
    }
}
