/**
 * Interface DapatDilacak
 * Mendefinisikan kontrak kemampuan pelacakan lokasi dan status operasional entitas.
 */
public interface DapatDilacak {
    String getLokasi();
    String getStatusOperasional();
    default void perbaruiLokasi(String lokasiBaru) {}
}
