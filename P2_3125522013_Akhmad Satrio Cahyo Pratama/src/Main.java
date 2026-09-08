public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK SAMPAH ===");
        System.out.println("P2 - Implementasi Class, Object, Attribute, Method, dan Constructor");
        System.out.println();

        System.out.println("--- Class Sampah ---");
        Sampah sampah1 = new Sampah("SP-001", "Anorganik", "Botol Plastik Pet", 5000);
        Sampah sampah2 = new Sampah("SP-002", "Kertas", "Koran Bekas", 2500);
        sampah1.tampilkanData();
        System.out.println();
        sampah2.tampilkanData();
        System.out.println();

        System.out.println("--- Class Nasabah ---");
        Nasabah nasabah1 = new Nasabah("NS-001", "Budi", "Jl. Raya Sumenep 10", 0);
        Nasabah nasabah2 = new Nasabah("NS-002", "Ani", "Jl. Melati 5", 0);
        nasabah1.tampilkanData();
        System.out.println();
        nasabah2.tampilkanData();
        System.out.println();

        System.out.println("--- Class Setoran ---");
        Setoran setoran1 = new Setoran("ST-001", "NS-001", "SP-001", 3.0, 5000);
        Setoran setoran2 = new Setoran("ST-002", "NS-002", "SP-002", 5.0, 2500);
        setoran1.tampilkanData();
        System.out.println();
        setoran2.tampilkanData();
        System.out.println();

        System.out.println("--- Pengujian Method ---");
        sampah1.ubahHarga(5500);
        System.out.println("Nama sampah1  : " + sampah1.getNama());
        System.out.println("Harga sampah1 setelah diubah: Rp" + sampah1.hargaPerKg);

        nasabah1.ubahAlamat("Jl. Anggrek 12");
        System.out.println("Alamat nasabah1 setelah diubah: " + nasabah1.alamat);

        setoran1.ubahBerat(4.0);
        System.out.println("Total setoran1 setelah ubah berat: Rp" + setoran1.hitungTotal());
    }
}
