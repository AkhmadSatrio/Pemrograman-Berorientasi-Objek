/**
 * Class Sampah
 * Merepresentasikan komoditas sampah dan stok inventaris fisik di gudang.
 * Mengimplementasikan prinsip object responsibility dalam memelihara state stok dan harga.
 */
public class Sampah {
    private String kode;
    private String jenis;
    private String nama;
    private double hargaPerKg;
    private double stokKg; // State inventaris fisik di gudang

    public Sampah(String kode, String jenis, String nama, double hargaPerKg) {
        this(kode, jenis, nama, hargaPerKg, 0.0);
    }

    public Sampah(String kode, String jenis, String nama, double hargaPerKg, double stokAwalKg) {
        this.kode = kode;
        this.jenis = jenis;
        this.nama = nama;
        setHargaPerKg(hargaPerKg);
        if (stokAwalKg >= 0) {
            this.stokKg = stokAwalKg;
        } else {
            this.stokKg = 0;
        }
    }

    public String getKode() {
        return kode;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public double getStokKg() {
        return stokKg;
    }

    public void setJenis(String jenis) {
        if (jenis != null && !jenis.isBlank()) {
            this.jenis = jenis;
        } else {
            System.out.println("Jenis sampah tidak valid");
        }
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isBlank()) {
            this.nama = nama;
        } else {
            System.out.println("Nama sampah tidak valid");
        }
    }

    public void setHargaPerKg(double hargaPerKg) {
        if (hargaPerKg > 0) {
            this.hargaPerKg = hargaPerKg;
        } else {
            System.out.println("[Validasi Gagal] Harga per kg harus lebih dari 0. Disetel default Rp1.000.");
            this.hargaPerKg = 1000;
        }
    }

    /**
     * Method bisnis untuk memperbarui harga pasar sampah.
     */
    public void sesuaikanHarga(double hargaBaru) {
        if (hargaBaru > 0) {
            this.hargaPerKg = hargaBaru;
            System.out.println("[Update Harga] Harga " + nama + " berhasil diperbarui menjadi Rp" 
                    + Entitas.formatRupiah(hargaBaru) + "/Kg.");
        } else {
            System.out.println("[Validasi Gagal] Harga baru tidak boleh kurang dari atau sama dengan nol.");
        }
    }

    /**
     * Method bisnis untuk menambah stok inventaris sampah masuk.
     */
    public void tambahStok(double kg) {
        if (kg > 0) {
            this.stokKg += kg;
        } else {
            System.out.println("[Validasi Gagal] Penambahan stok sampah harus lebih dari 0 kg.");
        }
    }

    /**
     * Method bisnis untuk mengurangi stok (misal dikirim ke pabrik daur ulang).
     * Menerapkan validasi state: stok tidak boleh defisit / bernilai negatif.
     */
    public boolean kurangiStok(double kg) {
        if (kg <= 0) {
            System.out.println("[Validasi Gagal] Jumlah pengurangan stok harus lebih dari 0 kg.");
            return false;
        }
        if (this.stokKg >= kg) {
            this.stokKg -= kg;
            return true;
        } else {
            System.out.printf("[Validasi Gagal] Stok %s tidak mencukupi untuk dikurangi %.2f kg (Stok saat ini: %.2f kg).%n",
                    nama, kg, stokKg);
            return false;
        }
    }

    public void tampilkanData() {
        System.out.println("Kode Sampah : " + kode);
        System.out.println("Jenis       : " + jenis);
        System.out.println("Nama Sampah : " + nama);
        System.out.println("Harga/Kg    : Rp" + Entitas.formatRupiah(hargaPerKg));
        System.out.printf("Stok Gudang : %.2f kg%n", stokKg);
    }
}
