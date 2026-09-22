import java.util.ArrayList;
import java.util.List;

public class BankSampah {
    private String nama;
    private String alamat;
    private List<Sampah> daftarSampah;

    public BankSampah(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.daftarSampah = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
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
        System.out.println("Nama Bank Sampah     : " + nama);
        System.out.println("Alamat               : " + alamat);
        System.out.println("Jumlah Sampah Ditampung : " + daftarSampah.size());
        for (Sampah s : daftarSampah) {
            System.out.println("  - " + s.getKode() + " | " + s.getNama()
                    + " | " + s.getJenis() + " | Rp" + s.getHargaPerKg());
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
}