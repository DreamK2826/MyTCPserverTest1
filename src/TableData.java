import java.util.List;

/**
 * 用于生成表格的数据
 */
public class TableData {

    String name;
    String pos1;
    String pos2;
    List<String> apDATA;

    public TableData(String name, String pos1, String pos2, List<String> apDATA) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.apDATA = apDATA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public List<String> getApDATA() {
        return apDATA;
    }

    public void setApDATA(List<String> apDATA) {
        this.apDATA = apDATA;
    }
}
