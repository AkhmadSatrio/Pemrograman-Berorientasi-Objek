import java.util.ArrayList;
import java.util.List;

public class BankSampah extends Entitas {
    private List<Sampah> daftarSampah;

    public BankSampah(String nama, String alamat) {
        super(nama, alamat);
        this.daftarSampah = new ArrayList<>();
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