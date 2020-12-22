package altinbulma;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class C extends Player {

    ImageIcon icon = new ImageIcon("images/playerIconC.png");
    private int hamleMaliyeti;
    private int hedefBelirlemeMaliyeti;
    private int x;
    private int y;
    private int toplananAltinSayisi=0;
    private int toplamAdimSayisi=0;

    public int getToplamAdimSayisi() {
        return toplamAdimSayisi;
    }

    public void setToplamAdimSayisi(int toplamAdimSayisi) {
        this.toplamAdimSayisi = toplamAdimSayisi;
    }

    public int getToplananAltinSayisi() {
        return toplananAltinSayisi;
    }

    public void setToplananAltinSayisi(int toplananAltinSayisi) {
        this.toplananAltinSayisi = toplananAltinSayisi;
    }

    public C(int y, int x) {
        this.hamleMaliyeti = 5;
        this.hedefBelirlemeMaliyeti = 15;
        this.y = y;
        this.x = x;
    }

    public int getHamleMaliyeti() {
        return hamleMaliyeti;
    }

    public void setHamleMaliyeti(int hamleMaliyeti) {
        this.hamleMaliyeti = hamleMaliyeti;
    }

    public int getHedefBelirlemeMaliyeti() {
        return hedefBelirlemeMaliyeti;
    }

    public void setHedefBelirlemeMaliyeti(int hedefBelirlemeMaliyeti) {
        this.hedefBelirlemeMaliyeti = hedefBelirlemeMaliyeti;
    }

    //initalize et dediginden basta bi 0 verdim
    int hedefX = 0;
    int hedefY = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHedefX() {
        return hedefX;
    }

    public int getHedefY() {
        return hedefY;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public void gizliAltinBul(Board board, JLabel[][] grid) {
//        System.out.println("C**********************************C\n");
        int fark;
        int enKucukFark = 9999;
        int satir = -1, sutun = -1;

        for (int i = 0; i < board.getSatirSayisi(); i++) {
            for (int j = 0; j < board.getSutunSayisi(); j++) {

                if (board.getHiddenGoldLocations()[i][j] != 0) {

                    fark = (Math.abs(i - this.y)) + Math.abs(j - this.x);

                    if (fark < enKucukFark) {
                        enKucukFark = fark;
                        satir = i;
                        sutun = j;
                    }

                }
            }
        }
        if (satir != -1 && sutun != -1) {
//            System.out.println("I___[" + satir + "] [" + sutun + "] de gizli altin bulundu !___I");
            board.GoldLocations[satir][sutun] = board.hiddenGoldLocations[satir][sutun];
            board.hiddenGoldLocations[satir][sutun] = 0;
            board.setGizliAltinSayisi(board.getGizliAltinSayisi() - 1);
            grid[satir][sutun].setBackground(Color.yellow);
        }
//        else {
//            System.out.println("C gizli altin bul'da hata var.");
//        }

    }

    public void altinBul(Board board, JLabel jLabelGoldsC) {
//        System.out.println("C**********************************C\n");
        int fark;
        int enKucukFark = 9999;
        int kar;
        int enYuksekKar = -9999;
        int mesafe = 0;
        int altinSayisi;

        for (int i = 0; i < board.getSatirSayisi(); i++) {
            for (int j = 0; j < board.getSutunSayisi(); j++) {

                if (board.getGoldLocations()[i][j] != 0) {

                    fark = (Math.abs(i - this.y)) + Math.abs(j - this.x);

                    kar = board.getGoldLocations()[i][j] - (this.getHamleMaliyeti() * fark) - this.getHedefBelirlemeMaliyeti();
                    if (kar >= enYuksekKar) {
                        enYuksekKar = kar;
                        mesafe = fark;
                        altinSayisi = board.getGoldLocations()[i][j];
                        this.hedefY = i;
                        this.hedefX = j;
                    }

                }
            }
        }

        int mesafeY = this.hedefY - this.getY();
        int mesafeX = this.hedefX - this.getX();

        if (this.getOyuncuAltinSayisi() - this.getHedefBelirlemeMaliyeti() >= 0) { //burası aslında pek gerekli degil
            this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() - this.getHedefBelirlemeMaliyeti());
            jLabelGoldsC.setText(String.valueOf(this.getOyuncuAltinSayisi()));
        } 
//        else {
//            System.out.println("C Oyuncusunun Altını Yetmediginden Hedef Belirleyemiyor!");
//
//        }

//        System.out.println("\n" + "C " + "Sutun mesafesi: " + mesafeY + " Satir mesafesi: " + mesafeX + " top.uzaklik: " + mesafe + "\n" + "hedef belirlemeden sonraki guncel altin:" + this.getOyuncuAltinSayisi());
//        System.out.println("C hedef: [" + this.hedefY + "][" + this.hedefX + "]");
//        System.out.println("\nC**********************************C\n");

    }

    public void hamleYap(Board board, JLabel[][] grid, JLabel jLabelGoldsC) {
        System.out.println("C**********************************C\n");
        Random random = new Random();
        int r;
        int atilanAdimSayisi = 0;

        while ((hedefX != this.x || hedefY != this.y) && atilanAdimSayisi < this.getAdimSayisi()) {
            r = random.nextInt(2);

            if (r == 1 && this.x < hedefX) {
                grid[this.y][this.x].setIcon(null);
                this.x++;
                grid[this.y][this.x].setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() - this.hamleMaliyeti);
                System.out.println("C " + (atilanAdimSayisi + 1) + ".adım atıldı yeni yer: [" + this.y + "] [" + this.x + "] guncel altin: " + this.getOyuncuAltinSayisi());
                atilanAdimSayisi++;
                this.toplamAdimSayisi++;

            }

            if (r == 1 && this.x > hedefX) {
                grid[this.y][this.x].setIcon(null);
                this.x--;
                grid[this.y][this.x].setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() - this.hamleMaliyeti);
                System.out.println("C " + (atilanAdimSayisi + 1) + ".adım atıldı yeni yer: [" + this.y + "] [" + this.x + "] guncel altin: " + this.getOyuncuAltinSayisi());
                atilanAdimSayisi++;
                this.toplamAdimSayisi++;

            }

            if (r == 0 && this.y < hedefY) {
                grid[this.y][this.x].setIcon(null);
                this.y++;
                grid[this.y][this.x].setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() - this.hamleMaliyeti);
                System.out.println("C " + (atilanAdimSayisi + 1) + ".adım atıldı yeni yer: [" + this.y + "] [" + this.x + "] guncel altin: " + this.getOyuncuAltinSayisi());
                atilanAdimSayisi++;
                this.toplamAdimSayisi++;

            }

            if (r == 0 && this.y > hedefY) {
                grid[this.y][this.x].setIcon(null);
                this.y--;
                grid[this.y][this.x].setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() - this.hamleMaliyeti);
                System.out.println("C " + (atilanAdimSayisi + 1) + ".adım atıldı yeni yer: [" + this.y + "] [" + this.x + "] guncel altin: " + this.getOyuncuAltinSayisi());
                atilanAdimSayisi++;
                this.toplamAdimSayisi++;

            }

            if (board.getGoldLocations()[this.y][this.x] != 0) {
                this.setOyuncuAltinSayisi(this.getOyuncuAltinSayisi() + board.getGoldLocations()[this.y][this.x]);
                System.out.println("C " + "[" + this.y + "] [" + this.x + "] de " + board.getGoldLocations()[this.y][this.x] + " gold bulundu" + " guncel altin:" + this.getOyuncuAltinSayisi());
                this.toplananAltinSayisi+=board.getGoldLocations()[this.y][this.x];
                board.GoldLocations[this.y][this.x] = 0;
                board.setAltinSayisi(board.getAltinSayisi() - 1);

                grid[this.y][this.x].setText("0");
                grid[this.y][this.x].setBackground(Color.GREEN);

            }
            //bu üst ve altın sıralaması önemli 

            if (board.getHiddenGoldLocations()[this.y][this.x] != 0) {
                System.out.println("I___[" + this.y + "] [" + this.x + "] de gizli altin bulundu !___I");
                board.GoldLocations[this.y][this.x] = board.hiddenGoldLocations[this.y][this.x]; //gizli altını normale koyduk
                board.hiddenGoldLocations[this.y][this.x] = 0;
                board.setGizliAltinSayisi(board.getGizliAltinSayisi() - 1);
                grid[this.y][this.x].setBackground(Color.yellow);

            }
            if (this.getOyuncuAltinSayisi() <= 0) {
                jLabelGoldsC.setText(String.valueOf(0));
            } else {
                jLabelGoldsC.setText(String.valueOf(this.getOyuncuAltinSayisi()));
            }

            if (this.getOyuncuAltinSayisi() <= 0) {
                return; //oyuncunun altını 0 a veya altına düştüyse birdaha adım atma
            }

        }
        System.out.println("\nC**********************************C\n");

    }

}
