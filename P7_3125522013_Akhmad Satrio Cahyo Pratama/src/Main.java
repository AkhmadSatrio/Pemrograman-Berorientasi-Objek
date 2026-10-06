import java.util.List;
import java.util.Locale;

public class Main {

    private static final Locale ID = Locale.forLanguageTag("in-ID");

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("       SISTEM MANAJEMEN BANK SAMPAH - PRAKTIKUM PBO MODUL 7");
        System.out.println("           Abstract Class, Abstract Method, dan Interface");
        System.out.println("================================================================================");
        System.out.println("Nama Mahasiswa : Akhmad Satrio Cahyo Pratama");
        System.out.println("NRP            : 3125522013");
        System.out.println("Program Studi  : D4 Teknik Informatika - PENS PSDKU Sumenep");
        System.out.println();

        // 1. Inisialisasi Data Master (Relasi P4)
        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik PET", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);
        Sampah sampah3 = new Sampah("SP-003", "Logam", "Kaleng Bekas", 12000);

        // 2. Inisialisasi Subclass Konkret (Mewarisi Abstract Class Entitas)
        Nasabah nas1 = new Nasabah("NS-001", "Budi Santoso", "Jl. Raya Sumenep 10", 10000);
        Nasabah nas2 = new Nasabah("NS-002", "Ani Wijaya", "Jl. Melati 5", 0);
        Nasabah nas3 = new Nasabah("NS-003", "Citra Lestari", "Jl. Anggrek 7", 7500);

        BankSampah bank = new BankSampah("Bank Sampah PENS", "PENS Kampus Sumenep", "IZN-2026-001",
                "PENS Kampus Sumenep [Koordinat: -7.0091, 113.8622]");
        bank.tambahSampah(sampah1);
        bank.tambahSampah(sampah2);
        bank.tambahSampah(sampah3);

        Koperasi kop = new Koperasi("KOP-01", "Koperasi Sampah Mandiri", "Jl. Trunojoyo 3", 45, 75000000,
                "Jl. Trunojoyo 3, Sumenep [Gedung Pusat Koperasi Lantai 1]");

        // Menjalankan Rangkaian Pengujian & Demonstrasi Modul 7
        tampilkanSprintGoalDanBacklog();
        bagianAAuditDesain();
        bagianBAbstractClassDanLaranganInstansiasi();
        bagianCAbstractMethodSubclass(nas1, bank, kop);
        bagianDInterfaceDapatDilacak(bank, kop);
        bagianEPengujianPolymorphism4Skenario(nas1, nas2, nas3, bank, kop);
        bagianFPolymorphicCollectionDanDynamicBinding(nas1, nas2, nas3, bank, kop);
        regresiFiturP4P5P6(nas1, nas2, bank, sampah1, sampah2);
        sprintReviewDanRetrospective();
    }

    private static void tampilkanSprintGoalDanBacklog() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. AGILE SPRINT - P7");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Sprint Goal:");
        System.out.println("  Mengembangkan desain proyek P6 dengan menerapkan abstract class dan interface");
        System.out.println("  yang sesuai sehingga struktur class lebih jelas, perilaku object memiliki kontrak");
        System.out.println("  yang konsisten, dan program tetap dapat dijalankan tanpa merusak fitur P1-P6.");
        System.out.println();
        System.out.println("Sprint Backlog P7 (7/7 Selesai):");
        System.out.println("  [DONE] SB-01 - Audit hierarchy dan behavior proyek P6");
        System.out.println("  [DONE] SB-02 - Menentukan kandidat abstract class (Entitas)");
        System.out.println("  [DONE] SB-03 - Menentukan abstract method (getPeran(), hitungKontribusi())");
        System.out.println("  [DONE] SB-04 - Menentukan kandidat interface (DapatDilacak)");
        System.out.println("  [DONE] SB-05 - Memperbarui class diagram dengan relasi generalization & realization");
        System.out.println("  [DONE] SB-06 - Mengimplementasikan abstract class dan interface pada kode");
        System.out.println("  [DONE] SB-07 - Menguji polymorphism (4 skenario), dynamic binding, dan regresi");
        System.out.println();
    }

    private static void bagianAAuditDesain() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("2. BAGIAN A - TABEL AUDIT DESAIN P6 KE P7");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "Class / Behavior", "Kondisi P6", "Rencana P7", "Alasan");
        System.out.println("-------------------+------------------+--------------------+---------------------------------------");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "Entitas", "Class biasa", "Abstract Class", "Hanya konsep umum, tidak boleh new Entitas");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "getPeran()", "Method biasa", "Abstract Method", "Tiap subclass wajib mendefinisikan peran");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "hitungKontribusi()", "Method biasa (0)", "Abstract Method", "Rumus kontribusi unik per jenis entitas");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "DapatDilacak", "Belum ada", "Interface", "Kontrak pelacakan lokasi & status operasional");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "BankSampah", "Subclass biasa", "Implements Interface", "Mewujudkan kontrak DapatDilacak & Entitas");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "Koperasi", "Subclass biasa", "Implements Interface", "Mewujudkan kontrak DapatDilacak & Entitas");
        System.out.printf(ID, "%-18s | %-16s | %-18s | %-38s%n",
                "Nasabah", "Subclass biasa", "Subclass konkret", "Mengimplementasikan abstract method Entitas");
        System.out.println();
    }

    private static void bagianBAbstractClassDanLaranganInstansiasi() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("3. BAGIAN B - ABSTRACT CLASS & LARANGAN INSTANSIASI LANGSUNG");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Karakteristik Abstract Class Entitas:");
        System.out.println("  1. Dideklarasikan dengan keyword 'abstract class Entitas'.");
        System.out.println("  2. Memiliki constructor untuk inisialisasi attribute 'nama' dan 'alamat'.");
        System.out.println("  3. Memiliki concrete method seperti getNama(), setAlamat(), dan tampilkanPeran().");
        System.out.println("  4. Memiliki 2 abstract method: getPeran() dan hitungKontribusi().");
        System.out.println();
        System.out.println("Uji Aturan Bahasa Java (Instantiation Rule):");
        System.out.println("  - Kode: Entitas e = new Entitas(\"Generic\", \"Alamat\");");
        System.out.println("  - Status: DILARANG (Compile-Time Error: Entitas is abstract; cannot be instantiated)");
        System.out.println("  - Namun, 'Entitas' tetap valid dan berdaya guna tinggi sebagai TIPE REFERENCE:");
        System.out.println("      Entitas ref1 = new Nasabah(\"NS-001\", \"Budi\", \"Jl. Raya\", 10000);  // VALID");
        System.out.println("      Entitas ref2 = new BankSampah(\"BS PENS\", \"Kampus\", \"IZN-01\");      // VALID");
        System.out.println("      Entitas ref3 = new Koperasi(\"KOP-01\", \"Kop Mandiri\", \"Jl. T\", 45, 75000000); // VALID");
        System.out.println();
    }

    private static void bagianCAbstractMethodSubclass(Nasabah nas, BankSampah bank, Koperasi kop) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("4. BAGIAN C - IMPLEMENTASI ABSTRACT METHOD PADA SUBCLASS");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Subclass konkret wajib memberikan body implementasi (@Override) untuk seluruh abstract method.");
        System.out.println();

        System.out.println("[C.1] Subclass Nasabah:");
        System.out.println("  - getPeran()         -> \"" + nas.getPeran() + "\"");
        System.out.println("  - hitungKontribusi() -> Saldo rekening nasabah = Rp" + Entitas.formatRupiah(nas.hitungKontribusi()));
        System.out.println("  - Output tampilkanPeran():");
        nas.tampilkanPeran();
        System.out.println();

        System.out.println("[C.2] Subclass BankSampah:");
        System.out.println("  - getPeran()         -> \"" + bank.getPeran() + "\"");
        System.out.println("  - hitungKontribusi() -> Total akumulasi nilai sampah = Rp" + Entitas.formatRupiah(bank.hitungKontribusi()));
        System.out.println("  - Output tampilkanPeran():");
        bank.tampilkanPeran();
        System.out.println();

        System.out.println("[C.3] Subclass Koperasi:");
        System.out.println("  - getPeran()         -> \"" + kop.getPeran() + "\"");
        System.out.println("  - hitungKontribusi() -> Total permodalan koperasi = Rp" + Entitas.formatRupiah(kop.hitungKontribusi()));
        System.out.println("  - Output tampilkanPeran():");
        kop.tampilkanPeran();
        System.out.println();
    }

    private static void bagianDInterfaceDapatDilacak(BankSampah bank, Koperasi kop) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("5. BAGIAN D - IMPLEMENTASI INTERFACE DAPATDILACAK");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Interface DapatDilacak mendefinisikan kontrak perilaku getLokasi() & getStatusOperasional().");
        System.out.println("Diimplementasikan oleh 2 class berbeda: BankSampah dan Koperasi.");
        System.out.println();

        System.out.println("[D.1] Pemanggilan via Reference Interface DapatDilacak pada BankSampah:");
        DapatDilacak pelacakBank = bank;
        System.out.println("  Tipe Reference : DapatDilacak");
        System.out.println("  Tipe Objek     : " + pelacakBank.getClass().getSimpleName());
        System.out.println("  Lokasi         : " + pelacakBank.getLokasi());
        System.out.println("  Status Ops     : " + pelacakBank.getStatusOperasional());
        System.out.println();

        System.out.println("[D.2] Pemanggilan via Reference Interface DapatDilacak pada Koperasi:");
        DapatDilacak pelacakKop = kop;
        System.out.println("  Tipe Reference : DapatDilacak");
        System.out.println("  Tipe Objek     : " + pelacakKop.getClass().getSimpleName());
        System.out.println("  Lokasi         : " + pelacakKop.getLokasi());
        System.out.println("  Status Ops     : " + pelacakKop.getStatusOperasional());
        System.out.println();
    }

    private static void bagianEPengujianPolymorphism4Skenario(Nasabah nas1, Nasabah nas2, Nasabah nas3,
            BankSampah bank, Koperasi kop) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("6. BAGIAN E - PENGUJIAN POLYMORPHISM (MINIMAL 4 SKENARIO LENGKAP)");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("[SKENARIO 1] Membuat Objek Subclass Konkret");
        System.out.println("  - Objek Nasabah, BankSampah, dan Koperasi berhasil diinstansiasi dengan constructor masing-masing.");
        System.out.println("  - Status: BERHASIL (Objek nas1=" + nas1.getNama() + ", bank=" + bank.getNama() + ", kop=" + kop.getNama() + ")");
        System.out.println();

        System.out.println("[SKENARIO 2] Memanggil Abstract Method Melalui Reference Superclass (Entitas)");
        Entitas refSuper1 = nas1;
        Entitas refSuper2 = bank;
        Entitas refSuper3 = kop;
        System.out.println("  - refSuper1.getPeran()         -> " + refSuper1.getPeran() + " (Class: " + refSuper1.getClass().getSimpleName() + ")");
        System.out.println("  - refSuper1.hitungKontribusi() -> Rp" + Entitas.formatRupiah(refSuper1.hitungKontribusi()));
        System.out.println("  - refSuper2.getPeran()         -> " + refSuper2.getPeran() + " (Class: " + refSuper2.getClass().getSimpleName() + ")");
        System.out.println("  - refSuper2.hitungKontribusi() -> Rp" + Entitas.formatRupiah(refSuper2.hitungKontribusi()));
        System.out.println("  - refSuper3.getPeran()         -> " + refSuper3.getPeran() + " (Class: " + refSuper3.getClass().getSimpleName() + ")");
        System.out.println("  - refSuper3.hitungKontribusi() -> Rp" + Entitas.formatRupiah(refSuper3.hitungKontribusi()));
        System.out.println("  - Status: BERHASIL (Method subclass yang sesuai dieksekusi via Dynamic Method Dispatch)");
        System.out.println();

        System.out.println("[SKENARIO 3] Memanggil Method Melalui Reference Interface (DapatDilacak)");
        DapatDilacak[] daftarPelacak = { bank, kop };
        for (DapatDilacak p : daftarPelacak) {
            System.out.println("  * Objek: " + p.getClass().getSimpleName());
            System.out.println("    Lokasi: " + p.getLokasi());
            System.out.println("    Status: " + p.getStatusOperasional());
        }
        System.out.println("  - Status: BERHASIL (Implementasi interface yang sesuai dijalankan)");
        System.out.println();

        System.out.println("[SKENARIO 4] Menggunakan Beberapa Objek dalam Array/List Bertipe Superclass & Interface");
        Entitas[] entitasArray = { nas1, nas2, nas3, bank, kop };
        System.out.println("  Iterasi Entitas[] (Jumlah: " + entitasArray.length + " elemen):");
        for (int i = 0; i < entitasArray.length; i++) {
            Entitas e = entitasArray[i];
            System.out.printf(ID, "    [%d] %-15s | Peran: %-12s | Kontribusi: Rp%s%n",
                    i + 1, e.getNama(), e.getPeran(), Entitas.formatRupiah(e.hitungKontribusi()));
        }
        System.out.println("  - Status: BERHASIL (Setiap objek menjalankan perilakunya sendiri dalam 1 perulangan)");
        System.out.println();
    }

    private static void bagianFPolymorphicCollectionDanDynamicBinding(Nasabah nas1, Nasabah nas2, Nasabah nas3,
            BankSampah bank, Koperasi kop) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("7. BAGIAN F - POLYMORPHIC COLLECTION (KATALOGENTITAS) & DYNAMIC BINDING");
        System.out.println("--------------------------------------------------------------------------------");

        KatalogEntitas katalog = new KatalogEntitas();
        katalog.tambahEntitas(nas1);
        katalog.tambahEntitas(nas2);
        katalog.tambahEntitas(nas3);
        katalog.tambahEntitas(bank);
        katalog.tambahEntitas(kop);

        System.out.println("[F.1] KatalogEntitas mengelola " + katalog.getJumlahEntitas() + " objek bertipe Entitas:");
        katalog.tampilkanSemua();

        System.out.println("[F.2] Total Kontribusi Seluruh Entitas (Polymorphic Sum):");
        System.out.println("  Total Nilai: Rp" + Entitas.formatRupiah(katalog.totalKontribusi()));
        System.out.println();

        System.out.println("[F.3] Query Entitas yang Mengimplementasikan Interface DapatDilacak:");
        List<DapatDilacak> fasilitasTerlacak = katalog.getEntitasDapatDilacak();
        System.out.println("  Ditemukan " + fasilitasTerlacak.size() + " fasilitas terlacak dalam sistem:");
        for (DapatDilacak d : fasilitasTerlacak) {
            System.out.println("  - " + ((Entitas) d).getNama() + " (" + d.getClass().getSimpleName() + ")");
            System.out.println("    Lokasi: " + d.getLokasi());
            System.out.println("    Status: " + d.getStatusOperasional());
        }
        System.out.println();

        System.out.println("[F.4] Tabel Bukti Dynamic Binding pada Runtime:");
        System.out.println("No | Tipe Reference | Tipe Objek Aktual | Method Dipanggil   | Output getPeran() | hitungKontribusi()");
        System.out.println("---+----------------+-------------------+--------------------+-------------------+--------------------");
        Entitas[] sample = { nas1, bank, kop };
        for (int i = 0; i < sample.length; i++) {
            System.out.printf(ID, "%-2d | %-14s | %-17s | %-18s | %-17s | Rp%s%n",
                    i + 1,
                    "Entitas (Abst)",
                    sample[i].getClass().getSimpleName(),
                    "getPeran()",
                    sample[i].getPeran(),
                    Entitas.formatRupiah(sample[i].hitungKontribusi()));
        }
        System.out.println();
        System.out.println("Penjelasan Dynamic Binding:");
        System.out.println("  Tipe reference statis adalah abstract class 'Entitas', namun JVM melakukan Dynamic Method");
        System.out.println("  Dispatch saat runtime berdasarkan 'vtable' (virtual method table) tipe objek aktual");
        System.out.println("  sehingga kode yang dieksekusi adalah implementasi spesifik pada masing-masing subclass.");
        System.out.println();
    }

    private static void regresiFiturP4P5P6(Nasabah nas1, Nasabah nas2, BankSampah bank,
            Sampah sampah1, Sampah sampah2) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("8. REGRESI FITUR P4, P5, & P6 (MEMASTIKAN SISTEM TETAP UTUH)");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("[P4 - Composition] Nasabah memiliki Rekening:");
        nas1.getRekening().tampilkanData();
        System.out.println();

        System.out.println("[P4 - Aggregation] BankSampah memiliki Daftar Sampah:");
        System.out.println("  Jumlah Sampah Bank: " + bank.getJumlahSampah() + " jenis");
        System.out.println();

        System.out.println("[P4 - Association] Setoran Sampah oleh Nasabah:");
        Setoran setoran1 = new Setoran("ST-701", nas1, sampah1, 4.0);
        Setoran setoran2 = new Setoran("ST-702", nas2, sampah2, 6.0);
        setoran1.prosesSetoran();
        setoran2.prosesSetoran();
        System.out.println();

        System.out.println("[P6 - Overloading] Pencarian Sampah di BankSampah:");
        System.out.println("  - cariSampah(\"SP-001\"): " + bank.cariSampah("SP-001").getNama());
        System.out.println("  - cariSampah(\"Anorganik\", 4000.0): " + bank.cariSampah("Anorganik", 4000.0).size() + " data");
        System.out.println("  - cariSampah(3000.0): " + bank.cariSampah(3000.0).size() + " data");
        System.out.println();
    }

    private static void sprintReviewDanRetrospective() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("9. SPRINT REVIEW & SPRINT RETROSPECTIVE");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Sprint Review Checklist (7/7 Terverifikasi):");
        System.out.println("  [V] 1. Abstract class (Entitas) berhasil dibuat & instansiasi langsung dicegah");
        System.out.println("  [V] 2. Abstract method (getPeran, hitungKontribusi) diimplementasikan seluruh subclass");
        System.out.println("  [V] 3. Minimal 2 subclass konkret (Nasabah, BankSampah, Koperasi) dapat digunakan");
        System.out.println("  [V] 4. Interface (DapatDilacak) berhasil dibuat & diimplementasikan pada 2 class");
        System.out.println("  [V] 5. Class diagram sesuai dengan kode Java");
        System.out.println("  [V] 6. Pengujian polymorphism (4 skenario + dynamic binding) berhasil 100%");
        System.out.println("  [V] 7. Fitur proyek sebelumnya (P1-P6) tetap berjalan normal (lolos uji regresi)");
        System.out.println();
        System.out.println("Sprint Retrospective:");
        System.out.println("  - What Went Well? Refactoring abstract class Entitas dan interface DapatDilacak");
        System.out.println("    berjalan mulus tanpa merusak relasi komposisi, agregasi, maupun asosiasi dari P4-P6.");
        System.out.println("  - What Went Wrong? Perlu kehati-hatian dalam memisahkan method yang benar-benar");
        System.out.println("    merupakan konsep umum (abstract class) dengan kontrak kemampuan/perilaku (interface).");
        System.out.println("  - Improvement: Mempersiapkan integrasi modularitas dan relasi antarmuka untuk persiapan");
        System.out.println("    Evaluasi Kompetensi UTS Praktikum (P8).");
        System.out.println("================================================================================");
    }
}
