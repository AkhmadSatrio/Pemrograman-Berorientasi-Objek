public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK SAMPAH ===");
        System.out.println("P4 - Relasi Antarobject: Association, Aggregation, dan Composition");
        System.out.println();

        System.out.println("=========== TEST 1 : OBJECT BERHASIL DIBUAT ===========");
        System.out.println();

        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik Pet", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);
        System.out.println("[1.1] Object Sampah berhasil dibuat.");
        sampah1.tampilkanData();
        System.out.println();
        sampah2.tampilkanData();
        System.out.println();

        Nasabah nasabah1 = new Nasabah("NS-001", "Budi", "Jl. Raya Sumenep 10", 0);
        Nasabah nasabah2 = new Nasabah("NS-002", "Ani", "Jl. Melati 5", 0);
        System.out.println("[1.2] Object Nasabah berhasil dibuat.");
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
        System.out.println();

        BankSampah bank = new BankSampah("Bank Sampah PENS", "PENS Kampus Sumenep");
        System.out.println("[1.3] Object BankSampah berhasil dibuat.");
        bank.tampilkanData();
        System.out.println();

        System.out.println("=========== TEST 2 : DUA ATAU LEBIH OBJECT BERINTERAKSI ===========");
        System.out.println();

        System.out.println("[2.1] Aggregation - Object Sampah dibuat di luar BankSampah");
        bank.tambahSampah(sampah1);
        bank.tambahSampah(sampah2);
        System.out.println("Sampah ditambahkan ke BankSampah melalui method tambahSampah().");
        bank.tampilkanData();
        System.out.println("Sampah tetap dapat berdiri sendiri tanpa BankSampah:");
        sampah1.tampilkanData();
        System.out.println();

        System.out.println("[2.2] Association - Setoran menghubungkan object Nasabah dan Sampah");
        Setoran setoran1 = new Setoran("ST-001", nasabah1, sampah1, 3.0);
        Setoran setoran2 = new Setoran("ST-002", nasabah2, sampah2, 5.0);
        System.out.println("Object Setoran berhasil dibuat dengan referensi object Nasabah dan Sampah.");
        setoran1.tampilkanData();
        System.out.println();
        setoran2.tampilkanData();
        System.out.println();

        System.out.println("[2.3] Object berinteraksi - proses setoran mengubah saldo Nasabah");
        setoran1.prosesSetoran();
        setoran2.prosesSetoran();
        System.out.println();

        System.out.println("=========== TEST 3 : DATA OBJECT LAIN DIGUNAKAN VIA METHOD ===========");
        System.out.println();

        Nasabah nasabahDitemukan = setoran1.getNasabah();
        System.out.println("[3.1] Setoran memanggil nasabah.getNama() : " + nasabahDitemukan.getNama());
        System.out.println("[3.2] Setoran memanggil sampah.getHargaPerKg() : Rp" + setoran1.getSampah().getHargaPerKg());
        System.out.println("[3.3] Saldo nasabah1 setelah interaksi : Rp" + nasabah1.getSaldo());
        System.out.println("[3.4] Composition - Rekening dibuat di dalam Nasabah:");
        nasabah1.getRekening().tampilkanData();
        System.out.println("[3.5] cariSampah(\"SP-002\") : " + bank.cariSampah("SP-002").getNama());
        System.out.println();

        System.out.println("=========== PENGUJIAN DATA TIDAK VALID (LANJUTAN P3) ===========");
        System.out.println();

        System.out.println("[Invalid] setBeratKg(-2) pada setoran1:");
        setoran1.setBeratKg(-2);
        System.out.println("Berat setoran1 tetap = " + setoran1.getBeratKg());
        System.out.println();

        System.out.println("[Invalid] setNama(\"\") pada nasabah1:");
        nasabah1.setNama("");
        System.out.println("Nama nasabah1 tetap = " + nasabah1.getNama());
        System.out.println();

        System.out.println("[Invalid] tambahSaldo(-500) pada nasabah2:");
        nasabah2.tambahSaldo(-500);
        System.out.println("Saldo nasabah2 tetap = Rp" + nasabah2.getSaldo());
        System.out.println();

        System.out.println("=========== HASIL AKHIR ===========");
        System.out.println();
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
    }
}