package pay.scope.payscope.Model;

public class StateDetailsModel {
    String StateDetailsName, StateDetailsFullName,StateDetailsShortName;

    public StateDetailsModel(String stateDetailsName, String stateDetailsFullName, String stateDetailsShortName) {
        StateDetailsName = stateDetailsName;
        StateDetailsFullName = stateDetailsFullName;
        StateDetailsShortName = stateDetailsShortName;
    }

    public String getStateDetailsName() {
        return StateDetailsName;
    }

    public void setStateDetailsName(String stateDetailsName) {
        StateDetailsName = stateDetailsName;
    }

    public String getStateDetailsFullName() {
        return StateDetailsFullName;
    }

    public void setStateDetailsFullName(String stateDetailsFullName) {
        StateDetailsFullName = stateDetailsFullName;
    }

    public String getStateDetailsShortName() {
        return StateDetailsShortName;
    }

    public void setStateDetailsShortName(String stateDetailsShortName) {
        StateDetailsShortName = stateDetailsShortName;
    }
}
