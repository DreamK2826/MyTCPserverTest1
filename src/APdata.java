public class APdata {
    String SSID;
    String mac;
    int rssi;

    public APdata(String SSID, String mac, int rssi) {
        this.SSID = SSID;
        this.mac = mac;
        this.rssi = rssi;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
