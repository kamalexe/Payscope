package pay.scope.payscope.Model;

public class StateNameModel {
    String stateName;
    int stateImg;

    public StateNameModel(String stateName, int stateImg) {
        this.stateName = stateName;
        this.stateImg = stateImg;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateImg() {
        return stateImg;
    }

    public void setStateImg(int stateImg) {
        this.stateImg = stateImg;
    }
}
