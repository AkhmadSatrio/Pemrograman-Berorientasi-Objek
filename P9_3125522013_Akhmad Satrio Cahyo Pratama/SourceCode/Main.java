import java.util.ArrayList;
import java.util.List;

/**
 * Driver Class Main
 * Menguji implementasi UML Class Diagram ke dalam kode Java sesuai Modul 9 PBO.
 * Menguji interaksi object, relasi (Komposisi, Agregasi, Asosiasi, Generalisasi, Realisasi),
 * enkapsulasi, validasi state, dan object responsibility.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("      MODUL 9 - IMPLEMENTASI UML CLASS DIAGRAM KE PROGRAM JAVA");
        System.out.println("                   Proyek: Sistem Manajemen Bank Sampah");
        System.out.println("================================================================================");
        System.out.println("Nama Mahasiswa : Akhmad Satrio Cahyo Pratama");
        System.out.println("NRP            : 3125522013");
        System.out.println("Kelas          : D4 Teknik Informatika - PENS PSDKU Sumenep");
        System.out.println("Mata Kuliah    : Praktikum Pemrograman Berorientasi Obyek");
        System.out.println("================================================================================\n");

        // ---------------------------------------------------------------------
        // LANGKAH 1 & 2: INISIALISASI OBJECT SESUAI CLASS DIAGRAM
        // ---------------------------------------------------------------------
        System.out.println(">>> 1. INISIALISASI MASTER DATA & OBJECT MODEL");
        // Inisialisasi Sampah (Entity)
        Sampah botolPlastik = new Sampah("SP01", "Anorganik", "Botol Plastik PET", 5000, 15.0);
        Sampah kardusBekas  = new Sampah("SP02", "Kertas", "Kardus Karton", 2500, 30.0);
        Sampah kalengAluminium = new Sampah("SP03", "Logam", "Kaleng Aluminium", 14000, 8.0);

        // Inisialisasi BankSampah (Subclass Entitas implements DapatDilacak)
        BankSampah bankSampah = new BankSampah("Bank Sampah Mandiri PENS", "Jl. Raya Lenteng No. 1 Sumenep", "IZN-2026-PENS-009", "Koordinat [-7.0091, 113.8622]");
        bankSampah.tambahSampah(botolPlastik);
        bankSampah.tambahSampah(kardusBekas);
        bankSampah.tambahSampah(kalengAluminium);

        // Inisialisasi Koperasi Mitra (Subclass Entitas implements DapatDilacak)
        Koperasi koperasiMitra = new Koperasi("KOP-09", "Koperasi Sirkular Berkah", "Jl. Trunojoyo No. 45 Sumenep", 50, 60000000);

        // Inisialisasi Nasabah (Subclass Entitas dengan Relasi Komposisi ke Rekening)
        Nasabah nasabah1 = new Nasabah("NS01", "Ahmad Fauzi", "Jl. Diponegoro No. 12", 25000);
        Nasabah nasabah2 = new Nasabah("NS02", "Siti Nurhaliza", "Jl. KH. Agus Salim No. 8", 10000);

        System.out.println("Master data berhasil diinisialisasi.\n");

        // ---------------------------------------------------------------------
        // PENGUJIAN 1: RELASI KOMPOSISI (Nasabah -> Rekening) & ENKAPSULASI SALDO
        // ---------------------------------------------------------------------
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(">>> 2. PENGUJIAN RELASI KOMPOSISI & ENKAPSULASI REKENING (Nasabah *-- Rekening)");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Data Profil Nasabah 1:");
        nasabah1.tampilkanData();
        System.out.println();

        System.out.println("[Uji State 1] Penarikan Saldo Normal Rp15.000:");
        boolean tarik1 = nasabah1.tarikUang(15000);
        System.out.println("Status Penarikan: " + (tarik1 ? "BERHASIL" : "GAGAL") 
                + " | Sisa Saldo: Rp" + Entitas.formatRupiah(nasabah1.getSaldo()));
        System.out.println();

        System.out.println("[Uji State 2] Penarikan Saldo Melebihi Batas Rp50.000 (Mencegah Defisit):");
        boolean tarik2 = nasabah1.tarikUang(50000);
        System.out.println("Status Penarikan: " + (tarik2 ? "BERHASIL" : "GAGAL (VALIDASI AMAN)") 
                + " | Sisa Saldo: Rp" + Entitas.formatRupiah(nasabah1.getSaldo()));
        System.out.println();

        // ---------------------------------------------------------------------
        // PENGUJIAN 2: RELASI ASOSIASI (Setoran menghubungkan Nasabah & Sampah)
        // ---------------------------------------------------------------------
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(">>> 3. PENGUJIAN RELASI ASOSIASI TRANSAKSI SETORAN (Setoran -> Nasabah & Sampah)");
        System.out.println("--------------------------------------------------------------------------------");
        Setoran setoran1 = new Setoran("TRX-001", nasabah1, botolPlastik, 10.0); // 10 kg x 5.000 = 50.000
        Setoran setoran2 = new Setoran("TRX-002", nasabah2, kalengAluminium, 5.0); // 5 kg x 14.000 = 70.000

        System.out.println("Detail Rincian Setoran 1 sebelum diproses:");
        setoran1.tampilkanData();
        System.out.println();

        System.out.println("Memproses Setoran 1 ke Bank Sampah:");
        bankSampah.prosesSetoran(setoran1);
        System.out.println("Saldo Nasabah 1 sekarang: Rp" + Entitas.formatRupiah(nasabah1.getSaldo()));
        System.out.println();

        System.out.println("Memproses Setoran 2 ke Bank Sampah:");
        bankSampah.prosesSetoran(setoran2);
        System.out.println("Saldo Nasabah 2 sekarang: Rp" + Entitas.formatRupiah(nasabah2.getSaldo()));
        System.out.println();

        System.out.println("[Uji State 3] Validasi Mencegah Pemrosesan Setoran Ganda (Idempotensi):");
        boolean setoranUlang = bankSampah.prosesSetoran(setoran1);
        System.out.println("Status Proses Ulang: " + (setoranUlang ? "BERHASIL" : "DITOLAK (VALIDASI AMAN)"));
        System.out.println();

        // ---------------------------------------------------------------------
        // PENGUJIAN 3: RESPONSIBILITY OBJECT & ENKAPSULASI STATE STOK SAMPAH
        // ---------------------------------------------------------------------
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(">>> 4. PENGUJIAN OBJECT RESPONSIBILITY: kurangiStok() vs setStok(-100)");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Keadaan Gudang Saat Ini (setelah setoran masuk):");
        bankSampah.tampilkanRingkasanGudang();
        System.out.println();

        System.out.println("[Uji State 4] Pengiriman Sampah ke Pabrik Daur Ulang (Normal: 12 kg Botol Plastik):");
        bankSampah.kirimKeIndustriDaurUlang("SP01", 12.0);
        System.out.println();

        System.out.println("[Uji State 5] Pengiriman Sampah Melebihi Stok Gudang (Uji Validasi: 50 kg Botol Plastik):");
        bankSampah.kirimKeIndustriDaurUlang("SP01", 50.0);
        System.out.println();

        // ---------------------------------------------------------------------
        // PENGUJIAN 4: GENERALIZATION (Inheritance) & REALIZATION (Interface)
        // ---------------------------------------------------------------------
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(">>> 5. PENGUJIAN GENERALISASI & REALISASI (Polymorphic List & Interface Tracking)");
        System.out.println("--------------------------------------------------------------------------------");
        List<Entitas> daftarEntitas = new ArrayList<>();
        daftarEntitas.add(nasabah1);
        daftarEntitas.add(nasabah2);
        daftarEntitas.add(bankSampah);
        daftarEntitas.add(koperasiMitra);

        System.out.println("Daftar Entitas Sistem (Polymorphism & Dynamic Binding hitungKontribusi()):");
        for (Entitas e : daftarEntitas) {
            System.out.println("- [" + e.getPeran() + "] " + e.getNama() 
                    + " | Nilai Kontribusi: Rp" + Entitas.formatRupiah(e.hitungKontribusi()));
        }
        System.out.println();

        System.out.println("Daftar Entitas Terlacak (Interface DapatDilacak):");
        List<DapatDilacak> fasilitasTerlacak = new ArrayList<>();
        fasilitasTerlacak.add(bankSampah);
        fasilitasTerlacak.add(koperasiMitra);

        for (DapatDilacak f : fasilitasTerlacak) {
            System.out.println("- Fasilitas : " + f.getLokasi());
            System.out.println("  Status    : " + f.getStatusOperasional());
        }
        System.out.println();

        // ---------------------------------------------------------------------
        // REKAPITULASI AKHIR SISTEM
        // ---------------------------------------------------------------------
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(">>> 6. REKAPITULASI AKHIR GUDANG & SALDO NASABAH");
        System.out.println("--------------------------------------------------------------------------------");
        bankSampah.tampilkanRingkasanGudang();
        System.out.println();
        System.out.println("Ringkasan Saldo Akhir Nasabah:");
        System.out.println("- " + nasabah1.getNama() + " (" + nasabah1.getId() + ") : Rp" + Entitas.formatRupiah(nasabah1.getSaldo()));
        System.out.println("- " + nasabah2.getNama() + " (" + nasabah2.getId() + ") : Rp" + Entitas.formatRupiah(nasabah2.getSaldo()));
        System.out.println("\nSeluruh pengujian Modul 9 berhasil dijalankan dengan konsistensi 100% terhadap UML!");
        System.out.println("================================================================================");
    }
}
