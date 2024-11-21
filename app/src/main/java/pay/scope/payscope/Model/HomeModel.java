package pay.scope.payscope.Model;

public class HomeModel {
    private final int homeIcon;
    private final String homeText;

    public HomeModel(int homeIcon, String homeText) {
        this.homeIcon = homeIcon;
        this.homeText = homeText;
    }

    public int getHomeIcon() {
        return homeIcon;
    }

    public String getHomeText() {
        return homeText;
    }
}
