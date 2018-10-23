package com.polinema.meme;

/**
 * Created by HP on 9/18/2018.
 */

public class Resep {
    private String nama_meme;
    private String detail_meme;
    private String rating_meme;
    private int gambar_meme;

    public static final Resep[] resepmeme = {
            new Resep("Troll", "Penjelasan Karakter ini adalah karakter yang paling jahil dalam meme. Dia sangat suka ngusilin orang atau karakter meme yang lainnya. Dia pintar, tapi kepintarannya disalahgunakan untuk jahil kepada orang. Dia juga memiliki muka yang khas dengan senyum sadisnya yang terkesan meledek. Karakter ini juga sangat terkenal dan paling banyak penggemarnya.", "Rating : 7/10", R.drawable.troll2 ),
            new Resep("Derp & Derpina", "Derp adalah seorang laki-laki yang memiliki karakter bodoh dalam meme. Dia sering sekali melakukan aktivitas-aktivitas bodoh. Dia juga memiliki pasangan bernama Derpina. Sekilas, jika dilihat mata Derp & Derpina terlihat juling. di Amerika, mata yang juling biasa digunakan untuk melambangkan orang yang bodoh.","Rating : 7/10", R.drawable.derp ),
            new Resep("Herp", "Herp, ini dia karakter yang bisa dibilang menggemaskan dan suka bikin jengkel. Dan ini juga karakter yang bodohnya sangat melebihi si Derp dan Derpina. Dalam meme, dia selalu membuat pernyataan bodoh dan bikin kesal.","Rating : 7/10", R.drawable.herp ),
            new Resep("Bitch Please", "B*tch Please arti gaulnya di Indonesia adalah “please deh yaa”, ya maksudnya kalau seandainya pembuat komik ingin protes ke ilustrasi gambar yang ia tampilkan. Muka b*tch please didapat dari wajah mantan atlet basket dari tim Houston Rockets, Yao Ming.","Rating : 7/10", R.drawable.bitchpls ),
            new Resep("Forever Alone", "Seperti namanya, dia adalah karakter yang digambarkan selalu sendiri, tidak punya teman, pacar, bahkan dikisahkan di facebook pun dia tidak memiliki teman satupun, dan kalau dapat sms, paling juga dari operator.","Rating : 7/10", R.drawable.foralone2 ),
            new Resep("Fuu", "Karakter yang satu ini digunakan ketika seseorang sedang marah. Biasanya karakter ini di temani dengan karakter troll. Karakter ini muncul setelah si Troll berhasil menjahili lawan mainnya.","Rating : 7/10", R.drawable.fuu ),
            new Resep("You Dont Say", "Kamu tidak katakan, ungkapan yang digunakan ketika ada orang (Herp) yang mengatakan sesuatu yang sudah jelas.","Rating : 7/10", R.drawable.yds ),

    };
    public Resep(String nama, String detail, String rating,int gambar){
        this.nama_meme = nama;
        this.detail_meme = detail;
        this.rating_meme = rating;
        this.gambar_meme = gambar;
    }

    public String getDetail_meme() {
        return detail_meme;
    }

    public String getNama_meme() {
        return nama_meme;
    }

    public String getRating_meme() {
        return rating_meme;
    }

    public int getGambar_meme() { return gambar_meme; }

    @Override
    public String toString() {
        return this.nama_meme;
    }
}
