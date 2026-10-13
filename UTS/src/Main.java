import java.util.ArrayList;
import java.util.List;

/**
 * Main Class - Pengujian Sistem Pemesanan Makanan (Tipe D)
 * 
 * Identitas Mahasiswa:
 * Nama        : Akhmad Satrio Cahyo Pratama
 * NRP         : 3125522013
 * Kelas       : 3D3PSDKU-SM-IT A
 * Dosen       : Nirwana Haidar Hari, S.Pd., M.Kom.
 * Mata Kuliah : Praktikum Pemrograman Berorientasi Obyek
 * Topik Kasus : Tipe D - Sistem Pemesanan Makanan
 */
public class Main {

    public static void main(String[] args) {
        cetakHeader();

        // =========================================================================
        // 1. PENGUJIAN CLASS, OBJECT & INHERITANCE
        // =========================================================================
        System.out.println(">>> 1. MEMBUAT OBJEK MENU (INHERITANCE: Makanan, Minuman, PaketHemat)");
        Makanan nasiGoreng = new Makanan("MK01", "Nasi Goreng Spesial", 25000.0, 4, true);
        Makanan ayamBakar = new Makanan("MK02", "Ayam Bakar Madu", 30000.0, 2, true);
        Minuman esTeh = new Minuman("MN01", "Es Teh Melati", 5000.0, "LARGE", "DINGIN");
        Minuman jusAlpukat = new Minuman("MN02", "Jus Alpukat Kocok", 15000.0, "REGULER", "DINGIN");

        // Membuat objek Paket Hemat (Bundling combo menu)
        PaketHemat paketKenyang = new PaketHemat("PK01", "Paket Kenyang Madura", 15.0); // Diskon 15%
        paketKenyang.tambahItem(ayamBakar);
        paketKenyang.tambahItem(esTeh);

        System.out.println("\nDaftar Menu yang Berhasil Diinisialisasi:");
        nasiGoreng.tampilkanDetail();
        ayamBakar.tampilkanDetail();
        esTeh.tampilkanDetail();
        jusAlpukat.tampilkanDetail();
        paketKenyang.tampilkanDetail();

        // =========================================================================
        // 2. PENGUJIAN ENKAPSULASI & VALIDASI INPUT
        // =========================================================================
        System.out.println("\n-------------------------------------------------------------");
        System.out.println(">>> 2. PENGUJIAN ENKAPSULASI & VALIDASI (Pencegahan Data Anomali)");
        System.out.println("Menguji input yang melanggar aturan bisnis:");

        // Uji validasi 1: Harga tidak boleh <= 0
        System.out.print("[Test Validasi Harga] ");
        Makanan testHarga = new Makanan("MK-ERR", "Menu Gratisan", -5000.0, 1, true);

        // Uji validasi 2: Level pedas harus 0 - 5
        System.out.print("[Test Validasi Pedas] ");
        nasiGoreng.setLevelPedas(99);

        // Uji validasi 3: Nomor telepon pelanggan harus 10-14 digit angka
        System.out.print("[Test Validasi Telepon] ");
        Pelanggan testPelanggan = new Pelanggan("P-ERR", "Andi", "abc1234", "VIP");

        // =========================================================================
        // 3. PENGUJIAN POLYMORPHISM (Dynamic Method Dispatch)
        // =========================================================================
        System.out.println("\n-------------------------------------------------------------");
        System.out.println(">>> 3. PENGUJIAN POLYMORPHISM");
        System.out.println("Menyimpan objek Makanan, Minuman, dan PaketHemat ke dalam List<Menu>");
        System.out.println("dan memanggil method hitungTotalHarga(2) serta hitungEstimasiWaktu(2):\n");

        List<Menu> daftarPolimorfisme = new ArrayList<>();
        daftarPolimorfisme.add(nasiGoreng);
        daftarPolimorfisme.add(esTeh);
        daftarPolimorfisme.add(paketKenyang);

        int porsiUji = 2;
        for (Menu m : daftarPolimorfisme) {
            System.out.println("Menu: " + m.getNama() + " (" + m.getClass().getSimpleName() + ")");
            System.out.println(" -> Harga Dasar       : " + Menu.formatRupiah(m.getHargaDasar()));
            System.out.println(" -> Total (" + porsiUji + " porsi)    : " + Menu.formatRupiah(m.hitungTotalHarga(porsiUji)));
            System.out.println(" -> Estimasi Waktu    : " + m.hitungEstimasiWaktu(porsiUji) + " Menit");
            System.out.println(" -> Diskon Promo (10%): Potongan " + Menu.formatRupiah(m.hitungDiskon(10)) + 
                               " | Menjadi " + Menu.formatRupiah(m.getHargaSetelahDiskon(10)));
            System.out.println();
        }

        // =========================================================================
        // 4. PENGUJIAN DATA PELANGGAN & UNSUR KREATIVITAS (MEMBERSHIP & REWARD)
        // =========================================================================
        System.out.println("-------------------------------------------------------------");
        System.out.println(">>> 4. PENGUJIAN UNSUR KREATIVITAS: SISTEM MEMBERSHIP & POIN LOYALTI");
        Pelanggan p1 = new Pelanggan("CUST-001", "Akhmad Satrio", "081234567890", "GOLD");
        Pelanggan p2 = new Pelanggan("CUST-002", "Budi Santoso", "082345678901", "SILVER");
        Pelanggan p3 = new Pelanggan("CUST-003", "Citra Lestari", "085678901234", "REGULER");

        // Beri saldo poin awal untuk pelanggan p1 sebagai simulasi reward sebelumnya
        p1.tambahPoin(15); // 15 Poin = senilai Rp15.000 diskon tambahan

        p1.tampilkanProfil();
        p2.tampilkanProfil();
        p3.tampilkanProfil();

        // =========================================================================
        // 5. PENGUJIAN TRANSAKSI PEMESANAN (RELATIONSHIP AGGREGATION & COMPOSITION)
        // =========================================================================
        System.out.println("\n-------------------------------------------------------------");
        System.out.println(">>> 5. PENGUJIAN PEMESANAN (TRANSAKSI 1: MEMBER GOLD DENGAN POIN)");
        Pesanan pesanan1 = new Pesanan("TRX-20261013-001", p1, "QRIS");
        pesanan1.tambahItem(nasiGoreng, 2, "Level 4 sangat pedas");
        pesanan1.tambahItem(jusAlpukat, 2, "Sedikit gula, es sedang");
        pesanan1.tambahItem(paketKenyang, 1, "Ayam bakar paha atas");

        // Simulasi update status pesanan
        pesanan1.setStatus(StatusPesanan.DIPROSES);

        // Pelanggan 1 menukarkan 10 poin loyalitas (Diskon Rp10.000)
        pesanan1.gunakanPoinLoyalti(10);

        // Cetak struk pesanan 1
        pesanan1.cetakStruk();

        // Selesaikan transaksi (akan menambah poin cashback baru dari total belanja)
        pesanan1.selesaikanTransaksi();

        // Transaksi 2: Member Silver tanpa penukaran poin
        System.out.println(">>> PENGUJIAN PEMESANAN (TRANSAKSI 2: MEMBER SILVER DENGAN CASH)");
        Pesanan pesanan2 = new Pesanan("TRX-20261013-002", p2, "TUNAI");
        pesanan2.tambahItem(ayamBakar, 1, "Bumbu ekstra manis");
        pesanan2.tambahItem(esTeh, 2, "Manis segar");
        pesanan2.setStatus(StatusPesanan.SIAP_DISAJIKAN);
        pesanan2.cetakStruk();
        pesanan2.selesaikanTransaksi();

        // Transaksi 3: Member Reguler (Harga normal)
        System.out.println(">>> PENGUJIAN PEMESANAN (TRANSAKSI 3: PELANGGAN REGULER)");
        Pesanan pesanan3 = new Pesanan("TRX-20261013-003", p3, "DEBIT");
        pesanan3.tambahItem(nasiGoreng, 1, "Pedas sedang level 1");
        pesanan3.tambahItem(esTeh, 1, "Ukuran Large");
        pesanan3.cetakStruk();
        pesanan3.selesaikanTransaksi();

        // Tampilkan saldo poin akhir pelanggan setelah transaksi selesai
        System.out.println("-------------------------------------------------------------");
        System.out.println("STATUS AKHIR LOYALITAS PELANGGAN SETELAH TRANSAKSI:");
        p1.tampilkanProfil();
        p2.tampilkanProfil();
        p3.tampilkanProfil();

        System.out.println("\n[SUKSES] Seluruh skenario pengujian OOP berhasil dijalankan!");
    }

    private static void cetakHeader() {
        System.out.println("=============================================================");
        System.out.println("       UJIAN TENGAH SEMESTER (UTS) - PRAKTIKUM PBO           ");
        System.out.println("       POLITEKNIK ELEKTRONIKA NEGERI SURABAYA (PENS)         ");
        System.out.println("=============================================================");
        System.out.println(" Nama Mahasiswa : Akhmad Satrio Cahyo Pratama");
        System.out.println(" NRP            : 3125522013");
        System.out.println(" Kelas          : 3D3PSDKU-SM-IT A");
        System.out.println(" Kasus          : Tipe D - Sistem Pemesanan Makanan");
        System.out.println("=============================================================\n");
    }
}
