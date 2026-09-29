public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK SAMPAH ===");
        System.out.println("P5 - Refactoring dengan Inheritance, Generalization, Superclass, dan Subclass");
        System.out.println();

        System.out.println("=========== P5 - PEMBUATAN OBJECT SUBCLASS ===========");
        System.out.println();

        Nasabah nasabah1 = new Nasabah("NS-001", "Budi", "Jl. Raya Sumenep 10", 0);
        Nasabah nasabah2 = new Nasabah("NS-002", "Ani", "Jl. Melati 5", 0);
        System.out.println("[P5.1] Object dibuat dari subclass Nasabah (is-a Entitas).");
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
        System.out.println();

        BankSampah bank = new BankSampah("Bank Sampah PENS", "PENS Kampus Sumenep");
        System.out.println("[P5.2] Object dibuat dari subclass BankSampah (is-a Entitas).");
        bank.tampilkanData();
        System.out.println();

        System.out.println("=========== P5 - AKSES MEMBER SUPERCLASS DARI SUBCLASS ===========");
        System.out.println();

        System.out.println("[P5.3] Subclass memanggil method superclass (getNama/getAlamat):");
        System.out.println("  nasabah1.getNama()       : " + nasabah1.getNama());
        System.out.println("  nasabah1.getAlamat()     : " + nasabah1.getAlamat());
        System.out.println("  bank.getNama()           : " + bank.getNama());
        System.out.println("  bank.getAlamat()         : " + bank.getAlamat());
        System.out.println();

        System.out.println("[P5.4] Validasi superclass diakses dari subclass:");
        nasabah1.setNama("");
        System.out.println("  Nama nasabah1 tetap = " + nasabah1.getNama());
        bank.setAlamat("");
        System.out.println("  Alamat bank tetap = " + bank.getAlamat());
        System.out.println();

        System.out.println("=========== P5 - POLYMORPHISM (REFERENSI SUPERCLASS) ===========");
        System.out.println();

        Entitas entitas1 = new Nasabah("NS-003", "Citra", "Jl. Anggrek 7", 10000);
        Entitas entitas2 = new BankSampah("BS Mitra", "Jl. Cempaka 2");
        System.out.println("[P5.5] Referensi bertipe superclass menunjuk ke object subclass:");
        System.out.println("  entitas1 (" + entitas1.getClass().getSimpleName() + ") : "
                + entitas1.getNama() + " - " + entitas1.getAlamat());
        System.out.println("  entitas2 (" + entitas2.getClass().getSimpleName() + ") : "
                + entitas2.getNama() + " - " + entitas2.getAlamat());
        System.out.println();

        System.out.println("=========== REGRESI P4 : FUNGSIONALITAS TETAP BERJALAN ===========");
        System.out.println();

        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik Pet", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);

        bank.tambahSampah(sampah1);
        bank.tambahSampah(sampah2);
        bank.tampilkanData();
        System.out.println();

        Setoran setoran1 = new Setoran("ST-001", nasabah1, sampah1, 3.0);
        Setoran setoran2 = new Setoran("ST-002", nasabah2, sampah2, 5.0);
        setoran1.prosesSetoran();
        setoran2.prosesSetoran();
        System.out.println();

        System.out.println("[Regresi] cariSampah(\"SP-002\") : " + bank.cariSampah("SP-002").getNama());
        System.out.println("[Regresi] Setoran memanggil nasabah.getNama() : " + setoran1.getNasabah().getNama());
        System.out.println("[Regresi] Composition - Rekening masih dibuat di dalam Nasabah:");
        nasabah1.getRekening().tampilkanData();
        System.out.println();

        System.out.println("=========== HASIL AKHIR ===========");
        System.out.println();
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
    }
}