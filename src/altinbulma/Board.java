package altinbulma;


public class Board {

    private int satirSayisi;
    private int sutunSayisi;
    private int altinSayisi;
    private int gizliAltinSayisi;

    int[][] GoldLocations;
    int[][] hiddenGoldLocations;

    public int[][] getGoldLocations() {
        return GoldLocations;
    }

    public void setGoldLocations(int[][] GoldLocations) {
        this.GoldLocations = GoldLocations;
    }

    public int[][] getHiddenGoldLocations() {
        return hiddenGoldLocations;
    }

    public void setHiddenGoldLocations(int[][] hiddenGoldLocations) {
        this.hiddenGoldLocations = hiddenGoldLocations;
    }

    public Board() {
        this.satirSayisi = 20;
        this.sutunSayisi = 20;
        this.altinSayisi = (int) ((this.satirSayisi * this.sutunSayisi) * 0.2);
        this.gizliAltinSayisi = (int) (this.altinSayisi * 0.1);

    }

    public int getSatirSayisi() {
        return satirSayisi;
    }

    public void setSatirSayisi(int satirSayisi) {
        this.satirSayisi = satirSayisi;
    }

    public int getSutunSayisi() {
        return sutunSayisi;
    }

    public void setSutunSayisi(int sutunSayisi) {
        this.sutunSayisi = sutunSayisi;
    }

    public int getAltinSayisi() {
        return altinSayisi;
    }

    public void setAltinSayisi(int altinSayisi) {
        this.altinSayisi = altinSayisi;
    }

    public int getGizliAltinSayisi() {
        return gizliAltinSayisi;
    }

    public void setGizliAltinSayisi(int gizliAltinSayisi) {
        this.gizliAltinSayisi = gizliAltinSayisi;
    }

}
