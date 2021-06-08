import java.util.ArrayList;
import java.util.Random;

public class Kolonia {
    private int liczbaKomorek;
    private int sumaPunktowZycia = 0;
    private ArrayList<Komorka> komorki= new ArrayList<Komorka>();
    private int idKoloni;
    private int ileZywych;

    Kolonia(int zakresX1, int zakresX2, int zakresY, int bokKomorki, int idKoloni) {
       boolean wsp = false;
       this.idKoloni = idKoloni;
       Random random = new Random();
       boolean kierunek = random.nextBoolean();
       ileZywych = liczbaKomorek = random.nextInt(4) + 2;
       komorki.add(new Komorka(zakresX1,zakresX2,0, zakresY, bokKomorki, kierunek, idKoloni));
       sumaPunktowZycia = komorki.get(0).wezPunktyZycia();
       int x = komorki.get(0).wezX();
       int y = komorki.get(0).wezY();

       for( int i = 1; komorki.size() != liczbaKomorek; i++) {
           System.out.println(komorki.size());
           komorki.add(new Komorka(Math.max(x - liczbaKomorek * bokKomorki, zakresX1),Math.min(x + liczbaKomorek + bokKomorki, zakresX2),
                   Math.max(y - liczbaKomorek * bokKomorki, 0), Math.min(y + liczbaKomorek * bokKomorki, zakresY), bokKomorki, kierunek, idKoloni));
           for (int j = 0; j < komorki.size(); j++) {
               if(i!=j) {
                   if (komorki.get(i).wspolnaSciana(komorki.get(j).wezX(), komorki.get(j).wezY(), bokKomorki)) {
                       sumaPunktowZycia += komorki.get(i).wezPunktyZycia();
                       wsp = true;
                   }
                   if(komorki.get(i).czyTeSameWsp(komorki.get(j).wezX(), komorki.get(j).wezY())) {
                       wsp = false;
                       break;
                 /*  } else {
                       for(Komorka k : komorki) {
                          // System.out.println("\nwsporzedne X komorki: " + k.wezX() + "\nwsporzedna Y komorki: " + k.wezY() + "\nPunkty zycia: " + k.wezPunktyZycia());
                       }
                       wsp = false;
                       break;
                   }*/
                   }
               }
           }

           if(!wsp && i > 0) {
               komorki.remove(i);
               i--;
           }

           wsp = false;

       }

   }

   public ArrayList<Komorka> wezKolonie() {
       return komorki;
   }
   public int wezPunktyZycia() {
       return sumaPunktowZycia;
   }
   public int wezID() {
        return idKoloni;
   }
   public int ileZywych() {
        return ileZywych;
   }
   public void zabijKomorke() {
        ileZywych--;
   }


}
