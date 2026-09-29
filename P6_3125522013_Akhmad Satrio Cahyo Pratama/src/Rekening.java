public class Rekening {
    private String nomorRekening;
    private double saldo;

    public Rekening(String nomorRekening, double saldoAwal) {
        this.nomorRekening = nomorRekening;
        setSaldo(saldoAwal);
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
            System.out.println("Saldo tidak valid (tidak boleh negatif)");
        }
    }

    public void tambahSaldo(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
        } else {
            System.out.println("Nominal tidak valid (harus lebih dari 0)");
        }
    }

    public void tambahSaldo(double nominal, String keterangan) {
        if (nominal > 0) {
            this.saldo += nominal;
            System.out.println("Setoran Rp" + Entitas.formatRupiah(nominal) + " (" + keterangan
                    + ") masuk ke rekening " + nomorRekening);
        } else {
            System.out.println("Nominal tidak valid (harus lebih dari 0)");
        }
    }

    public void tampilkanData() {
        System.out.println("No Rekening : " + nomorRekening);
        System.out.println("Saldo       : Rp" + Entitas.formatRupiah(saldo));
    }
}
