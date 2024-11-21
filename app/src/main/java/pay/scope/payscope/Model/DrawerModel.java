package pay.scope.payscope.Model;

public class DrawerModel {
    private final int drawerImg;
    private final String drawerTitle;
    private final String drawerDec;

    public DrawerModel(int drawerImg, String drawerTitle, String drawerDec) {
        this.drawerImg = drawerImg;
        this.drawerTitle = drawerTitle;
        this.drawerDec = drawerDec;
    }

    public int getDrawerImg() {
        return drawerImg;
    }

    public String getDrawerTitle() {
        return drawerTitle;
    }

    public String getDrawerDec() {
        return drawerDec;
    }
}
