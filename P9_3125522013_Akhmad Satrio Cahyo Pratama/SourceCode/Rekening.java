/**
 * Class Rekening
 * Merupakan komponen penyusun Nasabah (Relasi Komposisi).
 * Bertanggung jawab penuh menjaga integritas saldo dan validasi mutasi kas.
 */
public class Rekening {
    private String nomorRekening;
    private double saldo;

    public Rekening(String nomorRekening, double saldoAwal) {
        this.nomorRekening = (nomorRekening != null) ? nomorRekening.trim() : "REK-DEFAULT";
        if (saldoAwal >= 0) {
            this.saldo = saldoAwal;
        } else {
            System.out.println("[Validasi Gagal] Saldo awal tidak boleh negatif. Disetel ke 0.");
            this.saldo = 0;
        }
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            System.out.println("[Validasi Gagal] Saldo tidak boleh negatif.");
        }
    }

    public void tambahSaldo(double nominal) {
        setor(nominal);
    }

    public void tambahSaldo(double nominal, String keterangan) {
        if (setor(nominal)) {
            System.out.println("Setoran Rp" + Entitas.formatRupiah(nominal) + " (" + keterangan + ") masuk ke rekening " + nomorRekening);
        }
    }

    /**
     * Method bisnis untuk menambah saldo (penyetoran).
     * Menerapkan validasi state: nominal harus positif.
     */
    public boolean setor(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
            return true;
        } else {
            System.out.println("[Validasi Gagal] Nominal setoran harus lebih dari 0.");
            return false;
        }
    }

    /**
     * Method bisnis untuk penarikan saldo.
     * Menerapkan validasi state: saldo tidak boleh defisit/negatif.
     */
    public boolean tarik(double nominal) {
        if (nominal <= 0) {
            System.out.println("[Validasi Gagal] Nominal penarikan harus lebih dari 0.");
            return false;
        }
        if (this.saldo >= nominal) {
            this.saldo -= nominal;
            return true;
        } else {
            System.out.println("[Validasi Gagal] Saldo tidak mencukupi untuk penarikan Rp" 
                    + Entitas.formatRupiah(nominal) + " (Saldo saat ini: Rp" + Entitas.formatRupiah(saldo) + ").");
            return false;
        }
    }

    public void tampilkanData() {
        System.out.println("No Rekening : " + nomorRekening);
        System.out.println("Saldo       : Rp" + Entitas.formatRupiah(saldo));
    }
}
