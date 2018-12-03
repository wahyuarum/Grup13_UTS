package com.polinema.meme;

public class SinetronModel {
    private String name;
    private String durasi;
    private String videoRawId;

    public static final SinetronModel[] drama ={
            new SinetronModel("Tersandung","1:11","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
            new SinetronModel("Amburadul","1:11","https://r1---sn-htgx20capjpq-habe.googlevideo.com/videoplayback?requiressl=yes&initcwndbps=986250&pcm2cms=yes&gir=yes&mime=video%2Fwebm&aitags=133%2C134%2C135%2C136%2C160%2C242%2C243%2C244%2C247%2C278&dur=437.880&ipbits=0&expire=1543437621&key=yt6&lmt=1537336869841620&fvip=4&pl=24&itag=247&ei=1Kj-W8v3LaGi8QOt_KboCQ&source=youtube&mn=sn-htgx20capjpq-habe%2Csn-htgx20capjpq-jb3l&mm=31%2C29&keepalive=yes&id=o-AIPn__fFWXLKFd0PjFLKf61NG7fWZ-22_jQOi-JDVG0n&c=WEB&clen=68777376&ip=158.140.172.15&mv=m&sparams=aitags%2Cclen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Ckeepalive%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Crequiressl%2Csource%2Cexpire&mt=1543415847&ms=au%2Crdu&alr=yes&signature=D9239F3D8B2C5DBF6EE41A27CFFDCE10F99EC17D.731FA8EFE7B46CAC4DAAAD260BC41B2D9263F2B9&cpn=J3fA-EQZyOe4dtea&cver=2.20181127&rn=28")
    };

    public SinetronModel(String name, String durasi, String videoRawId) {
        this.name = name;
        this.durasi = durasi;
        this.videoRawId = videoRawId;
    }

    public String getName() {
        return name;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getVideoRawId() {
        return videoRawId;
    }
    public String toString(){
        return this.name;
    }


}
