import java.util.ArrayList;
import java.util.List;

/**
 * Class KatalogEntitas
 * Menyimpan dan mengelola koleksi polimorfik bertipe Abstract Class Entitas.
 */
public class KatalogEntitas {
    private List<Entitas> daftarEntitas;

    public KatalogEntitas() {
        this.daftarEntitas = new ArrayList<>();
    }

    public void tambahEntitas(Entitas entitas) {
        if (entitas != null) {
            daftarEntitas.add(entitas);
        } else {
            System.out.println("Object Entitas tidak valid (null)");
        }
    }

    public int getJumlahEntitas() {
        return daftarEntitas.size();
    }

    public Entitas getEntitas(int indeks) {
        if (indeks >= 0 && indeks < daftarEntitas.size()) {
            return daftarEntitas.get(indeks);
        }
        return null;
    }

    public List<Entitas> getSemuaEntitas() {
        return new ArrayList<>(daftarEntitas);
    }

    // Method Overloading cari
    public Entitas cari(String nama) {
        for (Entitas e : daftarEntitas) {
            if (e.getNama().equalsIgnoreCase(nama)) {
                return e;
            }
        }
        return null;
    }

    public List<Entitas> cari(double minimalKontribusi) {
        List<Entitas> hasil = new ArrayList<>();
        for (Entitas e : daftarEntitas) {
            if (e.hitungKontribusi() >= minimalKontribusi) {
                hasil.add(e);
            }
        }
        return hasil;
    }

    public List<Entitas> cari(String peran, double minimalKontribusi) {
        List<Entitas> hasil = new ArrayList<>();
        for (Entitas e : daftarEntitas) {
            if (e.getPeran().equalsIgnoreCase(peran) && e.hitungKontribusi() >= minimalKontribusi) {
                hasil.add(e);
            }
        }
        return hasil;
    }

    public List<Entitas> cariPeran(String peran) {
        List<Entitas> hasil = new ArrayList<>();
        for (Entitas e : daftarEntitas) {
            if (e.getPeran().equalsIgnoreCase(peran)) {
                hasil.add(e);
            }
        }
        return hasil;
    }

    // Mengambil semua entitas yang mengimplementasikan interface DapatDilacak
    public List<DapatDilacak> getEntitasDapatDilacak() {
        List<DapatDilacak> hasil = new ArrayList<>();
        for (Entitas e : daftarEntitas) {
            if (e instanceof DapatDilacak dilacak) {
                hasil.add(dilacak);
            }
        }
        return hasil;
    }

    public void tampilkanSemua() {
        for (Entitas e : daftarEntitas) {
            e.tampilkanPeran();
            System.out.println();
        }
    }

    public double totalKontribusi() {
        double total = 0;
        for (Entitas e : daftarEntitas) {
            total += e.hitungKontribusi();
        }
        return total;
    }
}
