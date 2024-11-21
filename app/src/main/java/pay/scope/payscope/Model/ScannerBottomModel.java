package pay.scope.payscope.Model;

public class ScannerBottomModel {
    private int ScannerBottomImg;
    private String ScannerBottomName;
    private int ScannerBottomCardColor;

    public ScannerBottomModel(int scannerBottomImg, String scannerBottomName, int scannerBottomCardColor) {
        ScannerBottomImg = scannerBottomImg;
        ScannerBottomName = scannerBottomName;
        ScannerBottomCardColor = scannerBottomCardColor;
    }

    public int getScannerBottomCardColor() {
        return ScannerBottomCardColor;
    }

    public void setScannerBottomCardColor(int scannerBottomCardColor) {
        ScannerBottomCardColor = scannerBottomCardColor;
    }

    public int getScannerBottomImg() {
        return ScannerBottomImg;
    }

    public void setScannerBottomImg(int scannerBottomImg) {
        ScannerBottomImg = scannerBottomImg;
    }

    public String getScannerBottomName() {
        return ScannerBottomName;
    }

    public void setScannerBottomName(String scannerBottomName) {
        ScannerBottomName = scannerBottomName;
    }
}
