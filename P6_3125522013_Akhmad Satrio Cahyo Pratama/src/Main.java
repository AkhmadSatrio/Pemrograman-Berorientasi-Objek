import java.util.List;
import java.util.Locale;

public class Main {

    private static final Locale ID = Locale.forLanguageTag("in-ID");

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK SAMPAH ===");
        System.out.println("P6 - Polymorphism, Method Overriding, Method Overloading, dan Dynamic Binding");
        System.out.println("Refactoring project P5, bukan project baru.");
        System.out.println();

        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik PET", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);
        Sampah sampah3 = new Sampah("SP-003", "Logam", "Kaleng Bekas", 12000);

        Nasabah nas1 = new Nasabah("NS-001", "Budi", "Jl. Raya Sumenep 10", 10000);
        Nasabah nas2 = new Nasabah("NS-002", "Ani", "Jl. Melati 5", 0);
        Nasabah nas3 = new Nasabah("NS-003", "Citra", "Jl. Anggrek 7", 7500);

        BankSampah bank = new BankSampah("Bank Sampah PENS", "PENS Kampus Sumenep", "IZN-2026-001");
        bank.tambahSampah(sampah1);
        bank.tambahSampah(sampah2);
        bank.tambahSampah(sampah3);

        Koperasi kop = new Koperasi("KOP-01", "Koperasi Sampah Sumenep",
                "Jl. Trunojoyo 3", 45, 75000000);

        bagianAAudit();
        bagianBOverriding(nas1, bank, kop);
        bagianCOverloading(nas1, bank, kop);
        bagianDUpcasting(nas1, bank, kop);
        bagianECollection(nas1, nas2, nas3, bank, kop);
        bagianFDynamicBinding(nas1, bank, kop);
        regresiP4P5(nas1, nas2, bank, sampah1, sampah2);
        kesimpulan();
    }

    private static void bagianAAudit() {
        System.out.println("=========== BAGIAN A - AUDIT BEHAVIOR (HASIL P5) ===========");
        System.out.println();
        System.out.println("Hierarchy P5 : Entitas (superclass) <- Nasabah, BankSampah (subclass)");
        System.out.println("Class yang diaudit : Entitas, Nasabah, BankSampah, Rekening, Sampah, Setoran");
        System.out.println();
        System.out.println("Temuan : behavior getPeran(), tampilkanPeran(), dan hitungKontribusi()");
        System.out.println("dipunya superclass, tetapi setiap subclass memberi implementasi berbeda.");
        System.out.println();
        System.out.println("Superclass | Subclass   | Method             | Implementasi pada Subclass");
        System.out.println("-----------+------------+---------------------+-----------------------------------------------");
        System.out.println("Entitas    | Nasabah    | getPeran()         | \"Nasabah\"");
        System.out.println("Entitas    | Nasabah    | tampilkanPeran()   | identitas + id nasabah + no rekening");
        System.out.println("Entitas    | Nasabah    | hitungKontribusi() | saldo rekening nasabah");
        System.out.println("Entitas    | BankSampah | getPeran()         | \"Bank Sampah\"");
        System.out.println("Entitas    | BankSampah | tampilkanPeran()   | identitas + nomor izin + jumlah sampah");
        System.out.println("Entitas    | BankSampah | hitungKontribusi() | total nilai sampah yang ditampung");
        System.out.println("Entitas    | Koperasi   | getPeran()         | \"Koperasi\"");
        System.out.println("Entitas    | Koperasi   | tampilkanPeran()   | identitas + kode + modal per anggota");
        System.out.println("Entitas    | Koperasi   | hitungKontribusi() | total modal koperasi");
        System.out.println();
        System.out.println("Subclass baru di P6 : Koperasi, agar polymorphic collection punya 3 tipe.");
        System.out.println();
    }

    private static void bagianBOverriding(Nasabah nas, BankSampah bank, Koperasi kop) {
        System.out.println("=========== BAGIAN B - METHOD OVERRIDING ===========");
        System.out.println();
        System.out.println("Subclass overriding memakai annotation @Override dan memanggil super.");
        System.out.println();

        System.out.println("[P6.1] Object bertipe Nasabah memanggil method milik Nasabah:");
        nas.tampilkanPeran();
        System.out.println();

        System.out.println("[P6.2] Object bertipe BankSampah memanggil method milik BankSampah:");
        bank.tampilkanPeran();
        System.out.println();

        System.out.println("[P6.3] Object bertipe Koperasi memanggil method milik Koperasi:");
        kop.tampilkanPeran();
        System.out.println();

        System.out.println("[P6.4] getPeran() di-override oleh 3 subclass:");
        System.out.println("  nas.getPeran()  = " + nas.getPeran());
        System.out.println("  bank.getPeran() = " + bank.getPeran());
        System.out.println("  kop.getPeran()  = " + kop.getPeran());
        System.out.println();

        System.out.println("[P6.5] hitungKontribusi() di-override oleh 3 subclass:");
        System.out.println("  nas.hitungKontribusi()  = Rp" + format(nas.hitungKontribusi()));
        System.out.println("  bank.hitungKontribusi() = Rp" + format(bank.hitungKontribusi()));
        System.out.println("  kop.hitungKontribusi()  = Rp" + format(kop.hitungKontribusi()));
        System.out.println();
    }

    private static void bagianCOverloading(Nasabah nas, BankSampah bank, Koperasi kop) {
        System.out.println("=========== BAGIAN C - METHOD OVERLOADING ===========");
        System.out.println();
        System.out.println("Alasan penggunaan : satu nama method melayani kebutuhan berbeda, sehingga");
        System.out.println("                   pemanggil tidak perlu menghafal banyak nama method.");
        System.out.println();

        System.out.println("[P6.6] BankSampah.cariSampah() di-overload 3 kali:");
        System.out.println("  cariSampah(\"SP-002\")        -> " + bank.cariSampah("SP-002").getNama());
        List<Sampah> a = bank.cariSampah("Logam", 10000.0);
        System.out.println("  cariSampah(\"Logam\", 10000.0) -> " + a.size() + " data: " + namaSampah(a));
        List<Sampah> b = bank.cariSampah(5000.0);
        System.out.println("  cariSampah(5000.0)           -> " + b.size() + " data: " + namaSampah(b));
        System.out.println("  Alasan: kode sampah unik, sedangkan jenis dan batas harga dapat cocok");
        System.out.println("          dengan banyak data sekaligus.");
        System.out.println();

        System.out.println("[P6.7] Rekening.tambahSaldo() di-overload 2 kali:");
        System.out.println("  tambahSaldo(2500.0) tanpa keterangan:");
        nas.getRekening().tambahSaldo(2500.0);
        System.out.println("  tambahSaldo(7500.0, \"Setoran Sampah Logam\") dengan keterangan:");
        nas.getRekening().tambahSaldo(7500.0, "Setoran Sampah Logam");
        System.out.println("  Alasan: variant berketerangan dipakai saat setoran perlu dicatat");
        System.out.println("          sumbernya pada laporan.");
        System.out.println();

        System.out.println("[P6.8] Koperasi.tambahAnggota() di-overload 2 kali:");
        System.out.println("  jumlah anggota sebelum : " + kop.getJumlahAnggota());
        kop.tambahAnggota();
        System.out.println("  tambahAnggota()       -> " + kop.getJumlahAnggota() + " anggota");
        kop.tambahAnggota(3);
        System.out.println("  tambahAnggota(3)      -> " + kop.getJumlahAnggota() + " anggota");
        System.out.println("  Alasan: pendaftaran perorangan memakai tanpa parameter, sedangkan");
        System.out.println("          pendaftaran massal memakai jumlah sekaligus.");
        System.out.println();

        KatalogEntitas katalog = new KatalogEntitas();
        katalog.tambahEntitas(nas);
        katalog.tambahEntitas(bank);
        katalog.tambahEntitas(kop);

        System.out.println("[P6.9] KatalogEntitas.cari() di-overload 3 kali pada collection polimorfik:");
        System.out.println("  cari(\"Budi\")            -> " + katalog.cari("Budi").getNama()
                + " (" + katalog.cari("Budi").getPeran() + ")");
        List<Entitas> c = katalog.cari(10000.0);
        System.out.println("  cari(10000.0)          -> " + c.size() + " entitas: " + namaEntitas(c));
        List<Entitas> d = katalog.cari("Nasabah", 1000.0);
        System.out.println("  cari(\"Nasabah\", 1000.0)   -> " + d.size() + " entitas: " + namaEntitas(d));
        System.out.println("  Alasan: kunci pencarian dapat berupa nama, ambang nilai kontribusi,");
        System.out.println("          atau kombinasi keduanya.");
        System.out.println();
    }

    private static void bagianDUpcasting(Nasabah nas, BankSampah bank, Koperasi kop) {
        System.out.println("=========== BAGIAN D - UPCASTING & POLYMORPHIC REFERENCE ===========");
        System.out.println();

        System.out.println("[P6.10] Upcasting eksplisit dari reference subclass ke superclass:");
        Entitas upcast = nas;
        System.out.println("  tipe reference awal    : Nasabah");
        System.out.println("  tipe reference akhir   : Entitas");
        System.out.println("  tipe object sebenarnya: " + upcast.getClass().getSimpleName());
        System.out.println("  nama tetap terbaca    : " + upcast.getNama());
        System.out.println("  peran yang berjalan   : " + upcast.getPeran());
        System.out.println();

        System.out.println("[P6.11] Upcasting langsung pada deklarasi (bentuk ringkas):");
        Entitas e1 = new Nasabah("NS-004", "Dewi", "Jl. Kenanga 9", 20000);
        Entitas e2 = new BankSampah("BS Mitra", "Jl. Cempaka 2", "IZN-2026-002");
        Entitas e3 = new Koperasi("KOP-02", "Koperasi Bakti", "Jl. Panji 4", 20, 30000000);
        System.out.println("  tipe reference : Entitas, Entitas, Entitas");
        System.out.println("  tipe object    : " + e1.getClass().getSimpleName() + ", "
                + e2.getClass().getSimpleName() + ", " + e3.getClass().getSimpleName());
        System.out.println("  nama            : " + e1.getNama() + ", " + e2.getNama() + ", " + e3.getNama());
        System.out.println("  peran           : " + e1.getPeran() + ", " + e2.getPeran() + ", " + e3.getPeran());
        System.out.println();

        System.out.println("[P6.12] Polymorphic reference: reference superclass, object subclass:");
        System.out.println("  bank.getClass() = " + bank.getClass().getSimpleName()
                + " -> peran " + bank.getPeran());
        System.out.println("  kop.getClass()  = " + kop.getClass().getSimpleName()
                + " -> peran " + kop.getPeran());
        System.out.println("  Superclass Entitas hanya membutuhkan method getPeran() yang di-override,");
        System.out.println("  sehingga reference superclass tetap memancing behavior spesifik subclass.");
        System.out.println();
    }

    private static void bagianECollection(Nasabah nas1, Nasabah nas2, Nasabah nas3,
            BankSampah bank, Koperasi kop) {
        System.out.println("=========== BAGIAN E - POLYMORPHIC COLLECTION ===========");
        System.out.println();

        Entitas[] daftar = { nas1, nas2, nas3, bank, kop };

        System.out.println("[P6.13] Polymorphic collection berbentuk array (5 object, 3 tipe subclass):");
        System.out.println("  tipe array : Entitas[]");
        System.out.println("  jumlah     : " + daftar.length);
        System.out.println();
        for (Entitas e : daftar) {
            e.tampilkanPeran();
            System.out.println();
        }

        System.out.println("[P6.14] Satu loop, tiga behavior berbeda, tanpa if-else per tipe:");
        double total = 0;
        for (Entitas e : daftar) {
            total += e.hitungKontribusi();
        }
        System.out.println("  total kontribusi seluruh entitas : Rp" + format(total));
        System.out.println();

        KatalogEntitas katalog = new KatalogEntitas();
        for (Entitas e : daftar) {
            katalog.tambahEntitas(e);
        }

        System.out.println("[P6.15] Polymorphic collection berbentuk List<Entitas>:");
        System.out.println("  jumlah entitas  : " + katalog.getJumlahEntitas());
        System.out.println("  tipe per elemen : Entitas, tetapi objek aktual beragam");
        System.out.println();

        System.out.println("[P6.16] Iterasi List<Entitas> dengan pemanggilan method polymorphic:");
        katalog.tampilkanSemua();

        System.out.println("[P6.17] Penyaringan collection tetap polymorphic:");
        List<Entitas> difilter = katalog.cari("Nasabah", 0.0);
        System.out.println("  cari(\"Nasabah\", 0.0) mengembalikan " + difilter.size() + " entitas:");
        for (Entitas e : difilter) {
            System.out.println("    - " + e.getNama() + " | peran=" + e.getPeran()
                    + " | kontribusi=Rp" + format(e.hitungKontribusi()));
        }
        System.out.println();
    }

    private static void bagianFDynamicBinding(Nasabah nas, BankSampah bank, Koperasi kop) {
        System.out.println("=========== BAGIAN F - PENGUJIAN DYNAMIC BINDING ===========");
        System.out.println();

        Entitas[] uji = { nas, bank, kop };

        System.out.println("Setiap reference bertipe Entitas, tetapi method yang dieksekusi berbeda.");
        System.out.println();
        System.out.println("No | Tipe Reference | Tipe Object   | Method          | getPeran()  | hitungKontribusi()");
        System.out.println("---+----------------+---------------+-----------------+-------------+------------------");
        for (int i = 0; i < uji.length; i++) {
            System.out.printf(ID, "%-2d | %-14s | %-13s | %-13s | %-11s | Rp%s%n",
                    i + 1,
                    "Entitas",
                    uji[i].getClass().getSimpleName(),
                    "tampilkanPeran()",
                    uji[i].getPeran(),
                    format(uji[i].hitungKontribusi()));
        }
        System.out.println();

        System.out.println("Pembuktian:");
        System.out.println("  nas.getClass()   = " + nas.getClass().getSimpleName());
        System.out.println("  bank.getClass()  = " + bank.getClass().getSimpleName());
        System.out.println("  kop.getClass()   = " + kop.getClass().getSimpleName());
        System.out.println("  Ketiganya dipanggil melalui method yang sama pada reference bertipe");
        System.out.println("  Entitas, tetapi tiga implementasi berbeda yang dieksekusi.");
        System.out.println();

        System.out.println("Mengapa method yang dijalankan berbeda walaupun reference bertipe sama?");
        System.out.println("  1. Reference hanya menyimpan tipe statis (Entitas) dan alamat object di memory.");
        System.out.println("  2. Saat runtime, JVM membaca tipe aktual object pada object header, yaitu");
        System.out.println("     Nasabah, BankSampah, atau Koperasi.");
        System.out.println("  3. JVM mencari method dengan nama, parameter, dan return type yang cocok pada");
        System.out.println("     tipe aktual tersebut, bukan pada tipe reference.");
        System.out.println("  4. Karena setiap subclass meng-override method, implementasi yang dipanggil");
        System.out.println("     adalah milik subclass. Proses ini disebut Dynamic Binding atau");
        System.out.println("     Dynamic Method Dispatch, dan terjadi saat runtime.");
        System.out.println();

        System.out.println("Konsekuensi : menambah subclass baru di P7 tidak mengubah loop pada");
        System.out.println("collection, cukup menambah satu class baru.");
        System.out.println();
    }

    private static void regresiP4P5(Nasabah nas1, Nasabah nas2, BankSampah bank,
            Sampah sampah1, Sampah sampah2) {
        System.out.println("=========== REGRESI P4 & P5 ===========");
        System.out.println();

        System.out.println("[Regresi] Composition Nasabah-Rekening :");
        nas1.getRekening().tampilkanData();
        System.out.println();

        System.out.println("[Regresi] Aggregation BankSampah-Sampah :");
        bank.tampilkanData();
        System.out.println();

        System.out.println("[Regresi] Association Setoran-Nasabah-Sampah :");
        Setoran s1 = new Setoran("ST-001", nas1, sampah1, 3.0);
        Setoran s2 = new Setoran("ST-002", nas2, sampah2, 5.0);
        s1.prosesSetoran();
        s2.prosesSetoran();
        System.out.println();

        System.out.println("[Regresi] Fungsi pencarian P4 masih bekerja :");
        System.out.println("  bank.cariSampah(\"SP-002\").getNama() = " + bank.cariSampah("SP-002").getNama());
        System.out.println("  s1.getNasabah().getNama()              = " + s1.getNasabah().getNama());
        System.out.println("  inheritance P5 getNama/getAlamat      = " + nas1.getNama()
                + " / " + bank.getAlamat());
        System.out.println();
    }

    private static void kesimpulan() {
        System.out.println("=========== KESIMPULAN ===========");
        System.out.println();
        System.out.println("Bagian A : 3 behavior Entitas di-override dengan implementasi berbeda.");
        System.out.println("Bagian B : 3 subclass melakukan overriding dengan annotation @Override.");
        System.out.println("Bagian C : overloading pada BankSampah, Rekening, Koperasi, KatalogEntitas.");
        System.out.println("Bagian D : upcasting dari Nasabah, BankSampah, dan Koperasi ke Entitas.");
        System.out.println("Bagian E : polymorphic collection array dan List dengan 5 object, 3 tipe.");
        System.out.println("Bagian F : dynamic binding terbukti pada 3 reference bertipe Entitas.");
        System.out.println("Regresi  : seluruh fungsi P4 dan P5 masih berjalan.");
        System.out.println();
    }

    private static String namaSampah(List<Sampah> daftar) {
        StringBuilder sb = new StringBuilder();
        for (Sampah s : daftar) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(s.getKode()).append(" ").append(s.getNama());
        }
        return sb.length() == 0 ? "(tidak ada)" : sb.toString();
    }

    private static String namaEntitas(List<Entitas> daftar) {
        StringBuilder sb = new StringBuilder();
        for (Entitas e : daftar) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(e.getNama());
        }
        return sb.length() == 0 ? "(tidak ada)" : sb.toString();
    }

    private static String format(double nilai) {
        return Entitas.formatRupiah(nilai);
    }
}
