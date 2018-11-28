package com.ti4e.wira.mediaplayback;

public class SinetronModel {
    private String nama, durasi, videoRawId;

    public static final SinetronModel[] drama = {

            new SinetronModel("tersandung", "4:12", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
            new SinetronModel("terjungkir", "3:22", "sample_video_2"),
            new SinetronModel("terbalik", "4:12", "sample_video_2"),
    };

    public SinetronModel(String nama, String durasi, String videoRawId) {
        this.nama = nama;
        this.durasi = durasi;
        this.videoRawId = videoRawId;
    }

    public String getNama() {
        return nama;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getVideoRawId() {
        return videoRawId;
    }

    public String toString(){
        return this.nama;
    }
}
