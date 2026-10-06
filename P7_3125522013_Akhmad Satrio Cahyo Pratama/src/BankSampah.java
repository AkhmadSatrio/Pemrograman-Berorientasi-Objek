import java.util.ArrayList;
import java.util.List;

/**
 * Subclass BankSampah
 * Mewarisi Abstract Class Entitas dan mengimplementasikan Interface DapatDilacak.
 */
public class BankSampah extends Entitas implements DapatDilacak {
    private String nomorIzin;
    private List<Sampah> daftarSampah;
    private String koordinatLokasi;

    public BankSampah(String nama, String alamat, String nomorIzin) {
        super(nama, alamat);
        this.nomorIzin = nomorIzin;
        this.daftarSampah = new ArrayList<>();
        this.koordinatLokasi = alamat + " [Titik Operasional Utama]";
    }

    public BankSampah(String nama, String alamat, String nomorIzin, String koordinatLokasi) {
        super(nama, alamat);
        this.nomorIzin = nomorIzin;
        this.daftarSampah = new ArrayList<>();
        this.koordinatLokasi = koordinatLokasi;
    }

    public String getNomorIzin() {
        return nomorIzin;
    }

    public List<Sampah> getDaftarSampah() {
        return daftarSampah;
    }

    public int getJumlahSampah() {
        return daftarSampah.size();
    }

    public void tambahSampah(Sampah sampah) {
        if (sampah != null) {
            daftarSampah.add(sampah);
        } else {
            System.out.println("Object Sampah tidak valid (null)");
        }
    }

    public void tampilkanData() {
        System.out.println("Nama Bank Sampah        : " + getNama());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Nomor Izin              : " + nomorIzin);
        System.out.println("Jumlah Sampah Ditampung : " + daftarSampah.size());
        for (Sampah s : daftarSampah) {
            System.out.println("  - " + s.getKode() + " | " + s.getNama()
                    + " | " + s.getJenis() + " | Rp" + formatRupiah(s.getHargaPerKg()));
        }
    }

    // Method Overloading cariSampah (dari P6)
    public Sampah cariSampah(String kode) {
        for (Sampah s : daftarSampah) {
            if (s.getKode().equalsIgnoreCase(kode)) {
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

    public double getTotalNilaiSampah() {
        double total = 0;
        for (Sampah s : daftarSampah) {
            total += s.getHargaPerKg();
        }
        return total;
    }

    // Implementasi Abstract Method getPeran() dari Entitas
    @Override
    public String getPeran() {
        return "Bank Sampah";
    }

    // Implementasi Abstract Method hitungKontribusi() dari Entitas
    @Override
    public double hitungKontribusi() {
        return getTotalNilaiSampah();
    }

    // Overriding tampilkanPeran()
    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("Nomor Izin       : " + nomorIzin);
        System.out.println("Jumlah Sampah    : " + daftarSampah.size()
                + " jenis (" + formatRupiah(getTotalNilaiSampah()) + " poin/kg)");
    }

    // Implementasi Method Interface DapatDilacak
    @Override
    public String getLokasi() {
        return koordinatLokasi != null ? koordinatLokasi : getAlamat();
    }

    @Override
    public String getStatusOperasional() {
        return "Buka / Menerima Setoran Sampah (Izin: " + nomorIzin + ")";
    }
}
