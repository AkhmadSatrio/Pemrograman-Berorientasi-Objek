import java.util.ArrayList;
import java.util.List;

/**
 * Subclass BankSampah
 * Mewarisi Abstract Class Entitas dan mengimplementasikan Interface DapatDilacak.
 * Mengelola relasi Agregasi dengan Sampah (Katalog & Gudang) dan riwayat Setoran.
 */
public class BankSampah extends Entitas implements DapatDilacak {
    private String nomorIzin;
    private List<Sampah> daftarSampah;   // Relasi Agregasi (1 to 0..*)
    private List<Setoran> riwayatSetoran; // Relasi Agregasi (1 to 0..*)
    private String koordinatLokasi;
    private String statusOperasional;

    public BankSampah(String nama, String alamat, String nomorIzin) {
        this(nama, alamat, nomorIzin, alamat + " [Sentra Operasional Utama]");
    }

    public BankSampah(String nama, String alamat, String nomorIzin, String koordinatLokasi) {
        super(nama, alamat);
        this.nomorIzin = nomorIzin;
        this.daftarSampah = new ArrayList<>();
        this.riwayatSetoran = new ArrayList<>();
        this.koordinatLokasi = koordinatLokasi;
        this.statusOperasional = "BEROPERASI AKTIF";
    }

    public String getNomorIzin() {
        return nomorIzin;
    }

    public List<Sampah> getDaftarSampah() {
        return daftarSampah;
    }

    public List<Setoran> getRiwayatSetoran() {
        return riwayatSetoran;
    }

    /**
     * Menambahkan jenis sampah ke katalog (Agregasi).
     */
    public void tambahSampah(Sampah sampah) {
        if (sampah != null) {
            daftarSampah.add(sampah);
        } else {
            System.out.println("[Validasi Gagal] Object Sampah tidak valid (null).");
        }
    }

    /**
     * Mencari objek Sampah berdasarkan kodenya.
     */
    public int getJumlahSampah() {
        return daftarSampah.size();
    }

    public double getTotalNilaiSampah() {
        double total = 0;
        for (Sampah s : daftarSampah) {
            total += s.getHargaPerKg();
        }
        return total;
    }

    public Sampah cariSampah(String kode) {
        if (kode == null) return null;
        for (Sampah s : daftarSampah) {
            if (s.getKode().equalsIgnoreCase(kode.trim())) {
                return s;
            }
        }
        return null;
    }

    public List<Sampah> cariSampah(String jenis, double minimalHarga) {
        List<Sampah> hasil = new ArrayList<>();
        for (Sampah s : daftarSampah) {
            if (s.getJenis().equalsIgnoreCase(jenis) && s.getHargaPerKg() >= minimalHarga) {
                hasil.add(s);
            }
        }
        return hasil;
    }

    public List<Sampah> cariSampah(double maksimalHarga) {
        List<Sampah> hasil = new ArrayList<>();
        for (Sampah s : daftarSampah) {
            if (s.getHargaPerKg() <= maksimalHarga) {
                hasil.add(s);
            }
        }
        return hasil;
    }

    /**
     * Menerima dan memproses setoran sampah dari nasabah.
     */
    public boolean prosesSetoran(Setoran setoran) {
        if (setoran == null) {
            System.out.println("[Validasi Gagal] Setoran tidak valid (null).");
            return false;
        }
        boolean sukses = setoran.selesaikanSetoran();
        if (sukses) {
            riwayatSetoran.add(setoran);
        }
        return sukses;
    }

    /**
     * Mengirim stok sampah dari gudang ke pabrik daur ulang.
     * Menerapkan validasi state stok melalui object Sampah.
     */
    public boolean kirimKeIndustriDaurUlang(String kodeSampah, double jumlahKg) {
        Sampah s = cariSampah(kodeSampah);
        if (s == null) {
            System.out.println("[Operasi Gagal] Sampah dengan kode " + kodeSampah + " tidak ditemukan di katalog.");
            return false;
        }

        System.out.printf("[Daur Ulang] Memproses pengiriman %.2f kg %s ke pabrik mitra...%n", jumlahKg, s.getNama());
        boolean berhasilKurang = s.kurangiStok(jumlahKg);
        if (berhasilKurang) {
            System.out.printf("[Daur Ulang Berhasil] %.2f kg %s berhasil dikirim. Sisa stok: %.2f kg.%n",
                    jumlahKg, s.getNama(), s.getStokKg());
            return true;
        } else {
            System.out.println("[Daur Ulang Ditolak] Pengiriman dibatalkan karena stok di gudang tidak mencukupi.");
            return false;
        }
    }

    public void tampilkanRingkasanGudang() {
        System.out.println("============================================================");
        System.out.println("          RINGKASAN INVENTARIS GUDANG BANK SAMPAH           ");
        System.out.println("============================================================");
        System.out.printf("%-8s | %-12s | %-18s | %-12s | %-10s%n",
                "KODE", "JENIS", "NAMA KOMODITAS", "HARGA/KG", "STOK FISIK");
        System.out.println("---------+--------------+--------------------+--------------+-----------");
        double totalNilaiInventaris = 0;
        double totalBeratGudang = 0;
        for (Sampah s : daftarSampah) {
            double nilai = s.getStokKg() * s.getHargaPerKg();
            totalNilaiInventaris += nilai;
            totalBeratGudang += s.getStokKg();
            System.out.printf("%-8s | %-12s | %-18s | Rp%-10s | %6.2f kg%n",
                    s.getKode(), s.getJenis(), s.getNama(), formatRupiah(s.getHargaPerKg()), s.getStokKg());
        }
        System.out.println("---------+--------------+--------------------+--------------+-----------");
        System.out.printf("Total Berat Sampah Tersimpan : %.2f kg%n", totalBeratGudang);
        System.out.printf("Estimasi Total Nilai Gudang  : Rp%s%n", formatRupiah(totalNilaiInventaris));
        System.out.println("============================================================");
    }

    public void tampilkanData() {
        System.out.println("Nama Bank Sampah : " + getNama());
        System.out.println("Alamat           : " + getAlamat());
        System.out.println("Nomor Izin       : " + nomorIzin);
        System.out.println("Jumlah Komoditas : " + daftarSampah.size() + " varian");
        System.out.println("Total Transaksi  : " + riwayatSetoran.size() + " setoran terlayani");
    }

    @Override
    public String getPeran() {
        return "Sentra Pengolahan & Pengumpulan Sampah";
    }

    @Override
    public double hitungKontribusi() {
        double omzet = 0;
        for (Setoran s : riwayatSetoran) {
            omzet += s.getTotalNilai();
        }
        return omzet;
    }

    @Override
    public String getLokasi() {
        return koordinatLokasi;
    }

    @Override
    public String getStatusOperasional() {
        return statusOperasional;
    }

    @Override
    public void perbaruiLokasi(String lokasiBaru) {
        if (lokasiBaru != null && !lokasiBaru.isBlank()) {
            this.koordinatLokasi = lokasiBaru;
        }
    }
}
