import java.util.ArrayList;
import java.util.List;

public class BankSampah extends Entitas {
    private String nomorIzin;
    private List<Sampah> daftarSampah;

    public BankSampah(String nama, String alamat, String nomorIzin) {
        super(nama, alamat);
        this.nomorIzin = nomorIzin;
        this.daftarSampah = new ArrayList<>();
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
        System.out.println("Nama Bank Sampah     : " + getNama());
        System.out.println("Alamat               : " + getAlamat());
        System.out.println("Nomor Izin           : " + nomorIzin);
        System.out.println("Jumlah Sampah Ditampung : " + daftarSampah.size());
        for (Sampah s : daftarSampah) {
            System.out.println("  - " + s.getKode() + " | " + s.getNama()
                    + " | " + s.getJenis() + " | Rp" + formatRupiah(s.getHargaPerKg()));
        }
    }

    public Sampah cariSampah(String kode) {
        for (Sampah s : daftarSampah) {
            if (s.getKode().equals(kode)) {
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

    @Override
    public String getPeran() {
        return "Bank Sampah";
    }

    @Override
    public void tampilkanPeran() {
        super.tampilkanPeran();
        System.out.println("Nomor Izin       : " + nomorIzin);
        System.out.println("Jumlah Sampah    : " + daftarSampah.size()
                + " jenis (" + formatRupiah(getTotalNilaiSampah()) + " poin/kg)");
    }

    @Override
    public double hitungKontribusi() {
        return getTotalNilaiSampah();
    }
}
