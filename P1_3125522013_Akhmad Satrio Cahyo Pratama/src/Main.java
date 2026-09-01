public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN SAMPAH ===");
        System.out.println("Project Kickoff - Program Java Pertama");
        System.out.println();

        Sampah sampah1 = new Sampah();
        sampah1.kode = "SP-001";
        sampah1.jenis = "Anorganik";
        sampah1.nama = "Botol Plastik Pet";
        sampah1.hargaPerKg = 5000;
        sampah1.tampilkanData();

        System.out.println();

        Sampah sampah2 = new Sampah();
        sampah2.kode = "SP-002";
        sampah2.jenis = "Kertas";
        sampah2.nama = "Koran Bekas";
        sampah2.hargaPerKg = 2500;
        sampah2.tampilkanData();
    }
}
