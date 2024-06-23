public class APdata {
    String SSID;
    int rssi;

    public APdata(String SSID, int rssi) {
        this.SSID = SSID;
        this.rssi = rssi;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
