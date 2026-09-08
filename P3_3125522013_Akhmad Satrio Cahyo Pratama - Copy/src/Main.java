public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK SAMPAH ===");
        System.out.println("P3 - Encapsulation, Access Modifier, Getter-Setter, dan Validasi Data");
        System.out.println();

        System.out.println("----- PENGUJIAN DATA VALID -----");
        System.out.println();

        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik Pet", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);
        System.out.println("[Valid] Object Sampah berhasil dibuat.");
        sampah1.tampilkanData();
        System.out.println();
        sampah2.tampilkanData();
        System.out.println();

        Nasabah nasabah1 = new Nasabah("NS-001", "Budi", "Jl. Raya Sumenep 10", 0);
        Nasabah nasabah2 = new Nasabah("NS-002", "Ani", "Jl. Melati 5", 0);
        System.out.println("[Valid] Object Nasabah berhasil dibuat.");
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
        System.out.println();

        Setoran setoran1 = new Setoran("ST-001", "NS-001", "SP-001", 3.0, 5000);
        Setoran setoran2 = new Setoran("ST-002", "NS-002", "SP-002", 5.0, 2500);
        System.out.println("[Valid] Object Setoran berhasil dibuat.");
        setoran1.tampilkanData();
        System.out.println();
        setoran2.tampilkanData();
        System.out.println();

        System.out.println("----- PENGUJIAN DATA TIDAK VALID -----");
        System.out.println();

        System.out.println("[Invalid] setHargaPerKg(-1000) pada sampah1:");
        sampah1.setHargaPerKg(-1000);
        System.out.println("Harga/Kg sampah1 tetap = Rp" + sampah1.getHargaPerKg());
        System.out.println();

        System.out.println("[Invalid] setBeratKg(-2) pada setoran2:");
        setoran2.setBeratKg(-2);
        System.out.println("Berat setoran2 tetap = " + setoran2.getBeratKg());
        System.out.println();

        System.out.println("[Invalid] setNama(\"\") pada nasabah1:");
        nasabah1.setNama("");
        System.out.println("Nama nasabah1 tetap = " + nasabah1.getNama());
        System.out.println();

        System.out.println("[Invalid] setSaldo(-500) pada nasabah2:");
        nasabah2.setSaldo(-500);
        System.out.println("Saldo nasabah2 tetap = Rp" + nasabah2.getSaldo());
        System.out.println();

        System.out.println("----- UJI GETTER DAN SETTER VALID -----");
        System.out.println();

        nasabah1.setSaldo(15000);
        System.out.println("Saldo nasabah1 setelah setSaldo(15000) = Rp" + nasabah1.getSaldo());
        sampah1.setNama("Botol Plastik HDPE");
        System.out.println("Nama sampah1 setelah setNama = " + sampah1.getNama());
        System.out.println();

        System.out.println("Total setoran1 (3 kg x Rp5000) = Rp" + setoran1.hitungTotal());
    }
}
